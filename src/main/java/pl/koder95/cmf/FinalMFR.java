package pl.koder95.cmf;

import pl.koder95.cmf.core.Cycle;
import pl.koder95.cmf.core.MovableFeastsRow;

import java.time.LocalDate;
import java.util.stream.Stream;

public final class FinalMFR implements MovableFeastsRow {

    public final int year;
    public final Cycle.Solemnities solemnitiesCycle;
    public final Cycle.Normal normalCycle;
    public final LocalDate ashWednesday, easterDay, ascensionDay, pentecost, feastOfCorpusChristi, firstSundayOfAdvent;
    public final FinalOTR firstPartOfOrdinaryTime, secondPartOfOrdinaryTime;

    public FinalMFR(int year, Cycle.Solemnities solemnitiesCycle, Cycle.Normal normalCycle,
                    LocalDate ashWednesday, LocalDate easterDay, LocalDate ascensionDay, LocalDate pentecost,
                    LocalDate feastOfCorpusChristi, LocalDate firstSundayOfAdvent, FinalOTR firstPartOfOrdinaryTime,
                    FinalOTR secondPartOfOrdinaryTime) {
        this.year = year;
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
    public int getYear() {
        return year;
    }

    @Override
    public Stream<?> stream() {
        return MovableFeastsRow.super.stream();
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
    public FinalOTR getFirstPartOfOrdinaryTime() {
        return firstPartOfOrdinaryTime;
    }

    @Override
    public FinalOTR getSecondPartOfOrdinaryTime() {
        return secondPartOfOrdinaryTime;
    }

    public MFR_Stable toNonFinal() {
        return new MFR_Stable(year, solemnitiesCycle, normalCycle, ashWednesday, easterDay, ascensionDay, pentecost,
                feastOfCorpusChristi, firstSundayOfAdvent,
                firstPartOfOrdinaryTime == null? null : firstPartOfOrdinaryTime.toNonFinal(),
                secondPartOfOrdinaryTime == null? null : secondPartOfOrdinaryTime.toNonFinal());
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
