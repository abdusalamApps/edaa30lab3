package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Holgersson {

    public static final String[] REGIONS = {"blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
            "halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
            "södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
            "öland", "östergötland"};

    public static void main(String[] args) throws FileNotFoundException {
        List<Long> times = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            long t0 = System.nanoTime();
            List<TextProcessor> textProcessors = new ArrayList<>();
            textProcessors.add(new SingleWordCounter("nils"));
            textProcessors.add(new SingleWordCounter("norge"));
            textProcessors.add(new MultiWordCounter(REGIONS));

            Scanner scanner = new Scanner(new File("undantagsord.txt"));
            scanner.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
            Set<String> stopWords = new HashSet<>();
            while (scanner.hasNext()) {
                stopWords.add(scanner.next().toLowerCase());
            }
            textProcessors.add(new GeneralWordCounter(stopWords));

            Scanner s = null;
            for (TextProcessor textProcessor : textProcessors) {
                s = new Scanner(new File("nilsholg.txt"));
                s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

                while (s.hasNext()) {
                    String word = s.next().toLowerCase();

                    textProcessor.process(word);
                }
//                textProcessor.report();
            }
            s.close();

            long t1 = System.nanoTime();

//            System.out.println("tid " + (i + 1) + ": " + (t1 - t0) + " ns");
            times.add(t1 - t0);
        }

        double sum = 0;
        try {
            FileWriter fileWriter = new FileWriter("treemap_average.txt");
            for (int i = 0; i < times.size(); i++) {
                fileWriter.append(String.valueOf(i + 1)).append(". ").append(String.valueOf(times.get(i))).append(" ns").append("\n");
                sum += times.get(i);
            }
            fileWriter.append("Average execution time is ").append(String.valueOf((sum/1000000) / times.size())).append(" ms");
            fileWriter.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}