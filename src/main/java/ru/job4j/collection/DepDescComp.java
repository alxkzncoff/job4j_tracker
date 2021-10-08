package ru.job4j.collection;

import java.util.Comparator;

/**
 * Компаратор сравнивает первые элементы по убыванию,
 * если они равны, то сравнивает последующие элементы,
 * но в возрастающем порядке.
 * @author Aleksandr Kuznetsov
 * @version 1.0
 */
public class DepDescComp implements Comparator<String> {
    public int compare(String o1, String o2) {
        int result = o2.split("/")[0].compareTo(o1.split("/")[0]);
        return result == 0 ? o1.compareTo(o2) : result;
    }
}