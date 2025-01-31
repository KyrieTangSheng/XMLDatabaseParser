package my.xpath.ast;

/**
 * Represents an absolute path expression, e.g., doc("filename")/rp or doc("filename")//rp.
 */
public class ApNode implements ASTNode {
    private final String fileName;    // e.g., "books.xml"
    private final RpNode child;        // The relative path part
    private final boolean isDoubleSlash; // True if //, false if /

    public ApNode(String fileName, RpNode child, boolean isDoubleSlash) {
        this.fileName = fileName;
        this.child = child;
        this.isDoubleSlash = isDoubleSlash;
    }

    public String getFileName() {
        return fileName;
    }

    public RpNode getChild() {
        return child;
    }

    public boolean isDoubleSlash() {
        return isDoubleSlash;
    }

    @Override
    public String toString() {
        return "ApNode(doc(" + fileName + ")" 
                + (isDoubleSlash ? "//" : "/")
                + child;
    }
}

