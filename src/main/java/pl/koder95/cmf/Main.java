package pl.koder95.cmf;

import org.apache.commons.cli.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Catholic Moveable Feasts by Kamil Jan Mularski [Koder95]. See more: https://koder95.pl/");
        System.out.println("—   It is starting with arguments: " + Arrays.deepToString(args));
        System.out.println();

        Options options = new Options();
        options.addOption("h", "help", false, "wyświetla pomoc programu");
        CommandLineParser parser = new DefaultParser(false);
        try {
            CommandLine cl = parser.parse(options, args);
            if (cl.hasOption("h")) {
                HelpFormatter hf = new HelpFormatter();
                hf.printHelp("cmf", options);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
