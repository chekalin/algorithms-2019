package algorithms.course3.week4;

import java.util.Arrays;
import java.util.List;

class Knapsack {

    static long solve(List<Element> elements, int capacity) {
        if (elements.isEmpty()) return 0;

        long[][] solutions = new long[elements.size()][capacity + 1];

        Element firstElement = elements.get(0);
        for (int c = 0; c < capacity + 1; c++) {
            solutions[0][c] = firstElement.getWeight() > c ? 0 : firstElement.getValue();
        }

        for (int i = 1; i < elements.size(); i++) {
            Element currentElement = elements.get(i);
            int currentWeight = currentElement.getWeight();

            for (int c = 1; c <= capacity; c++) {
                long solutionWithoutCurrent = solutions[i - 1][c];
                if (currentWeight > c) {
                    solutions[i][c] = solutionWithoutCurrent;
                } else {
                    long solutionWithCurrent = solutions[i - 1][c - currentWeight] + currentElement.getValue();
                    solutions[i][c] = Math.max(solutionWithoutCurrent, solutionWithCurrent);
                }
            }
        }
        return solutions[elements.size() - 1][capacity];
    }

    /**
     * Optimizes previous solution by using only two columns in solutions array. This approach will not allow to
     * reconstruct what is the actual set of elements in optimal solution, but allows to handle larger data sets
     */
    static long solveBig(List<Element> elements, int capacity) {
        if (elements.isEmpty()) return 0;

        long[][] solutions = new long[2][capacity + 1];

        Element firstElement = elements.get(0);
        for (int c = 0; c < capacity + 1; c++) {
            solutions[0][c] = firstElement.getWeight() > c ? 0 : firstElement.getValue();
        }

        for (int i = 1; i < elements.size(); i++) {
            Element currentElement = elements.get(i);
            int currentWeight = currentElement.getWeight();
            for (int c = 1; c <= capacity; c++) {
                long solutionWithoutCurrent = solutions[0][c];
                if (currentWeight > c) {
                    solutions[1][c] = solutionWithoutCurrent;
                } else {
                    long solutionWithCurrent = solutions[0][c - currentWeight] + currentElement.getValue();
                    solutions[1][c] = Math.max(solutionWithoutCurrent, solutionWithCurrent);
                }
            }
            solutions[0] = Arrays.copyOf(solutions[1], capacity + 1);
        }
        return solutions[0][capacity];
    }

    static class Element {
        private int weight;
        private int value;

        Element(int value, int weight) {
            this.weight = weight;
            this.value = value;
        }

        int getWeight() {
            return weight;
        }

        int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "weight=" + weight +
                    ", value=" + value +
                    '}';
        }
    }
}
