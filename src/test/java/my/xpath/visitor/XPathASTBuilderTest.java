package my.xpath.visitor;

import my.xpath.ast.*;
import my.xpath.parser.XPathLexer;
import my.xpath.parser.XPathParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import static org.junit.Assert.*;

public class XPathASTBuilderTest {
    
    private ASTNode parse(String xpath) {
        XPathLexer lexer = new XPathLexer(CharStreams.fromString(xpath));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        XPathParser parser = new XPathParser(tokens);
        XPathASTBuilder visitor = new XPathASTBuilder();
        return visitor.visit(parser.xpath());
    }

    @Test
    public void testSimpleTagName() {
        ASTNode ast = parse("doc(\"test.xml\")/book");
        System.out.println("AST for doc(\"test.xml\")/book:");
        System.out.println(ASTPrinter.print(ast));
        
        assertTrue("Root should be ApNode", ast instanceof ApNode);
        ApNode ap = (ApNode) ast;
        assertEquals("test.xml", ap.getFileName());
        assertFalse("Should be single slash", ap.isDoubleSlash());
        
        assertTrue("Child should be TagNameRp", ap.getChild() instanceof TagNameRp);
        assertEquals("book", ((TagNameRp)ap.getChild()).getTagName());
    }

    @Test
    public void testDoubleSlashPath() {
        ASTNode ast = parse("doc(\"test.xml\")//book");
        System.out.println("\nAST for doc(\"test.xml\")//book:");
        System.out.println(ASTPrinter.print(ast));
        
        assertTrue(ast instanceof ApNode);
        ApNode ap = (ApNode) ast;
        assertTrue("Should be double slash", ap.isDoubleSlash());
        assertTrue(ap.getChild() instanceof TagNameRp);
    }

    @Test
    public void testComplexPath() {
        ASTNode ast = parse("doc(\"test.xml\")/book/title");
        System.out.println("\nAST for doc(\"test.xml\")/book/title:");
        System.out.println(ASTPrinter.print(ast));
        
        assertTrue(ast instanceof ApNode);
        ApNode ap = (ApNode) ast;
        assertFalse(ap.isDoubleSlash());
        
        assertTrue(ap.getChild() instanceof SlashRp);
        SlashRp slash = (SlashRp) ap.getChild();
        
        assertTrue(slash.getLeft() instanceof TagNameRp);
        assertEquals("book", ((TagNameRp)slash.getLeft()).getTagName());
        
        assertTrue(slash.getRight() instanceof TagNameRp);
        assertEquals("title", ((TagNameRp)slash.getRight()).getTagName());
    }

    @Test
    public void testFilter() {
        ASTNode ast = parse("doc(\"test.xml\")/book[@id=\"1\"]");
        System.out.println("\nAST for doc(\"test.xml\")/book[@id=\"1\"]:");
        System.out.println(ASTPrinter.print(ast));
        
        assertTrue(ast instanceof ApNode);
        ApNode ap = (ApNode) ast;
        assertTrue(ap.getChild() instanceof FilteredRp);
        
        FilteredRp filter = (FilteredRp) ap.getChild();
        assertTrue(filter.getRp() instanceof TagNameRp);
        assertEquals("book", ((TagNameRp)filter.getRp()).getTagName());
        
        assertTrue(filter.getFilter() instanceof StringFilterNode);
        StringFilterNode stringFilter = (StringFilterNode) filter.getFilter();
        assertTrue(stringFilter.getRp() instanceof AttributeRp);
        assertEquals("id", ((AttributeRp)stringFilter.getRp()).getAttributeName());
        assertEquals("1", stringFilter.getStringConstant());
    }

    @Test
    public void testComplexFilter() {
        ASTNode ast = parse("doc(\"test.xml\")/book[title and @id=\"1\"]");
        System.out.println("\nAST for doc(\"test.xml\")/book[title and @id=\"1\"]:");
        System.out.println(ASTPrinter.print(ast));
        
        assertTrue(ast instanceof ApNode);
        ApNode ap = (ApNode) ast;
        assertTrue(ap.getChild() instanceof FilteredRp);
        
        FilteredRp filter = (FilteredRp) ap.getChild();
        assertTrue(filter.getFilter() instanceof AndFilterNode);
        
        AndFilterNode andFilter = (AndFilterNode) filter.getFilter();
        assertTrue(andFilter.getLeft() instanceof RpFilterNode);
        assertTrue(andFilter.getRight() instanceof StringFilterNode);
    }

    @Test
    public void testAttribute() {
        ASTNode ast = parse("doc(\"test.xml\")/book/@id");
        System.out.println("\nAST for doc(\"test.xml\")/book/@id:");
        System.out.println(ASTPrinter.print(ast));
        
        assertTrue(ast instanceof ApNode);
        ApNode ap = (ApNode) ast;
        assertTrue(ap.getChild() instanceof SlashRp);
        SlashRp slash = (SlashRp) ap.getChild();
        assertTrue(slash.getRight() instanceof AttributeRp);
        assertEquals("id", ((AttributeRp)slash.getRight()).getAttributeName());
    }

    @Test
    public void testRelativePathOperations() {
        // Test all basic relative path operations
        printAndVerify("doc(\"test.xml\")/book/*");                   // Wildcard
        printAndVerify("doc(\"test.xml\")/book/.");                   // Self
        printAndVerify("doc(\"test.xml\")/book/..");                  // Parent
        printAndVerify("doc(\"test.xml\")/book/text()");             // Text
        printAndVerify("doc(\"test.xml\")/book/@id");                // Attribute
        printAndVerify("doc(\"test.xml\")/book/(title)");            // Grouping
        printAndVerify("doc(\"test.xml\")/book/author");             // Simple path
        printAndVerify("doc(\"test.xml\")//book");                   // Double slash
        printAndVerify("doc(\"test.xml\")/book,author");             // Concatenation
    }

    @Test
    public void testFilters() {
        // Test all types of filters
        printAndVerify("doc(\"test.xml\")/book[title]");             // Simple filter
        printAndVerify("doc(\"test.xml\")/book[price = text()]");    // Equality filter
        printAndVerify("doc(\"test.xml\")/book[price eq text()]");   // Value equality
        printAndVerify("doc(\"test.xml\")/book[. == ..]");          // Identity equality
        printAndVerify("doc(\"test.xml\")/book[. is ..]");          // Value identity
        printAndVerify("doc(\"test.xml\")/book[@id = \"1\"]");      // String comparison
        printAndVerify("doc(\"test.xml\")/book[(price = text())]");  // Filter grouping
        printAndVerify("doc(\"test.xml\")/book[price and title]");   // And filter
        printAndVerify("doc(\"test.xml\")/book[price or title]");    // Or filter
        printAndVerify("doc(\"test.xml\")/book[not price]");         // Not filter
    }

    @Test
    public void testComplexQueries() {
        // Test combinations of operations
        printAndVerify("doc(\"test.xml\")//book[@id=\"1\"]//author[text()]/.");
        printAndVerify("doc(\"test.xml\")/bookstore/book[price and ./author]/title");
        printAndVerify("doc(\"test.xml\")//book[../bookstore/@type=\"fiction\"]/author");
    }

    private void printAndVerify(String xpath) {
        System.out.println("\nTesting XPath: " + xpath);
        ASTNode ast = parse(xpath);
        System.out.println(ASTPrinter.print(ast));
        assertNotNull(ast); // Basic verification that parsing succeeded
    }
} 