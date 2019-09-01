package algorithms.course2.week1;

import java.util.*;
import java.util.stream.Collectors;

public class StronglyConnectedComponents {
    public static class DirectedGraph {

        private Map<String, List<String>> edges = new HashMap<>();

        public void addEdge(String edge1, String edge2) {
            edges.putIfAbsent(edge1, new LinkedList<>());
            edges.putIfAbsent(edge2, new LinkedList<>());
            edges.get(edge1).add(edge2);
        }

        public int numberOfEdges() {
            return edges.size();
        }

        public int numberOfVertices() {
            return edges.values().stream().map(List::size).reduce(0, Integer::sum);
        }

        Map<String, Integer> topologicalSort() {
            Map<String, Integer> edgeOrders = new HashMap<>();
            Set<String> explored = new HashSet<>();
            int maxLabel = numberOfEdges();
            for (String edge : edges.keySet()) {
                if (!explored.contains(edge)) {
                    maxLabel = depthFirstSearchTopologicalSort(edge, maxLabel, explored, edgeOrders);
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

        DirectedGraph reversed() {
            DirectedGraph reversed = new DirectedGraph();
            for (Map.Entry<String, List<String>> entry : edges.entrySet()) {
                String edge = entry.getKey();
                for (String adjacent : entry.getValue()) {
                    reversed.addEdge(adjacent, edge);
                }
            }
            return reversed;
        }

        Set<Set<String>> getStronglyConnectedComponents() {
            DirectedGraph reversed = this.reversed();
            Map<String, Integer> orders = reversed.topologicalSort();
            List<String> sortedEdges = edges.keySet().stream().sorted(Comparator.comparing(orders::get)).collect(Collectors.toList());
            Set<String> explored = new HashSet<>();
            Set<Set<String>> connectedComponents = new HashSet<>();
            for (String edge : sortedEdges) {
                if (!explored.contains(edge)) {
                    HashSet<String> connectedComponent = new HashSet<>();
                    dfsScc(edge, explored, connectedComponent);
                    connectedComponents.add(connectedComponent);
                }
            }
            return connectedComponents;
        }

        private void dfsScc(String edge, Set<String> explored, Set<String> connectedComponent) {
            explored.add(edge);
            connectedComponent.add(edge);
            List<String> vertices = edges.get(edge);
            for (String vertex : vertices) {
                if (!explored.contains(vertex)) {
                    dfsScc(vertex, explored, connectedComponent);
                }
            }
        }
    }
}
