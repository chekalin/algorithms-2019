package leetcode.contest.biweekly16;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import static algorithms.util.AssignmentInputReader.getScanner;
import static org.assertj.core.api.Assertions.assertThat;

class SumOfMutatedArrayTest {
    private SumOfMutatedArray problem = new SumOfMutatedArray();

    @Test
    void whenValueGreaterThanTargetReturnsTarget() {
        int result = problem.findBestValue(new int[]{100}, 10);
        assertThat(result).isEqualTo(10);
    }

    @Test
    void whenValuesGreaterThanTargetReturnsTargetDividedByLength() {
        int result = problem.findBestValue(new int[]{5, 5, 5}, 10);
        assertThat(result).isEqualTo(3);
    }

    @Test
    void whenValueLessThanTargetReturnsThatValue() {
        int result = problem.findBestValue(new int[]{2}, 10);
        assertThat(result).isEqualTo(2);
    }

    @Test
    void testCase1() {
        int result = problem.findBestValue(new int[]{4, 9, 3}, 10);
        assertThat(result).isEqualTo(3);
    }

    @Test
    void testCase2() {
        int result = problem.findBestValue(new int[]{2, 3, 5}, 10);
        assertThat(result).isEqualTo(5);
    }

    @Test
    void testCase3() {
        int result = problem.findBestValue(new int[]{60864, 25176, 27249, 21296, 20204}, 56803);
        assertThat(result).isEqualTo(11361);
    }

    @Test
    void testCase4() {
        int result = problem.findBestValue(new int[]{1547, 83230, 57084, 93444, 70879}, 71237);
        assertThat(result).isEqualTo(17422);
    }

    @Test
    void testCase5() throws FileNotFoundException {
        Scanner scanner = getScanner("contest/large_input.txt");
        int[] ints = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        System.out.println("ints.length = " + ints.length);
        int result = problem.findBestValue(ints, 4203);
        assertThat(result).isEqualTo(0);
    }
}