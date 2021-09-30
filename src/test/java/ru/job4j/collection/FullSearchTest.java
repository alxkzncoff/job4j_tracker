package ru.job4j.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FullSearchTest {

    @Test
    public void extractNumber() {
        List<Task> tasks = Arrays.asList(
                new Task("1", "First desc"),
                new Task("2", "Second desc"),
                new Task("1", "First desc")
        );
        Set<String> expected = new HashSet<>(Arrays.asList("1", "2"));
        assertThat(FullSearch.extractNumber(tasks), is(expected));
    }

    @Test
    public void extractNumberAllDuplicate() {
        List<Task> tasks = Arrays.asList(
                new Task("1", "Fix"),
                new Task("1", "Fix"),
                new Task("1", "Fix")
        );
        Set<String> expected = new HashSet<>(Arrays.asList("1"));
        assertThat(FullSearch.extractNumber(tasks), is(expected));
    }

    @Test
    public void extractNumberUnique() {
        List<Task> tasks = Arrays.asList(
                new Task("1", "Fix"),
                new Task("2", "Issue"),
                new Task("3", "Bug")
        );
        Set<String> expected = new HashSet<>(Arrays.asList("1", "2", "3"));
        assertThat(FullSearch.extractNumber(tasks), is(expected));
    }
}