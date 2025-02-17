package xpath;

import xmlparser.XMLParser;
import java.util.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class XQueryEvaluator extends XQueryBaseVisitor<List<Node>> {

    // The document used to create new nodes (e.g., for tags and text)
    private Document outputDoc;
    // A stack-based environment for variable bindings
    private Deque<Map<String, List<Node>>> env;
    // Delegate evaluator for absolute/relative XPath expressions
    private XPathEvaluator xpath;

    public XQueryEvaluator() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.outputDoc = builder.newDocument();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create output document", e);
        }
        env = new ArrayDeque<>();
        env.push(new HashMap<>());
        xpath = new XPathEvaluator();
    }

    // ------------------- XQuery Expression Visitors -------------------

    // xquery: var  # XQueryVariable
    @Override
    public List<Node> visitXQueryVariable(XQueryParser.XQueryVariableContext ctx) {
        // Get the full variable name including $ prefix
        String varName = ctx.var().getText();
        for (Map<String, List<Node>> scope : env) {
            if (scope.containsKey(varName)) {
                List<Node> value = scope.get(varName);
                return value != null ? value : new ArrayList<>();
            }
        }
        throw new RuntimeException("Undefined variable: " + varName);
    }

    // xquery: stringConstant  # XQueryConstant
    @Override
    public List<Node> visitXQueryConstant(XQueryParser.XQueryConstantContext ctx) {
        String str = ctx.stringConstant().getText();
        // Remove the surrounding quotes
        str = str.substring(1, str.length() - 1);
        Text text = outputDoc.createTextNode(str);
        return Collections.singletonList(text);
    }

    // xquery: absolutePath  # XQueryAbsolutePath
    @Override
    public List<Node> visitXQueryAbsolutePath(XQueryParser.XQueryAbsolutePathContext ctx) {
        // Debug the input
        System.out.println("Raw absolute path: " + ctx.getText());
        System.out.println("Absolute path context type: " + ctx.absolutePath().getClass().getName());
        
        // Instead of reparsing, use the existing parse tree
        if (ctx.absolutePath() instanceof XQueryParser.AbsoluteSlashContext) {
            XQueryParser.AbsoluteSlashContext slashCtx = (XQueryParser.AbsoluteSlashContext) ctx.absolutePath();
            String fileName = slashCtx.fileName().getText().replace("\"", "");
            System.out.println("Filename: " + fileName);
            
            Document doc = XMLParser.parse(fileName);
            Node root = doc.getDocumentElement();
            xpath.setCurrentContext(Collections.singletonList(root));
            
            // Debug the relative path
            System.out.println("Relative path: " + slashCtx.relativePath().getText());
            return xpath.visit(slashCtx.relativePath());
        }
        
        return new ArrayList<>();
    }

    // xquery: '(' xquery ')'  # XQueryParentheses
    @Override
    public List<Node> visitXQueryParentheses(XQueryParser.XQueryParenthesesContext ctx) {
        List<Node> result = visit(ctx.xquery());
        return result != null ? result : new ArrayList<>();
    }

    // xquery: xquery ',' xquery  # XQueryConcat
    @Override
    public List<Node> visitXQueryConcat(XQueryParser.XQueryConcatContext ctx) {
        List<Node> left = visit(ctx.xquery(0));
        List<Node> right = visit(ctx.xquery(1));
        List<Node> result = new ArrayList<>();
        if (left != null) result.addAll(left);
        if (right != null) result.addAll(right);
        return result;
    }

    // xquery: xquery '/' relativePath  # XQueryPath
    @Override
    public List<Node> visitXQueryPath(XQueryParser.XQueryPathContext ctx) {
        List<Node> contextNodes = visit(ctx.xquery());
        if (contextNodes == null || contextNodes.isEmpty()) {
            return new ArrayList<>();
        }
        
        xpath.setCurrentContext(contextNodes);
        List<Node> result = xpath.visit(ctx.relativePath());
        return result != null ? result : new ArrayList<>();
    }

    // xquery: xquery '//' relativePath  # XQueryDoubleSlash
    @Override
    public List<Node> visitXQueryDoubleSlash(XQueryParser.XQueryDoubleSlashContext ctx) {
        List<Node> left = visit(ctx.xquery());
        if (left == null) left = new ArrayList<>();
        List<Node> allNodes = new ArrayList<>();
        for (Node n : left) {
            allNodes.add(n);
            getDescendants(n, allNodes);
        }
        xpath.setCurrentContext(allNodes);
        List<Node> result = xpath.visit(ctx.relativePath());
        return result != null ? result : new ArrayList<>();
    }

    // xquery: '<' tagName '>' '{' xquery '}' '</' tagName '>'  # XQueryTag
    @Override
    public List<Node> visitXQueryTag(XQueryParser.XQueryTagContext ctx) {
        String tagName = ctx.tagName(0).getText();
        Element elem = outputDoc.createElement(tagName);
        List<Node> children = visit(ctx.xquery());
        if (children != null) {
            for (Node child : children) {
                // Import node into our document so that it belongs to outputDoc.
                Node imported = outputDoc.importNode(child, true);
                elem.appendChild(imported);
            }
        }
        return Collections.singletonList(elem);
    }

    // xquery: forClause letClause? whereClause? returnClause  # XQueryFLWR
    @Override
    public List<Node> visitXQueryFLWR(XQueryParser.XQueryFLWRContext ctx) {
        List<Map<String, List<Node>>> bindings = evaluateForClause(ctx.forClause());
        List<Node> results = new ArrayList<>();
        
        // Save current environment
        Map<String, List<Node>> savedEnv = new HashMap<>(env.peek());
        
        try {
            for (Map<String, List<Node>> binding : bindings) {
                // Create new scope with saved environment as parent
                Map<String, List<Node>> newScope = new HashMap<>(savedEnv);
                newScope.putAll(binding);
                env.push(newScope);
                
                try {
                    if (ctx.letClause() != null) {
                        Map<String, List<Node>> letBindings = evaluateLetClause(ctx.letClause());
                        env.peek().putAll(letBindings);
                    }
                    
                    boolean condition = true;
                    if (ctx.whereClause() != null) {
                        condition = evaluateCondition(ctx.whereClause().cond());
                    }
                    
                    if (condition) {
                        List<Node> ret = visit(ctx.returnClause().xquery());
                        if (ret != null) {
                            results.addAll(ret);
                        }
                    }
                } finally {
                    env.pop();
                }
            }
            return results;
        } finally {
            // Restore original environment
            env.peek().clear();
            env.peek().putAll(savedEnv);
        }
    }

    // xquery: letClause xquery  # XQueryLet
    @Override
    public List<Node> visitXQueryLet(XQueryParser.XQueryLetContext ctx) {
        Map<String, List<Node>> letBindings = evaluateLetClause(ctx.letClause());
        Map<String, List<Node>> newScope = new HashMap<>(env.peek());
        newScope.putAll(letBindings);
        env.push(newScope);
        List<Node> result = visit(ctx.xquery());
        env.pop();
        return result != null ? result : new ArrayList<>();
    }

    // ------------------- Helpers for FLWR Clauses -------------------

    /**
     * Evaluate a forClause, returning a list of variable bindings.
     * Each binding is a map from variable name to a singleton list of a node.
     */
    private List<Map<String, List<Node>>> evaluateForClause(XQueryParser.ForClauseContext ctx) {
        List<Map<String, List<Node>>> bindings = new ArrayList<>();
        bindings.add(new HashMap<>()); // Start with an empty binding

        int count = ctx.var().size();
        for (int i = 0; i < count; i++) {
            String varName = ctx.var(i).getText();
            List<Node> nodes = visit(ctx.xquery(i));
            if (nodes == null) {
                nodes = new ArrayList<>();
            }
            
            List<Map<String, List<Node>>> newBindings = new ArrayList<>();
            for (Map<String, List<Node>> binding : bindings) {
                for (Node n : nodes) {
                    Map<String, List<Node>> newBinding = new HashMap<>(binding);
                    newBinding.put(varName, Collections.singletonList(n));
                    newBindings.add(newBinding);
                }
            }
            bindings = newBindings;
        }
        return bindings;
    }

    /**
     * Evaluate a letClause and return a map of variable bindings.
     */
    private Map<String, List<Node>> evaluateLetClause(XQueryParser.LetClauseContext ctx) {
        Map<String, List<Node>> bindings = new HashMap<>();
        int count = ctx.var().size();
        for (int i = 0; i < count; i++) {
            String varName = ctx.var(i).getText();
            List<Node> nodes = visit(ctx.xquery(i));
            if (nodes == null) nodes = new ArrayList<>();
            bindings.put(varName, nodes);
        }
        return bindings;
    }

    // ------------------- Helpers for Conditions -------------------

    /**
     * Evaluate a condition expression and return a boolean.
     */
    private boolean evaluateCondition(XQueryParser.CondContext ctx) {
        if (ctx instanceof XQueryParser.XQueryValueEqualContext) {
            List<Node> left = visit(((XQueryParser.XQueryValueEqualContext) ctx).xquery(0));
            List<Node> right = visit(((XQueryParser.XQueryValueEqualContext) ctx).xquery(1));
            if (left == null) left = new ArrayList<>();
            if (right == null) right = new ArrayList<>();
            return nodesValueEqual(left, right);
        } else if (ctx instanceof XQueryParser.XQueryIdentityEqualContext) {
            List<Node> left = visit(((XQueryParser.XQueryIdentityEqualContext) ctx).xquery(0));
            List<Node> right = visit(((XQueryParser.XQueryIdentityEqualContext) ctx).xquery(1));
            if (left == null) left = new ArrayList<>();
            if (right == null) right = new ArrayList<>();
            return nodesIdentityEqual(left, right);
        } else if (ctx instanceof XQueryParser.XQueryEmptyContext) {
            List<Node> nodes = visit(((XQueryParser.XQueryEmptyContext) ctx).xquery());
            return nodes == null || nodes.isEmpty();
        } else if (ctx instanceof XQueryParser.XQuerySomeContext) {
            XQueryParser.XQuerySomeContext someCtx = (XQueryParser.XQuerySomeContext) ctx;
            List<Map<String, List<Node>>> bindings = new ArrayList<>();
            bindings.add(new HashMap<>());
            int count = someCtx.var().size();
            for (int i = 0; i < count; i++) {
                String varName = someCtx.var(i).getText();
                List<Node> nodes = visit(someCtx.xquery(i));
                if (nodes == null) nodes = new ArrayList<>();
                List<Map<String, List<Node>>> newBindings = new ArrayList<>();
                for (Map<String, List<Node>> binding : bindings) {
                    for (Node n : nodes) {
                        Map<String, List<Node>> newBinding = new HashMap<>(binding);
                        newBinding.put(varName, Collections.singletonList(n));
                        newBindings.add(newBinding);
                    }
                }
                bindings = newBindings;
            }
            // Satisfied if at least one binding makes the condition true.
            for (Map<String, List<Node>> binding : bindings) {
                Map<String, List<Node>> newScope = new HashMap<>(env.peek());
                newScope.putAll(binding);
                env.push(newScope);
                boolean sat = evaluateCondition(someCtx.cond());
                env.pop();
                if (sat) return true;
            }
            return false;
        } else if (ctx instanceof XQueryParser.XQueryCondParenthesesContext) {
            return evaluateCondition(((XQueryParser.XQueryCondParenthesesContext) ctx).cond());
        } else if (ctx instanceof XQueryParser.XQueryCondAndContext) {
            XQueryParser.XQueryCondAndContext andCtx = (XQueryParser.XQueryCondAndContext) ctx;
            return evaluateCondition(andCtx.cond(0)) && evaluateCondition(andCtx.cond(1));
        } else if (ctx instanceof XQueryParser.XQueryCondOrContext) {
            XQueryParser.XQueryCondOrContext orCtx = (XQueryParser.XQueryCondOrContext) ctx;
            return evaluateCondition(orCtx.cond(0)) || evaluateCondition(orCtx.cond(1));
        } else if (ctx instanceof XQueryParser.XQueryCondNotContext) {
            return !evaluateCondition(((XQueryParser.XQueryCondNotContext) ctx).cond());
        }
        return false;
    }

    /**
     * Compare two lists of nodes for value equality.
     */
    private boolean nodesValueEqual(List<Node> left, List<Node> right) {
        if (left.size() != right.size()) return false;
        for (int i = 0; i < left.size(); i++) {
            if (!left.get(i).getTextContent().equals(right.get(i).getTextContent()))
                return false;
        }
        return true;
    }

    /**
     * Compare two lists of nodes for identity (reference equality).
     */
    private boolean nodesIdentityEqual(List<Node> left, List<Node> right) {
        if (left.size() != right.size()) return false;
        for (int i = 0; i < left.size(); i++) {
            if (left.get(i) != right.get(i))
                return false;
        }
        return true;
    }

    // ------------------- Utility -------------------

    /**
     * Recursively collect all descendants of a node.
     */
    private void getDescendants(Node node, List<Node> list) {
        Node child = node.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                list.add(child);
                getDescendants(child, list);
            }
            child = child.getNextSibling();
        }
    }

    // ------------------- Override Default Visitor Behavior -------------------

    @Override
    protected List<Node> defaultResult() {
        return new ArrayList<>();
    }

    @Override
    protected List<Node> aggregateResult(List<Node> aggregate, List<Node> nextResult) {
        if (aggregate == null)
            aggregate = new ArrayList<>();
        if (nextResult != null)
            aggregate.addAll(nextResult);
        return aggregate;
    }

}
