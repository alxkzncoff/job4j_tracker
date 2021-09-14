package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("Поехали.");
    }

    @Override
    public void passengers(int number) {
        System.out.println("В автобусе " + number + " пассажиров.");
    }

    @Override
    public double refuel(double fuel) {
        return fuel * 49.9;
    }
}
