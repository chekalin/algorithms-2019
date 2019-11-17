package algorithms.course4.week1;

import java.util.Arrays;

class AllPairsShortestPaths {

    static double[][] calculateAllPairsShortestPath(DirectedWeightedGraph graph) {
        int n = graph.numberOfVertices();
        if (n == 0) return new double[0][0];

        double[][][] solutions = new double[2][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    solutions[0][i][j] = 0;
                } else {
                    solutions[0][i][j] = graph.getEdge(i, j)
                            .map(Double::valueOf)
                            .orElse(Double.POSITIVE_INFINITY);
                }
            }
        }
        for (int k = 1; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double bestSolutionSoFar = solutions[0][i][j];
                    double solutionWithNewEdge = solutions[0][i][k] + solutions[0][k][j];
                    solutions[1][i][j] = Math.min(bestSolutionSoFar, solutionWithNewEdge);
                }
                if (solutions[1][i][i] < 0) throw new NegativeCycleException();
            }
            for (int i = 0; i < n; i++) {
                solutions[0][i] = Arrays.copyOf(solutions[1][i], n);
            }
        }
        return solutions[1];

    }

    static class NegativeCycleException extends RuntimeException {
        NegativeCycleException() {
            super("Negative cycle found. Unable to calculate shortest paths");
        }
    }

}
