package algorithms.course4.week2;

import algorithms.course4.tsputil.Coordinate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.*;

import static algorithms.course4.week2.TravelingSalesmanProblem.*;
import static algorithms.util.AssignmentInputReader.getScanner;
import static org.assertj.core.api.Assertions.assertThat;

class TravelingSalesmanProblemTest {

    @Test
    void minimumCostOfOneCoordinateIsZero() {
        double result = minimumCostTour(List.of(new Coordinate(0, 0)));
        assertThat(result).isEqualTo(0);
    }

    @Test
    void minimumCostOfTwoVerticesIsDoubleTheDistance() {
        double result = minimumCostTour(List.of(
                new Coordinate(0, 0),
                new Coordinate(0, 1)
        ));
        assertThat(result).isEqualTo(2);
    }

    @Test
    void minimumCostOfThreeVerticesIsSumOfTheDistances() {
        double result = minimumCostTour(List.of(
                new Coordinate(0, 0),
                new Coordinate(0, 1),
                new Coordinate(1, 0)
        ));
        assertThat(result).isEqualTo(1 + 1 + Math.sqrt(2));
    }

    @Test
    void input_int_10_4() throws FileNotFoundException {
        List<Coordinate> vertices = readCoordinatesFromFile("course4/week2/input_int_10_4.txt");
        double result = minimumCostTour(vertices);
        assertThat((int) result).isEqualTo(31);
    }

    @Test
    void input_float_10_4() throws FileNotFoundException {
        List<Coordinate> vertices = readCoordinatesFromFile("course4/week2/input_float_10_4.txt");
        double result = minimumCostTour(vertices);
        assertThat((int) result).isEqualTo(7);
    }

    @Test
    void input_float_33_10() throws FileNotFoundException {
        List<Coordinate> vertices = readCoordinatesFromFile("course4/week2/input_float_33_10.txt");
        double result = minimumCostTour(vertices);
        assertThat((int) result).isEqualTo(27);
    }

    @Test
    void input_float_53_15() throws FileNotFoundException {
        List<Coordinate> vertices = readCoordinatesFromFile("course4/week2/input_float_53_15.txt");
        double result = minimumCostTour(vertices);
        assertThat((int) result).isEqualTo(43);
    }

    @Test
    @Disabled("Slow & requires -Xmx8G")
    void assignment() throws FileNotFoundException {
        List<Coordinate> vertices = readCoordinatesFromFile("course4/week2/problem_set.txt");
        double result = minimumCostTour(vertices);
        System.out.println("result = " + result);
    }

    @Test
    void testGetSetBits() {
        List<Integer> result = fromBitSet(toBitSet(new int[]{0, 3, 4}));
        assertThat(result).hasSize(3).contains(0, 3, 4);
    }

    @Test
    void testToBitSet() {
        int[] ints = {0, 3, 4};

        BitSet bitSet = toBitSet(ints);

        assertThat(bitSet.get(0)).isTrue();
        assertThat(bitSet.get(1)).isFalse();
        assertThat(bitSet.get(2)).isFalse();
        assertThat(bitSet.get(3)).isTrue();
        assertThat(bitSet.get(4)).isTrue();
    }

    private List<Coordinate> readCoordinatesFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = getScanner(filename);
        int n = Integer.parseInt(scanner.nextLine());
        List<Coordinate> coordinates = new ArrayList<>(n);
        while (scanner.hasNextLine()) {
            String[] coordinatesArray = scanner.nextLine().split(" ");
            Coordinate coordinate = new Coordinate(Double.parseDouble(coordinatesArray[0]), Double.parseDouble(coordinatesArray[1]));
            coordinates.add(coordinate);
        }
        return coordinates;
    }
}