package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;

public class StreamUsage {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(0, -4, 21, -1, 99, -1024, 777);
        List<Integer> positive = numbers.stream().filter(number -> number >= 0)
                .collect(Collectors.toList());
        positive.forEach(System.out::println);
    }
}
