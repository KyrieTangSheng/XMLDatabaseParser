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
        String xpathQuery = "doc(\"j_caesar.xml\")//ACT/SCENE";  // Selecting all <TITLE> elements
        XPathLexer lexer = new XPathLexer(CharStreams.fromString(xpathQuery));
        XPathParser parser = new XPathParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.xpath(); // Parse as relative path

        System.out.println("Parse Tree: " + tree.toStringTree(parser)); // Debugging parse tree

        // ✅ Step 3: Evaluate XPath expression
        XPathEvaluator evaluator = new XPathEvaluator();
        LinkedList<Node> result = evaluator.visit(tree);

        System.out.println(result.toString());
        
    }
}
