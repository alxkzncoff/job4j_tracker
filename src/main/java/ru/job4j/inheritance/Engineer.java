package ru.job4j.inheritance;

public class Engineer extends Profession {
    private String experience;

    public Engineer(String experience) {
        this.experience = experience;
    }

    public Engineer() {
    }

    public void design(String idea) {
    }
}
