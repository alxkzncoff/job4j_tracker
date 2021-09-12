package ru.job4j.inheritance;

public class Dentist extends Doctor {
    public Dentist(String name, String surname, String education, String birthday,
                   boolean paid) {
        super(name, surname, education, birthday, paid);
    }

    public void makeToothFilling() {
    }
}
