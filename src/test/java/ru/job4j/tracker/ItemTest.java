package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void itemSortAsc() {
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
        Collections.sort(items, new SortItemAsc());
        assertEquals(expected, items);
    }

    @Test
    public void itemSortDesc() {
        List<Item> items = Arrays.asList(
                new Item("Fix", 2),
                new Item("Patch", 1),
                new Item("Issue", 3),
                new Item("Bug", 4)
        );
        List<Item> expected = Arrays.asList(
                new Item("Bug", 4),
                new Item("Issue", 3),
                new Item("Fix", 2),
                new Item("Patch", 1));
        Collections.sort(items, new SortItemDesc());
        assertEquals(expected, items);
    }
}