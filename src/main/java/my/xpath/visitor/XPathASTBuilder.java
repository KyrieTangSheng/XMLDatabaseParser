package my.xpath.visitor;

import my.xpath.parser.XPathBaseVisitor;
import my.xpath.parser.XPathParser;
import my.xpath.ast.ASTNode;
import my.xpath.ast.AndFilterNode;
import my.xpath.ast.ApNode;
import my.xpath.ast.AttributeRp;
import my.xpath.ast.ConcatRp;
import my.xpath.ast.DotDotRp;
import my.xpath.ast.DotRp;
import my.xpath.ast.DoubleSlashRp;
import my.xpath.ast.EqualityFilterNode;
import my.xpath.ast.FilterNode;
import my.xpath.ast.FilteredRp;
import my.xpath.ast.NotFilterNode;
import my.xpath.ast.OrFilterNode;
import my.xpath.ast.ParenFilterNode;
import my.xpath.ast.ParenthesesRp;
import my.xpath.ast.RpFilterNode;
import my.xpath.ast.RpNode;
import my.xpath.ast.SlashRp;
import my.xpath.ast.StarRp;
import my.xpath.ast.StringFilterNode;
import my.xpath.ast.TagNameRp;
import my.xpath.ast.TextRp;
import my.xpath.ast.EqualityFilterNode.EqualityOp;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * A custom visitor to walk the ANTLR parse tree and build an AST of our classes.
 */
public class XPathASTBuilder extends XPathBaseVisitor<ASTNode> {

    // ------------------
    // Entry Rule (optional)
    // ------------------
    @Override
    public ASTNode visitXpath(XPathParser.XpathContext ctx) {
        // Our grammar says: xpath : absolutePath | relativePath ;
        if (ctx.absolutePath() != null) {
            return visit(ctx.absolutePath());
        } else {
            return visit(ctx.relativePath());
        }
    }

    // ------------------
    // Absolute Path
    // ------------------
    @Override
    public ASTNode visitAbsoluteSlash(XPathParser.AbsoluteSlashContext ctx) {
        // Strip quotes from the filename
        String fn = ctx.fileName().getText().replaceAll("\"", "");
        RpNode rpNode = (RpNode) visit(ctx.relativePath());
        return new ApNode(fn, rpNode, false);
    }

    @Override
    public ASTNode visitAbsoluteDoubleSlash(XPathParser.AbsoluteDoubleSlashContext ctx) {
        // Strip quotes from the filename
        String fn = ctx.fileName().getText().replaceAll("\"", "");
        RpNode rpNode = (RpNode) visit(ctx.relativePath());
        return new ApNode(fn, rpNode, true);
    }

    // ------------------
    // Relative Path
    // ------------------
    @Override
    public ASTNode visitTagName(XPathParser.TagNameContext ctx) {
        return new TagNameRp(ctx.getText());
    }

    @Override
    public ASTNode visitAllChildren(XPathParser.AllChildrenContext ctx) {
        return new StarRp();
    }

    @Override
    public ASTNode visitSelf(XPathParser.SelfContext ctx) {
        return new DotRp();
    }

    @Override
    public ASTNode visitParent(XPathParser.ParentContext ctx) {
        return new DotDotRp();
    }

    @Override
    public ASTNode visitTextFunc(XPathParser.TextFuncContext ctx) {
        return new TextRp();
    }

    @Override
    public ASTNode visitAttribute(XPathParser.AttributeContext ctx) {
        // ctx.attributeName() might be something like "attr"
        return new AttributeRp(ctx.attributeName().getText());
    }

    @Override
    public ASTNode visitRpGrouping(XPathParser.RpGroupingContext ctx) {
        // ( rp )
        RpNode inner = (RpNode) visit(ctx.relativePath());
        return new ParenthesesRp(inner);
    }

    @Override
    public ASTNode visitRpSlash(XPathParser.RpSlashContext ctx) {
        // rp1 / rp2
        RpNode left = (RpNode) visit(ctx.relativePath(0));
        RpNode right = (RpNode) visit(ctx.relativePath(1));
        return new SlashRp(left, right);
    }

    @Override
    public ASTNode visitRpDoubleSlash(XPathParser.RpDoubleSlashContext ctx) {
        // rp1 // rp2
        RpNode left = (RpNode) visit(ctx.relativePath(0));
        RpNode right = (RpNode) visit(ctx.relativePath(1));
        return new DoubleSlashRp(left, right);
    }

