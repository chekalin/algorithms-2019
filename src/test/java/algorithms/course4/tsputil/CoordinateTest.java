package algorithms.course4.tsputil;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void testDistance() {
        assertThat(Coordinate.calculateDistance(new Coordinate(0, 0), new Coordinate(1, 0))).isEqualTo(1);
        assertThat(Coordinate.calculateDistance(new Coordinate(0, 0), new Coordinate(0, 1))).isEqualTo(1);
        assertThat(Coordinate.calculateDistance(new Coordinate(0, 0), new Coordinate(1, 1))).isEqualTo(Math.sqrt(2));
    }

}