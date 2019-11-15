package algorithms.course3.week4;

import algorithms.course3.week4.Knapsack.Element;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static algorithms.util.AssignmentInputReader.getScanner;
import static org.assertj.core.api.Assertions.assertThat;

class KnapsackTest {

    @Test
    void returnsZeroWhenThereAreNoItems() {
        long solution = Knapsack.solve(Lists.emptyList(), 0);
        assertThat(solution).isEqualTo(0);
    }

    @Test
    void returnsWhenCapacityIsZero() {
        long solution = Knapsack.solve(List.of(new Element(1, 1)), 0);
        assertThat(solution).isEqualTo(0);
    }

    @Test
    void returnsSingleValueWhenItFitsCapacity() {
        List<Element> elements = List.of(new Element(2, 9));
        int capacity = 9;
        long solution = Knapsack.solve(elements, capacity);
        assertThat(solution).isEqualTo(2);
    }

    @Test
    void doesNotAddElementsThatExceedCapacityToValue() {
        List<Element> elements = List.of(
                new Element(2, 9),
                new Element(2, 10)
        );
        int capacity = 9;
        long solution = Knapsack.solve(elements, capacity);
        assertThat(solution).isEqualTo(2);
    }

    @Test
    void addsElementsThatFit() {
        List<Element> elements = List.of(
                new Element(8, 5),
                new Element(7, 4)
        );
        int capacity = 10;
        long solution = Knapsack.solve(elements, capacity);
        assertThat(solution).isEqualTo(15);
    }

    @Test
    void picksTheBestCombinationOfElements() {
        List<Element> elements = List.of(
                new Element(8, 5), // should not pick this one
                new Element(7, 4),
                new Element(12, 4)
        );
        int capacity = 8;
        long solution = Knapsack.solve(elements, capacity);
        assertThat(solution).isEqualTo(19);
    }

    @Test
    void moreComplexScenario() {
        List<Element> elements = List.of(
                new Element(3, 4),
                new Element(2, 3),
                new Element(4, 2),
                new Element(4, 3)
        );
        int capacity = 6;
        long solution = Knapsack.solve(elements, capacity);
        assertThat(solution).isEqualTo(8);
    }

    @Test
    void input_random_10_100_10() throws FileNotFoundException {
        long result = solveFile("course3/week4/input_random_10_100_10.txt");
        assertThat(result).isEqualTo(147);
    }

    @Test
    void assignment() throws FileNotFoundException {
        long result = solveFile("course3/week4/problem_set_1.txt");
        System.out.println("result = " + result);
    }

    private long solveFile(String filename) throws FileNotFoundException {
        Scanner scanner = getScanner(filename);
        String[] meta = scanner.nextLine().split(" ");
        int capacity = Integer.parseInt(meta[0]);
        int numberOfElements = Integer.parseInt(meta[1]);
        List<Element> elements = new ArrayList<>(numberOfElements);
        while(scanner.hasNextLine()) {
            String[] elementLine = scanner.nextLine().split(" ");
            int value = Integer.parseInt(elementLine[0]);
            int weight = Integer.parseInt(elementLine[1]);
            elements.add(new Element(value, weight));
        }
        return Knapsack.solve(elements, capacity);
    }
}