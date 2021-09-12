package ru.job4j.inheritance;

public class Surgeon extends Doctor {
    private String type;

    public Surgeon(String name, String surname, String education, String birthday,
                   boolean paid, String type) {
        super(name, surname, education, birthday, paid);
        this.type = type;
    }

    public void makeOperation() {
    }
}
