package algorithms.course1.week4;

import algorithms.course1.util.AssignmentInputReader;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

class GraphTest {

    @AfterEach
    void tearDown() {
        Graph.DEBUG = false;
    }

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
    void contractsVertex() {
        Graph graph = new Graph();
        graph.addVertex("A", "B");
        graph.addVertex("B", "C");

        graph.contractVertex("A", "B");
        assertThat(graph.numberOfEdges()).isEqualTo(2);
        assertThat(graph.numberOfVertices()).isEqualTo(1);
        assertThat(graph.isConnected("A_B", "C")).isTrue();
    }

    @Test
    void contractsVertexMaintainingMultipleVertexesBetweenEdges() {
        Graph graph = new Graph();
        graph.addVertex("A", "B");
        graph.addVertex("A", "C");

        graph.contractVertex("B", "C");
        assertThat(graph.numberOfEdges()).isEqualTo(2);
        assertThat(graph.numberOfVertices()).isEqualTo(2);
        assertThat(graph.isConnected("B_C", "A")).isTrue();
    }

    @Test
    void contractsRandomVertex() {
        Graph graph = new Graph();
        graph.addVertex("A", "B");
        graph.addVertex("B", "C");
        graph.addVertex("C", "D");

        graph.contractRandomVertex();
        assertThat(graph.numberOfEdges()).isEqualTo(3);

        graph.contractRandomVertex();
        assertThat(graph.numberOfEdges()).isEqualTo(2);
    }

    @Test
    void readsGraphFromAFile() throws FileNotFoundException {
        Graph graph = AssignmentInputReader.readGraph("course1/week4/week4_problem_set.txt", "\t");
        assertThat(graph.numberOfEdges()).isEqualTo(200);
        assertThat(graph.numberOfVertices()).isEqualTo(2517);
    }

    @Test
    void minCutOfTree() {
        Graph graph = new Graph();
        graph.addVertex("A", "B");
        graph.addVertex("B", "C");
        graph.addVertex("B", "D");

        int minCut = graph.findMinCut(1);
        assertThat(minCut).isEqualTo(1);
    }

    @Test
    void minCutOfCycle() {
        Graph graph = new Graph();
        graph.addVertex("A", "B");
        graph.addVertex("B", "C");
        graph.addVertex("C", "D");
        graph.addVertex("D", "A");

        int minCut = graph.findMinCut(1);
        assertThat(minCut).isEqualTo(2);
    }

    @Test
    void minCutOfMultipleOptions() {
        /*
         * A - B
         * | / |
         * D - C
         * cut is either 3 or 2
         * */

        Graph graph = new Graph();
        graph.addVertex("A", "B");
        graph.addVertex("B", "C");
        graph.addVertex("C", "D");
        graph.addVertex("D", "A");
        graph.addVertex("B", "D");

        int minCut = graph.findMinCut(100);
        assertThat(minCut).isEqualTo(2);
    }

    @Test
    void testCase_1_6() throws FileNotFoundException {
        Graph graph = AssignmentInputReader.readGraph("course1/week4/input_random_1_6.txt", " ");
        int minCut = graph.findMinCut(10);
        assertThat(minCut).isEqualTo(2);
    }

    @Test
    void testCase_4_6() throws FileNotFoundException {
        Graph graph = AssignmentInputReader.readGraph("course1/week4/input_random_4_6.txt", " ");
        int minCut = graph.findMinCut(10);
        assertThat(minCut).isEqualTo(4);
    }

    @Test
    @Ignore // slow
    void testCase_40_200() throws FileNotFoundException {
        Graph graph = AssignmentInputReader.readGraph("course1/week4/input_random_40_200.txt", " ");
        int minCut = graph.findMinCut(100);
        assertThat(minCut).isEqualTo(61);
    }

    @Test
    void assignment() throws FileNotFoundException {
        Graph graph = AssignmentInputReader.readGraph("course1/week4/week4_problem_set.txt", "\t");
        int minCut = graph.findMinCut(100);
        assertThat(minCut).isEqualTo(17);
    }
}