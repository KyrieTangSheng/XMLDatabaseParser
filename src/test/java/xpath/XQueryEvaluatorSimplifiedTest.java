package xpath;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import static org.junit.Assert.*;

public class XQueryEvaluatorSimplifiedTest {
    private XQueryEvaluator evaluator;
    // Use an XML file that matches the expected structure:
    // <root>
    //    <a>Hello</a>
    //    <b>World</b>
    // </root>
    private static final String TEST_XML = "src/test/resources/j_caesar.xml";

    @Before
    public void setUp() {       
        evaluator = new XQueryEvaluator();
    }

    // Helper method to run a query through the parser and evaluator.
    private List<Node> evaluateXQuery(String query) {
        XQueryLexer lexer = new XQueryLexer(CharStreams.fromString(query));
        XQueryParser parser = new XQueryParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.xquery();
        return evaluator.visit(tree);
    }

    @Test
    public void testStringConstant() {
        String query = "\"Hello World\"";
        List<Node> result = evaluateXQuery(query);
        assertNotNull(result);
        // Expect one text node containing "Hello World"
        assertEquals(1, result.size());
        assertEquals("Hello World", result.get(0).getTextContent());
    }

    @Test
    public void testElementConstructor() {
        String query = "<result>{\"Hello World\"}</result>";
        List<Node> result = evaluateXQuery(query);
        assertNotNull(result);
        // Expect one element node with tag "result"
        assertEquals(1, result.size());
        Node node = result.get(0);
        assertEquals("result", node.getNodeName());
        assertEquals("Hello World", node.getTextContent());
    }

    @Test
    public void testForClause() {
        // For clause is defined as: for $x in XQ return XQ.
        // This query binds $x to the text "Hello" and returns it.
        String query = "for $x in \"Hello\" return $x";
        List<Node> result = evaluateXQuery(query);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Hello", result.get(0).getTextContent());
    }

    @Test
    public void testLetClause() {
        // In the simplified syntax the let clause is defined as: letClause XQ
        // (without a "return" keyword). Here, we bind $x and then simply evaluate $x.
        String query = "let $x := \"Hello\" $x";
        List<Node> result = evaluateXQuery(query);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Hello", result.get(0).getTextContent());
    }

    @Test
    public void testAbsolutePath() {
        // Adjusted to match the CAESAR document: the root is <PLAY> with a child <TITLE>
        String query = "doc(\"" + TEST_XML + "\")/PLAY/TITLE";
        List<Node> result = evaluateXQuery(query);
        System.out.println("Result of testAbsolutePath: " + result);
        assertNotNull(result);
        // Expect one TITLE element (assuming your CAESAR file has a single <TITLE> under <PLAY>)
        assertEquals(1, result.size());
        assertEquals("TITLE", result.getFirst().getNodeName());
        // You may also check the content if you know the title text.
    }

    @Test
    public void testNestedFLWR() {
        // A nested FLWR expression: for each <PLAY> in the document, return an element that contains the PLAY's TITLE.
        String query = "for $x in doc(\"" + TEST_XML + "\")/PLAY return <result>{ $x/TITLE }</result>";
        List<Node> result = evaluateXQuery(query);
        assertNotNull(result);
        // Expect one <result> element (if the document contains one PLAY element)
        assertEquals(1, result.size());
        Node node = result.get(0);
        assertEquals("result", node.getNodeName());
        // Optionally, check that the result contains the expected TITLE text.
    }
}