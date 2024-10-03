package pl.koder95.cmf;

import org.apache.commons.cli.*;
import pl.koder95.cmf.format.JSON;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private Main() {}

    public static void main(String[] args) {
        System.out.println("Catholic Moveable Feasts by Kamil Jan Mularski [Koder95]. See more: https://koder95.pl/");
        System.out.println("-   It is starting with arguments: " + Arrays.deepToString(args));
        System.out.println();

        Options options = new Options();
        options.addOption("h", "help", false, "wyświetla pomoc programu");
        options.addOption("d", "dir", true, "określa katalog dla zapisania pliku");
        options.addOption("n", "filename", true, "określa nazwę pliku");
        options.addOption(Option.builder("f")
                .longOpt("format")
                .hasArg()
                .desc("określa format danych wyjściowych")
                .build());
        options.addOption(Option.builder("y")
                .longOpt("years")
                .hasArg()
                .type(int[].class)
                .converter(string -> {
                    System.out.println("Converting: " + string);
                    String digits = "(\\d)+";
                    if (string.matches(digits)) {
                        return new int[] { Integer.parseInt(string) };
                    }
                    int dash = string.indexOf('-');
                    if (dash > 0) {
                        int begin = Integer.parseInt(string.substring(0, dash));
                        int end = Integer.parseInt(string.substring(dash + 1));
                        return IntStream.rangeClosed(begin, end).toArray();
                    } else {
                        String[] years = string.split("\\.");
                        System.out.println("Parts: " + Arrays.deepToString(years));
                        return Arrays.stream(years).filter(s -> s.matches(digits)).mapToInt(Integer::parseInt).toArray();
                    }
                })
                .build());
        CommandLineParser parser = new DefaultParser(false);
        try {
            CommandLine cl = parser.parse(options, args);
            if (cl.hasOption("h")) {
                HelpFormatter hf = new HelpFormatter();
                hf.printHelp("cmf", options);
            } else {
                String dir = cl.hasOption("dir")? cl.getOptionValue("dir") : "./";
                String filename = cl.hasOption("filename")? cl.getOptionValue("filename") : defaultFilename();
                String format = cl.hasOption("format")? cl.getOptionValue("format") : "json";
                int[] years = cl.hasOption("years")? cl.getParsedOptionValue("years") : new int[] { LocalDate.now().getYear() };
                List<FinalMFR> result = generate(years);
                save(result, dir, filename, format);
            }
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String defaultFilename() {
        LocalDateTime now = LocalDateTime.now();
        String formatted = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String adapted = formatted.replace('T', '_').replace(":", "");
        String clipped = adapted.substring(0, adapted.indexOf('.'));
        return clipped + ".cmf";
    }

    private static List<FinalMFR> generate(int... years) {
        System.out.println("Generating for years: " + Arrays.toString(years));
        return IntStream.of(years).sorted().mapToObj(MFR_Basic::new).map(MFR_Basic::toStable).map(MFR_Stable::toFinal).collect(Collectors.toList());
    }

    private static void save(List<FinalMFR> list, String dir, String filename, String format) throws IOException {
        if (format.endsWith("json")) {
            String json = JSON.toJson(list, format.replace("json", "").equals("pretty"));
            Path destin = Paths.get(dir, filename + ".json");
            Files.write(destin, Collections.singleton(json), StandardCharsets.UTF_8);
        }
    }
}
