package algorithms.course1.course4.week2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CombinationGeneratorTest {

    @Test
    void combinations() {
        assertThat(CombinationGenerator.generateCombinations(0, 1))
                .containsExactly(new int[]{0});

        assertThat(CombinationGenerator.generateCombinations(0, 2))
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new int[]{0},
                        new int[]{1},
                        new int[]{0, 1}
                );

        assertThat(CombinationGenerator.generateCombinations(0, 3))
                .hasSize(7)
                .containsExactlyInAnyOrder(
                        new int[]{0},
                        new int[]{1},
                        new int[]{2},
                        new int[]{0, 1},
                        new int[]{0, 2},
                        new int[]{1, 2},
                        new int[]{0, 1, 2}
                );

        assertThat(CombinationGenerator.generateCombinations(0, 4))
                .hasSize((int) Math.pow(2, 4) - 1);
    }

    @Test
    void combinationsWithFirstElementIncluded() {
        assertThat(CombinationGenerator.generateCombinationsWithFirstElementIncluded(0, 1))
                .containsExactly(new int[]{0});

        assertThat(CombinationGenerator.generateCombinationsWithFirstElementIncluded(0, 2))
                .hasSize(2)
                .containsExactlyInAnyOrder(
                        new int[]{0},
                        new int[]{0, 1}
                );

        assertThat(CombinationGenerator.generateCombinationsWithFirstElementIncluded(0, 3))
                .hasSize(4)
                .containsExactlyInAnyOrder(
                        new int[]{0},
                        new int[]{0, 1},
                        new int[]{0, 2},
                        new int[]{0, 1, 2}
                );
    }

}