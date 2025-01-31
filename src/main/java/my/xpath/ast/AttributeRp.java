package my.xpath.ast;

/**
 * rp → @attName
 */
public class AttributeRp extends RpNode {
    private final String attributeName;

    public AttributeRp(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeName() {
        return attributeName;
    }

    @Override
    public String toString() {
        return "AttributeRp(@" + attributeName + ")";
    }
}
