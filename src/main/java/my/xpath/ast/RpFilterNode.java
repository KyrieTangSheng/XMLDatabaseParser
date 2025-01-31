package my.xpath.ast;

/**
 * f → rp
 */
public class RpFilterNode extends FilterNode {
    private final RpNode rp;

    public RpFilterNode(RpNode rp) {
        this.rp = rp;
    }

    public RpNode getRp() {
        return rp;
    }

    @Override
    public String toString() {
        return "RpFilterNode(" + rp + ")";
    }
}
