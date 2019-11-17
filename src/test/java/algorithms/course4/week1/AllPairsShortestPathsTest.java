package algorithms.course4.week1;

import algorithms.course4.week1.AllPairsShortestPaths.NegativeCycleException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static algorithms.course4.week1.AllPairsShortestPaths.calculateAllPairsShortestPath;
import static algorithms.util.AssignmentInputReader.getScanner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AllPairsShortestPathsTest {

    @Test
    void returnsEmptyArrayForEmptyGraph() {
        double[][] result = calculateAllPairsShortestPath(new DirectedWeightedGraph(0, 0));
        assertThat(result).isEqualTo(new long[0][0]);
    }

    @Test
    void shortestPathToItselfIsAlwaysZero() {
        DirectedWeightedGraph graph = new DirectedWeightedGraph(1, 0);
        double[][] result = calculateAllPairsShortestPath(graph);
        assertThat(result[0][0]).isEqualTo(0);
    }

    @Test
    void returnsMaxLongValueWhenThereIsNoPath() {
        DirectedWeightedGraph graph = new DirectedWeightedGraph(2, 0);
        double[][] result = calculateAllPairsShortestPath(graph);
        assertThat(result[0][1]).isEqualTo(Double.POSITIVE_INFINITY);
        assertThat(result[1][0]).isEqualTo(Double.POSITIVE_INFINITY);
    }

    @Test
    void returnsWeightOfTheEdgeWhenVerticesAreConnected() {
        DirectedWeightedGraph graph = new DirectedWeightedGraph(2, 1);
        graph.addEdge(0, 1, 100);
        double[][] result = calculateAllPairsShortestPath(graph);
        assertThat(result[0][1]).isEqualTo(100);
    }

    @Test
    void returnsSumOfWeightsWhenNoDirectConnection() {
        DirectedWeightedGraph graph = new DirectedWeightedGraph(3, 2);
        graph.addEdge(0, 1, 100);
        graph.addEdge(1, 2, 50);
        double[][] result = calculateAllPairsShortestPath(graph);
        assertThat(result[0][2]).isEqualTo(150);
    }

    @Test
    void returnsShortestPathWhenThereAreMultipleOptions() {
        DirectedWeightedGraph graph = new DirectedWeightedGraph(4, 4);
        // path 0 -> 1 -> 2: 150
        graph.addEdge(0, 1, 100);
        graph.addEdge(1, 2, 50);

        // path 0 -> 3 -> 2: 100
        graph.addEdge(0, 3, 40);
        graph.addEdge(3, 2, 60);

        double[][] result = calculateAllPairsShortestPath(graph);
        assertThat(result[0][2]).isEqualTo(100);
    }

    @Test
    void throwsExceptionWhenThereAreNegativeCycles() {
        DirectedWeightedGraph graph = new DirectedWeightedGraph(4, 4);
        graph.addEdge(0, 1, -1);
        graph.addEdge(1, 0, -1);

        assertThatThrownBy(() -> calculateAllPairsShortestPath(graph)).isInstanceOf(NegativeCycleException.class);
    }

    @Test
    void input_random_10_8() throws FileNotFoundException {
        DirectedWeightedGraph graph = readGraphFromFile("course4/week1/input_random_10_8.txt");

        double[][] paths = calculateAllPairsShortestPath(graph);

        assertThat(min(paths)).isEqualTo(-41);
    }

    @Test
    @Disabled("Slow")
    void assignment() throws FileNotFoundException {
        System.out.println("Graph 1: " + calculateResult(readGraphFromFile("course4/week1/problem_set_graph_1.txt")));
        System.out.println("Graph 2: " + calculateResult(readGraphFromFile("course4/week1/problem_set_graph_2.txt")));
        System.out.println("Graph 3: " + calculateResult(readGraphFromFile("course4/week1/problem_set_graph_3.txt")));
    }

    private static String calculateResult(DirectedWeightedGraph graph) {
        try {
            double[][] paths = calculateAllPairsShortestPath(graph);
            return "min(paths) = " + min(paths);
        } catch (NegativeCycleException ex) {
            return "Negative cycle!";
        }
    }

    private static double min(double[][] paths) {
        double min = Double.POSITIVE_INFINITY;
        for (double[] subarray : paths) {
            for (double v : subarray) {
                if (v < min)
                    min = v;
            }
        }
        return min;
    }

    private DirectedWeightedGraph readGraphFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = getScanner(filename);
        String[] meta = scanner.nextLine().split(" ");
        int numberOfVertices = Integer.parseInt(meta[0]);
        int numberOfEdges = Integer.parseInt(meta[1]);
        DirectedWeightedGraph graph = new DirectedWeightedGraph(numberOfVertices, numberOfEdges);
        while (scanner.hasNextLine()) {
            String[] edge = scanner.nextLine().split(" ");
            // input file is 1-based, so we adjust indices of vertices
            int from = Integer.parseInt(edge[0]) - 1;
            int to = Integer.parseInt(edge[1]) - 1;
            int weight = Integer.parseInt(edge[2]);
            graph.addEdge(from, to, weight);
        }
        return graph;
    }
}