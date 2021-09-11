package ru.job4j.oop;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.Matchers.closeTo;

public class PointTest {

    @Test
    public void distanceFrom00to02() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        double result = a.distance(b);
        Assert.assertThat(result, closeTo(2.0, 0.1));
    }

    @Test
    public void distanceFrom12to93() {
        Point a = new Point(1, 2);
        Point b = new Point(9, 3);
        double result = a.distance(b);
        Assert.assertThat(result, closeTo(8.0, 0.1));
    }

    @Test
    public void distanceFromMinus11to2Minus3() {
        Point a = new Point(-1, 1);
        Point b = new Point(2, -3);
        double result = a.distance(b);
        Assert.assertThat(result, closeTo(5.0, 0.1));
    }
}