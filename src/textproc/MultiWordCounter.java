package textproc;

import java.util.Map;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor {

    private Map<String, Integer> map = new TreeMap<>();

    public MultiWordCounter(String[] words) {

        for (String word : words) {
            map.put(word, 0);
        }

    }

    @Override
    public void process(String w) {
        if (map.containsKey(w)) {
            Integer integer = map.get(w);
            integer++;
            map.put(w, integer);
        }
    }

    @Override
    public void report() {
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
    }
}
