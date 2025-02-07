package xpath;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.LinkedList;
import static org.junit.Assert.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class XPathEvaluatorTest {
    private XPathEvaluator evaluator;
    private static final String CAESAR_FILE = "src/test/resources/j_caesar.xml";
    
    @Before
    public void setUp() {
        evaluator = new XPathEvaluator();
    }

    private LinkedList<Node> evaluateXPath(String xpathQuery) {
        XPathLexer lexer = new XPathLexer(CharStreams.fromString(xpathQuery));
        XPathParser parser = new XPathParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.xpath();
        LinkedList<Node> result = evaluator.visit(tree);
        
        // Print query and results in XML format
        System.out.println("\nQuery: " + xpathQuery);
        System.out.println("Result size: " + (result != null ? result.size() : "null"));
        if (result != null) {
            System.out.println("Results:");
            for (Node node : result) {
                System.out.println(formatNode(node));
            }
        }
        
        return result;
    }

    private String formatNode(Node node) {
        StringBuilder sb = new StringBuilder();
        String nodeName = node.getNodeName();
        String content = node.getTextContent().trim();
        
        // Skip #text nodes
        if (nodeName.equals("#text")) {
            return "";
        }
        
        // Format as XML
        sb.append("<").append(nodeName).append(">");
        if (!content.isEmpty()) {
            sb.append(content);
        }
        sb.append("</").append(nodeName).append(">\n");
        
        return sb.toString();
    }

    // Absolute Path Tests
    @Test
    public void testAbsoluteSlash() {
        String xpathQuery = "doc(\"" + CAESAR_FILE + "\")/PLAY/TITLE";
        LinkedList<Node> result = evaluateXPath(xpathQuery);
        assertNotNull("Result should not be null", result);
        assertFalse("Result should not be empty", result.isEmpty());
        assertEquals("TITLE", result.getFirst().getNodeName());
    }

    @Test
    public void testAbsoluteDoubleSlash() {
        String xpathQuery = "doc(\"" + CAESAR_FILE + "\")//SPEAKER";
        LinkedList<Node> result = evaluateXPath(xpathQuery);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue("Should find multiple SPEAKER elements", result.size() > 10);
    }

    // Relative Path Tests
    @Test
    public void testTagNameMatch() {
        String xpathQuery = "doc(\"" + CAESAR_FILE + "\")/PLAY/ACT/SCENE/SPEECH/SPEAKER";
        LinkedList<Node> result = evaluateXPath(xpathQuery);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("SPEAKER", result.getFirst().getNodeName());
    }

    @Test
    public void testAllChildren() {
        String xpathQuery = "doc(\"" + CAESAR_FILE + "\")/PLAY/ACT/SCENE/*";
        LinkedList<Node> result = evaluateXPath(xpathQuery);
        assertNotNull(result);
        assertTrue("Should find multiple child elements", result.size() > 5);
    }

    @Test
    public void testSelf() {
        String xpathQuery = "doc(\"" + CAESAR_FILE + "\")/PLAY/ACT/.";
        LinkedList<Node> result = evaluateXPath(xpathQuery);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("ACT", result.getFirst().getNodeName());
    }

    @Test
    public void testParent() {
        String xpathQuery = "doc(\"" + CAESAR_FILE + "\")/PLAY/ACT/SCENE/SPEECH/SPEAKER/..";
        LinkedList<Node> result = evaluateXPath(xpathQuery);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("SPEECH", result.getFirst().getNodeName());
    }

    // Filter Tests
    @Test
    public void testSpeakerFilter() {
        String xpathQuery = "doc(\"" + CAESAR_FILE + "\")//SPEECH[SPEAKER=\"CAESAR\"]";
        LinkedList<Node> result = evaluateXPath(xpathQuery);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue("Should find multiple CAESAR speeches", result.size() > 1);
    }

    @Test
    public void testLineFilter() {
        String xpathQuery = "doc(\"" + CAESAR_FILE + "\")//SPEECH[LINE=\"Caesar!\"]";
        LinkedList<Node> result = evaluateXPath(xpathQuery);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testAndFilter() {
        String xpathQuery = "doc(\"" + CAESAR_FILE + "\")//SPEECH[SPEAKER=\"BRUTUS\" and LINE]";
        LinkedList<Node> result = evaluateXPath(xpathQuery);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue("Should find multiple BRUTUS speeches with lines", result.size() > 1);
    }

    @Test
    public void testOrFilter() {
        String xpathQuery = "doc(\"" + CAESAR_FILE + "\")//SPEECH[SPEAKER=\"CASSIUS\" or SPEAKER=\"BRUTUS\"]";
        LinkedList<Node> result = evaluateXPath(xpathQuery);
        assertNotNull(result);
        assertTrue("Should find speeches from both CASSIUS and BRUTUS", result.size() > 2);
    }

    @Test
    public void testNotFilter() {
        String xpathQuery = "doc(\"" + CAESAR_FILE + "\")//SPEECH[not(STAGEDIR)]";
        LinkedList<Node> result = evaluateXPath(xpathQuery);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testNestedFilter() {
        String xpathQuery = "doc(\"" + CAESAR_FILE + "\")//SPEECH[(SPEAKER=\"CAESAR\" and LINE) or STAGEDIR]";
        LinkedList<Node> result = evaluateXPath(xpathQuery);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testDoubleSlashWithFilter() {
        String xpathQuery = "doc(\"" + CAESAR_FILE + "\")//SCENE[TITLE]//SPEECH[SPEAKER=\"CAESAR\"]";
        LinkedList<Node> result = evaluateXPath(xpathQuery);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue("Should find multiple CAESAR speeches across scenes", result.size() > 1);
    }
} 