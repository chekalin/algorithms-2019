package algorithms.course3.week1;

import java.util.*;
import java.util.function.Predicate;

class MinimumSpanningTree {

    static List<UndirectedWeightedGraph.Edge> findMinimumSpanningTree(UndirectedWeightedGraph graph) {
        Set<String> processedVertices = new HashSet<>();
        List<UndirectedWeightedGraph.Edge> minimumSpanningTree = new ArrayList<>();
        for (String vertex : graph.getVertices()) {
            if (processedVertices.isEmpty()) {
                processedVertices.add(vertex);
            } else {
                Optional<UndirectedWeightedGraph.Edge> optionalMinWeightEdgeCrossingFrontier = graph.getEdges().stream()
                        .filter(crossesFrontier(processedVertices))
                        .min(Comparator.comparingInt(UndirectedWeightedGraph.Edge::getWeight));
                if (optionalMinWeightEdgeCrossingFrontier.isEmpty()) break;
                UndirectedWeightedGraph.Edge minWeightEdgeCrossingFrontier = optionalMinWeightEdgeCrossingFrontier.get();
                minimumSpanningTree.add(minWeightEdgeCrossingFrontier);
                processedVertices.add(minWeightEdgeCrossingFrontier.getVertex1());
                processedVertices.add(minWeightEdgeCrossingFrontier.getVertex2());
            }
        }
        return minimumSpanningTree;
    }

    private static Predicate<UndirectedWeightedGraph.Edge> crossesFrontier(Set<String> processedVertices) {
        return edge -> (processedVertices.contains(edge.getVertex1()) && !processedVertices.contains(edge.getVertex2()))
                || (processedVertices.contains(edge.getVertex2()) && !processedVertices.contains(edge.getVertex1()));
    }
}
