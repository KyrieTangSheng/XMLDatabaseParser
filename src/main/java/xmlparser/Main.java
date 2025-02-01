package xmlparser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.w3c.dom.*;
import java.util.LinkedList;
import xpath.XPathLexer;
import xpath.XPathParser;
import xpath.XPathEvaluator;

public class Main {
    public static void main(String[] args) {
        String xpathQuery = "doc(\"j_caesar.xml\")//PERSONA";  // Selecting all <TITLE> elements
        XPathLexer lexer = new XPathLexer(CharStreams.fromString(xpathQuery));
        XPathParser parser = new XPathParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.xpath(); // Parse as relative path

        System.out.println("Parse Tree: " + tree.toStringTree(parser)); // Debugging parse tree

        // ✅ Step 3: Evaluate XPath expression
        XPathEvaluator evaluator = new XPathEvaluator();
        LinkedList<Node> result = evaluator.visit(tree);


        // ✅ Step 4: Print matching nodes
        System.out.println("Matching nodes for XPath: " + xpathQuery);
        for (Node node : result) {
            System.out.println(node.getTextContent().trim()); // Print text inside matched nodes
        }
    }
}
