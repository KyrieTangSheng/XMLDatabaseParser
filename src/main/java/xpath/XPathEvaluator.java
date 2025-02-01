package xpath;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.LinkedList;

public class XPathEvaluator extends XPathBaseVisitor<LinkedList<Node>> {
    private Node root; // Root of the XML Document

    public XPathEvaluator(Node root) {
        this.root = root;
    }

    @Override
    public LinkedList<Node> visitTagNameMatch(XPathParser.TagNameMatchContext ctx) {
        String tagName = ctx.tagName().getText(); // Extract tag name from XPath query
        return findMatchingNodes(root, tagName); // Find matching nodes in the XML
    }

    // Helper function to find all matching nodes in the DOM tree
    private LinkedList<Node> findMatchingNodes(Node node, String tagName) {
        LinkedList<Node> result = new LinkedList<>();
        
        // If the current node matches, add it
        if (node.getNodeName().equals(tagName)) {
            result.add(node);
        }

        // Recursively check all children
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            result.addAll(findMatchingNodes(child, tagName));
        }
        
        return result;
    }
}
