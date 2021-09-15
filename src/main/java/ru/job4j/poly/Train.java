package ru.job4j.poly;

public class Train implements Vehicle {
    @Override
    public void move() {
        System.out.println("Поезд передвигается по рельсам.");
    }

    @Override
    public void arrive() {
        System.out.println("Поезд прибывает на железнодорожную станцию.");
    }

    @Override
    public void depart() {
        System.out.println("Поезд отходит от железнодорожной станции.");
    }
}
