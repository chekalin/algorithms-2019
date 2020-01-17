package ctci.sorting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PeaksAndValleysTest {

    private PeaksAndValleys solution = new PeaksAndValleys();

    @Test
    void testPeaksAndValleys() {
        int[] array = {5, 3, 1, 2, 3};
        solution.sort(array);
        assertThat(array).containsExactly(5, 1, 3, 2, 3);
    }
}