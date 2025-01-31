package my.xpath.ast;

/**
 * rp → tagName
 */
public class TagNameRp extends RpNode {
    private final String tagName;

    public TagNameRp(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    @Override
    public String toString() {
        return "TagNameRp(" + tagName + ")";
    }
}
