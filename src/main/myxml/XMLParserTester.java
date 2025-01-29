package src.main.myxml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLParserTester {
    public static void main(String[] args) {
        // File path to your XML file
        String xmlFilePath = "src/main/files/j_caesar.xml";

        // Parse the XML file
        Document doc = XMLParser.parse(xmlFilePath);

        if (doc == null) {
            System.out.println("Failed to parse the XML file.");
            return;
        }

        try {
            // Test 1: Root element check
            Element root = doc.getDocumentElement();
            System.out.println("Root Element: " + root.getTagName());
            if (!"PLAY".equals(root.getTagName())) {
                throw new Exception("Root element should be PLAY, but found: " + root.getTagName());
            }

            // Test 2: Check <TITLE> elements count and content
            NodeList titleNodes = doc.getElementsByTagName("TITLE");
            System.out.println("\nTest 2: <TITLE> Elements");
            if (titleNodes.getLength() != 25) {
                throw new Exception("Expected 3 <TITLE> elements, but found: " + titleNodes.getLength());
            }
            for (int i = 0; i < titleNodes.getLength(); i++) {
                System.out.println("Title " + (i + 1) + ": " + titleNodes.item(i).getTextContent());
            }

            // Test 3: Check <PERSONA> elements count
            NodeList personaNodes = doc.getElementsByTagName("PERSONA");
            System.out.println("\nTest 3: <PERSONA> Elements");
            if (personaNodes.getLength() != 36) {
                throw new Exception("Expected 35 <PERSONA> elements, but found: " + personaNodes.getLength());
            }
            for (int i = 0; i < personaNodes.getLength(); i++) {
                System.out.println("Persona " + (i + 1) + ": " + personaNodes.item(i).getTextContent());
            }

            // Test 4: Check <SCENE> count within <ACT>
            NodeList actNodes = doc.getElementsByTagName("ACT");
            int totalScenes = 0;
            for (int i = 0; i < actNodes.getLength(); i++) {
                Element act = (Element) actNodes.item(i);
                NodeList scenes = act.getElementsByTagName("SCENE");
                System.out.println("ACT " + (i + 1) + " has " + scenes.getLength() + " scenes.");
                totalScenes += scenes.getLength();
            }
            if (totalScenes == 0) {
                throw new Exception("There should be scenes in the play, but none were found.");
            }

            System.out.println("\nAll tests passed successfully!");

        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
        }
    }
}
