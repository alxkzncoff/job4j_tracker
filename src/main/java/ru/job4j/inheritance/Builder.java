package ru.job4j.inheritance;

public class Builder extends Engineer {
    private String materials;

    public Builder(String name, String surname, String education, String birthday,
                   String experience, String materials) {
        super(name, surname, education, birthday, experience);
        this.materials = materials;
    }

    public void buildStructure() {
    }
}
