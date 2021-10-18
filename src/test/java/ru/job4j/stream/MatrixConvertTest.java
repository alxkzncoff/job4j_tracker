package ru.job4j.stream;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MatrixConvertTest {
    @Test
    public void convertTwoRows() {
        MatrixConvert mc =  new MatrixConvert();
        Integer[][] matrix = {{1, 2}, {3, 4}};
        List<Integer> expected = List.of(1, 2, 3, 4);
        assertThat(expected, is(mc.toList(matrix)));
    }

    @Test
    public void convertThreeRows() {
        MatrixConvert mc =  new MatrixConvert();
        Integer[][] matrix = {{6, 4}, {8, 1}, {-1, 0}};
        List<Integer> expected = List.of(6, 4, 8, 1, -1, 0);
        assertThat(expected, is(mc.toList(matrix)));
    }
}