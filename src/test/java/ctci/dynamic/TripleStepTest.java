package ctci.dynamic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TripleStepTest {

    @Test
    void stepsOfOneIsOne() {
        assertThat(TripleStep.count(1)).isEqualTo(1);
    }

    @Test
    void stepsOfTwoIsTwo() {
        // 1 + 1 or 2
        assertThat(TripleStep.count(2)).isEqualTo(2);
    }

    @Test
    void stepsOfThreeIsFour() {
        // 1 + 1 + 1 or 2 + 1 or 1 + 2 or 3
        assertThat(TripleStep.count(3)).isEqualTo(4);
    }

    @Test
    void stepsOfFourIsFour() {
        // 1 + 1 + 1 + 1
        // or 1 + 2 + 1
        // or 1 + 1 + 2
        // or 1 + 3
        // or 2 + 1 + 1
        // or 2 + 2
        // or 3 + 1
        assertThat(TripleStep.count(4)).isEqualTo(7);
    }
}