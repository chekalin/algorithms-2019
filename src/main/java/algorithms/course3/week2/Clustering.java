package algorithms.course3.week2;

import algorithms.course3.week1.UndirectedWeightedGraph;

import java.util.Comparator;
import java.util.Set;

class Clustering {

    static int getMaximumSpacingForCluster(UndirectedWeightedGraph graph, int numberOfClusters) {
        Set<String> vertices = graph.getVertices();
        UnionFind unionFind = UnionFind.of(vertices);
        graph.getEdges().sort(Comparator.comparingInt(UndirectedWeightedGraph.Edge::getWeight));
        for (UndirectedWeightedGraph.Edge edge : graph.getEdges()) {
            unionFind.union(edge.getVertex1(), edge.getVertex2());
            if (unionFind.numberOfSets() == numberOfClusters - 1) {
                return edge.getWeight();
            }
        }
        return -1;
    }

}
