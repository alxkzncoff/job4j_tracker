package ru.job4j.inheritance;

public class Doctor extends Profession {
    private boolean paid;

    public Doctor(boolean paid) {
        this.paid = paid;
    }

    public Doctor() {
    }

    public void heal(String symptoms) {
    }
}
