package edu.hw3.task7;

import java.util.Comparator;

public class TreeMapComparator implements Comparator<String> {
    @Override
    public int compare(String string1, String string2) {
        if (string1 == null && string2 == null) {
            return 0;
        } else if (string1 == null) {
            return -1;
        } else if (string2 == null) {
            return 1;
        }
        return string1.compareTo(string2);
    }
}
