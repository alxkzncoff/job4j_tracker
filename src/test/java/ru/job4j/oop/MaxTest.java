package ru.job4j.oop;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaxTest {

    @Test
    public void maxOfTwo() {
        int first = 3;
        int second = 5;
        Max compare = new Max();
        int result = compare.max(first, second);
        int expected = 5;
        Assert.assertEquals(expected, result);
    }

    @Test
    public void maxOfThree() {
        int first = 9;
        int second = 1;
        int third = 8;
        Max compare = new Max();
        int result = compare.max(first, second, third);
        int expected = 9;
        Assert.assertEquals(expected, result);
    }

    @Test
    public void maxOfFour() {
        int first = -1;
        int second = 0;
        int third = -4;
        int fourth = -9;
        Max compare = new Max();
        int result = compare.max(first, second, third, fourth);
        int expected = 0;
        Assert.assertEquals(expected, result);
    }
}