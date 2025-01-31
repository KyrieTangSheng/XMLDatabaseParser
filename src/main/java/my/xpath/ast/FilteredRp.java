package my.xpath.ast;

/**
 * rp[f]
 */
public class FilteredRp extends RpNode {
    private final RpNode rp;
    private final FilterNode filter;

    public FilteredRp(RpNode rp, FilterNode filter) {
        this.rp = rp;
        this.filter = filter;
    }

    public RpNode getRp() {
        return rp;
    }

    public FilterNode getFilter() {
        return filter;
    }

    @Override
    public String toString() {
        return "FilteredRp(" + rp + " [" + filter + "])";
    }
}
