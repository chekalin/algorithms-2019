package ctci.sorting;

/**
 * Search in Rotated Array: Given a sorted array of n integers that has been rotated an unknown number of times, write
 * code to find an element in the array. You may assume that the array was originally sorted in increasing order.
 */
public class SearchInRotatedArray {

    static int search(int[] array, int target) {
        return binarySearchWithPivot(array, target, findPivot(array));
    }

    private static int binarySearchWithPivot(int[] array, int target, int pivot) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            int middleWithPivot = (middle + pivot) % array.length;
            if (array[middleWithPivot] == target) {
                return middleWithPivot;
            } else if (array[middleWithPivot] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }

    private static int findPivot(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (array[middle] > array[0]) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return left + 1;
    }
}
