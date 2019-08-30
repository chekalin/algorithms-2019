package algorithms.course2.week1;

import java.util.*;
import java.util.stream.Collectors;

class StronglyConnectedComponents {
    static class DirectedGraph {

        private Map<String, List<String>> edges = new HashMap<>();

        void addEdge(String edge1, String edge2) {
            edges.putIfAbsent(edge1, new LinkedList<>());
            edges.putIfAbsent(edge2, new LinkedList<>());
            edges.get(edge1).add(edge2);
        }

        int numberOfEdges() {
            return edges.size();
        }

        int numberOfVertices() {
            return edges.values().stream().map(List::size).reduce(0, Integer::sum);
        }

        Map<String, Integer> topologicalSort() {
            Map<String, Integer> edgeOrders = new HashMap<>();
            Set<String> explored = new HashSet<>();
            for (String edge : edges.keySet()) {
                if (!explored.contains(edge)) {
                    depthFirstSearchTopologicalSort(edge, numberOfEdges(), explored, edgeOrders);
                }
            }
            return edgeOrders;
        }

        private Integer depthFirstSearchTopologicalSort(String edge, Integer maxLabel, Set<String> explored, Map<String, Integer> edgeOrders) {
            explored.add(edge);
            for (String adjacent : edges.get(edge)) {
                if (!explored.contains(adjacent)) {
                    maxLabel = depthFirstSearchTopologicalSort(adjacent, maxLabel, explored, edgeOrders);
                }
            }
            edgeOrders.put(edge, maxLabel);
            return maxLabel - 1;
        }

        Set<Set<String>> getStronglyConnectedComponents() {
            return edges.keySet().stream().map(Set::of).collect(Collectors.toSet());
        }
    }
}
