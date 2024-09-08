package pl.koder95.cmf;

import java.time.LocalDate;

public class OTR_Basic extends OTR_Abstract {
    private LocalDate start, end;

    public OTR_Basic(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            if (start == null && end != null) {
                start = new MFR_Basic(end.getYear()).getFirstPartOfOrdinaryTime().getStart();
            } else if (start != null) {
                end = new MFR_Basic(start.getYear()).getSecondPartOfOrdinaryTime().getEnd();
            } else {
                MFR_Basic tmp = new MFR_Basic(LocalDate.now().getYear());
                start = tmp.getFirstPartOfOrdinaryTime().getStart();
                end = tmp.getSecondPartOfOrdinaryTime().getEnd();
            }
        }
        setPeriod(start, end);
    }

    @Override
    public LocalDate getStart() {
        return start;
    }

    @Override
    public LocalDate getEnd() {
        return end;
    }

    public void setStart(LocalDate start) {
        if (this.end != null && start.getYear() != this.end.getYear()) {
            start = LocalDate.ofYearDay(this.end.getYear(), 1);
        }
        if (this.start == null) {
            this.start = start;
        } else {
            if (start == null) {
                start = LocalDate.now();
            }
            this.start = start.withYear(this.start.getYear());
        }
    }

    public void setEnd(LocalDate end) {
        if (this.start != null && end.getYear() != this.start.getYear()) {
            end = LocalDate.ofYearDay(this.start.getYear(), 366);
        }
        if (this.end == null) {
            this.end = end;
        } else {
            if (end == null) {
                end = getStart().plusDays(1);
            } else if (end.isBefore(this.start)) {
                this.start = null;
                this.end = null;
                return;
            }
            this.end = end.withYear(this.end.getYear());
        }
    }

    public void setPeriod(LocalDate start, LocalDate end) {
        setStart(start);
        setEnd(end);
    }

    private void clear() {
        LocalDate date = LocalDate.now().withMonth(1);
        setPeriod(date.plusDays(1), date);
    }

    public void setYear(int year) {
        LocalDate start = this.start, end = this.end;
        clear();
        if (start != null && end != null) {
            setPeriod(start.withYear(year), end.withYear(year));
        } else {
            throw new IllegalStateException("Cannot set year when field 'start' or 'end' is null.");
        }
    }

    public OTR_Stable toStable() {
        return new OTR_Stable(start, end, getWeeksAmount(), getStartWeekNumber(), getEndWeekNumber());
    }

    @Override
    public String toString() {
        return "OTR_Basic{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
