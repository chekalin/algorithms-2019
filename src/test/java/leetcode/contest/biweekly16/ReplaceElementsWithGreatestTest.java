package leetcode.contest.biweekly16;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReplaceElementsWithGreatestTest {
    private ReplaceElementsWithGreatest problem = new ReplaceElementsWithGreatest();

    @Test
    void lastIsAlwaysMinusOne() {
        int[] result = problem.replaceElements(new int[]{1});
        assertThat(result).isEqualTo(new int[]{-1});
    }

    @Test
    void firstReplacedWithRightWhenTwoElements() {
        int[] result = problem.replaceElements(new int[]{2, 1});
        assertThat(result).isEqualTo(new int[]{1, -1});
    }

    @Test
    void testCase() {
        int[] result = problem.replaceElements(new int[]{17, 18, 5, 4, 6, 1});
        assertThat(result).isEqualTo(new int[]{18, 6, 6, 6, 1, -1});
    }
}