package pl.koder95.cmf.core;

/**
 * Klasa ostateczna typu Singleton, której instancji nie można utworzyć, ale zawiera dwa typy numeryczne
 * {@link Solemnities } i {@link Normal }. Poza tymi typami posiada dwie statyczne metody, które pozwalają
 * pobrać odpowiedni obiekt dostosowany od wprowadzonego roku.
 *
 * @author Kamil Jan Mularski
 * @see <a href="https://pl.wikipedia.org/wiki/Czytanie_(liturgia)#Katolicyzm">Cykle czytań w liturgii</a>
 */
public final class Cycle {

    /**
     * Typ wyliczeniowy odnoszący się do trzech cykli czytań niedzielnych i uroczystości.
     */
    public enum Solemnities {
        C, A, B;
    }

    /**
     * Typ wyliczeniowy odnoszący się do dwóch cykli czytań dni powszednich (dla ferii).
     */
    public enum Normal {
        II, I;
    }

    /**
     * @param year rok
     * @return zwraca cykl czytań w niedziele i uroczystości dla podanego roku
     */
    public static Solemnities solemnities(int year) {
        return Solemnities.values()[year % 3];
    }

    /**
     * @param year rok
     * @return zwraca cykl czytań w ferie dla podanego roku
     */
    public static Normal normal(int year) {
        return Normal.values()[year % 2];
    }

    private Cycle() {}
}
