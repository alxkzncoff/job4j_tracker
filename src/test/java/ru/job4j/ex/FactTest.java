package ru.job4j.ex;

import org.junit.Test;

import static org.junit.Assert.*;

public class FactTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenInputLessThen0() {
        Fact factorial = new Fact();
        factorial.calc(-1);
    }
}