package algorithms.course3.week2;

import algorithms.course3.week1.UndirectedWeightedGraph;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Clustering {

    static int getMaximumSpacingForCluster(UndirectedWeightedGraph graph, int numberOfClusters) {
        Set<String> vertices = graph.getVertices();
        UnionFind<String> unionFind = UnionFind.of(vertices);
        graph.getEdges().sort(Comparator.comparingInt(UndirectedWeightedGraph.Edge::getWeight));
        for (UndirectedWeightedGraph.Edge edge : graph.getEdges()) {
            unionFind.union(edge.getVertex1(), edge.getVertex2());
            if (unionFind.numberOfSets() == numberOfClusters - 1) {
                return edge.getWeight();
            }
        }
        return -1;
    }

    static int findNumberOfClustersWithMinSpacing(int[] vertexAddresses, int minSpacing) {
        UnionFind<Integer> unionFind = UnionFind.of(IntStream.range(0, vertexAddresses.length).boxed().collect(Collectors.toList()));
        for (int i = 0; i < vertexAddresses.length; i++) {
            for (int j = i; j < vertexAddresses.length; j++) {
                int distance = hammingDistance(vertexAddresses[i], vertexAddresses[j]);
                if (distance < minSpacing) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.numberOfSets();
    }

    static int hammingDistance(int vertexAddress1, int vertexAddress2) {
        return Integer.bitCount(vertexAddress1 ^ vertexAddress2);
    }
}
