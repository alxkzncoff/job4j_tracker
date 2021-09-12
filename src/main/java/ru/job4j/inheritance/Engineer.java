package ru.job4j.inheritance;

public class Engineer extends Profession {
    private String experience;

    public Engineer(String name, String surname, String education, String birthday,
                    String experience) {
        super(name, surname, education, birthday);
        this.experience = experience;
    }

    public void design(String idea) {
    }
}
