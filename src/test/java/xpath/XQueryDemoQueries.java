package xpath;

import org.w3c.dom.Node;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class XQueryDemoQueries {
    private static XQueryEvaluator evaluator;
    private static final String TEST_XML = "src/test/resources/j_caesar.xml";
    private static final String OUTPUT_DIR = "xquery_output";
    
    private static String formatNode(Node node) {
        StringBuilder sb = new StringBuilder();
        String nodeName = node.getNodeName();
        String content = node.getTextContent().trim();
        
        // Handle text nodes differently
        if (nodeName.equals("#text")) {
            if (!content.isEmpty()) {
                sb.append(content).append("\n");
            }
            return sb.toString();
        }
        
        // Format as XML with proper indentation
        sb.append("<").append(nodeName).append(">");
        if (!content.isEmpty()) {
            sb.append("\n  ").append(content.replace("\n", "\n  "));
        }
        sb.append("\n</").append(nodeName).append(">\n");
        
        return sb.toString();
    }

    private static List<Node> evaluateXQuery(String query) {
        XQueryLexer lexer = new XQueryLexer(CharStreams.fromString(query));
        XQueryParser parser = new XQueryParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.xquery();
        return evaluator.visit(tree);
    }

    private static String sanitizeFilename(String input) {
        return input.toLowerCase().replaceAll("\\s+", "_").replaceAll("[^a-z0-9_-]", "");
    }

    private static void runQuery(String description, String query) {
        String filename = sanitizeFilename(description) + ".txt";
        File outputFile = new File(OUTPUT_DIR, filename);
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            writer.println("=== " + description + " ===");
            writer.println("Query:");
            writer.println(query);
            writer.println();
            writer.println("Result:");
            
            List<Node> result = evaluateXQuery(query);
            for (Node node : result) {
                writer.println(formatNode(node));
            }
            
            System.out.println("Results written to: " + outputFile.getPath());
            
        } catch (Exception e) {
            System.err.println("Error writing to file " + filename + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            // Create output directory if it doesn't exist
            File dir = new File(OUTPUT_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            evaluator = new XQueryEvaluator();

            // Query 1: Find the context of "Et tu, Brute!"
            String etTuQuery = 
                "<result>{" +
                "for $a in document(\"" + TEST_XML + "\")//ACT," +
                "    $sc in $a//SCENE," +
                "    $sp in $sc/SPEECH " +
                // "where $sp/LINE/text() = \"Et tu, Brute! Then fall, Caesar.\" " +
                "return <who>{$sp/SPEAKER/text()}</who>," +
                "       <when>{" +
                "           <act>{$a/TITLE/text()}</act>," +
                "           <scene>{$sc/TITLE/text()}</scene>" +
                "       }</when>" +
                "}</result>";
            
            runQuery("Et Tu Brute Context", etTuQuery);

            // Query 2: Group acts by speaker
            String speakerActsQuery = 
                "<result>{" +
                "for $s in document(\"" + TEST_XML + "\")//SPEAKER " +
                "return <speaks>{" +
                "    <who>{$s/text()}</who>," +
                "    for $a in doc(\"" + TEST_XML + "\")//ACT " +
                "    where some $s1 in $a//SPEAKER satisfies $s1 eq $s " +
                "    return <when>{$a/TITLE/text()}</when>" +
                "}</speaks>" +
                "}</result>";
            
            runQuery("Speaker Acts Grouping", speakerActsQuery);

            System.out.println("All demo queries have been executed. Check the '" + OUTPUT_DIR + "' directory for results.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 