package algorithms.course3.week4;

import java.util.List;
import java.util.stream.IntStream;

class Knapsack {

    static long solve(List<Element> elements, int capacity) {
        if (elements.isEmpty()) return 0;

        long[][] solutions = new long[elements.size()][capacity + 1];
        Element firstElement = elements.get(0);
        IntStream.range(0, capacity + 1)
                .forEach(c -> solutions[0][c] = firstElement.weight > c ? 0 : firstElement.value);

        for (int i = 1; i < elements.size(); i++) {
            for (int c = 1; c <= capacity; c++) {
                Element currentElement = elements.get(i);
                int currentWeight = currentElement.weight;
                int currentValue = currentElement.value;

                long solutionWithoutCurrent = solutions[i - 1][c];
                if (currentWeight > c) {
                    solutions[i][c] = solutionWithoutCurrent;
                } else {
                    long solutionWithCurrent = solutions[i - 1][c - currentWeight] + currentValue;
                    solutions[i][c] = Math.max(solutionWithoutCurrent, solutionWithCurrent);
                }
            }
        }
        return solutions[elements.size() - 1][capacity];
    }

    static class Element {
        private int weight;
        private int value;

        Element(int value, int weight) {
            this.weight = weight;
            this.value = value;
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
