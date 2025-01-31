package my.xpath.ast;

/**
 * rp = StringConstant
 */
public class StringFilterNode extends FilterNode {
    private final RpNode rp;
    private final String stringConstant;

    public StringFilterNode(RpNode rp, String stringConstant) {
        this.rp = rp;
        this.stringConstant = stringConstant;
    }

    public RpNode getRp() {
        return rp;
    }

    public String getStringConstant() {
        return stringConstant;
    }

    @Override
    public String toString() {
        return "StringFilterNode(" + rp + " = \"" + stringConstant + "\")";
    }
}
