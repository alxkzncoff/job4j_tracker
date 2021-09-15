package ru.job4j.poly;

public class Bus implements Vehicle {
    @Override
    public void move() {
        System.out.println("Автобус двигается по дороге.");
    }

    @Override
    public void arrive() {
        System.out.println("Автобус прибывает на автобусную остановку.");
    }

    @Override
    public void depart() {
        System.out.println("Автобус отходит от автобусной остановки.");
    }
}
