package my.xpath.ast;

public class NotFilterNode extends FilterNode {
    private final FilterNode inner;

    public NotFilterNode(FilterNode inner) {
        this.inner = inner;
    }

    public FilterNode getInner() {
        return inner;
    }

    @Override
    public String toString() {
        return "NotFilterNode(not " + inner + ")";
    }
}
