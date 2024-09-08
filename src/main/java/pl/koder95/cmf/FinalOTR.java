package pl.koder95.cmf;

import pl.koder95.cmf.core.OrdinaryTimeRow;

import java.time.LocalDate;

public final class FinalOTR implements OrdinaryTimeRow {

    public final LocalDate start, end;
    public final long weeksAmount, startWeekNumber, endWeekNumber;

    public FinalOTR(LocalDate start, LocalDate end, long weeksAmount, long startWeekNumber, long endWeekNumber) {
        this.start = start;
        this.end = end;
        this.weeksAmount = weeksAmount;
        this.startWeekNumber = startWeekNumber;
        this.endWeekNumber = endWeekNumber;
    }

    @Override
    public LocalDate getStart() {
        return start;
    }

    @Override
    public LocalDate getEnd() {
        return end;
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

    public OTR_Stable toNonFinal() {
        return new OTR_Stable(start, end, weeksAmount, startWeekNumber, endWeekNumber);
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
