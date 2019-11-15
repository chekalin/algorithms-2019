package algorithms.course3.week4;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static algorithms.course3.week4.Knapsack.*;
import static algorithms.course3.week4.KnapsackTest.TestCase.testCase;
import static algorithms.util.AssignmentInputReader.getScanner;
import static org.assertj.core.api.Assertions.assertThat;

class KnapsackTest {

    private static Stream<TestCase> provideTestCases() {
        return List.of(
                testCase("Empty elements", List.of(), 0, 0),
                testCase("Zero capacity", List.of(new Element(1, 1)), 0, 0),
                testCase("Single element fits", List.of(new Element(2, 9)), 9, 2),
                testCase("One out of two elements fit", List.of(
                        new Element(2, 9),
                        new Element(2, 10)
                ), 9, 2),
                testCase("Both elements fit", List.of(
                        new Element(8, 5),
                        new Element(7, 4)
                ), 10, 15),
                testCase("Last two elements compose better solution", List.of(
                        new Element(8, 5), // should skip this element
                        new Element(7, 4),
                        new Element(12, 4)
                ), 8, 19),
                testCase("Example from book", List.of(
                        new Element(3, 4),
                        new Element(2, 3),
                        new Element(4, 2),
                        new Element(4, 3)
                ), 6, 8)
        ).stream();
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void parametrizedBigKnapsackTest(TestCase testCase) {
        assertThat(solveBig(testCase.elements, testCase.capacity)).isEqualTo(testCase.expectedResult);
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void parametrizedKnapsackTest(TestCase testCase) {
        assertThat(solve(testCase.elements, testCase.capacity)).isEqualTo(testCase.expectedResult);
    }

    @Test
    void input_random_10_100_10_with_big() throws FileNotFoundException {
        long result = solveFile("course3/week4/input_random_10_100_10.txt", Knapsack::solveBig);
        assertThat(result).isEqualTo(147);
    }

    @Test
    void input_random_10_100_10() throws FileNotFoundException {
        long result = solveFile("course3/week4/input_random_10_100_10.txt", Knapsack::solve);
        assertThat(result).isEqualTo(147);
    }

    @Test
    void assignment1() throws FileNotFoundException {
        long result = solveFile("course3/week4/problem_set_1.txt", Knapsack::solve);
        System.out.println("assignment 1 result = " + result);
    }

    @Test
    @Disabled("slow")
    void assignment2() throws FileNotFoundException {
        long result = solveFile("course3/week4/problem_set_2.txt", Knapsack::solveBig);
        System.out.println("assignment 2 result = " + result);
    }

    private long solveFile(String filename, BiFunction<List<Element>, Integer, Long> solver) throws FileNotFoundException {
        Scanner scanner = getScanner(filename);
        String[] meta = scanner.nextLine().split(" ");
        int capacity = Integer.parseInt(meta[0]);
        int numberOfElements = Integer.parseInt(meta[1]);
        List<Element> elements = new ArrayList<>(numberOfElements);
        while (scanner.hasNextLine()) {
            String[] elementLine = scanner.nextLine().split(" ");
            int value = Integer.parseInt(elementLine[0]);
            int weight = Integer.parseInt(elementLine[1]);
            elements.add(new Element(value, weight));
        }
        return solver.apply(elements, capacity);
    }

    static class TestCase {
        String name;
        List<Element> elements;
        int capacity;
        long expectedResult;

        private TestCase(String name, List<Element> elements, int capacity, long expectedResult) {
            this.name = name;
            this.elements = elements;
            this.capacity = capacity;
            this.expectedResult = expectedResult;
        }

        static TestCase testCase(String name, List<Element> elements, int capacity, long expectedResult) {
            return new TestCase(name, elements, capacity, expectedResult);
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
