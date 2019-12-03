package algorithms.course4.week4;

import algorithms.course2.week1.StronglyConnectedComponents;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class TwoSat {

    static boolean isSatisfiable(List<Clause> clauses) {
        StronglyConnectedComponents.DirectedGraph graph = new StronglyConnectedComponents.DirectedGraph();
        clauses.forEach(clause -> {
            graph.addEdge(String.valueOf(-clause.left), String.valueOf(clause.right));
            graph.addEdge(String.valueOf(-clause.right), String.valueOf(clause.left));
        });
        Set<Set<String>> scc = graph.getStronglyConnectedComponents();
        Set<Set<Integer>> intSccs = scc.stream()
                .map(components -> components.stream()
                        .mapToInt(Integer::valueOf).boxed()
                        .collect(Collectors.toSet()))
                .collect(Collectors.toSet());

        for (Set<Integer> component : intSccs) {
            for (Integer integer : component) {
                if (component.contains(-integer)) {
                    return false;
                }
            }
        }
        return true;
    }

    static class Clause {
        int left;
        int right;

        Clause(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Clause{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
