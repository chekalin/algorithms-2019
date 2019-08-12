package algorithms.course1.week2;

import java.util.Arrays;

class InversionCounter {

    static long countInversions(int[] input) {
        return sortAndCountInversions(input).count;
    }

    static ArrayAndCount sortAndCountInversions(int[] input) {
        if (input.length <= 1) {
            return arrayAndCount(input, 0);
        }
        int[] left = Arrays.copyOfRange(input, 0, input.length / 2);
        ArrayAndCount sortedLeftAndLeftInversions = sortAndCountInversions(left);
        long leftInversions = sortedLeftAndLeftInversions.count;
        int[] sortedLeft = sortedLeftAndLeftInversions.sortedInput;

        int[] right = Arrays.copyOfRange(input, input.length / 2, input.length);
        ArrayAndCount sortedRightAndRightInversions = sortAndCountInversions(right);
        long rightInversions = sortedRightAndRightInversions.count;
        int[] sortedRight = sortedRightAndRightInversions.sortedInput;

        ArrayAndCount sortedArrayAndSplitInversions = mergeAndCountSplitInversions(sortedLeft, sortedRight);
        long splitInversions = sortedArrayAndSplitInversions.count;
        int[] sortedInput = sortedArrayAndSplitInversions.sortedInput;

        return arrayAndCount(sortedInput, leftInversions + rightInversions + splitInversions);
    }

    static ArrayAndCount mergeAndCountSplitInversions(int[] sortedLeft, int[] sortedRight) {
        int i = 0;
        int j = 0;
        int inversions = 0;
        int n = sortedLeft.length + sortedRight.length;
        int[] result = new int[n];
        for (int k = 0; k < n; k++) {
            if (i == sortedLeft.length) {
                result[k] = sortedRight[j];
                j++;
            } else if (j == sortedRight.length) {
                result[k] = sortedLeft[i];
                i++;
            } else if (sortedLeft[i] < sortedRight[j]) {
                result[k] = sortedLeft[i];
                i++;
            } else {
                result[k] = sortedRight[j];
                j++;
                inversions = inversions + (n / 2 - i);
            }
        }
        return arrayAndCount(result, inversions);
    }

    static ArrayAndCount arrayAndCount(int[] sortedInput, long count) {
        return new ArrayAndCount(sortedInput, count);
    }

    private static class ArrayAndCount {
        long count;
        int[] sortedInput;

        public ArrayAndCount(int[] sortedInput, long count) {
            this.count = count;
            this.sortedInput = sortedInput;
        }
    }
}
