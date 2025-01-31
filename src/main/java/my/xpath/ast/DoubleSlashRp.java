package my.xpath.ast;

/**
 * rp1 // rp2
 */
public class DoubleSlashRp extends RpNode {
    private final RpNode left;
    private final RpNode right;

    public DoubleSlashRp(RpNode left, RpNode right) {
        this.left = left;
        this.right = right;
    }

    public RpNode getLeft() {
        return left;
    }

    public RpNode getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "DoubleSlashRp(" + left + " // " + right + ")";
    }
}
