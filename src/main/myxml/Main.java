package src.main.myxml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import src.main.myxml.XMLParser;

public class Main {
    public static void main(String[] args) {
        // Specify the path to your XML file
        String filePath = "src/main/myxml/j_caesar.xml";

        // Parse the XML file into a DOM Document object
        Document doc = XMLParser.parse(filePath);

        if (doc != null) {
            // Get the root element and print its name
            Element root = doc.getDocumentElement();
            System.out.println("Root Element: " + root.getTagName());

            // Example: Get all <TITLE> elements
            NodeList titles = doc.getElementsByTagName("TITLE");
            for (int i = 0; i < titles.getLength(); i++) {
                System.out.println("Title: " + titles.item(i).getTextContent());
            }

            // Example: Get all <PERSONA> elements
            NodeList personas = doc.getElementsByTagName("PERSONA");
            for (int i = 0; i < personas.getLength(); i++) {
                System.out.println("Persona: " + personas.item(i).getTextContent());
            }
        } else {
            System.out.println("Failed to parse the XML file.");
        }
    }
}
