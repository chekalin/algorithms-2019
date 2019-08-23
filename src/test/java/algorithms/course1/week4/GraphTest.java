package algorithms.course1.week4;

import algorithms.course1.util.AssignmentInputReader;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

class GraphTest {

    @Test
    void createsGraph() {
        Graph graph = new Graph();
        graph.addVertex("A", "B");

        assertThat(graph.numberOfEdges()).isEqualTo(2);
        assertThat(graph.numberOfVertices()).isEqualTo(1);
        assertThat(graph.isConnected("A", "B")).isTrue();
    }

    @Test
    void makesADeepCopyOfTheGraph() {
        Graph original = new Graph();
        original.addVertex("A", "B");

        Graph copy = original.deepCopy();
        copy.addVertex("B", "C");

        assertThat(original.numberOfEdges()).isEqualTo(2);
        assertThat(original.numberOfVertices()).isEqualTo(1);
        assertThat(original.isConnected("A", "B")).isTrue();
        assertThat(original.isConnected("B", "C")).isFalse();

        assertThat(copy.numberOfEdges()).isEqualTo(3);
        assertThat(copy.numberOfVertices()).isEqualTo(2);
        assertThat(copy.isConnected("B", "C")).isTrue();
    }

    @Test
    void readsGraphFromAFile() throws FileNotFoundException {
        Graph graph = AssignmentInputReader.readGraph("course1/week4/week4_problem_set.txt");
        assertThat(graph.numberOfEdges()).isEqualTo(200);
        assertThat(graph.numberOfVertices()).isEqualTo(2517);
    }
}