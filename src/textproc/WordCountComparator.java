package textproc;

import java.util.Comparator;
import java.util.Map;

public class WordCountComparator implements Comparator<Map.Entry<String, Integer>> {


    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        if (o1.getValue() > o2.getValue()) {
            return -1;
        } else if (o1.getValue() < o2.getValue()) {
            return 1;
        } else {
            return 0;
        }

       /* if (o1.getKey().toCharArray()[0] > o2.getKey().toCharArray()[0]) {
            return -1;
        } else if (o1.getKey().toCharArray()[0] < o2.getKey().toCharArray()[0]) {
            return 1;
        } else {
            return 0;
        }*/
    }
}
