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

    /**
     * @return rok, dla którego wyznaczane są daty
     */
    int getYear();

    /**
     * @return cykl uroczystości dla czytań (rok A, B albo C)
     */
    default Cycle.Solemnities getSolemnitiesCycle() {
        return Cycle.solemnities(getYear());
    }

    /**
     * @return cykl czytań w ferie (rok I albo II)
     */
    default Cycle.Normal getNormalCycle() {
        return Cycle.normal(getYear());
    }

    /**
     * @return data Środy Popielcowej
     */
    default LocalDate getAshWednesday() {
        return getEasterDay().minusWeeks(6).minusDays(4);
    }

    /**
     * @return data Uroczystości Zmartwychwstania Pańskiego
     */
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

    /**
     * @return data Uroczystości Wniebowstąpienia Pańskiego
     */
    default LocalDate getAscensionDay() {
        return getEasterDay().plusDays(39);
    }

    /**
     * @return data Uroczystości Zesłania Ducha Świętego
     */
    default LocalDate getPentecost() {
        return getEasterDay().plusWeeks(7);
    }

    /**
     * @return data Uroczystości Ciała i Krwi Pańskiej (Boże Ciało)
     */
    default LocalDate getFeastOfCorpusChristi() {
        return getPentecost().plusWeeks(1).plusDays(4);
    }

    /**
     * @return dane dotyczące pierwszej części okresu zwykłego
     */
    default OrdinaryTimeRow getFirstPartOfOrdinaryTime() {
        return OrdinaryTimeRow.create(LocalDate.of(getYear(), Month.JANUARY, 6)
                .with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).plusDays(1), getAshWednesday().minusDays(1));
    }

    /**
     * @return dane dotyczące drugiej części okresu zwykłego
     */
    default OrdinaryTimeRow getSecondPartOfOrdinaryTime() {
        return OrdinaryTimeRow.create(getPentecost().plusDays(1), getFirstSundayOfAdvent().minusWeeks(1));
    }

    /**
     * @return data rozpoczęcia nowego roku liturgicznego (1. niedziela adwentu)
     */
    default LocalDate getFirstSundayOfAdvent() {
        return LocalDate.of(getYear(), Month.DECEMBER, 25).with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).minusWeeks(3);
    }

    /**
     *
     * @return strumień zawierający dane w kolejności wywołania metod:<ol>
     *     <li>{@link MovableFeastsRow#getYear()},</li>
     *     <li>{@link MovableFeastsRow#getSolemnitiesCycle()},</li>
     *     <li>{@link MovableFeastsRow#getNormalCycle()},</li>
     *     <li>{@link MovableFeastsRow#getAshWednesday()},</li>
     *     <li>{@link MovableFeastsRow#getEasterDay()},</li>
     *     <li>{@link MovableFeastsRow#getAscensionDay()},</li>
     *     <li>{@link MovableFeastsRow#getPentecost()},</li>
     *     <li>{@link MovableFeastsRow#getFeastOfCorpusChristi()},</li>
     *     <li>{@link OrdinaryTimeRow#getStart() getFirstPartOfOrdinaryTime().getStart()},</li>
     *     <li>{@link OrdinaryTimeRow#getStartWeekNumber() getFirstPartOfOrdinaryTime().getStartWeekNumber()},</li>
     *     <li>{@link OrdinaryTimeRow#getEnd() getFirstPartOfOrdinaryTime().getEnd()},</li>
     *     <li>{@link OrdinaryTimeRow#getEndWeekNumber() getFirstPartOfOrdinaryTime().getEndWeekNumber()},</li>
     *     <li>{@link OrdinaryTimeRow#getStart() getSecondPartOfOrdinaryTime().getStart()},</li>
     *     <li>{@link OrdinaryTimeRow#getStartWeekNumber() getSecondPartOfOrdinaryTime().getStartWeekNumber()},</li>
     *     <li>{@link OrdinaryTimeRow#getEnd() getSecondPartOfOrdinaryTime().getEnd()},</li>
     *     <li>{@link OrdinaryTimeRow#getEndWeekNumber() getSecondPartOfOrdinaryTime().getEndWeekNumber()},</li>
     *     <li>{@link MovableFeastsRow#getFirstSundayOfAdvent()}.</li></ol>
     */
    default Stream<?> stream() {
        return Stream.of(getYear(), getSolemnitiesCycle(), getNormalCycle(), getAshWednesday(), getEasterDay(),
                getAscensionDay(), getPentecost(), getFeastOfCorpusChristi(),
                getFirstPartOfOrdinaryTime().getStart(), getFirstPartOfOrdinaryTime().getStartWeekNumber(), getFirstPartOfOrdinaryTime().getEnd(), getFirstPartOfOrdinaryTime().getEndWeekNumber(),
                getSecondPartOfOrdinaryTime().getStart(), getSecondPartOfOrdinaryTime().getStartWeekNumber(), getSecondPartOfOrdinaryTime().getEnd(), getSecondPartOfOrdinaryTime().getEndWeekNumber(),
                getFirstSundayOfAdvent());
    }
}
