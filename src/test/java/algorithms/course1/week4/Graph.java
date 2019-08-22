package algorithms.course1.week4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Graph {

    List<Edge> edges = new ArrayList<>();
    List<Vertex> vertices = new ArrayList<>();

    void addVertex(String edge1id, String edge2id) {
        Edge edge1 = findOrAddEdge(edge1id);
        Edge edge2 = findOrAddEdge(edge2id);
        Vertex vertex = new Vertex(edge1, edge2);
        edge1.vertices.add(vertex);
        edge2.vertices.add(vertex);
        vertices.add(vertex);
    }

    boolean isConnected(String edge1id, String edge2id) {
        Optional<Edge> edge1 = findEdge(edge1id);
        Optional<Edge> edge2 = findEdge(edge2id);
        return edge1.isPresent()
                && edge2.isPresent()
                && edge1.get().isConnected(edge2.get());
    }

    private Edge findOrAddEdge(String id) {
        return findEdge(id).orElseGet(() -> {
                    Edge newEdge = new Edge(id);
                    edges.add(newEdge);
                    return newEdge;
                });
    }

    private Optional<Edge> findEdge(String id) {
        return edges.stream()
                .filter(e -> e.id.equals(id))
                .findAny();
    }

    @Override
    public String toString() {
        return "Graph{" +
                "edges=" + edges +
                ", vertices=" + vertices +
                '}';
    }

    public static class Edge {
        String id;
        List<Vertex> vertices = new ArrayList<>();

        Edge(String id) {
            this.id = id;
        }

        boolean isConnected(Edge that) {
            return this.vertices.stream().anyMatch(v -> v.connects(this, that));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return Objects.equals(id, edge.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "id='" + id + '\'' +
                    ", vertices=" + vertices +
                    '}';
        }
    }

    public static class Vertex {
        Edge edge1;
        Edge edge2;

        Vertex(Edge edge1, Edge edge2) {
            this.edge1 = edge1;
            this.edge2 = edge2;
        }

        boolean connects(Edge e1, Edge e2) {
            return edge1.equals(e1) && edge2.equals(e2)
                    || (edge2.equals(e1) && edge1.equals(e2));
        }

        @Override
        public String toString() {
            return "(" + edge1.id + ", " + edge2.id + ")";
        }
    }
}
