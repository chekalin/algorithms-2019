package algorithms.course2.week3;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static algorithms.course2.week3.MedianMaintainer.HeapMedianMaintainer;
import static algorithms.course2.week3.MedianMaintainer.SortedListMedianMaintainer;
import static algorithms.util.AssignmentInputReader.getScanner;
import static org.assertj.core.api.Assertions.assertThat;

class MedianMaintainerTest {

    @Test
    void listBasedMedianMaintainer() {
        MedianMaintainer medianMaintainer = new SortedListMedianMaintainer();
        medianMaintainer.add(1);
        medianMaintainer.add(3);
        assertThat(medianMaintainer.median()).isEqualTo(1);
        medianMaintainer.add(2);
        assertThat(medianMaintainer.median()).isEqualTo(2);
    }

    @Test
    void heapBasedMedianMaintainer() {
        MedianMaintainer medianMaintainer = new HeapMedianMaintainer(5);
        medianMaintainer.add(1);
        medianMaintainer.add(3);
        assertThat(medianMaintainer.median()).isEqualTo(1);
        medianMaintainer.add(2);
        assertThat(medianMaintainer.median()).isEqualTo(2);
        medianMaintainer.add(4);
        assertThat(medianMaintainer.median()).isEqualTo(2);
        medianMaintainer.add(5);
        assertThat(medianMaintainer.median()).isEqualTo(3);
    }

    @Test
    void test_set_input_random_10_40_sorted_list() throws FileNotFoundException {
        List<Integer> inputs = readInputs("course2/week3/input_random_10_40.txt");
        MedianMaintainer medianMaintainer = new SortedListMedianMaintainer();

        List<Integer> medians = new ArrayList<>();
        inputs.forEach(input -> {
            medianMaintainer.add(input);
            medians.add(medianMaintainer.median());
        });

        Integer result = medians.stream().reduce(0, Integer::sum);
        assertThat(result).isEqualTo(695);
    }

    @Test
    void test_set_input_random_10_40_heap() throws FileNotFoundException {
        List<Integer> inputs = readInputs("course2/week3/input_random_10_40.txt");
        MedianMaintainer medianMaintainer = new HeapMedianMaintainer(inputs.size());

        List<Integer> medians = new ArrayList<>();
        inputs.forEach(input -> {
            medianMaintainer.add(input);
            medians.add(medianMaintainer.median());
        });

        Integer result = medians.stream().reduce(0, Integer::sum);
        assertThat(result).isEqualTo(695);
    }

    @Test
    void test_assignement() throws FileNotFoundException {
        List<Integer> inputs = readInputs("course2/week3/week3_problem_set.txt");
        MedianMaintainer medianMaintainer = new HeapMedianMaintainer(inputs.size());

        List<Integer> medians = new ArrayList<>();
        inputs.forEach(input -> {
            medianMaintainer.add(input);
            medians.add(medianMaintainer.median());
        });

        long sum = medians.stream().mapToLong(Long::valueOf).reduce(0L, Long::sum);
        System.out.println("sum = " + sum);
        System.out.println("sum % 10000 = " + sum % 10000);
    }


    @Test
    void test_assignement_sorted_list() throws FileNotFoundException {
        List<Integer> inputs = readInputs("course2/week3/week3_problem_set.txt");
        MedianMaintainer medianMaintainer = new SortedListMedianMaintainer();

        List<Integer> medians = new ArrayList<>();
        inputs.forEach(input -> {
            medianMaintainer.add(input);
            medians.add(medianMaintainer.median());
        });

        long sum = medians.stream().mapToLong(Long::valueOf).reduce(0L, Long::sum);
        System.out.println("sum = " + sum);
        System.out.println("sum % 10000 = " + sum % 10000);
    }

    private List<Integer> readInputs(String filename) throws FileNotFoundException {
        Scanner scanner = getScanner(filename);
        List<Integer> inputs = new ArrayList<>();
        while (scanner.hasNextLine()) {
            inputs.add(Integer.valueOf(scanner.nextLine()));
        }
        return inputs;
    }
}