package my.xpath.ast;

public class OrFilterNode extends FilterNode {
    private final FilterNode left;
    private final FilterNode right;

    public OrFilterNode(FilterNode left, FilterNode right) {
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
        return "OrFilterNode(" + left + " or " + right + ")";
    }
}
