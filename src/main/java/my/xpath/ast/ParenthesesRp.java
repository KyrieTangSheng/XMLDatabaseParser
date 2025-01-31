package my.xpath.ast;

/**
 * rp → ( rp )
 * Usually we might just store the 'inner' rp, but let's keep a wrapper for clarity.
 */
public class ParenthesesRp extends RpNode {
    private final RpNode inner;

    public ParenthesesRp(RpNode inner) {
        this.inner = inner;
    }

    public RpNode getInner() {
        return inner;
    }

    @Override
    public String toString() {
        return "( " + inner + " )";
    }
}
