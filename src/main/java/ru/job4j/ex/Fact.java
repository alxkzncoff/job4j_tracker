package ru.job4j.ex;

public class Fact {
    public int calc(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input value should be more or equal 0.");
        }
        int rsl = 1;
        for (int index = 2; index <= n; index++) {
            rsl *= index;
        }
        return rsl;
    }
}