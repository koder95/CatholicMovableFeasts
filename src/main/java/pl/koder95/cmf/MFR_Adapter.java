package pl.koder95.cmf;

import pl.koder95.cmf.core.Cycle;
import pl.koder95.cmf.core.MovableFeastsRow;

import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Stream;

public class MFR_Adapter extends MFR_Abstract {

    private final MovableFeastsRow mfr;

    public MFR_Adapter(MovableFeastsRow mfr) {
        this.mfr = Objects.requireNonNull(mfr);
    }

    @Override
    public int getYear() {
        return mfr.getYear();
    }

    @Override
    public Cycle.Solemnities getSolemnitiesCycle() {
        return mfr.getSolemnitiesCycle();
    }

    @Override
    public Cycle.Normal getNormalCycle() {
        return mfr.getNormalCycle();
    }

    @Override
    public LocalDate getAshWednesday() {
        return mfr.getAshWednesday();
    }

    @Override
    public LocalDate getEasterDay() {
        return mfr.getEasterDay();
    }

    @Override
    public LocalDate getAscensionDay() {
        return mfr.getAscensionDay();
    }

    @Override
    public LocalDate getPentecost() {
        return mfr.getPentecost();
    }

    @Override
    public LocalDate getFeastOfCorpusChristi() {
        return mfr.getFeastOfCorpusChristi();
    }

    @Override
    public OTR_Abstract getFirstPartOfOrdinaryTime() {
        return new OTR_Adapter(mfr.getFirstPartOfOrdinaryTime());
    }

    @Override
    public OTR_Abstract getSecondPartOfOrdinaryTime() {
        return new OTR_Adapter(mfr.getSecondPartOfOrdinaryTime());
    }

    @Override
    public LocalDate getFirstSundayOfAdvent() {
        return mfr.getFirstSundayOfAdvent();
    }

    @Override
    public Stream<?> stream() {
        return mfr.stream();
    }
}
