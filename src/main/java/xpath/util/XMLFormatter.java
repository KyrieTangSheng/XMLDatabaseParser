package xpath.util;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLFormatter {
    public static String formatNode(Node node) {
        StringBuilder sb = new StringBuilder();
        String nodeName = node.getNodeName();

        if (nodeName.equals("#text")) {
            // Normalize text content: replace multiple spaces with single space and trim
            String text = node.getTextContent().replaceAll("\\s+", " ").trim();
            return text.isEmpty() ? "" : text;
        }

        // Start tag
        sb.append("<").append(nodeName);
        
        // Handle attributes if any
        if (node.hasAttributes()) {
            for (int i = 0; i < node.getAttributes().getLength(); i++) {
                Node attr = node.getAttributes().item(i);
                sb.append(" ").append(attr.getNodeName()).append("=\"").append(attr.getNodeValue().trim()).append("\"");
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
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                String childText = formatNode(child);
                if (!childText.isEmpty()) {
                    sb.append(childText);
                }
            }
        }

        // End tag (NO extra newline for inline consistency)
        sb.append("</").append(nodeName).append(">");

        return sb.toString();
    }
}
