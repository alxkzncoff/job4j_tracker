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
        Input validate = new ValidateInput(
                new ConsoleInput()
        );
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
        new StartUI().init(validate, tracker, actions);
    }
}