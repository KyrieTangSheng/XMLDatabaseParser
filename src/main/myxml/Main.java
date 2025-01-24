package src.main.myxml;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        Document d = new Document();
        System.out.println(d.getEncoding());

    
        /*
        // Suppose "xmlString" contains your XML content
        String xmlString = "<TEI> ... </TEI>";
    
        XMLParser parser = new XMLParser();
        Document doc = parser.parse(xmlString);
        */
    
        // Now "doc" is your in-memory tree. 
        // You can navigate doc.getRootElement(), doc.getRootElement().getChildren(), etc.
    }
    
}
