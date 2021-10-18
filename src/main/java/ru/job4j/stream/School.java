package ru.job4j.stream;

import java.util.List;
import java.util.Map;
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

    /**
     * Метод преобразует список учеников в словарь, где ключом
     * является фамилия ученика, а значением ученик (class Student)
     * @param students список учеников.
     * @return Map словарь учеников.
     */
    public Map<String, Student> studentList(List<Student> students) {
        return students.stream()
                .collect(Collectors.toMap(
                        Student::getSurname,
                        student -> student,
                        (student1, student2) -> student1
                ));
    }
}
