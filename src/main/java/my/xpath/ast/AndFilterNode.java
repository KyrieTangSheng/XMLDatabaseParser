package my.xpath.ast;

public class AndFilterNode extends FilterNode {
    private final FilterNode left;
    private final FilterNode right;

    public AndFilterNode(FilterNode left, FilterNode right) {
        this.left = left;
        this.right = right;
    }

    public FilterNode getLeft() {
        return left;
    }

    public FilterNode getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "AndFilterNode(" + left + " and " + right + ")";
    }
}
