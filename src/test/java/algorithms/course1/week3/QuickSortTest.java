package algorithms.course1.week3;

import algorithms.util.AssignmentInputReader;
import algorithms.course1.week3.QuickSort.PivotStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class QuickSortTest {

    @Test
    void arrayOfOneIsAlreadySorted() {
        int[] input = {1};
        long comparisons = QuickSort.sort(input);
        assertThat(input).isEqualTo(new int[]{1});
        assertThat(comparisons).isEqualTo(0);
    }

    @Test
    void sortsArrayOf2() {
        int[] input = {2, 1};
        long comparisons = QuickSort.sort(input);
        assertThat(input).isEqualTo(new int[]{1, 2});
        assertThat(comparisons).isEqualTo(1);
    }

    @Test
    void sortsArrayOf3() {
        int[] input = {3, 2, 1};
        long comparisons = QuickSort.sort(input);
        assertThat(input).isEqualTo(new int[]{1, 2, 3});
        assertThat(comparisons).isEqualTo(3);
    }

    @Test
    void sortsArrayOf3UsingLastPivotStrategy() {
        int[] input = {3, 2, 1};
        long comparisons = QuickSort.sort(input, PivotStrategy.LAST);
        assertThat(input).isEqualTo(new int[]{1, 2, 3});
        assertThat(comparisons).isEqualTo(3);
    }

    @Test
    void sortsArrayOf3UsingMedianPivotStrategy() {
        int[] input = {3, 2, 1};
        long comparisons = QuickSort.sort(input, PivotStrategy.MEDIAN);
        assertThat(input).isEqualTo(new int[]{1, 2, 3});
        assertThat(comparisons).isEqualTo(2);
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of(new int[]{3, 2, 1, 4, 5}, new long[]{6, 10, 6}),
                Arguments.of(new int[]{4, 3, 2, 5, 1}, new long[]{7, 8, 6}),
                Arguments.of(new int[]{2, 5, 1, 3, 4}, new long[]{7, 7, 6}),
                Arguments.of(new int[]{4, 1, 2, 5, 3}, new long[]{7, 6, 6}),
                Arguments.of(new int[]{4, 5, 2, 3, 1}, new long[]{7, 8, 6})
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void parametrizedQuickSortTest(int[] input, long[] expectations) {
        verifyForPivotStrategy(PivotStrategy.FIRST, copy(input), expectations[0]);
        verifyForPivotStrategy(PivotStrategy.LAST, copy(input), expectations[1]);
        verifyForPivotStrategy(PivotStrategy.MEDIAN, copy(input), expectations[2]);
    }

    private static int[] copy(int[] input) {
        return Arrays.copyOf(input, input.length);
    }

    private void verifyForPivotStrategy(PivotStrategy pivotStrategy, int[] input, long expectedComparisons) {
        long comparisons = QuickSort.sort(input, pivotStrategy);
        assertThat(input).isSorted();
        assertThat(comparisons).isEqualTo(expectedComparisons);
    }

    @Test
    void testLargeSet() throws FileNotFoundException {
        int[] input = AssignmentInputReader.readArrayInput("course1/week3/week3_test_problem_set.txt.txt", 100000);
        verifyForPivotStrategy(PivotStrategy.FIRST, copy(input), 2127173);
        verifyForPivotStrategy(PivotStrategy.LAST, copy(input), 2079088);
        verifyForPivotStrategy(PivotStrategy.MEDIAN, copy(input), 1749103);
    }

    @Test
    void testWithProblemSet() throws FileNotFoundException {
        int[] input = AssignmentInputReader.readArrayInput("course1/week3/week3_problem_set.txt", 10000);
        long comparisonsWithFirst = QuickSort.sort(copy(input), PivotStrategy.FIRST);
        System.out.println("comparisonsWithFirst = " + comparisonsWithFirst);
        long comparisonsWithLast = QuickSort.sort(copy(input), PivotStrategy.LAST);
        System.out.println("comparisonsWithLast = " + comparisonsWithLast);
        long comparisonsWithMedian = QuickSort.sort(copy(input), PivotStrategy.MEDIAN);
        System.out.println("comparisonsWithMedian = " + comparisonsWithMedian);
    }

    @Test
    void testMedianPivot() {
        assertThat(PivotStrategy.MEDIAN.choosePivot(new int[]{1, 2, 3}, 0, 2)).isEqualTo(1);
        assertThat(PivotStrategy.MEDIAN.choosePivot(new int[]{2, 1, 3}, 0, 2)).isEqualTo(0);
        assertThat(PivotStrategy.MEDIAN.choosePivot(new int[]{3, 1, 2}, 0, 2)).isEqualTo(2);
        assertThat(PivotStrategy.MEDIAN.choosePivot(new int[]{4, 5, 6, 7}, 0, 3)).isEqualTo(1);
        assertThat(PivotStrategy.MEDIAN.choosePivot(new int[]{8, 2, 4, 5, 7, 1}, 0, 5)).isEqualTo(2);
        assertThat(PivotStrategy.MEDIAN.choosePivot(new int[]{1, 2, 4, 5, 3}, 2, 4)).isEqualTo(2);
    }
}