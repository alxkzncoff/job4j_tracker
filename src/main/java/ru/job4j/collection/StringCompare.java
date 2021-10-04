package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int rsl = 0;
        int index = 0;
        while (index < left.length() && index < right.length()) {
            if (left.charAt(index) != right.charAt(index)) {
                rsl = Character.compare(left.charAt(index), right.charAt(index));
                break;
            } else if (index == left.length() - 1 || index == right.length() - 1) {
                rsl = Integer.compare(left.length(), right.length());
                break;
            }
            index++;
        }
        return rsl;
    }
}
