package my.xpath.ast;

public class ASTPrinter {
    private static final String INDENT = "  "; // Two spaces for each level

    public static String print(ASTNode node) {
        return print(node, 0);
    }

    private static String print(ASTNode node, int level) {
        StringBuilder sb = new StringBuilder();
        String indent = INDENT.repeat(level);

        if (node instanceof ApNode) {
            ApNode ap = (ApNode) node;
            sb.append(indent).append("AbsolutePath:\n");
            sb.append(indent).append(INDENT).append("File: ").append(ap.getFileName()).append("\n");
            sb.append(indent).append(INDENT).append("Type: ").append(ap.isDoubleSlash() ? "//" : "/").append("\n");
            sb.append(print(ap.getChild(), level + 1));
        } else if (node instanceof TagNameRp) {
            TagNameRp tn = (TagNameRp) node;
            sb.append(indent).append("TagName: ").append(tn.getTagName()).append("\n");
        } else if (node instanceof AttributeRp) {
            AttributeRp attr = (AttributeRp) node;
            sb.append(indent).append("Attribute: @").append(attr.getAttributeName()).append("\n");
        } else if (node instanceof SlashRp) {
            SlashRp slash = (SlashRp) node;
            sb.append(indent).append("Slash:\n");
            sb.append(print(slash.getLeft(), level + 1));
            sb.append(print(slash.getRight(), level + 1));
        } else if (node instanceof DoubleSlashRp) {
            DoubleSlashRp dslash = (DoubleSlashRp) node;
            sb.append(indent).append("DoubleSlash:\n");
            sb.append(print(dslash.getLeft(), level + 1));
            sb.append(print(dslash.getRight(), level + 1));
        } else if (node instanceof FilteredRp) {
            FilteredRp filter = (FilteredRp) node;
            sb.append(indent).append("Filter:\n");
            sb.append(print(filter.getRp(), level + 1));
            sb.append(print(filter.getFilter(), level + 1));
        } else if (node instanceof DotRp) {
            sb.append(indent).append("Self (.)\n");
        } else if (node instanceof DotDotRp) {
            sb.append(indent).append("Parent (..)\n");
        } else if (node instanceof StarRp) {
            sb.append(indent).append("Wildcard (*)\n");
        }
        // Add more node types as needed

        return sb.toString();
    }
} 