package leetcode.contest.biweekly16;

class ReplaceElementsWithGreatest {

    int[] replaceElements(int[] arr) {
        if (arr == null || arr.length < 1) throw new IllegalArgumentException();
        int currentMax = arr[arr.length - 1];
        arr[arr.length - 1] = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            int current = arr[i];
            arr[i] = currentMax;
            currentMax = Math.max(current, currentMax);
        }
        return arr;
    }
}
