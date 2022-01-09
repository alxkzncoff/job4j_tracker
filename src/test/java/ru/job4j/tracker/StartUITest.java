package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

import java.util.List;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        Output out = new StubOutput();
        Input in = new StubInput(new String[] {"0", "Item name", "1"});
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = List.of(
                new CreateAction(out),
                new ExitAction()
        );
        new StartUI().init(in, memTracker, actions);
        assertThat(memTracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        /* Добавим в tracker новую заявку */
        Item item = memTracker.add(new Item("Replaced item"));
        /* Входные данные должны содержать ID добавленной заявки item.getId() */
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        List<UserAction> actions = List.of(
                new ReplaceAction(out),
                new ExitAction()
        );
        new StartUI().init(in, memTracker, actions);
        assertThat(memTracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        /* Добавим в tracker новую заявку */
        Item item = memTracker.add(new Item("Deleted item"));
        /* Входные данные должны содержать ID добавленной заявки item.getId() */
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = List.of(
                new DeleteAction(out),
                new ExitAction()
        );
        new StartUI().init(in, memTracker, actions);
        assertThat(memTracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenFindAll() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item[] item = {memTracker.add(new Item("Bug")), memTracker.add(new Item("Fix"))};
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        List<UserAction> actions = List.of(
                new ShowAction(out),
                new ExitAction()
        );
        new StartUI().init(in, memTracker, actions);
        assertThat(memTracker.findAll().size(), is(2));
    }

    @Test
    public void whenFindId() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Bug"));
        int id = item.getId();
        Input in = new StubInput(
                new String[] {"0", String.valueOf(id), "1"}
        );
        List<UserAction> actions = List.of(
                new FindIdAction(out),
                new ExitAction()
        );
        new StartUI().init(in, memTracker, actions);
        assertThat(memTracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenFindName() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Bug"));
        String name = item.getName();
        Input in = new StubInput(
                new String[] {"0", name, "1"}
        );
        List<UserAction> actions = List.of(
                new FindNameAction(out),
                new ExitAction()
        );
        new StartUI().init(in, memTracker, actions);
        assertThat(memTracker.findByName(name).get(0), is(item));
    }

    @Test
    public void findAllActionOut() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = List.of(
                new ShowAction(out),
                new ExitAction()
        );
        new StartUI().init(in, memTracker, actions);
        assertThat(out.toString(), is(
                    "=== Show all items ====" + System.lineSeparator()
                        + "Хранилище еще не содержит заявок." + System.lineSeparator()
        ));
    }

    @Test
    public void findByNameActionOut() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "Fix", "1"}
        );
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = List.of(
                new FindNameAction(out),
                new ExitAction()
        );
        new StartUI().init(in, memTracker, actions);
        assertThat(out.toString(), is(
                "=== Find items by name ====" + System.lineSeparator()
                        + "Заявки с именем: Fix не найдены." + System.lineSeparator()
        ));
    }

    @Test
    public void findByIdActionOut() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "1", "1"}
        );
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = List.of(
                new FindIdAction(out),
                new ExitAction()
        );
        new StartUI().init(in, memTracker, actions);
        assertThat(out.toString(), is(
                "=== Find item by id ====" + System.lineSeparator()
                        + "Заявка с введенным id: 1 не найдена." + System.lineSeparator()
        ));
    }

    @Test
    public void findAllOutWithItem() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Bug"));
        List<UserAction> actions = List.of(
                new ShowAction(out),
                new ExitAction()
        );
        new StartUI().init(in, memTracker, actions);
        assertThat(out.toString(), is(
                "=== Show all items ====" + System.lineSeparator()
                        + item + System.lineSeparator()
        ));
    }

    @Test
    public void findByNameOutWithItem() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "Fix", "1"}
        );
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Fix"));
        List<UserAction> actions = List.of(
                new FindNameAction(out),
                new ExitAction()
        );
        new StartUI().init(in, memTracker, actions);
        assertThat(out.toString(), is(
                "=== Find items by name ====" + System.lineSeparator()
                        + item + System.lineSeparator()
        ));
    }

    @Test
    public void findByIdOutWithItem() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "1", "1"}
        );
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Issue"));
        List<UserAction> actions = List.of(
                new FindIdAction(out),
                new ExitAction()
        );
        new StartUI().init(in, memTracker, actions);
        assertThat(out.toString(), is(
                "=== Find item by id ====" + System.lineSeparator()
                        + item + System.lineSeparator()
        ));
    }
}