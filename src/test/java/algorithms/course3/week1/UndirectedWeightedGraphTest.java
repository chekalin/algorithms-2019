package algorithms.course3.week1;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import static algorithms.util.AssignmentInputReader.getScanner;
import static org.assertj.core.api.Assertions.assertThat;

class UndirectedWeightedGraphTest {

    @Test
    void assignment() throws FileNotFoundException {
        UndirectedWeightedGraph graph = readGraph("course3/week1/problem_set_2.txt");
        List<UndirectedWeightedGraph.Edge> minimumSpanningTree = graph.findMinimumSpanningTree();
        long totalWeight = minimumSpanningTree.stream().map(UndirectedWeightedGraph.Edge::getWeight).mapToLong(Long::valueOf).sum();
        System.out.println("totalWeight = " + totalWeight);
    }

    @Test
    void input_random_1_10_question_3() throws FileNotFoundException {
        UndirectedWeightedGraph graph = readGraph("course3/week1/input_random_1_10_question_3.txt");
        List<UndirectedWeightedGraph.Edge> minimumSpanningTree = graph.findMinimumSpanningTree();
        long totalWeight = minimumSpanningTree.stream().map(UndirectedWeightedGraph.Edge::getWeight).mapToLong(Long::valueOf).sum();
        assertThat(totalWeight).isEqualTo(-7430);
    }

    private UndirectedWeightedGraph readGraph(String filename) throws FileNotFoundException {
        Scanner scanner = getScanner(filename);
        String[] sizes = scanner.nextLine().split(" ");
        int numberOfVertices = Integer.parseInt(sizes[0]);
        int numberOfEdges = Integer.parseInt(sizes[1]);

        UndirectedWeightedGraph graph = new UndirectedWeightedGraph(numberOfVertices, numberOfEdges);
        while (scanner.hasNextLine()) {
            String[] vertexArray = scanner.nextLine().split(" ");
            graph.addEdge(vertexArray[0], vertexArray[1], Integer.parseInt(vertexArray[2]));
        }

        System.out.println("read graph with " + graph.numberOfEdges() + " edges and " + graph.numberOfVertices() + " vertices");

        return graph;
    }
}