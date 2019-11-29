package algorithms.course4.week2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class TravelingSalesmanProblem {

    static double minimumCostTour(List<Vertex> vertices) {
        if (vertices.isEmpty()) throw new IllegalArgumentException("Empty list of vertices");
        int n = vertices.size();
        List<BitSet> combinations = generateAllCombinationsAsBitSets(n);
        Map<BitSet, double[]> solutions = initializeSolutions(n, combinations);
        calculateAllShortestPaths(vertices, combinations, solutions);
        double[] solutionsForAllVertices = solutions.get(combinationWithAllVertices(n));
        return findShortestLoop(vertices, solutionsForAllVertices);
    }

    private static List<BitSet> generateAllCombinationsAsBitSets(int n) {
        return CombinationGenerator.generateCombinationsWithFirstElementIncluded(0, n)
                .stream()
                .map(TravelingSalesmanProblem::toBitSet)
                .collect(Collectors.toList());
    }

    private static Map<BitSet, double[]> initializeSolutions(int n, List<BitSet> combinations) {
        Map<BitSet, double[]> solutions = new HashMap<>();
        for (BitSet combination : combinations) {
            double[] shortestPaths = new double[n];
            BitSet justFirstElement = toBitSet(new int[]{0});
            if (!combination.equals(justFirstElement)) {
                // no path from first element to itself (we cannot visit same vertex twice)
                shortestPaths[0] = Double.POSITIVE_INFINITY;
            }
            solutions.put(combination, shortestPaths);
        }
        return solutions;
    }

    private static void calculateAllShortestPaths(List<Vertex> vertices, List<BitSet> combinations, Map<BitSet, double[]> solutions) {
        IntStream.range(1, vertices.size() + 1).forEach(m ->
                combinations.stream()
                        .filter(combination -> combination.cardinality() == m)
                        .forEach(combination -> {
                            List<Integer> verticesInCombination = fromBitSet(combination);
                            verticesInCombination.stream()
                                    .filter(j -> j != 0)
                                    .forEach(j -> {
                                        BitSet combinationWithoutJ = (BitSet) combination.clone();
                                        combinationWithoutJ.set(j, false);
                                        double minimalPathToJ = verticesInCombination.stream()
                                                .filter(k -> !k.equals(j))
                                                .mapToDouble(k -> solutions.get(combinationWithoutJ)[k] + calculateDistance(vertices.get(k), vertices.get(j)))
                                                .min()
                                                .orElse(Double.POSITIVE_INFINITY);
                                        solutions.get(combination)[j] = minimalPathToJ;
                                    });
                        }));
    }

    private static double findShortestLoop(List<Vertex> vertices, double[] solutionsForAllVertices) {
        return IntStream.range(1, vertices.size())
                .mapToDouble(j -> solutionsForAllVertices[j] + calculateDistance(vertices.get(0), vertices.get(j)))
                .min()
                .orElse(0);
    }

    private static BitSet combinationWithAllVertices(int n) {
        BitSet allVertices = new BitSet(n);
        allVertices.set(0, n);
        return allVertices;
    }

    static List<Integer> fromBitSet(BitSet set) {
        List<Integer> result = new LinkedList<>();
        for (int i = set.nextSetBit(0); i != -1; i = set.nextSetBit(i + 1)) {
            result.add(i);
        }
        return result;
    }

    static double calculateDistance(Vertex v1, Vertex v2) {
        return Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2));
    }

    static BitSet toBitSet(int[] ints) {
        BitSet bitSet = new BitSet();
        for (int anInt : ints) {
            bitSet.set(anInt);
        }
        return bitSet;
    }

    static class Vertex {
        private double x;
        private double y;

        Vertex(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
