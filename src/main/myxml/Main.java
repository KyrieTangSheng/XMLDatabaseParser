package src.main.myxml;
public class Main {
    public static void main(String[] args) {
        String xmlString = "<root><child attr=\"value\">Some text</child></root>";
        XMLParser parser = new XMLParser();

        Document doc = parser.parse(xmlString);

        doc.print();
        // Now "doc" is your in-memory tree. 
        // You can navigate doc.getRootElement(), doc.getRootElement().getChildren(), etc.
    }
    
}
