package textproc;

import java.util.*;

public class GeneralWordCounter implements TextProcessor {

    private Map<String, Integer> map = new TreeMap<>();
    private Set<String> stopWords;

    public GeneralWordCounter(Set<String> stopWords) {
        this.stopWords = stopWords;
    }

    @Override
    public void process(String w) {
        if (!stopWords.contains(w)) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }

    }

    @Override
    public void report() {
//        for (String key : map.keySet()) {
//            if (map.get(key) >= 200) {
//                System.out.println(key + ": " + map.get(key));
//            }
//        }
        Set<Map.Entry<String, Integer>> wordSet = map.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
        wordList.sort(new WordCountComparator());

        for (int i = 0; i < 10; i++) {
            System.out.println((i+1) + ": " + wordList.get(i));
        }

    }

    public Set<Map.Entry<String, Integer>> getWords() {
        return map.entrySet();
    }
}
