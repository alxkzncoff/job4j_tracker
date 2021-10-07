package ru.job4j.collection;

import java.util.*;

/**
 * Класс добавляет пропущенные подразделения, так же
 * производит сортировку подразделений.
 * @author Aleksand Kuznetsov
 * @version 1.0
 */
public class Departments {
    /**
     * Метод принимает на вход список строк содержащий подразделения,
     * где каждая строка имеет следующую структуру: код текущего подразделения,
     * а перед ним коды всех более крупных подразделений. Добавляет недостающие
     * подразделения.
     * @param departments список строк содержащий подразделения.
     * @return List(String)
     */
    public static List<String> fillGaps(List<String> departments) {
        Set<String> tmp = new LinkedHashSet<>();
        for (String value : departments) {
            String start = "";
            for (String el : value.split("/")) {
                tmp.add(start + el);
                start = start + el + "/";
            }
        }
        return new ArrayList<>(tmp);
    }

    /**
     * Метод сортирует коды подразделений по возрастанию.
     * @param orgs коды подразделений.
     */
    public static void sortAsc(List<String> orgs) {
        Collections.sort(orgs);
    }

    /**
     * Метод сортирует коды подразделений по убыванию.
     * @param orgs коды подразделений.
     */
    public static void sortDesc(List<String> orgs) {
        Collections.sort(orgs, new DepDescComp());
    }
}