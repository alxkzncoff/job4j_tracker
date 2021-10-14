package ru.job4j.stream;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *  В школе требование разделить класс на три класса по общему баллу.
 *  [70; 100]
 *  [50; 70)
 *  (0; 50)
 * @author Aleksandr Kuznetsov
 * @version 1.0
 */
public class School {
    /**
     * Фильтрует учеников по заданным критериям.
     * @param students список учеников.
     * @param predict фильтр.
     * @return список отфильтрованных учеников.
     */
    public List<Student> collect(List<Student> students, Predicate<Student> predict) {
        return students.stream()
                .filter(predict)
                .collect(Collectors.toList());
    }
}
