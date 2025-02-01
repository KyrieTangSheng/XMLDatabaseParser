package src.main.java.xmlparser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.w3c.dom.*;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // ✅ Step 1: Parse XML file
        String filePath = "src/main/files/j_caesar.xml";
        Document doc = XMLParser.parse(filePath);

        if (doc != null) {
            // Get the root element
            Element root = doc.getDocumentElement();
            System.out.println("Root Element: " + root.getTagName());

            // ✅ Step 2: Parse an XPath query
            String xpathQuery = "TITLE";  // Selecting all <TITLE> elements
            XPathLexer lexer = new XPathLexer(CharStreams.fromString(xpathQuery));
            XPathParser parser = new XPathParser(new CommonTokenStream(lexer));
            ParseTree tree = parser.relativePath(); // Parse as relative path

            // ✅ Step 3: Evaluate XPath expression
            XPathEvaluator evaluator = new XPathEvaluator(root);
            LinkedList<Node> result = evaluator.visit(tree);

            // ✅ Step 4: Print matching nodes
            System.out.println("Matching nodes for XPath: " + xpathQuery);
            for (Node node : result) {
                System.out.println(node.getTextContent().trim()); // Print text inside matched nodes
            }
        } else {
            System.out.println("Failed to parse the XML file.");
        }
    }
}
