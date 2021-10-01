package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void itemSortByIdAsc() {
        List<Item> items = Arrays.asList(
                new Item("Bug", 4),
                new Item("Fix", 2),
                new Item("Issue", 3),
                new Item("Patch", 1)
        );
        List<Item> expected = Arrays.asList(
                new Item("Patch", 1),
                new Item("Fix", 2),
                new Item("Issue", 3),
                new Item("Bug", 4));
        Collections.sort(items);
        assertThat(items, is(expected));
    }

    @Test
    public void itemSortByIdDesc() {
        List<Item> items = Arrays.asList(
                new Item("Bug", 4),
                new Item("Fix", 2),
                new Item("Issue", 3),
                new Item("Patch", 1)
        );
        List<Item> expected = Arrays.asList(
                new Item("Bug", 4),
                new Item("Issue", 3),
                new Item("Fix", 2),
                new Item("Patch", 1));
        Collections.sort(items, new SortItemIdDesc());
        assertThat(items, is(expected));
    }

    @Test
    public void itemSortByNameAsc() {
        List<Item> items = Arrays.asList(
                new Item("CCC", 1),
                new Item("DDD", 2),
                new Item("WWW", 3),
                new Item("AAA", 0)
        );
        List<Item> expected = Arrays.asList(
                new Item("AAA", 0),
                new Item("CCC", 1),
                new Item("DDD", 2),
                new Item("WWW", 3));
        Collections.sort(items, new SortItemNameAsc());
        assertThat(items, is(expected));
    }

    @Test
    public void itemSortByNameDesc() {
        List<Item> items = Arrays.asList(
                new Item("CCC", 1),
                new Item("DDD", 2),
                new Item("WWW", 3),
                new Item("AAA", 0)
        );
        List<Item> expected = Arrays.asList(
                new Item("WWW", 3),
                new Item("DDD", 2),
                new Item("CCC", 1),
                new Item("AAA", 0));
        Collections.sort(items, new SortItemNameDesc());
        assertThat(items, is(expected));
    }

}