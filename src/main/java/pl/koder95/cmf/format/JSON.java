package pl.koder95.cmf.format;

import com.google.gson.*;
import pl.koder95.cmf.FinalMFR;

import java.time.LocalDate;
import java.util.*;

public class JSON {

    private JSON() {}

    public static Loader loader(String source) {
        return new Loader(JsonParser.parseString(source));
    }

    public static class Loader {
        private final JsonElement main;

        public Loader(JsonElement main) {
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

    public static String toJson(List<FinalMFR> mfrs) {
        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").registerTypeAdapter(
                LocalDate.class,
                (JsonSerializer<LocalDate>) (src, typeOfSrc, context) -> new JsonPrimitive(src.toString())
        ).create();
        return g.toJson(mfrs);
    }
}
