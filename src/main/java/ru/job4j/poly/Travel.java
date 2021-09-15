package ru.job4j.poly;

public class Travel {
    public static void main(String[] args) {
        Vehicle plane = new Airplane();

        Vehicle train = new Train();

        Vehicle bus = new Bus();

        Vehicle[] vehicles = new Vehicle[] {plane, train, bus};

        for (Vehicle vehicle : vehicles) {
            vehicle.depart();
            vehicle.move();
            vehicle.arrive();
        }
    }
}
