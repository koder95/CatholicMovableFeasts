package pl.koder95.cmf.core;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 * Interfejs określa wiersz w <b>Tabeli świąt ruchomych</b>, którego dane odnoszą się do <b>okresu zwykłego</b>.
 * Każdy wiersz tabeli zawiera dwa wiersze okresu zwykłego odnoszące się odpowiednio do części I i II okresu
 * zwykłego. Zdefiniowana jest również statyczna metoda ({@link #create(LocalDate, LocalDate)}) tworząca obiekt
 * z domyślnymi implementacjami metod oraz zwracający odpowiednimi metodami wprowadzone daty początku i końca.
 *
 * @author Kamil Jan Mularski
 * @see MovableFeastsRow
 * @see <a href="https://brewiarz.pl/czytelnia/rok_ruchome.php3">Tabela świąt ruchomych</a>
 * @see <a href="https://pl.wikipedia.org/wiki/Okres_zwyk%C5%82y">Okres zwykły - Wikipedia</a>
 * @see <a href="https://brewiarz.pl/czytelnia/rok.php3">Rok liturgiczny - brewiarz.pl</a>
 */
public interface OrdinaryTimeRow {

    /**
     * @return określa datę rozpoczynającą okres zwykły
     */
    LocalDate getStart();

    /**
     * @return określa datę kończącą okres zwykły
     */
    LocalDate getEnd();

    /**
     * @return zwraca liczbę tygodni, która jest między rozpoczęciem a zakończeniem okresu
     */
    default long getWeeksAmount() {
        return ChronoUnit.WEEKS.between(getStart(), getEnd().plusDays(1));
    }

    /**
     * @return zwraca porządkowy numer tygodnia dla daty rozpoczęcia okresu
     */
    default long getStartWeekNumber() {
        return getStart().getMonth() == Month.JANUARY? 1 : getEndWeekNumber() - getWeeksAmount();
    }

    /**
     * @return zwraca porządkowy numer tygodnia dla daty zakończenia okresu
     */
    default long getEndWeekNumber() {
        return getStart().getMonth() == Month.JANUARY? getStartWeekNumber() + getWeeksAmount() : 34;
    }

    /**
     * @param start data rozpoczynająca okres zwykły
     * @param end data kończąca okres zwykły
     * @return obiekt anonimowej klasy, która posiada wprowadzone dane i korzysta z domyślnych metod interfejsu
     */
    static OrdinaryTimeRow create(LocalDate start, LocalDate end) {
        return new OrdinaryTimeRow() {
            @Override
            public LocalDate getStart() {
                return start;
            }

            @Override
            public LocalDate getEnd() {
                return end;
            }
        };
    }
}
