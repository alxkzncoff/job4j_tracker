package ru.job4j.collection;

import java.util.Comparator;

public class SortJobNameDesc implements Comparator<Job> {
    @Override
    public int compare(Job first, Job second) {
        return second.getName().compareTo(first.getName());
    }
}
