package algorithms.course3.week1;

import java.util.*;
import java.util.function.Predicate;

class UndirectedWeightedGraph {

    private List<Edge> edges;
    private Set<String> vertices;

    UndirectedWeightedGraph(int expectedNumberOfVertices, int expectedNumberOfEdges) {
        this.edges = new ArrayList<>(expectedNumberOfEdges);
        this.vertices = new HashSet<>(expectedNumberOfVertices);
    }

    void addEdge(String vertex1, String vertex2, int weight) {
        vertices.add(vertex1);
        vertices.add(vertex2);
        edges.add(new Edge(vertex1, vertex2, weight));
    }

    int numberOfVertices() {
        return vertices.size();
    }

    int numberOfEdges() {
        return edges.size();
    }

    List<Edge> findMinimumSpanningTree() {
        Set<String> processedVertices = new HashSet<>();
        List<Edge> minimumSpanningTree = new ArrayList<>();
        for (String vertex : vertices) {
            if (processedVertices.isEmpty()) {
                processedVertices.add(vertex);
            } else {
                Optional<Edge> optionalMinWeightEdgeCrossingFrontier = edges.stream()
                        .filter(crossesFrontier(processedVertices))
                        .min(Comparator.comparingInt(Edge::getWeight));
                if (optionalMinWeightEdgeCrossingFrontier.isEmpty()) break;
                Edge minWeightEdgeCrossingFrontier = optionalMinWeightEdgeCrossingFrontier.get();
                minimumSpanningTree.add(minWeightEdgeCrossingFrontier);
                processedVertices.add(minWeightEdgeCrossingFrontier.vertex1);
                processedVertices.add(minWeightEdgeCrossingFrontier.vertex2);
            }
        }
        return minimumSpanningTree;
    }

    private Predicate<Edge> crossesFrontier(Set<String> processedVertices) {
        return edge -> (processedVertices.contains(edge.vertex1) && !processedVertices.contains(edge.vertex2))
                || (processedVertices.contains(edge.vertex2) && !processedVertices.contains(edge.vertex1));
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

        int getWeight() {
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
