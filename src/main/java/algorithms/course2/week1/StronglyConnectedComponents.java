package algorithms.course2.week1;

import org.assertj.core.util.Sets;

import java.util.*;
import java.util.stream.Collectors;

class StronglyConnectedComponents {
    static class DirectedGraph {

        private Map<String, List<String>> edges = new HashMap<>();

        void addEdge(String edge1, String edge2) {
            edges.putIfAbsent(edge1, new ArrayList<>());
            edges.get(edge1).add(edge2);
        }

        int numberOfEdges() {
            return edges.size();
        }

        int numberOfVertices() {
            return edges.values().stream().map(List::size).reduce(0, Integer::sum);
        }

        Set<Set<String>> getStronglyConnectedComponents() {
            Set<Set<String>> components = new HashSet<>();
            components.add(edges.keySet());
            components.add(edges.values().stream().flatMap(Collection::stream).collect(Collectors.toSet()));
            return components;
        }
    }
}
