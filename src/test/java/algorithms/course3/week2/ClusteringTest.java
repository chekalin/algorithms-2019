package algorithms.course3.week2;

import algorithms.course3.week1.UndirectedWeightedGraph;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static algorithms.course3.week2.Clustering.getMaximumSpacingForCluster;
import static algorithms.course3.week2.Clustering.hammingDistance;
import static algorithms.util.AssignmentInputReader.getScanner;
import static org.assertj.core.api.Assertions.assertThat;

class ClusteringTest {

    @Test
    void assignment1() throws FileNotFoundException {
        UndirectedWeightedGraph graph = readAssignment("course3/week2/week2_problem_set_1.txt");
        int maximumSpacing = getMaximumSpacingForCluster(graph, 4);
        System.out.println("Maximum spacing of 4-clustering = " + maximumSpacing);
    }

    @Test
    void input_completeRandom_1_8_problem_set_1() throws FileNotFoundException {
        UndirectedWeightedGraph graph = readAssignment("course3/week2/input_completeRandom_1_8_problem_set_1.txt");
        int maximumSpacing = getMaximumSpacingForCluster(graph, 4);
        assertThat(maximumSpacing).isEqualTo(21);
    }

    private UndirectedWeightedGraph readAssignment(String filename) throws FileNotFoundException {
        Scanner scanner = getScanner(filename);
        int numberOfVertices = Integer.parseInt(scanner.nextLine());
        UndirectedWeightedGraph graph = new UndirectedWeightedGraph(numberOfVertices, (int) Math.pow(numberOfVertices, 2) / 2);

        while (scanner.hasNextLine()) {
            String[] vertexArray = scanner.nextLine().split(" ");
            graph.addEdge(vertexArray[0], vertexArray[1], Integer.parseInt(vertexArray[2]));
        }

        System.out.println("read graph with " + graph.numberOfEdges() + " edges and " + graph.numberOfVertices() + " vertices");
        return graph;
    }

    @Test
    @Disabled("Slow")
    void assignment2() throws FileNotFoundException {
        int[] vertexAddresses = readAddresses("course3/week2/week2_problem_set_2.txt");

        int numberOfClusters = Clustering.findNumberOfClustersWithMinSpacing(vertexAddresses, 3);

        System.out.println("numberOfClusters = " + numberOfClusters);
    }

    @Test
    void input_random_1_4_14_problem_set_2() throws FileNotFoundException {
        int[] vertexAddresses = readAddresses("course3/week2/input_random_1_4_14_problem_set_2.txt");

        int numberOfClusters = Clustering.findNumberOfClustersWithMinSpacing(vertexAddresses, 3);

        assertThat(numberOfClusters).isEqualTo(3);
    }

    private int[] readAddresses(String filename) throws FileNotFoundException {
        Scanner scanner = getScanner(filename);
        String[] header = scanner.nextLine().split(" ");
        int numberOfVertices = Integer.parseInt(header[0]);
        int[] vertexAddresses = new int[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            int address = Integer.parseInt(scanner.nextLine().replaceAll(" ", ""), 2);
            vertexAddresses[i] = address;
        }
        return vertexAddresses;
    }

    @Test
    void hammingDistanceTest() {
        assertThat(hammingDistance(0b01, 0b10)).isEqualTo(2);
        assertThat(hammingDistance(0b001, 0b011)).isEqualTo(1);
        assertThat(hammingDistance(0b010, 0b011)).isEqualTo(1);
        assertThat(hammingDistance(0b010, 0b101)).isEqualTo(3);
        assertThat(hammingDistance(0b010, 0b111)).isEqualTo(2);
        assertThat(hammingDistance(0b011, 0b101)).isEqualTo(2);
        assertThat(hammingDistance(0b011, 0b111)).isEqualTo(1);
        assertThat(hammingDistance(0b011, 0b111)).isEqualTo(1);

        assertThat(hammingDistance(
                0b011001100101111110101101,
                0b010001000101111110100101))
                .isEqualTo(3);
    }
}