    @Override
    public ASTNode visitRpFilter(XPathParser.RpFilterContext ctx) {
        RpNode rpNode = (RpNode) visit(ctx.relativePath());
        FilterNode filterNode = (FilterNode) visit(ctx.filter());
        return new FilteredRp(rpNode, filterNode);
    }

    @Override
    public ASTNode visitRpConcat(XPathParser.RpConcatContext ctx) {
        // rp1 , rp2
        RpNode left = (RpNode) visit(ctx.relativePath(0));
        RpNode right = (RpNode) visit(ctx.relativePath(1));
        return new ConcatRp(left, right);
    }

    // ------------------
    // Filter
    // ------------------

    @Override
    public ASTNode visitRpInFilter(XPathParser.RpInFilterContext ctx) {
        // f -> rp
        RpNode rp = (RpNode) visit(ctx.relativePath());
        return new RpFilterNode(rp);
    }

    @Override
    public ASTNode visitEqualityFilter(XPathParser.EqualityFilterContext ctx) {
        // rp1 = rp2  or  rp1 eq rp2
        // We'll unify them in the same node with an operator.
        RpNode left = (RpNode) visit(ctx.relativePath(0));
        RpNode right = (RpNode) visit(ctx.relativePath(1));

        // We can detect which operator text was used:
        // e.g., if (ctx.EQ() != null) => '='
        // or (ctx.EQVALUE() != null) => 'eq'
        // We'll just say both are "VALUE_EQUAL"
        return new EqualityFilterNode(left, right, EqualityOp.VALUE_EQUAL);
    }

    @Override
    public ASTNode visitIdentityFilter(XPathParser.IdentityFilterContext ctx) {
        // rp1 == rp2  or  rp1 is rp2
        RpNode left = (RpNode) visit(ctx.relativePath(0));
        RpNode right = (RpNode) visit(ctx.relativePath(1));
        return new EqualityFilterNode(left, right, EqualityOp.IDENTITY_EQUAL);
    }

    @Override
    public ASTNode visitStringFilter(XPathParser.StringFilterContext ctx) {
        // rp = "string"
        RpNode rp = (RpNode) visit(ctx.relativePath());
        // The string literal might include quotes, so we can trim them if needed.
        String raw = ctx.StringConstant().getText(); // e.g. "\"hello\""
        String strVal = stripQuotes(raw);
        return new StringFilterNode(rp, strVal);
    }

    private String stripQuotes(String raw) {
        if (raw.length() >= 2 && raw.startsWith("\"") && raw.endsWith("\"")) {
            return raw.substring(1, raw.length() - 1);
        }
        return raw;
    }

    @Override
    public ASTNode visitFilterGrouping(XPathParser.FilterGroupingContext ctx) {
        // (f)
        FilterNode inner = (FilterNode) visit(ctx.filter());
        return new ParenFilterNode(inner);
    }

    @Override
    public ASTNode visitAndFilter(XPathParser.AndFilterContext ctx) {
        // f1 and f2
        FilterNode left = (FilterNode) visit(ctx.filter(0));
        FilterNode right = (FilterNode) visit(ctx.filter(1));
        return new AndFilterNode(left, right);
    }

    @Override
    public ASTNode visitOrFilter(XPathParser.OrFilterContext ctx) {
        // f1 or f2
        FilterNode left = (FilterNode) visit(ctx.filter(0));
        FilterNode right = (FilterNode) visit(ctx.filter(1));
        return new OrFilterNode(left, right);
    }

    @Override
    public ASTNode visitNotFilter(XPathParser.NotFilterContext ctx) {
        // not f
        FilterNode inner = (FilterNode) visit(ctx.filter());
        return new NotFilterNode(inner);
    }

    // ------------------
    // Default visitors
    // ------------------

    /**
     * If you forget to override a rule, it will go here. Usually we want
     * to handle every rule explicitly. But just in case, let's do a fallback.
     */
    @Override
    protected ASTNode defaultResult() {
        return null;
    }

    @Override
    public ASTNode visitChildren(org.antlr.v4.runtime.tree.RuleNode node) {
        // If we haven't overridden the rule, call super,
        // which visits children and returns last child's result.
        return super.visitChildren(node);
    }
}
