package ctci.sorting;

/**
 * 10.4 Sorted Search, No Size:
 * You are given an array-like data structure Listy which lacks a size method. It does, however, have an elementAt (i)
 * method that returns the element at index i in 0(1) time. If i is beyond the bounds of the data structure, it
 * returns -1. (For this reason, the data structure only supports positive integers.) Given a Listy which contains
 * sorted, positive integers, find the index at which an element x occurs. If x occurs multiple times, you may return
 * any index.
 */
public class SortedSearchNoSize {

    static int binarySearch(Listy listy, int target) {
        int left = 0;
        int right = Integer.MAX_VALUE;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            int value = listy.get(middle);
            if (value == target) {
                return middle;
            } else if (value > target || value == -1) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }

    static class Listy {
        private int[] values;

        Listy(int[] values) {
            this.values = values;
        }

        int get(int i) {
            if (i >= values.length) {
                return -1;
            } else {
                return values[i];
            }
        }
    }

}
