package algorithms.course4.week3;

import algorithms.course4.tsputil.Coordinate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static algorithms.course4.week3.NearestNeighborTsp.computeTour;
import static algorithms.util.AssignmentInputReader.getScanner;
import static org.assertj.core.api.Assertions.assertThat;

class NearestNeighborTspTest {

    @Test
    void tourOfOnePointIsZero() {
        double result = computeTour(List.of(new Coordinate(0, 0)));
        assertThat(result).isEqualTo(0);
    }

    @Test
    void tourOfTwoPointsIsDoubleTheDistance() {
        double result = computeTour(List.of(
                new Coordinate(0, 0),
                new Coordinate(0, 1)
        ));
        assertThat(result).isEqualTo(2);
    }

    @Test
    void tourOfThreePointsIsSumOfDistances() {
        double result = computeTour(List.of(
                new Coordinate(0, 0),
                new Coordinate(0, 1),
                new Coordinate(0, 3)
        ));
        assertThat(result).isEqualTo(6);
    }

    @Test
    void input_simple_10_8() throws FileNotFoundException {
        List<Coordinate> coordinates = readCoordinatesFromFile("course4/week3/input_simple_10_8.txt");
        double result = computeTour(coordinates);
        assertThat((int) result).isEqualTo(23);
    }

    @Test
    @Disabled("slow")
    void assignment() throws FileNotFoundException {
        List<Coordinate> coordinates = readCoordinatesFromFile("course4/week3/problem_set.txt");
        double result = computeTour(coordinates);
        System.out.println("result = " + result);
    }

    private static List<Coordinate> readCoordinatesFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = getScanner(filename);
        int n = Integer.parseInt(scanner.nextLine());
        List<Coordinate> coordinates = new ArrayList<>(n);
        while (scanner.hasNextLine()) {
            String[] coordinatesArray = scanner.nextLine().split(" ");
            Coordinate coordinate = new Coordinate(Double.parseDouble(coordinatesArray[1]), Double.parseDouble(coordinatesArray[2]));
            coordinates.add(coordinate);
        }
        return coordinates;
    }
}