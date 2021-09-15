package ru.job4j.poly;

public class Airplane implements Vehicle {
    @Override
    public void move() {
        System.out.println("Самолет летит по воздуху.");
    }

    @Override
    public void arrive() {
        System.out.println("Самолет прибывает в аэропорт.");
    }

    @Override
    public void depart() {
        System.out.println("Самолет вылетает из аэропорт.");
    }
}
