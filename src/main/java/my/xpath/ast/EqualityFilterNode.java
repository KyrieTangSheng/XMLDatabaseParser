package my.xpath.ast;

/**
 * rp1 = rp2 or rp1 eq rp2
 */
public class EqualityFilterNode extends FilterNode {
    public enum EqualityOp {
        VALUE_EQUAL, // for '=', 'eq'
        IDENTITY_EQUAL // for '==', 'is'
    }

    private final RpNode left;
    private final RpNode right;
    private final EqualityOp op;

    public EqualityFilterNode(RpNode left, RpNode right, EqualityOp op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    public RpNode getLeft() {
        return left;
    }

    public RpNode getRight() {
        return right;
    }

    public EqualityOp getOp() {
        return op;
    }

    @Override
    public String toString() {
        return "EqualityFilterNode(" + left + " " + op + " " + right + ")";
    }
}
