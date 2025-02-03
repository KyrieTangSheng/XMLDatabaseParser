package xpath;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class XPathFilterTest {
    private XPathEvaluator evaluator;

    @Before
    public void setUp() {
        evaluator = new XPathEvaluator();
    }

    // Basic path filter tests
    @Test
    public void testBasicFilter() {
        // Test: doc("j_caesar.xml")/PLAY/ACT[TITLE]
        String xpath = "doc(\"j_caesar.xml\")/PLAY/ACT[TITLE]";
        assertFalse(evaluator.evaluate(xpath).isEmpty());

        // Test: doc("j_caesar.xml")/PLAY/ACT[NONEXISTENT]
        xpath = "doc(\"j_caesar.xml\")/PLAY/ACT[NONEXISTENT]";
        assertTrue(evaluator.evaluate(xpath).isEmpty());
    }

    // Value equality tests
    @Test
    public void testEqualityFilter() {
        // Test: doc("j_caesar.xml")/PLAY/ACT[TITLE = "ACT I"]
        String xpath = "doc(\"j_caesar.xml\")/PLAY/ACT[TITLE = \"ACT I\"]";
        assertFalse(evaluator.evaluate(xpath).isEmpty());

        // Test non-matching equality
        xpath = "doc(\"j_caesar.xml\")/PLAY/ACT[TITLE = \"NONEXISTENT\"]";
        assertTrue(evaluator.evaluate(xpath).isEmpty());
    }

    // Identity equality tests
    @Test
    public void testIdentityFilter() {
        // Test: doc("j_caesar.xml")/PLAY/ACT[. is .]
        String xpath = "doc(\"j_caesar.xml\")/PLAY/ACT[. is .]";
        assertFalse(evaluator.evaluate(xpath).isEmpty());

        // Test different nodes
        xpath = "doc(\"j_caesar.xml\")/PLAY/ACT[. is ../TITLE]";
        assertTrue(evaluator.evaluate(xpath).isEmpty());
    }

    // String constant tests
    @Test
    public void testStringFilter() {
        // Test: doc("j_caesar.xml")/PLAY/ACT[TITLE = "ACT I"]
        String xpath = "doc(\"j_caesar.xml\")/PLAY/ACT[TITLE = \"ACT I\"]";
        assertFalse(evaluator.evaluate(xpath).isEmpty());

        // Test non-matching string
        xpath = "doc(\"j_caesar.xml\")/PLAY/ACT[TITLE = \"WRONG TITLE\"]";
        assertTrue(evaluator.evaluate(xpath).isEmpty());
    }

    // Logical AND tests
    @Test
    public void testAndFilter() {
        // Test: doc("j_caesar.xml")/PLAY/ACT[TITLE and SCENE]
        String xpath = "doc(\"j_caesar.xml\")/PLAY/ACT[TITLE and SCENE]";
        assertFalse(evaluator.evaluate(xpath).isEmpty());

        // Test with one false condition
        xpath = "doc(\"j_caesar.xml\")/PLAY/ACT[TITLE and NONEXISTENT]";
        assertTrue(evaluator.evaluate(xpath).isEmpty());
    }

    // Logical OR tests
    @Test
    public void testOrFilter() {
        // Test: doc("j_caesar.xml")/PLAY/ACT[TITLE or NONEXISTENT]
        String xpath = "doc(\"j_caesar.xml\")/PLAY/ACT[TITLE or NONEXISTENT]";
        assertFalse(evaluator.evaluate(xpath).isEmpty());

        // Test with both false conditions
        xpath = "doc(\"j_caesar.xml\")/PLAY/ACT[NONEXISTENT1 or NONEXISTENT2]";
        assertTrue(evaluator.evaluate(xpath).isEmpty());
    }

    // Logical NOT tests
    @Test
    public void testNotFilter() {
        // Test: doc("j_caesar.xml")/PLAY/ACT[not(NONEXISTENT)]
        String xpath = "doc(\"j_caesar.xml\")/PLAY/ACT[not(NONEXISTENT)]";
        assertFalse(evaluator.evaluate(xpath).isEmpty());

        // Test negation of true condition
        xpath = "doc(\"j_caesar.xml\")/PLAY/ACT[not(TITLE)]";
        assertTrue(evaluator.evaluate(xpath).isEmpty());
    }

    // Complex filter tests
    @Test
    public void testComplexFilter() {
        // Test: doc("j_caesar.xml")/PLAY/ACT[TITLE = "ACT I" and SCENE or not(NONEXISTENT)]
        String xpath = "doc(\"j_caesar.xml\")/PLAY/ACT[TITLE = \"ACT I\" and SCENE or not(NONEXISTENT)]";
        assertFalse(evaluator.evaluate(xpath).isEmpty());
    }
} 