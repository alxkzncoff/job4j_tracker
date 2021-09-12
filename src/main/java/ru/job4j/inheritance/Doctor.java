package ru.job4j.inheritance;

public class Doctor extends Profession {
    private boolean paid;

    public Doctor(String name, String surname, String education, String birthday, boolean paid) {
        super(name, surname, education, birthday);
        this.paid = paid;
    }

    public void heal(String symptoms) {
    }
}
