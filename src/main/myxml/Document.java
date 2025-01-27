package src.main.myxml;

/**
 * Represents the entire XML Document.
 * Holds a reference to the root element and possibly other metadata (version, encoding, etc.).
 */
public class Document {
    private ElementNode root;
    private String xmlVersion;
    private String encoding;

    public Document() {
        this.xmlVersion = "1.0";
        this.encoding = "UTF-8";
    }

    public ElementNode getRootElement() {
        return root;
    }

    public void setRootElement(ElementNode root) {
        this.root = root;
    }

    public String getXmlVersion() {
        return xmlVersion;
    }

    public void setXmlVersion(String xmlVersion) {
        this.xmlVersion = xmlVersion;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

        /**
     * Prints the entire XML document structure to the console.
     */
    public void print() {
        if (root == null) {
            System.out.println("Empty Document.");
            return;
        }
        System.out.println("<?xml version=\"" + xmlVersion + "\" encoding=\"" + encoding + "\"?>");
        printNode(root, 0);
    }

    /**
     * Recursively prints a node and its children with indentation.
     *
     * @param node  The current node to print.
     * @param level The current depth level in the tree for indentation.
     */
    private void printNode(Node node, int level) {
        String indent = "  ".repeat(level); // 2 spaces per level

        if (node instanceof ElementNode) {
            ElementNode elem = (ElementNode) node;
            // Print the opening tag with attributes
            System.out.print(indent + "<" + elem.getName());

            // Print attributes if any
            if (!elem.getAttributes().isEmpty()) {
                for (Attribute attr : elem.getAttributes()) {
                    System.out.print(" " + attr.getName() + "=\"" + attr.getValue() + "\"");
                }
            }

            // Check if the element has children
            if (elem.getChildren().isEmpty()) {
                // Self-closing tag
                System.out.println("/>");
            } else {
                System.out.println(">");

                // Recursively print all child nodes
                for (Node child : elem.getChildren()) {
                    printNode(child, level + 1);
                }

                // Print the closing tag
                System.out.println(indent + "</" + elem.getName() + ">");
            }

        } else if (node instanceof TextNode) {
            TextNode text = (TextNode) node;
            // Print text content with indentation
            System.out.println(indent + text.getContent());
        }

        // You can handle other node types (e.g., CommentNode) here if you have them
    }
}
