package ru.job4j.oop;

public class Error {

    private boolean active;

    private int status;

    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printError() {
        System.out.println("active: " + active);
        System.out.println("status: " + status);
        System.out.println("message: " + message);
    }

    public static void main(String[] args) {
        Error er1 = new Error();
        er1.printError();
        Error er2 = new Error(true, 21, "System error");
        er2.printError();
        Error er3 = new Error(false, 47, "Unknown error");
        er3.printError();
    }
}
