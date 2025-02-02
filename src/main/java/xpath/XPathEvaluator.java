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
     *    doc("fileName")/relativePath
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
     *    doc("fileName")//relativePath
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
     */
    @Override
    public LinkedList<Node> visitTagNameMatch(XPathParser.TagNameMatchContext ctx) {
        String tagName = ctx.tagName().getText();
        LinkedList<Node> result = new LinkedList<>();
        // For each node in the current context, add its children that match.
        for (Node node : currentContext) {
            LinkedList<Node> children = getChildren(node);
            for(Node child : children){
                if(child.getNodeName().equals(tagName)){
                    result.add(child);
                }
            }
        }
        currentContext = result;
        return currentContext;
    }

    @Override
    public LinkedList<Node> visitAllChildren(XPathParser.AllChildrenContext ctx) {
        LinkedList<Node> result = new LinkedList<>();
        // For each node in the current context, add its children that match.
        for (Node node : currentContext) {
            LinkedList<Node> children = getChildren(node);
            result.addAll(children);
        }
        currentContext = result;
        return currentContext;
    }

    @Override
    public LinkedList<Node> visitSelf(XPathParser.SelfContext ctx){
        return currentContext;
    }

    @Override
    public LinkedList<Node> visitParent(XPathParser.ParentContext ctx) {
        System.out.println("✅ visitParent() called!");
    
        LinkedList<Node> newContext = new LinkedList<>();
    
        // Traverse each node in the current context and collect their parent nodes
        for (Node node : currentContext) {
            Node parent = node.getParentNode();
            System.out.println("🔍 Checking parent of node: " + node.getNodeName());
    
            if (parent != null) {
                System.out.println("📌 Found parent: " + parent.getNodeName() + " (Type: " + parent.getNodeType() + ")");
    
                if (parent.getNodeType() == Node.ELEMENT_NODE) {
                    if (!newContext.contains(parent)) { // Ensure uniqueness
                        newContext.add(parent);
                        System.out.println("✅ Added parent: " + parent.getNodeName());
                    }
                } else {
                    System.out.println("❌ Skipping non-element parent: " + parent.getNodeName());
                }
            } else {
                System.out.println("❌ No parent found for node: " + node.getNodeName());
            }
        }
    
        System.out.println("🔍 Final Parent Nodes: " + newContext.size());
        
        // Update context
        currentContext = newContext;
        return currentContext;
    }

    // ----------------------------------------------------------------
    // Helper Methods
    // ----------------------------------------------------------------

    /**
     * Returns all children of the nodes
     */
    private LinkedList<Node> getChildren(Node node) {
        LinkedList<Node> result = new LinkedList<>();
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            result.add(child);
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
