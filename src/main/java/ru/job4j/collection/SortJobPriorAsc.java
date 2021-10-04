package ru.job4j.collection;

import java.util.Comparator;

public class SortJobPriorAsc implements Comparator<Job> {
    public int compare(Job first, Job second) {
        return Integer.compare(first.getPriority(), second.getPriority());
    }
}
