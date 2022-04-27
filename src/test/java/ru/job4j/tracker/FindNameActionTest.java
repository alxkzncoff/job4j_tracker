package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindNameActionTest {

    @Test
    public void whenFindSuccess() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        String name = "New item";
        Item item = new Item(name);
        tracker.add(item);
        FindNameAction find = new FindNameAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(name);

        find.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find items by name ====" + ln + item + ln));
        assertThat(tracker.findAll().get(0), is(item));
    }

    @Test
    public void whenFindFail() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        String name = "Item";
        tracker.add(new Item("New item"));
        FindNameAction find = new FindNameAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(name);

        find.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find items by name ====" + ln
                + "Заявки с именем: " + name + " не найдены." + ln));
    }
}