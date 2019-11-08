package algorithms.course3.week2;

import algorithms.course3.week1.UndirectedWeightedGraph;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static algorithms.course3.week2.Clustering.getMaximumSpacingForCluster;
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
}
