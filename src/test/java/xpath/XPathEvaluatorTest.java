package xpath;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;
import java.util.LinkedList;
import static org.junit.Assert.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.w3c.dom.*;
import java.util.LinkedList;

public class XPathEvaluatorTest {
    private XPathEvaluator evaluator;
    
    @Before
    public void setUp() {
        evaluator = new XPathEvaluator();
    }

    // Helper method to evaluate XPath expressions
    private LinkedList<Node> evaluateXPath(String xpathQuery) {
        XPathLexer lexer = new XPathLexer(CharStreams.fromString(xpathQuery));
        XPathParser parser = new XPathParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.xpath();
        return evaluator.visit(tree);
    }

    @Test
    public void testAbsolutePath() {
        LinkedList<Node> result = evaluateXPath("doc(\"src/test/resources/test.xml\")/root");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("root", result.getFirst().getNodeName());
    }

    @Test
    public void testDescendantPath() {
        LinkedList<Node> result = evaluateXPath("doc(\"src/test/resources/test.xml\")//child");
        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    @Test
    public void testBasicFilter() {
        // Test: doc("src/test/resources/test.xml")/root/parent/child[text()]
        LinkedList<Node> result = evaluateXPath("doc(\"src/test/resources/test.xml\")/root/parent/child[text()]");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        
        // Test non-existent path
        result = evaluateXPath("doc(\"src/test/resources/test.xml\")/root/parent/child[nonexistent]");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testEqualityFilter() {
        // Test value equality with text nodes
        LinkedList<Node> result = evaluateXPath("doc(\"src/test/resources/test.xml\")/root/parent/child[text() = \"Text1\"]");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        
        // Test equality with non-matching value
        result = evaluateXPath("doc(\"src/test/resources/test.xml\")/root/parent/child[text() = \"wrong\"]");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testIdentityFilter() {
        // Test identity equality with self
        LinkedList<Node> result = evaluateXPath("doc(\"src/test/resources/test.xml\")/root/parent/child[. is .]");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        
        // Test identity with different nodes
        result = evaluateXPath("doc(\"src/test/resources/test.xml\")/root/parent/child[. is ../child[2]]");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testAttributeFilter() {
        // Test attribute equality
        LinkedList<Node> result = evaluateXPath("doc(\"src/test/resources/test.xml\")/root/books/book[@id = \"1\"]");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("XML Processing", result.getFirst().getChildNodes().item(1).getTextContent());
    }

    @Test
    public void testLogicalFilters() {
        // Test AND
        LinkedList<Node> result = evaluateXPath("doc(\"src/test/resources/test.xml\")/root/books/book[title and author]");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        
        // Test OR
        result = evaluateXPath("doc(\"src/test/resources/test.xml\")/root/books/book[review or price = \"29.99\"]");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        
        // Test NOT
        result = evaluateXPath("doc(\"src/test/resources/test.xml\")/root/books/book[not(review)]");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testComplexFilter() {
        // Test combination of filters
        LinkedList<Node> result = evaluateXPath("doc(\"src/test/resources/test.xml\")/root/books/book[price = \"19.99\" and author = \"Jane Smith\" or not(review)]");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    // Add more test methods for each XPath feature...
} 