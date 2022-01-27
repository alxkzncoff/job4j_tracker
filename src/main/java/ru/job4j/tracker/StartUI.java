package ru.job4j.tracker;

import java.util.List;

public class StartUI {

    public void init(Input input, Store store, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Enter select: ");
            UserAction action = actions.get(select);
            run = action.execute(input, store);
        }
    }

    private void showMenu(List<UserAction> actions) {
        System.out.println("Menu.");
        for (int i = 0; i < actions.size(); i++) {
            System.out.printf("%d. %s%n", i, actions.get(i).name());
        }
    }

    public static void main(String[] args) {
        Input in = new StubInput(new String[] {
                "0", "test1",
                "0", "test2",
                "0", "test3",
                "0", "test4",
                "0", "test5",
                "0", "test6",
                "0", "test7",
                "0", "test8",
                "0", "test9",
                "0", "test10",
                "3",
                "1", "1", "test1 replaced",
                "1", "2", "test2 replaced",
                "1", "3", "test3 replaced",
                "3",
                "2", "1",
                "2", "2",
                "2", "3",
                "3",
                "4", "4",
                "5", "test5",
                "6"});
        Output output = new ConsoleOutput();
        List<UserAction> actions = List.of(
                new CreateAction(output),
                new ReplaceAction(output),
                new DeleteAction(output),
                new ShowAction(output),
                new FindIdAction(output),
                new FindNameAction(output),
                new ExitAction()
        );
        SqlTracker tracker = new SqlTracker();
        tracker.init();
        new StartUI().init(in, tracker, actions);
    }
}