package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    public int compare(String o1, String o2) {
        int result = o2.split("/")[0].compareTo(o1.split("/")[0]);
        if (result == 0) {
            return o1.compareTo(o2);
        }
        return result;
    }
}