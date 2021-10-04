package ru.job4j.collection;

import org.junit.Test;
import java.util.Comparator;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

public class JobTest {
    @Test
    public void nameAsc() {
        int rsl = new SortJobNameAsc().compare(new Job("Clean", 0), (new Job("Write", 1)));
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void nameDesc() {
        int rsl = new SortJobNameDesc().compare(new Job("Clean", 0), (new Job("Write", 1)));
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void priorAsc() {
        int rsl = new SortJobPriorAsc().compare(new Job("Clean", 0), (new Job("Write", 1)));
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void priorDesc() {
        int rsl = new SortJobPriorDesc().compare(new Job("Clean", 0), (new Job("Write", 1)));
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenCompByNameAndPriorityAsc() {
        Comparator<Job> cmpNamePriority =
                new SortJobNameAsc().thenComparing(new SortJobPriorAsc());
        int rsl = cmpNamePriority.compare(
                new Job("Clean", 0),
                new Job("Clean", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompByPriorityAndNameAsc() {
        Comparator<Job> cmpNamePriority =
                new SortJobPriorAsc().thenComparing(new SortJobNameAsc());
        int rsl = cmpNamePriority.compare(
                new Job("Clean", 1),
                new Job("Write", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompByNameAndPriorityDesc() {
        Comparator<Job> cmpNamePriority =
                new SortJobNameDesc().thenComparing(new SortJobPriorDesc());
        int rsl = cmpNamePriority.compare(
                new Job("Clean", 0),
                new Job("Clean", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenCompByPriorityAndNameDesc() {
        Comparator<Job> cmpNamePriority =
                new SortJobPriorDesc().thenComparing(new SortJobNameDesc());
        int rsl = cmpNamePriority.compare(
                new Job("Clean", 1),
                new Job("Write", 1)
        );
        assertThat(rsl, greaterThan(0));
    }
}