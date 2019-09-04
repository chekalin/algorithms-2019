package algorithms.course2.week2;

import java.util.*;
import java.util.stream.Collectors;

public class ShortestPath {
    public static class WeightedGraph {
        private List<Edge> edges = new LinkedList<>();
        private Set<String> vertices = new HashSet<>();

        public void addEdge(String vertexFrom, String vertexTo, int weight) {
            edges.add(new Edge(vertexFrom, vertexTo, weight));
            vertices.add(vertexFrom);
            vertices.add(vertexTo);
        }

        public int numberOfEdges() {
            return vertices.size();
        }

        public int numberOfVertices() {
            return edges.size();
        }

        Map<String, Integer> shortestPaths(String start) {
            Map<String, Integer> shortestPaths = new HashMap<>();
            shortestPaths.put(start, 0);
            Set<String> explored = new HashSet<>();
            explored.add(start);
            List<Edge> edgesCrossingFrontier = getEdgesCrossingFrontier(explored);
            while (!edgesCrossingFrontier.isEmpty()) {
                Edge minimalEdge = edgesCrossingFrontier.stream()
                        .min(Comparator.comparingInt(e -> shortestPaths.get(e.from) + e.weight))
                        .get();
                explored.add(minimalEdge.to);
                shortestPaths.put(minimalEdge.to, shortestPaths.get(minimalEdge.from) + minimalEdge.weight);
                edgesCrossingFrontier = getEdgesCrossingFrontier(explored);
            }
            ;
            return shortestPaths;
        }

        private List<Edge> getEdgesCrossingFrontier(Set<String> explored) {
            return edges.stream().filter(edge -> explored.contains(edge.from) && !explored.contains(edge.to)).collect(Collectors.toList());
        }

        public static class Edge {
            private String from;
            private String to;
            private int weight;

            Edge(String from, String to, int weight) {
                this.from = from;
                this.to = to;
                this.weight = weight;
            }
        }
    }


}
