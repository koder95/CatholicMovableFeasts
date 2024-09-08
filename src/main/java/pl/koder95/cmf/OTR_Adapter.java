package pl.koder95.cmf;

import pl.koder95.cmf.core.OrdinaryTimeRow;

import java.time.LocalDate;

public class OTR_Adapter extends OTR_Abstract {

    private final OrdinaryTimeRow otr;

    public OTR_Adapter(OrdinaryTimeRow otr) {
        this.otr = otr;
    }

    @Override
    public LocalDate getStart() {
        return otr.getStart();
    }

    @Override
    public LocalDate getEnd() {
        return otr.getEnd();
    }

    @Override
    public long getWeeksAmount() {
        return otr.getWeeksAmount();
    }

    @Override
    public long getStartWeekNumber() {
        return otr.getStartWeekNumber();
    }

    @Override
    public long getEndWeekNumber() {
        return otr.getEndWeekNumber();
    }
}
