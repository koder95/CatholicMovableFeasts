package pl.koder95.cmf;

import java.time.LocalDate;

public class OTR_Stable extends OTR_Basic {

    private long weeksAmount;
    private long startWeekNumber;
    private long endWeekNumber;

    public OTR_Stable(LocalDate start, LocalDate end, long weeksAmount, long startWeekNumber, long endWeekNumber) {
        super(start, end);
        this.weeksAmount = weeksAmount;
        this.startWeekNumber = startWeekNumber;
        this.endWeekNumber = endWeekNumber;
    }

    @Override
    public long getWeeksAmount() {
        return weeksAmount;
    }

    @Override
    public long getStartWeekNumber() {
        return startWeekNumber;
    }

    @Override
    public long getEndWeekNumber() {
        return endWeekNumber;
    }

    public void setWeeksAmount(long weeksAmount) {
        this.weeksAmount = weeksAmount;
    }

    public void setStartWeekNumber(long startWeekNumber) {
        this.startWeekNumber = startWeekNumber;
    }

    public void setEndWeekNumber(long endWeekNumber) {
        this.endWeekNumber = endWeekNumber;
    }

    public FinalOTR toFinal() {
        return new FinalOTR(getStart(), getEnd(), weeksAmount, startWeekNumber, endWeekNumber);
    }
}
