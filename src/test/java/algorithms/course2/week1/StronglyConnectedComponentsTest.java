package algorithms.course2.week1;

import algorithms.util.AssignmentInputReader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class StronglyConnectedComponentsTest {

    @Test
    @Disabled("slow")
    void readsInput() throws FileNotFoundException {
        Scanner scanner = AssignmentInputReader.getScanner("course2/week1/week1_problem_set.txt");
        StronglyConnectedComponents.DirectedGraph graph = new StronglyConnectedComponents.DirectedGraph();
        while(scanner.hasNextLine()) {
            String[] edge = scanner.nextLine().split(" ");
            graph.addEdge(edge[0], edge[1]);
        }

        System.out.println("graph.numberOfEdges() = " + graph.numberOfEdges());
        System.out.println("graph.numberOfVertices() = " + graph.numberOfVertices());
    }

    @Test
    void graphOfOneEdgeHasTwoComponents() {
        StronglyConnectedComponents.DirectedGraph graph = new StronglyConnectedComponents.DirectedGraph();
        graph.addEdge("A", "B");

        Set<Set<String>> stronglyConnectedComponents = graph.getStronglyConnectedComponents();

        assertThat(stronglyConnectedComponents).hasSize(2);
        assertThat(stronglyConnectedComponents).contains(Set.of("A"), Set.of("B"));
    }
}
