package pl.koder95.cmf;

import pl.koder95.cmf.core.Cycle;

import java.time.LocalDate;

public class MFR_Stable extends MFR_Basic {

    private Cycle.Solemnities solemnitiesCycle;
    private Cycle.Normal normalCycle;
    private LocalDate ashWednesday, easterDay, ascensionDay, pentecost, feastOfCorpusChristi, firstSundayOfAdvent;
    private OTR_Stable firstPartOfOrdinaryTime, secondPartOfOrdinaryTime;

    public MFR_Stable(int year, Cycle.Solemnities solemnitiesCycle, Cycle.Normal normalCycle, LocalDate ashWednesday,
                      LocalDate easterDay, LocalDate ascensionDay, LocalDate pentecost,
                      LocalDate feastOfCorpusChristi, LocalDate firstSundayOfAdvent, OTR_Stable firstPartOfOrdinaryTime,
                      OTR_Stable secondPartOfOrdinaryTime) {
        super(year);
        this.solemnitiesCycle = solemnitiesCycle;
        this.normalCycle = normalCycle;
        this.ashWednesday = ashWednesday;
        this.easterDay = easterDay;
        this.ascensionDay = ascensionDay;
        this.pentecost = pentecost;
        this.feastOfCorpusChristi = feastOfCorpusChristi;
        this.firstSundayOfAdvent = firstSundayOfAdvent;
        this.firstPartOfOrdinaryTime = firstPartOfOrdinaryTime;
        this.secondPartOfOrdinaryTime = secondPartOfOrdinaryTime;
    }

    @Override
    public Cycle.Solemnities getSolemnitiesCycle() {
        return solemnitiesCycle;
    }

    @Override
    public Cycle.Normal getNormalCycle() {
        return normalCycle;
    }

    @Override
    public LocalDate getAshWednesday() {
        return ashWednesday;
    }

    @Override
    public LocalDate getEasterDay() {
        return easterDay;
    }

    @Override
    public LocalDate getAscensionDay() {
        return ascensionDay;
    }

    @Override
    public LocalDate getPentecost() {
        return pentecost;
    }

    @Override
    public LocalDate getFeastOfCorpusChristi() {
        return feastOfCorpusChristi;
    }

    @Override
    public LocalDate getFirstSundayOfAdvent() {
        return firstSundayOfAdvent;
    }

    @Override
    public OTR_Stable getFirstPartOfOrdinaryTime() {
        return firstPartOfOrdinaryTime;
    }

    @Override
    public OTR_Stable getSecondPartOfOrdinaryTime() {
        return secondPartOfOrdinaryTime;
    }

    public void setSolemnitiesCycle(Cycle.Solemnities solemnitiesCycle) {
        this.solemnitiesCycle = solemnitiesCycle;
    }

    public void setNormalCycle(Cycle.Normal normalCycle) {
        this.normalCycle = normalCycle;
    }

    public void setAshWednesday(LocalDate ashWednesday) {
        this.ashWednesday = ashWednesday;
    }

    public void setEasterDay(LocalDate easterDay) {
        this.easterDay = easterDay;
    }

    public void setAscensionDay(LocalDate ascensionDay) {
        this.ascensionDay = ascensionDay;
    }

    public void setPentecost(LocalDate pentecost) {
        this.pentecost = pentecost;
    }

    public void setFeastOfCorpusChristi(LocalDate feastOfCorpusChristi) {
        this.feastOfCorpusChristi = feastOfCorpusChristi;
    }

    public void setFirstSundayOfAdvent(LocalDate firstSundayOfAdvent) {
        this.firstSundayOfAdvent = firstSundayOfAdvent;
    }

    public void resetSolemnitiesCycle() {
        setSolemnitiesCycle(super.getSolemnitiesCycle());
    }

    public void resetNormalCycle() {
        setNormalCycle(super.getNormalCycle());
    }

    public void resetAshWednesday() {
        setAshWednesday(super.getAshWednesday());
    }

    public void resetEasterDay() {
        setEasterDay(super.getEasterDay());
    }

    public void resetAscensionDay() {
        setAscensionDay(super.getAscensionDay());
    }

    public void resetPentecost() {
        setPentecost(super.getPentecost());
    }

    public void resetFeastOfCorpusChristi() {
        setFeastOfCorpusChristi(super.getFeastOfCorpusChristi());
    }

    public void resetFirstSundayOfAdvent() {
        setFirstSundayOfAdvent(super.getFirstSundayOfAdvent());
    }

    public void setFirstPartOfOrdinaryTime(OTR_Stable firstPartOfOrdinaryTime) {
        this.firstPartOfOrdinaryTime = firstPartOfOrdinaryTime;
    }

    public void setSecondPartOfOrdinaryTime(OTR_Stable secondPartOfOrdinaryTime) {
        this.secondPartOfOrdinaryTime = secondPartOfOrdinaryTime;
    }

    public void resetFirstPartOfOrdinaryTime() {
        setFirstPartOfOrdinaryTime(super.getFirstPartOfOrdinaryTime().toStable());
    }

    public void resetSecondPartOfOrdinaryTime() {
        setSecondPartOfOrdinaryTime(super.getSecondPartOfOrdinaryTime().toStable());
    }

    public void resetPartsOfOrdinaryTime() {
        resetFirstPartOfOrdinaryTime();
        resetSecondPartOfOrdinaryTime();
    }

    public void validate() {
        resetNormalCycle();
        resetSolemnitiesCycle();
        resetPartsOfOrdinaryTime();
    }

    public FinalMFR toFinal() {
        OTR_Stable fpot = getFirstPartOfOrdinaryTime();
        OTR_Stable spot = getSecondPartOfOrdinaryTime();
        return new FinalMFR(getYear(), getSolemnitiesCycle(), getNormalCycle(), getAshWednesday(), getEasterDay(),
                getAscensionDay(), getPentecost(), getFeastOfCorpusChristi(), getFirstSundayOfAdvent(),
                fpot == null? null : fpot.toFinal(), spot == null? null : spot.toFinal());
    }

    @Override
    public String toString() {
        return "{" +
                "solemnitiesCycle=" + solemnitiesCycle +
                ", normalCycle=" + normalCycle +
                ", ashWednesday=" + ashWednesday +
                ", easterDay=" + easterDay +
                ", ascensionDay=" + ascensionDay +
                ", pentecost=" + pentecost +
                ", feastOfCorpusChristi=" + feastOfCorpusChristi +
                ", firstSundayOfAdvent=" + firstSundayOfAdvent +
                ", firstPartOfOrdinaryTime=" + firstPartOfOrdinaryTime +
                ", secondPartOfOrdinaryTime=" + secondPartOfOrdinaryTime +
                '}';
    }
}
