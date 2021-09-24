package ru.job4j.collection;

import java.util.ArrayList;

public class UsageArrayList {
    public static void main(String[] args) {
        ArrayList<String> array = new ArrayList<String>();
        array.add("Petr");
        array.add("Stepan");
        array.add("Ivan");
        for (String el: array) {
            System.out.println(el);
        }
    }
}
