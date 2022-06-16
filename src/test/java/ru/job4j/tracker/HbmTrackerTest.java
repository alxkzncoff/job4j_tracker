package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HbmTrackerTest {

    @Test
    public void whenFindAll() {
        HbmTracker store = new HbmTracker();
        Item item1 = new Item("item1", "description1");
        Item item2 = new Item("item2", "description2");
        store.add(item1);
        store.add(item2);
        assertThat(store.findAll().size(), is(2));
    }

    @Test
    public void whenFindById() {
        HbmTracker store = new HbmTracker();
        Item item = new Item("item", "description");
        store.add(item);
        assertThat(store.findById(item.getId()).getName(), is(item.getName()));
    }

    @Test
    public void whenReplace() {
        HbmTracker store = new HbmTracker();
        Item item = new Item("item", "description");
        Item repItem = new Item("new item", "new description");
        store.add(item);
        store.replace(item.getId(), repItem);
        assertThat(store.findById(item.getId()).getName(), is(repItem.getName()));
    }

    @Test
    public void whenDelete() {
        HbmTracker store = new HbmTracker();
        Item item = new Item("item", "description");
        store.add(item);
        store.delete(item.getId());
        assertThat(store.findAll().size(), is(0));
    }
}