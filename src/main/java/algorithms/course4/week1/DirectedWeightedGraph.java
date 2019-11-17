package algorithms.course4.week1;

import java.util.*;

class DirectedWeightedGraph {
    private int numberOfVertices;
    private int numberOfEdges;
    private Map<Integer, Map<Integer, Integer>> edgeMap = new HashMap<>();

    DirectedWeightedGraph(int numberOfVertices, int numberOfEdges) {
        this.numberOfVertices = numberOfVertices;
        this.numberOfEdges = numberOfEdges;
        for (int i = 0; i < numberOfVertices; i++) {
             edgeMap.put(i, new HashMap<>());
        }
    }

    public void addEdge(int vertexFrom, int vertexTo, int weight) {
        edgeMap.get(vertexFrom).put(vertexTo, weight);
    }

    public int numberOfEdges() {
        return numberOfEdges;
    }

    public int numberOfVertices() {
        return numberOfVertices;
    }

    Optional<Integer> getEdge(int from, int to) {
        return Optional.ofNullable(edgeMap.get(from).get(to));
    }
}
