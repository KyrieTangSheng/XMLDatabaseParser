package xpath;

import org.junit.Test;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.LinkedList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import xpath.util.XMLFormatter;

public class XPathQueryDemo {
    private static final String CAESAR_FILE = "src/test/resources/j_caesar.xml";
    private static final String OUTPUT_DIR = "query_results";
    private static XPathEvaluator evaluator;

    @Test
    public void demonstrateQueries() {
        evaluator = new XPathEvaluator();
        
        // Create output directory if it doesn't exist
        File outputDir = new File(OUTPUT_DIR);
        System.out.println("Attempting to create directory: " + outputDir.getAbsolutePath());
        
        if (!outputDir.exists()) {
            boolean created = outputDir.mkdirs();
            System.out.println("Directory created: " + created);
            if (!created) {
                System.err.println("Failed to create output directory: " + OUTPUT_DIR);
                return;
            }
        } else {
            System.out.println("Directory already exists");
        }
        
        // Demo 1: Find all personas
        System.out.println("\nWriting to file: " + OUTPUT_DIR + "/query1_personas.txt");
        writeQueryResult("query1_personas.txt",
            "Find all personas", 
            "doc(\"" + CAESAR_FILE + "\")//PERSONA");
        
        // Demo 2: Find scenes where Caesar speaks
        writeQueryResult("query2_caesar_scenes.txt",
            "Find scenes where Caesar speaks", 
            "doc(\"" + CAESAR_FILE + "\")//SCENE[SPEECH/SPEAKER/text()=\"CAESAR\"]");
        
        // Demo 3: Find acts with Caesar and Brutus in same scene (Version 1)
        writeQueryResult("query3_caesar_brutus_and.txt",
            "Find acts where Caesar and Brutus speak in same scene (AND syntax)", 
            "doc(\"" + CAESAR_FILE + "\")//ACT[SCENE[SPEECH/SPEAKER/text()=\"CAESAR\" and " +
            "SPEECH/SPEAKER/text()=\"BRUTUS\"]]");
        
        // Demo 4: Find acts with Caesar and Brutus in same scene (Version 2)
        writeQueryResult("query4_caesar_brutus_filter.txt",
            "Find acts where Caesar and Brutus speak in same scene (Filter syntax)", 
            "doc(\"" + CAESAR_FILE + "\")//ACT[SCENE[SPEECH/SPEAKER/text()=\"CAESAR\"]" +
            "[SPEECH/SPEAKER/text()=\"BRUTUS\"]]");
        
        // Demo 5: Find acts without Caesar
        writeQueryResult("query5_no_caesar.txt",
            "Find acts where Caesar does not appear", 
            "doc(\"" + CAESAR_FILE + "\")//ACT[not(.//SPEAKER/text()=\"CAESAR\")]");
            
        System.out.println("Results have been written to the " + OUTPUT_DIR + " directory");
    }

    private static void writeQueryResult(String filename, String description, String xpathQuery) {
        File outputFile = new File(OUTPUT_DIR, filename);
        System.out.println("Full path of output file: " + outputFile.getAbsolutePath());
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            writer.println("=== " + description + " ===");
            writer.println("Generated at: " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            writer.println("Query: " + xpathQuery);
            writer.println();
            
            LinkedList<Node> result = evaluateXPath(xpathQuery);
            
            writer.println("Result size: " + (result != null ? result.size() : "null"));
            if (result != null) {
                writer.println("Results:");
                for (Node node : result) {
                    writer.println(XMLFormatter.formatNode(node));
                }
            }
            
            System.out.println("Successfully wrote to file: " + filename);
            
        } catch (IOException e) {
            System.err.println("Error writing to file " + filename + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static LinkedList<Node> evaluateXPath(String xpathQuery) {
        XPathLexer lexer = new XPathLexer(CharStreams.fromString(xpathQuery));
        XPathParser parser = new XPathParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.xpath();
        return evaluator.visit(tree);
    }
}    