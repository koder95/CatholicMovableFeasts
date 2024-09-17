package pl.koder95.cmf.format;

import com.google.gson.*;
import pl.koder95.cmf.FinalMFR;

import java.time.LocalDate;
import java.util.*;

/**
 * Klasa zawiera narzędzia dotyczące formatu JSON.
 *
 * @author Kamil Mularski
 * @see Loader
 * @see #loader(String)
 * @see #toJson(List)
 * @see #toJson(List, boolean)
 */
public class JSON {

    private JSON() {}

    /**
     * Zwraca obiekt wczytujący dane zapisane w formacie JSON.
     *
     * @param source dane zapisane w formacie JSON
     * @return obiekt wczytujący
     */
    public static Loader loader(String source) {
        return new Loader(JsonParser.parseString(source));
    }

    /**
     * Klasa przechowuje wczytująca obiekty w formacie JSON i umożliwiająca zwrócenie ich w formie listy obiektów typu {@link FinalMFR}.
     * Stworzenie instancji klasy powinno się odbywać przez wywołanie metody {@link JSON#loader(String)}, ponieważ
     * konstruktor jest zabezpieczony.
     */
    public static class Loader {
        private final JsonElement main;

        private Loader(JsonElement main) {
            this.main = main;
        }

        public List<FinalMFR> load() {
            Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd")
                    .registerTypeAdapter(
                            LocalDate.class,
                            (JsonDeserializer<LocalDate>) (json, type, context) -> LocalDate.parse(json.getAsJsonPrimitive().getAsString())
                    ).create();
            if (main.isJsonNull()) {
                return Collections.emptyList();
            } else if (main.isJsonObject()) {
                return Collections.singletonList(toMFR(g, main.getAsJsonObject()));
            } else if (main.isJsonArray()) {
                List<FinalMFR> loaded = new LinkedList<>();
                JsonArray array = (JsonArray) main;
                for (JsonElement je : array) {
                    if (je.isJsonObject())
                        loaded.add(toMFR(g, je.getAsJsonObject()));
                }
                ArrayList<FinalMFR> result = new ArrayList<>(loaded);
                loaded.clear();
                return result;
            }
            return null;
        }

        private FinalMFR toMFR(Gson g, JsonObject jo) {
            FinalMFR mfr = g.fromJson(jo, FinalMFR.class);
            if (mfr.firstPartOfOrdinaryTime == null || mfr.secondPartOfOrdinaryTime == null) {
                mfr = mfr.toNonFinal().toBasic().toStable().toFinal();
            }
            return mfr;
        }
    }

    /**
     * Zapisuje listę {@link FinalMFR} do formatu JSON.
     * @param mfrs lista obiektów do zapisania
     * @return dane zapisane w formacie JSON
     */
    public static String toJson(List<FinalMFR> mfrs) {
        return toJson(mfrs, false);
    }

    /**
     * Zapisuje listę {@link FinalMFR} do formatu JSON określając sposób zapisu.
     * @param mfrs lista obiektów do zapisania
     * @param prettyPrinting jeśli {@code true} wtedy zapis jest przyjazny dla człowieka, w przeciwnym razie – zapis pozbawiony zbędnych znaków
     * @return dane zapisane w formacie JSON
     */
    public static String toJson(List<FinalMFR> mfrs, boolean prettyPrinting) {
        GsonBuilder builder = new GsonBuilder();
        if (prettyPrinting) builder = builder.setPrettyPrinting();
        Gson g = builder.setDateFormat("yyyy-MM-dd").registerTypeAdapter(
                LocalDate.class,
                (JsonSerializer<LocalDate>) (src, typeOfSrc, context) -> new JsonPrimitive(src.toString())
        ).create();
        return g.toJson(mfrs);
    }
}
