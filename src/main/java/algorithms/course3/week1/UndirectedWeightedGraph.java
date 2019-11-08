package algorithms.course3.week1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UndirectedWeightedGraph {

    private List<Edge> edges;
    private Set<String> vertices;

    public UndirectedWeightedGraph(int expectedNumberOfVertices, int expectedNumberOfEdges) {
        this.edges = new ArrayList<>(expectedNumberOfEdges);
        this.vertices = new HashSet<>(expectedNumberOfVertices);
    }

    public void addEdge(String vertex1, String vertex2, int weight) {
        vertices.add(vertex1);
        vertices.add(vertex2);
        edges.add(new Edge(vertex1, vertex2, weight));
    }

    public int numberOfVertices() {
        return vertices.size();
    }

    public int numberOfEdges() {
        return edges.size();
    }

    public Set<String> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public static class Edge {
        private String vertex1;
        private String vertex2;
        private int weight;

        Edge(String vertex1, String to, int weight) {
            this.vertex1 = vertex1;
            this.vertex2 = to;
            this.weight = weight;
        }

        public String getVertex1() {
            return vertex1;
        }

        public String getVertex2() {
            return vertex2;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "vertex1='" + vertex1 + '\'' +
                    ", vertex2='" + vertex2 + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}
