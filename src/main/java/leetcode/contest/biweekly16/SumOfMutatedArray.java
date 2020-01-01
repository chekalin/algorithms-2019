package leetcode.contest.biweekly16;

import java.util.Arrays;

class SumOfMutatedArray {

    int findBestValue(int[] arr, int target) {
        int currentMax = Arrays.stream(arr).max().orElseThrow();
        int currentMin = 0;
        int minDelta = Integer.MAX_VALUE;
        int bestGuess = currentMax;
        while (currentMin <= currentMax) {
            int median = currentMin + (currentMax - currentMin) / 2;
            int sumWithReplacement = sumWithReplacement(arr, median);
            int currentDelta = Math.abs(sumWithReplacement - target);
            if (currentDelta < minDelta || (currentDelta == minDelta && bestGuess > median)) {
                minDelta = currentDelta;
                bestGuess = median;
            }
            if (sumWithReplacement > target) {
                currentMax = median - 1;
            } else {
                currentMin = median + 1;
            }
        }
        return bestGuess;
    }

    private int sumWithReplacement(int[] arr, int i) {
        int[] temp = arr.clone();
        for (int j = 0; j < temp.length; j++) {
            if (temp[j] > i) {
                temp[j] = i;
            }
        }
        return Arrays.stream(temp).sum();
    }
}
