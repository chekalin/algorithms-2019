package algorithms.course1.week4;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

class GraphTest {

    @Test
    void createsGraph() {
        Graph graph = new Graph();
        graph.addVertex("A", "B");

        assertThat(graph.edges).hasSize(2);
        assertThat(graph.vertices).hasSize(1);
        assertThat(graph.isConnected("A", "B")).isTrue();
    }

    @Test
    void readsGraphFromAFile() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("course1/week4/week4_problem_set.txt").getFile());
        Scanner scanner = new Scanner(file);
        Graph graph = new Graph();
        while (scanner.hasNextLine()) {
            String[] ids = scanner.nextLine().split("\t");
            String edge1Id = ids[0];
            for (int i = 1; i < ids.length; i++) {
                String edge2Id = ids[i];
                if (!graph.isConnected(edge1Id, edge2Id)) {
                    graph.addVertex(edge1Id, edge2Id);
                }
            }

        }
        assertThat(graph.edges).hasSize(200);
        Optional<Graph.Edge> edge1 = graph.edges.stream().filter(e -> e.id.equals("1")).findAny();
        assertThat(edge1).isPresent();
        assertThat(edge1.get().vertices).hasSize(24);

    }
}