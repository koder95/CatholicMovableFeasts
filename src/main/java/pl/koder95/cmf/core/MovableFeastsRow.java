package pl.koder95.cmf.core;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.Stream;

/**
 * Interfejs definiujący wiersz <b>Tabeli świąt ruchomych</b>. Wszystkie metody interfejsu mają
 * zdefiniowaną domyślną implementację oprócz metody {@link #getYear() }, z której korzysta każda z nich
 * pośrednio albo bezpośrednio.
 *
 * @author Kamil Jan Mularski
 * @see <a href="https://brewiarz.pl/czytelnia/rok_ruchome.php3">Tabela świąt ruchomych</a>
 */
public interface MovableFeastsRow {

    int getYear();

    default Cycle.Solemnities getSolemnitiesCycle() {
        return Cycle.solemnities(getYear());
    }

    default Cycle.Normal getNormalCycle() {
        return Cycle.normal(getYear());
    }

    default LocalDate getAshWednesday() {
        return getEasterDay().minusWeeks(6).minusDays(4);
    }

    default LocalDate getEasterDay() {
        int
                year = getYear(),
                a = year % 19,
                b = year / 100,
                c = year % 100,
                d = b / 4,
                e = b % 4,
                f = (b + 8) / 25,
                g = (b - f + 1) / 3,
                h = (19 * a + b - d - g + 15) % 30,
                i = c / 4,
                k = c % 4,
                l = (32 + 2 * e + 2 * i - h - k) % 7,
                m = (a + 11 * h + 22 * l) / 451,
                n = h + l - 7 * m + 114;
        return LocalDate.of(year, n / 31, (n % 31) + 1);
    }

    default LocalDate getAscensionDay() {
        return getEasterDay().plusDays(39);
    }

    default LocalDate getPentecost() {
        return getEasterDay().plusWeeks(7);
    }

    default LocalDate getFeastOfCorpusChristi() {
        return getPentecost().plusWeeks(1).plusDays(4);
    }

    default OrdinaryTimeRow getFirstPartOfOrdinaryTime() {
        return OrdinaryTimeRow.create(LocalDate.of(getYear(), Month.JANUARY, 6)
                .with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).plusDays(1), getAshWednesday().minusDays(1));
    }

    default OrdinaryTimeRow getSecondPartOfOrdinaryTime() {
        return OrdinaryTimeRow.create(getPentecost().plusDays(1), getFirstSundayOfAdvent().minusWeeks(1));
    }

    default LocalDate getFirstSundayOfAdvent() {
        return LocalDate.of(getYear(), Month.DECEMBER, 25).with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).minusWeeks(3);
    }

    default Stream<?> stream() {
        return Stream.of(getYear(), getSolemnitiesCycle(), getNormalCycle(), getAshWednesday(), getEasterDay(),
                getAscensionDay(), getPentecost(), getFeastOfCorpusChristi(),
                getFirstPartOfOrdinaryTime().getStart(), getFirstPartOfOrdinaryTime().getStartWeekNumber(), getFirstPartOfOrdinaryTime().getEnd(), getFirstPartOfOrdinaryTime().getEndWeekNumber(),
                getSecondPartOfOrdinaryTime().getStart(), getSecondPartOfOrdinaryTime().getStartWeekNumber(), getSecondPartOfOrdinaryTime().getEnd(), getSecondPartOfOrdinaryTime().getEndWeekNumber(),
                getFirstSundayOfAdvent());
    }
}
