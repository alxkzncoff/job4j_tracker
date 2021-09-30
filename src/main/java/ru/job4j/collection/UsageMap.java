package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> id = new HashMap<>();
        id.put("smith@gmail.com", "Smith");
        for (String key: id.keySet()) {
            String value = id.get(key);
            System.out.println(value);
        }
    }
}
