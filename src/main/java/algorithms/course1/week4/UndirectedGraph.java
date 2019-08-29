package algorithms.course1.week4;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UndirectedGraph {

    static boolean DEBUG = false;

    private List<Edge> edges = new ArrayList<>();
    private List<Vertex> vertices = new ArrayList<>();

    public void addVertex(String edge1id, String edge2id) {
        Edge edge1 = findOrAddEdge(edge1id);
        Edge edge2 = findOrAddEdge(edge2id);
        Vertex vertex = new Vertex(edge1, edge2);
        edge1.vertices.add(vertex);
        edge2.vertices.add(vertex);
        vertices.add(vertex);
    }

    public int numberOfEdges() {
        return edges.size();
    }

    public boolean isConnected(String edge1id, String edge2id) {
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

    int findMinCut(int iterations) {
        int minCut = Integer.MAX_VALUE;
        for (int i = 0; i < iterations; i++) {
            if (DEBUG) System.out.println("Iteration " + i + "/" + iterations);
            UndirectedGraph copy = this.deepCopy();
            while (copy.numberOfEdges() > 2) {
                if(DEBUG) System.out.println("before contracting = " + copy);
                copy.contractRandomVertex();
                if(DEBUG) System.out.println("after contracting = " + copy);
            }
            int cut = copy.numberOfVertices();
            if (DEBUG) System.out.println("cut=" + cut);
            if (cut < minCut) {
                minCut = cut;
            }
        }
        return minCut;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "edges=" + edges +
                ", vertices=" + vertices +
                '}';
    }

    public int numberOfVertices() {
        return vertices.size();
    }

    UndirectedGraph deepCopy() {
        UndirectedGraph newGraph = new UndirectedGraph();
        this.vertices.forEach(v -> newGraph.addVertex(v.edge1.id, v.edge2.id));
        return newGraph;
    }

    void contractVertex(String edge1id, String edge2id) {
        Edge edge1 = findEdge(edge1id).orElseThrow(() -> new IllegalArgumentException("Edge " + edge1id + "not found"));
        Edge edge2 = findEdge(edge2id).orElseThrow(() -> new IllegalArgumentException("Edge " + edge2id + "not found"));
        edges.remove(edge1);
        edges.remove(edge2);

        Edge combined = new Edge(edge1.id + "_" + edge2.id);
        combined.vertices = Stream.concat(edge1.vertices.stream(),edge2.vertices.stream()).collect(Collectors.toList());
        edges.add(combined);

        this.vertices.forEach(v -> v.replaceEdgesWithCombinedEdge(edge1, edge2, combined));
        this.vertices = this.vertices.stream().filter(Vertex::isNotLoop).collect(Collectors.toList());
        combined.vertices = combined.vertices.stream().filter(Vertex::isNotLoop).collect(Collectors.toList());
    }

    void contractRandomVertex() {
        Vertex randomVertex = vertices.get(new Random().nextInt(vertices.size()));
        if (DEBUG) System.out.println("picked randomVertex for contraction = " + randomVertex);
        contractVertex(randomVertex.edge1.id, randomVertex.edge2.id);
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

        private void replaceEdgesWithCombinedEdge(Edge edge1, Edge edge2, Edge combined) {
            if (this.edge1.equals(edge1) || this.edge1.equals(edge2)) {
                this.edge1 = combined;
            }
            if (this.edge2.equals(edge1) || this.edge2.equals(edge2)) {
                this.edge2 = combined;
            }
        }

        boolean isNotLoop() {
            return !edge1.equals(edge2);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return Objects.equals(edge1, vertex.edge1) &&
                    Objects.equals(edge2, vertex.edge2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(edge1, edge2);
        }

        @Override
        public String toString() {
            return "(" + edge1.id + ", " + edge2.id + ")";
        }
    }
}
