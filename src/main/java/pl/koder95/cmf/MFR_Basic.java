package pl.koder95.cmf;

import pl.koder95.cmf.core.OrdinaryTimeRow;

import java.time.LocalDate;

public class MFR_Basic extends MFR_Abstract {

    private int year;

    public MFR_Basic(int year) {
        setYear(year);
    }

    @Override
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year == 0? LocalDate.now().getYear() : year;
    }

    @Override
    public OTR_Basic getFirstPartOfOrdinaryTime() {
        OrdinaryTimeRow old = super.getFirstPartOfOrdinaryTime();
        return new OTR_Basic(old.getStart(), old.getEnd());
    }

    @Override
    public OTR_Basic getSecondPartOfOrdinaryTime() {
        OrdinaryTimeRow old = super.getSecondPartOfOrdinaryTime();
        return new OTR_Basic(old.getStart(), old.getEnd());
    }

    public MFR_Stable toStable() {
        return new MFR_Stable(this.year, getSolemnitiesCycle(), getNormalCycle(), getAshWednesday(), getEasterDay(),
                getAscensionDay(), getPentecost(), getFeastOfCorpusChristi(), getFirstSundayOfAdvent(),
                getFirstPartOfOrdinaryTime().toStable(), getSecondPartOfOrdinaryTime().toStable());
    }
}
