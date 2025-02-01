package xpath;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import xmlparser.XMLParser;

import java.util.LinkedList;

public class XPathEvaluator extends XPathBaseVisitor<LinkedList<Node>> {

    // This list holds the current set of nodes that the next step will operate on.
    private LinkedList<Node> currentContext;

    public XPathEvaluator() {
        // Initially, the context is empty.
        System.out.println("constructor");
        currentContext = new LinkedList<>();
    }

    // ------------------
    // Entry Rule (optional)
    // ------------------
    @Override
    public LinkedList<Node> visitXpath(XPathParser.XpathContext ctx) {
        System.out.println("✅ visitXpath() called");
        if (ctx.absolutePath() != null) {
            System.out.println("Processing absolutePath...");
            return visit(ctx.absolutePath());
        } else {
            System.out.println("Processing relativePath...");
            return visit(ctx.relativePath());
        }
    }

    /**
     * Handles expressions of the form: 
     *    doc("j_caesar.xml")/relativePath
     */
    @Override
    public LinkedList<Node> visitAbsoluteSlash(XPathParser.AbsoluteSlashContext ctx) {
        // Step 1: Load the XML document and get its root element.
        String fileName = ctx.fileName().getText().replace("\"", "");
        Document doc = XMLParser.parse(fileName);
        Node root = doc.getDocumentElement(); 

        // Set the current context to contain only the root.
        currentContext.clear();
        currentContext.add(root);

        // Step 2: Process the relative path on this context.
        return visit(ctx.relativePath());
    }

    /**
     * Handles expressions of the form:
     *    doc("j_caesar.xml")//relativePath
     */
    @Override
    public LinkedList<Node> visitAbsoluteDoubleSlash(XPathParser.AbsoluteDoubleSlashContext ctx) {
        // Step 1: Load the XML document and get its root element.
        String fileName = ctx.fileName().getText().replace("\"", "");
        Document doc = XMLParser.parse(fileName);
        Node root = doc.getDocumentElement();

        // Set the current context to the root and all its descendant elements.
        currentContext.clear();
        currentContext.add(root);
        currentContext.addAll(getDescendants(root));

        // Step 2: Process the relative path on the updated context.
        return visit(ctx.relativePath());
    }

    /**
     * Processes a tag name match (e.g., PERSONA).
     * For each node in the current context, we collect the immediate children
     * whose tag name matches the given tag name.
     */
    @Override
    public LinkedList<Node> visitTagNameMatch(XPathParser.TagNameMatchContext ctx) {
        String tagName = ctx.tagName().getText();
        LinkedList<Node> newContext = new LinkedList<>();

        // For each node in the current context, add its children that match.
        for (Node node : currentContext) {
            newContext.addAll(getDirectChildrenByTag(node, tagName));
        }

        // Update the current context.
        currentContext = newContext;
        return currentContext;
    }

    // ----------------------------------------------------------------
    // Helper Methods
    // ----------------------------------------------------------------

    /**
     * Returns all immediate child elements of the given node that have the specified tag name.
     */
    private LinkedList<Node> getDirectChildrenByTag(Node node, String tagName) {
        LinkedList<Node> result = new LinkedList<>();
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            // Only consider element nodes.
            if (child.getNodeType() == Node.ELEMENT_NODE && child.getNodeName().equals(tagName)) {
                result.add(child);
            }
        }
        return result;
    }

    /**
     * Recursively collects all descendant element nodes (children, grandchildren, etc.).
     */
    private LinkedList<Node> getDescendants(Node node) {
        LinkedList<Node> result = new LinkedList<>();
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                result.add(child);
                result.addAll(getDescendants(child));
            }
        }
        return result;
    }
}
