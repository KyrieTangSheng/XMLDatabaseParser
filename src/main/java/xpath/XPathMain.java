package xpath;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.LinkedList;
import xpath.util.XMLFormatter;

public class XPathMain {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java xpath.XPathMain <xml-file> <xpath-query>");
            System.out.println("Example: java xpath.XPathMain src/test/resources/j_caesar.xml \"//PERSONA\"");
            System.exit(1);
        }

        String xmlFile = args[0];
        String xpathQuery = args[1];

        // If query doesn't start with doc(), wrap it
        if (!xpathQuery.startsWith("doc(")) {
            xpathQuery = "doc(\"" + xmlFile + "\")" + xpathQuery;
        }

        try {
            XPathEvaluator evaluator = new XPathEvaluator();
            LinkedList<Node> result = evaluateXPath(xpathQuery, evaluator);

            System.out.println("Query: " + xpathQuery);
            System.out.println("Result size: " + (result != null ? result.size() : "null"));
            
            if (result != null) {
                System.out.println("Results:");
                for (Node node : result) {
                    System.out.println(XMLFormatter.formatNode(node));
                }
            }
        } catch (Exception e) {
            System.err.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static LinkedList<Node> evaluateXPath(String xpathQuery, XPathEvaluator evaluator) {
        XPathLexer lexer = new XPathLexer(CharStreams.fromString(xpathQuery));
        XPathParser parser = new XPathParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.xpath();
        return evaluator.visit(tree);
    }
}    