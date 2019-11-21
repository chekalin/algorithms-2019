package algorithms.course1.course4.week2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TravelingSalesmanProblem {

    double minimumCostTour(List<Vertex> vertices) {
        return 0;
    }

    static Set<int[]> generateCombinations(int firstElement, int[] elements, int size) {
        Set<int[]> combinations = new HashSet<>();
        int[] prefix = new int[]{firstElement};
        addCombinations(combinations, prefix, elements, 0, elements.length, size);
        return combinations;
    }

    private static void addCombinations(Set<int[]> combinations, int[] prefix, int[] elements, int elementsStart, int elementsEnd, int size) {
        if (prefix.length == size) {
            combinations.add(prefix);
        } else {
            for (int i = elementsStart; i < elementsEnd; i++) {
                int element = elements[i];
                int[] newPrefix = addElementToPrefix(prefix, element);
                addCombinations(combinations, newPrefix, elements, i + 1, elementsEnd, size);
            }
        }
    }

    private static int[] addElementToPrefix(int[] prefix, int element) {
        int[] newPrefix = new int[prefix.length + 1];
        System.arraycopy(prefix, 0, newPrefix, 0, prefix.length);
        newPrefix[newPrefix.length - 1] = element;
        return newPrefix;
    }

    static double calculateDistance(Vertex v1, Vertex v2) {
        return Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2));
    }

    static class Vertex {

        private double x;
        private double y;

        Vertex(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
