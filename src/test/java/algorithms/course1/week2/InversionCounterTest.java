package algorithms.course1.week2;

import algorithms.course1.util.AssignmentInputReader;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

class InversionCounterTest {

    @Test
    void shouldReturnZeroForSortedArray() {
        long result = InversionCounter.countInversions(new int[]{1, 2, 3});
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void shouldReturnZeroForArrayWithOneElement() {
        long result = InversionCounter.countInversions(new int[]{1});
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void shouldReturnZeroForEmptyArray() {
        long result = InversionCounter.countInversions(new int[]{});
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void shouldReturnNumberOfInversionsForArrayOfTwo() {
        long result = InversionCounter.countInversions(new int[]{2, 1});
        assertThat(result).isEqualTo(1L);
    }

    @Test
    void shouldReturnNumberOfInversionsForArrayOfThree() {
        assertThat(InversionCounter.countInversions(new int[]{3, 2, 1})).isEqualTo(3L);
        assertThat(InversionCounter.countInversions(new int[]{2, 3, 1})).isEqualTo(2L);
        assertThat(InversionCounter.countInversions(new int[]{2, 1, 3})).isEqualTo(1L);
    }

    @Test
    void assignment() throws FileNotFoundException {
        int[] input = new AssignmentInputReader().readArrayInput("course1/week2/week2_problem_set.txt", 100000);
        System.out.println(InversionCounter.countInversions(input));
    }

}