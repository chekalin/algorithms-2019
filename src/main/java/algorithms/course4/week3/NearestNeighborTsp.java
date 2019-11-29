package algorithms.course4.week3;

import algorithms.course4.tsputil.Coordinate;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static algorithms.course4.tsputil.Coordinate.calculateDistance;
import static algorithms.course4.tsputil.Coordinate.squareDistance;

class NearestNeighborTsp {

    static double computeTour(List<Coordinate> coordinates) {
        if (coordinates.size() == 1) {
            return 0;
        }
        Coordinate current = coordinates.get(0);
        Set<Coordinate> visited = new HashSet<>();
        visited.add(current);
        double tour = 0;
        while (visited.size() != coordinates.size()) {
            Coordinate finalCurrent = current;
            Coordinate nearest = coordinates.stream()
                    .filter(coordinate -> !visited.contains(coordinate))
                    .min(Comparator.comparingDouble(coordinate -> squareDistance(finalCurrent, coordinate)))
                    .orElseThrow();
            visited.add(nearest);
            tour += calculateDistance(current, nearest);
            current = nearest;

        }
        tour += calculateDistance(current, coordinates.get(0));
        return tour;
    }

}
