package algorithms.course1.week3;

import java.util.Arrays;

class QuickSort {

    static boolean DEBUG = false;

    static long sort(int[] input) {
        return sort(input, PivotStrategy.FIRST);
    }

    static long sort(int[] input, PivotStrategy pivotStrategy) {
        return sortRecursive(input, 0, input.length - 1, pivotStrategy);
    }

    private static long sortRecursive(int[] input, int left, int right, PivotStrategy pivotStrategy) {
        if (DEBUG) logInputs(input, left, right);
        if (left >= right) {
            if (DEBUG) {
                System.out.println("left >= right");
                logResults(input, left, right, 0L, 0L, 0);
            }
            return 0L;
        }
        int pivotIndex = pivotStrategy.choosePivot(input, left, right);
        if (DEBUG) System.out.println("pivotIndex = " + pivotIndex);
        swap(input, left, pivotIndex);
        int newPivot = partition(input, left, right);
        long comparisonsLeft = sortRecursive(input, left, newPivot - 1, pivotStrategy);
        long comparisonsRight = sortRecursive(input, newPivot + 1, right, pivotStrategy);
        int comparisons = right - left;
        if (DEBUG) logResults(input, left, right, comparisonsLeft, comparisonsRight, comparisons);
        return comparisons + comparisonsLeft + comparisonsRight;
    }

    private static void logInputs(int[] input, int left, int right) {
        System.out.println("Starting to sort subarray" + " input=" + Arrays.toString(input) + " left=" + left + " right=" + right);
    }

    private static void logResults(int[] input, int left, int right, long comparisonsLeft, long comparisonsRight, int comparisons) {
        System.out.println("Returning" +
                " input=" + Arrays.toString(input)
                + " left=" + left
                + " right=" + right
                + " comparisons=" + comparisons
                + " comparisonsLeft=" + comparisonsLeft
                + " comparisonsRight=" + comparisonsRight);
    }

    private static int partition(int[] input, int left, int right) {
        int p = input[left];
        int i = left + 1;
        for (int j = left + 1; j <= right; j++) {
            if (input[j] < p) {
                swap(input, j, i);
                i++;
            }
        }
        int newPivotPosition = i - 1;
        swap(input, left, newPivotPosition);
        return newPivotPosition;
    }

    private static void swap(int[] input, int i1, int i2) {
        int temp = input[i1];
        input[i1] = input[i2];
        input[i2] = temp;
    }

    public interface PivotStrategy {

        int choosePivot(int[] input, int left, int right);

        PivotStrategy FIRST = (input, left, right) -> left;

        PivotStrategy LAST = (input, left, right) -> right;

        PivotStrategy MEDIAN = (input, leftIndex, rightIndex) -> {
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
            int left = input[leftIndex];
            int middle = input[middleIndex];
            int right = input[rightIndex];
            if (left > middle) {
                if (middle > right) {
                    return middleIndex;
                } else if (left > right) {
                    return rightIndex;
                } else {
                    return leftIndex;
                }
            } else {
                if (left > right) {
                    return leftIndex;
                } else if (middle > right) {
                    return rightIndex;
                } else {
                    return middleIndex;
                }
            }
        };
    }

}
