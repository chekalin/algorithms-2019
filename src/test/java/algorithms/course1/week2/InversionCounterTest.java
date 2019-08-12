package algorithms.course1.week2;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
        int[] input = readInput("week2_problem_set.txt", 100000);
        System.out.println(InversionCounter.countInversions(input));
    }

    private int[] readInput(String filename, int size) throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        Scanner scanner = new Scanner(file);
        int[] input = new int[size];
        int i = 0;
        while (scanner.hasNextInt()) {
            input[i++] = scanner.nextInt();
        }
        System.out.println("Read input: [" + input[0] + ", ..., " + input[input.length - 1] + "]");
        return input;
    }
}