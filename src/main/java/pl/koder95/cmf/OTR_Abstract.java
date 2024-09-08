package pl.koder95.cmf;

import pl.koder95.cmf.core.OrdinaryTimeRow;

public abstract class OTR_Abstract implements OrdinaryTimeRow {

    public OTR_Basic toBasic() {
        return new OTR_Basic(getStart(), getEnd());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "start=" + getStart() +
                ", end=" + getEnd() +
                ", weeksAmount=" + getWeeksAmount() +
                ", startWeekNumber=" + getStartWeekNumber() +
                ", endWeekNumber=" + getEndWeekNumber() +
                '}';
    }
}
