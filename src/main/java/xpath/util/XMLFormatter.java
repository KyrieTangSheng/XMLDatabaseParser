package xpath.util;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;

public class XMLFormatter {
    // Common inline elements that shouldn't create new lines
    private static final Set<String> INLINE_ELEMENTS = new HashSet<>(Arrays.asList(
        "em", "strong", "a", "span", "code", "i", "b", "sub", "sup"
    ));
    
    public static String formatNode(Node node) {
        return formatNode(node, 0);
    }

    private static String formatNode(Node node, int indent) {
        StringBuilder sb = new StringBuilder();
        String nodeName = node.getNodeName();

        if (nodeName.equals("#text")) {
            // Normalize text content: replace multiple spaces with single space and trim
            String text = node.getTextContent().replaceAll("\\s+", " ").trim();
            return text.isEmpty() ? "" : text;
        }

        boolean isInline = INLINE_ELEMENTS.contains(nodeName.toLowerCase());
        
        // Add newline and indentation for block elements
        if (!isInline) {
            sb.append("\n").append("    ".repeat(indent));
        }

        // Start tag
        sb.append("<").append(nodeName);
        
        // Handle attributes if any
        if (node.hasAttributes()) {
            for (int i = 0; i < node.getAttributes().getLength(); i++) {
                Node attr = node.getAttributes().item(i);
                sb.append(" ").append(attr.getNodeName())
                  .append("=\"").append(attr.getNodeValue().trim()).append("\"");
            }
        }
        sb.append(">");

        // Normalize inner text (handling <TITLE> and others)
        String textContent = node.getTextContent().replaceAll("\\s+", " ").trim();

        // Handle child nodes (recursive call)
        NodeList children = node.getChildNodes();
        if (children.getLength() == 0) {
            if (!textContent.isEmpty()) {
                sb.append(textContent);
            }
        } else {
            boolean hasNonTextChildren = false;
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (!child.getNodeName().equals("#text")) {
                    hasNonTextChildren = true;
                }
                String childText = formatNode(child, indent + 1);
                if (!childText.isEmpty()) {
                    sb.append(childText);
                }
            }
            
            // Add newline and indent for closing tag of block elements with children
            if (!isInline && hasNonTextChildren) {
                sb.append("\n").append("    ".repeat(indent));
            }
        }

        // End tag (NO extra newline for inline consistency)
        sb.append("</").append(nodeName).append(">");

        return sb.toString();
    }
}
