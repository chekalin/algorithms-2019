package algorithms.course1.course4.week2;

import algorithms.course1.course4.week2.TravelingSalesmanProblem.Vertex;
import org.junit.jupiter.api.Test;

import static algorithms.course1.course4.week2.TravelingSalesmanProblem.calculateDistance;
import static algorithms.course1.course4.week2.TravelingSalesmanProblem.generateCombinations;
import static org.assertj.core.api.Assertions.assertThat;

class TravelingSalesmanProblemTest {

    @Test
    void combinations() {
        int firstElement = 0;
        int[] elements = new int[]{1, 2, 3};

        assertThat(generateCombinations(firstElement, elements, 1))
                .containsExactly(new int[]{0});

        assertThat(generateCombinations(firstElement, elements, 2))
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new int[]{0, 1},
                        new int[]{0, 2},
                        new int[]{0, 3}
                );

        assertThat(generateCombinations(firstElement, elements, 3))
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new int[]{0, 1, 2},
                        new int[]{0, 1, 3},
                        new int[]{0, 2, 3}
                );
    }


    @Test
    void testDistance() {
        assertThat(calculateDistance(new Vertex(0, 0), new Vertex(1, 0))).isEqualTo(1);
        assertThat(calculateDistance(new Vertex(0, 0), new Vertex(0, 1))).isEqualTo(1);
        assertThat(calculateDistance(new Vertex(0, 0), new Vertex(1, 1))).isEqualTo(Math.sqrt(2));
    }
}