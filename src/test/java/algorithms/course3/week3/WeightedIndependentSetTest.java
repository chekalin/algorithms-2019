package algorithms.course3.week3;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static algorithms.util.AssignmentInputReader.getScanner;
import static org.assertj.core.api.Assertions.assertThat;

class WeightedIndependentSetTest {

    @Test
    void returnsFirstIndexForPathOfOneElement() {
        int[] result = WeightedIndependentSet.calculateWeightedIndependentSet(new int[]{1});
        assertThat(result).hasSize(1);
        assertThat(result[0]).isEqualTo(0);
    }

    @Test
    void returnsIndexOfLargerElementForPathWithTwoElements() {
        int[] path = {1, 2};

        int[] result = WeightedIndependentSet.calculateWeightedIndependentSet(path);

        assertThat(result).hasSize(1);
        assertThat(result[0]).isEqualTo(1);
    }

    @Test
    void returnsSetForPathOfThreeElements() {
        int[] path = {1, 2, 3};

        int[] result = WeightedIndependentSet.calculateWeightedIndependentSet(path);

        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyInAnyOrder(0, 2);
    }

    @Test
    void problem_set_2_input_random_1_10() throws FileNotFoundException {
        int[] path = readPath("course3/week3/problem_set_2_input_random_1_10.txt");

        int[] wis = WeightedIndependentSet.calculateWeightedIndependentSet(path);

        String result = findExpectedVertices(wis);
        assertThat(result).isEqualTo("10100000");
    }

    @Test
    void assignment() throws FileNotFoundException {
        int[] path = readPath("course3/week3/week3_problem_set_2.txt");

        int[] wis = WeightedIndependentSet.calculateWeightedIndependentSet(path);

        String result = findExpectedVertices(wis);

        System.out.println("result = " + result);
    }

    private String findExpectedVertices(int[] wis) {
        List<Integer> expectedVertices = List.of(1, 2, 3, 4, 17, 117, 517, 997);
        Arrays.sort(wis);
        System.out.println("wis = " + Arrays.toString(wis));
        List<String> collect = expectedVertices.stream()
                // we look up expectedVertex -1 because assignment indices are 1-based
                .map(vertex -> Arrays.binarySearch(wis, vertex - 1) < 0 ? "0" : "1")
                .collect(Collectors.toList());
        return String.join("", collect);
    }

    private int[] readPath(String filename) throws FileNotFoundException {
        Scanner scanner = getScanner(filename);
        int numberOfVertices = Integer.parseInt(scanner.nextLine());
        int[] path = new int[numberOfVertices];
        int i = 0;
        while (scanner.hasNextLine()) {
            path[i++] = Integer.parseInt(scanner.nextLine());
        }
        return path;
    }
}