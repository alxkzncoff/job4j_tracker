package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class DiapasonTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        Diapason function = new Diapason();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D, 17D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLinearFunctionThenSquareResults() {
        Diapason function = new Diapason();
        List<Double> result = function.diapason(2, 4, x -> x * x + 2);
        List<Double> expected = Arrays.asList(6D, 11D, 18D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLinearFunctionThenPowResults() {
        Diapason function = new Diapason();
        List<Double> result = function.diapason(3, 6, x -> Math.pow(2, x) - 3);
        List<Double> expected = Arrays.asList(5D, 13D, 29D, 61D);
        assertThat(result, is(expected));
    }
}