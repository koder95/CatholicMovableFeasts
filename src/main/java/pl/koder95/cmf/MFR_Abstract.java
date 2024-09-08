package pl.koder95.cmf;

import pl.koder95.cmf.core.MovableFeastsRow;

public abstract class MFR_Abstract implements MovableFeastsRow {

    public MFR_Basic toBasic() {
        return new MFR_Basic(getYear());
    }

    @Override
    public OTR_Abstract getSecondPartOfOrdinaryTime() {
        return new OTR_Adapter(MovableFeastsRow.super.getSecondPartOfOrdinaryTime());
    }

    @Override
    public OTR_Abstract getFirstPartOfOrdinaryTime() {
        return new OTR_Adapter(MovableFeastsRow.super.getFirstPartOfOrdinaryTime());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "year=" + getYear() +
                ", solemnitiesCycle=" + getSolemnitiesCycle() +
                ", normalCycle=" + getNormalCycle() +
                ", ashWednesday=" + getAshWednesday() +
                ", easterDay=" + getEasterDay() +
                ", ascensionDay=" + getAscensionDay() +
                ", pentecost=" + getPentecost() +
                ", feastOfCorpusChristi=" + getFeastOfCorpusChristi() +
                ", firstSundayOfAdvent=" + getFirstSundayOfAdvent() +
                ", firstPartOfOrdinaryTime=" + getFirstPartOfOrdinaryTime() +
                ", secondPartOfOrdinaryTime=" + getSecondPartOfOrdinaryTime() +
                '}';
    }
}
