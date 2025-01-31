package my.xpath.ast;

/**
 * (f)
 */
public class ParenFilterNode extends FilterNode {
    private final FilterNode inner;

    public ParenFilterNode(FilterNode inner) {
        this.inner = inner;
    }

    public FilterNode getInner() {
        return inner;
    }

    @Override
    public String toString() {
        return "( " + inner + " )";
    }
}
