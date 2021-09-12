package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book dune = new Book("Dune", 500);
        Book lotr = new Book("Lord of the Rings", 800);
        Book hp = new Book("Harry Potter", 300);
        Book cc = new Book("Clean code", 250);
        Book[] books = new Book[4];
        books[0] = dune;
        books[1] = lotr;
        books[2] = hp;
        books[3] = cc;
        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            System.out.println("Title: " + bk.getName() + ", " + bk.getPages() + " pages");
        }
        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;
        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            System.out.println("Title: " + bk.getName() + ", " + bk.getPages() + " pages");
        }
        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            if (bk.equals(books[0])) {
                System.out.println("Title: " + bk.getName() + ", " + bk.getPages() + " pages");
            }
        }
    }
}
