package algorithms.course2.week1;

import algorithms.course2.week1.StronglyConnectedComponents.DirectedGraph;
import algorithms.util.AssignmentInputReader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class StronglyConnectedComponentsTest {

    @Test
    @Disabled("slow")
    void readsInput() throws FileNotFoundException {
        Scanner scanner = AssignmentInputReader.getScanner("course2/week1/week1_problem_set.txt");
        DirectedGraph graph = new DirectedGraph();
        while (scanner.hasNextLine()) {
            String[] edge = scanner.nextLine().split(" ");
            graph.addEdge(edge[0], edge[1]);
        }

        System.out.println("graph.numberOfEdges() = " + graph.numberOfEdges());
        System.out.println("graph.numberOfVertices() = " + graph.numberOfVertices());
    }

    @Test
    void graphOfOneEdgeHasTwoComponents() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge("A", "B");

        Set<Set<String>> stronglyConnectedComponents = graph.getStronglyConnectedComponents();

        assertThat(stronglyConnectedComponents).hasSize(2);
        assertThat(stronglyConnectedComponents).contains(Set.of("A"), Set.of("B"));
    }

    @Test
    void topologicalSort() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge("A", "B");

        Map<String, Integer> orders = graph.topologicalSort();
        assertThat(orders.get("A")).isEqualTo(1);
        assertThat(orders.get("B")).isEqualTo(2);
    }

    @Test
    void topologicalSortWithBranching() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge("A", "B");
        graph.addEdge("B", "D");
        graph.addEdge("A", "C");
        graph.addEdge("C", "D");

        Map<String, Integer> orders = graph.topologicalSort();

        assertThat(orders.get("A")).isEqualTo(1);
        assertThat(orders.get("B")).isBetween(2, 3);
        assertThat(orders.get("C")).isBetween(2, 3);
        assertThat(orders.get("D")).isEqualTo(4);
    }
}
