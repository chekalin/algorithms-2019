package algorithms.course4.week2;

import java.util.LinkedList;
import java.util.List;

class CombinationGenerator {
    static List<int[]> generateCombinations(int start, int end) {
        List<int[]> combinations = new LinkedList<>();
        addCombinations(combinations, new int[0], start, end);
        return combinations;
    }

    static List<int[]> generateCombinationsWithFirstElementIncluded(int start, int end) {
        List<int[]> combinations = new LinkedList<>();
        addCombinations(combinations, new int[]{start}, start + 1, end);
        return combinations;
    }

    private static void addCombinations(List<int[]> combinations, int[] prefix, int elementsStart, int elementsEnd) {
        if (prefix.length != 0) {
            combinations.add(prefix);
        }
        for (int i = elementsStart; i < elementsEnd; i++) {
            int[] newPrefix = addElement(prefix, i);
            addCombinations(combinations, newPrefix, i + 1, elementsEnd);
        }
    }

    private static int[] addElement(int[] prefix, int i) {
        int[] newPrefix = new int[prefix.length + 1];
        System.arraycopy(prefix, 0, newPrefix, 0, prefix.length);
        newPrefix[prefix.length] = i;
        return newPrefix;
    }
}
