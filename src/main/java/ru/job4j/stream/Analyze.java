package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс для подсчета статистики по аттестатам учеников.
 * @author Alkesandr Kuznetcov
 * @version 1.0
 */
public class Analyze {
    /**
     * Метод вычисляет общий средний балл.
     * @param stream поток учеников.
     * @return double средний балл.
     */
    public static double averageScore(Stream<Pupil> stream) {
        return stream.flatMap(pupil -> pupil.getSubjects().stream())
                .mapToInt(Subject::getScore)
                .average()
                .orElse(0D);
    }

    /**
     * Метод вычисляет средний балл ученика по его предметам.
     * @param stream поток учеников.
     * @return List из объектов Tuple (имя ученика и средний балл).
     */
    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream.map(pupil -> new Tuple(pupil.getName(),
                pupil.getSubjects().stream()
                        .mapToInt(Subject::getScore)
                                .average().orElse(0D)))
                .collect(Collectors.toList());
    }

    /**
     * Метод вычисляет средний бал по всем предметам для каждого ученика.
     * @param stream поток учеников.
     * @return List из объектов Tuple (название предмета и средний бал).
     */
    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        return stream.flatMap(subject -> subject.getSubjects().stream())
                .collect(Collectors.groupingBy(Subject::getName,
                        Collectors.averagingDouble(Subject::getScore)))
                .entrySet().stream()
                .map(subject -> new Tuple(subject.getKey(), subject.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * Метод ищет лучшего ученика. Лучшим считается ученик с наибольшим баллом
     * по всем предметам.
     * @param stream поток учеников.
     * @return Tuple лучший ученик.
     */
    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream.map(pupil -> new Tuple(pupil.getName(),
                        pupil.getSubjects().stream()
                                .mapToInt(Subject::getScore)
                                .sum())).max(Comparator.comparingDouble(Tuple::getScore))
                .orElse(null);
    }

    /**
     * Метод ищет предмет с наибольшим баллом для всех студентов
     * @param stream поток учеников.
     * @return Tuple (имя предмета, сумма баллов каждого ученика по этому предмету).
     */
    public static Tuple bestSubject(Stream<Pupil> stream) {
        return stream.flatMap(subject -> subject.getSubjects().stream())
                .collect(Collectors.groupingBy(Subject::getName,
                        Collectors.summingDouble(Subject::getScore)))
                .entrySet().stream()
                .map(subject -> new Tuple(subject.getKey(), subject.getValue()))
                .max(Comparator.comparingDouble(Tuple::getScore))
                .orElse(null);
    }
}