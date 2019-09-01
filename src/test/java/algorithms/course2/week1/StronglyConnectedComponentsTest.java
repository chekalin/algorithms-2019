package algorithms.course2.week1;

import algorithms.course2.week1.StronglyConnectedComponents.DirectedGraph;
import algorithms.util.AssignmentInputReader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class StronglyConnectedComponentsTest {

    @Test
    @Disabled("need to run with -Xss16m")
    void assignment() throws FileNotFoundException {
        DirectedGraph graph = AssignmentInputReader.readDirectedGraph("course2/week1/week1_problem_set.txt");

        System.out.println("graph.numberOfEdges() = " + graph.numberOfEdges());
        System.out.println("graph.numberOfVertices() = " + graph.numberOfVertices());
        Set<Set<String>> sccs = graph.getStronglyConnectedComponents();

        System.out.println("Top 5 = " + getTopFiveSizes(sccs));
    }

    @Test
    void testCase1() throws FileNotFoundException {
        DirectedGraph graph = AssignmentInputReader.readDirectedGraph("course2/week1/input_mostlyCycles_10_32.txt");

        Set<Set<String>> sccs = graph.getStronglyConnectedComponents();

        assertThat(getTopFiveSizes(sccs)).contains(11, 10, 5, 4);
    }

    @Test
    void testCase2() throws FileNotFoundException {
        DirectedGraph graph = AssignmentInputReader.readDirectedGraph("course2/week1/input_mostlyCycles_30_800.txt");

        Set<Set<String>> sccs = graph.getStronglyConnectedComponents();

        assertThat(getTopFiveSizes(sccs)).contains(437, 256, 51, 44, 10);
    }

    private List<Integer> getTopFiveSizes(Set<Set<String>> sccs) {
        return sccs.stream()
                .map(Set::size)
                .sorted((s1, s2) -> Integer.compare(s2, s1))
                .limit(5)
                .collect(Collectors.toList());
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
    void topologicalSortReversed() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge("A", "B");

        Map<String, Integer> orders = graph.reversed().topologicalSort();
        assertThat(orders.get("B")).isEqualTo(1);
        assertThat(orders.get("A")).isEqualTo(2);
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
