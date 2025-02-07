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
        String xpathQuery = "doc(\"j_caesar.xml\")//PERSONA, //SCENE";
        XPathLexer lexer = new XPathLexer(CharStreams.fromString(xpathQuery));
        XPathParser parser = new XPathParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.xpath();
        XPathEvaluator evaluator = new XPathEvaluator();
        evaluator.visit(tree);
    }
}
