package algorithms.course2.week2;

import algorithms.util.AssignmentInputReader;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Map;

import static algorithms.course2.week2.ShortestPath.WeightedGraph;
import static org.assertj.core.api.Assertions.assertThat;

class ShortestPathTest {

    @Test
    void calculatesSinglePathAsShortest() {
        WeightedGraph graph = new WeightedGraph();
        graph.addEdge("A", "B", 10);

        Map<String, Integer> shortestPaths = graph.shortestPaths("A");

        assertThat(shortestPaths).containsKey("A");
        assertThat(shortestPaths.get("A")).isEqualTo(0);

        assertThat(shortestPaths).containsKey("B");
        assertThat(shortestPaths.get("B")).isEqualTo(10);
    }

    @Test
    void calculatesShortestPath() {
        WeightedGraph graph = new WeightedGraph();
        graph.addEdge("s", "v", 1);
        graph.addEdge("s", "w", 4);
        graph.addEdge("v", "w", 2);
        graph.addEdge("v", "t", 6);
        graph.addEdge("w", "t", 3);

        Map<String, Integer> shortestPaths = graph.shortestPaths("s");

        assertThat(shortestPaths)
                .containsEntry("s", 0)
                .containsEntry("v", 1)
                .containsEntry("w", 3)
                .containsEntry("t", 6);
    }

    @Test
    void testCase_10_16() throws FileNotFoundException {
        WeightedGraph graph = AssignmentInputReader.readWeightedGraph("course2/week2/input_random_10_16.txt");
        Map<String, Integer> shortestPaths = graph.shortestPaths("1");
        assertThat(shortestPaths)
                .containsEntry("7", 588)
                .containsEntry("37", 405)
                .containsEntry("59", 675)
                .containsEntry("82", 521)
                .containsEntry("99", 909)
                .containsEntry("115", 328)
                .containsEntry("133", 418)
                .containsEntry("165", 957)
                .containsEntry("188", 830)
                .containsEntry("197", 839);
    }

    @Test
    void testCase_1_4() throws FileNotFoundException {
        WeightedGraph graph = AssignmentInputReader.readWeightedGraph("course2/week2/input_random_1_4.txt");
        Map<String, Integer> shortestPaths = graph.shortestPaths("1");
        assertThat(shortestPaths)
                .containsEntry("7", 253)
                .containsEntry("37", 172)
                .containsEntry("59", 197)
                .containsEntry("82", 242)
                .containsEntry("99", 331)
                .containsEntry("115", 402)
                .containsEntry("133", 143)
                .containsEntry("165", 272)
                .containsEntry("188", 249)
                .containsEntry("197", 265);
    }

    @Test
    void assignment() throws FileNotFoundException {
        WeightedGraph graph = AssignmentInputReader.readWeightedGraph("course2/week2/week2_problem_set.txt");
        assertThat(graph.numberOfEdges()).isEqualTo(200);

        Map<String, Integer> shortestPaths = graph.shortestPaths("1");
        System.out.println(
                shortestPaths.get("7") + "," +
                        shortestPaths.get("37") + "," +
                        shortestPaths.get("59") + "," +
                        shortestPaths.get("82") + "," +
                        shortestPaths.get("99") + "," +
                        shortestPaths.get("115") + "," +
                        shortestPaths.get("133") + "," +
                        shortestPaths.get("165") + "," +
                        shortestPaths.get("188") + "," +
                        shortestPaths.get("197")
        );
    }
}