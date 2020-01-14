package ctci.sorting;

/**
 * 10.S Sparse Search:
 * Given a sorted array of strings that is interspersed with empty strings, write a method to find the location of a
 * given string.
 */
public class SparseSearch {
    static int search(String[] input, String target) {
        int left = 0;
        int right = input.length - 1;
        while (left <= right) {
            int median = left + (right - left) / 2;

            int i = 1;
            while (input[median].isEmpty() && (median - i >= left || median + i < right)) {
                if (median - i >= left && !input[median - i].isEmpty()) {
                    median = median - i;
                } else if (median + i < right && !input[median + i].isEmpty()) {
                    median = median + i;
                }
                i++;
            }
            if (input[median].isEmpty()) {
                return -1;
            }

            if (input[median].equals(target)) {
                return median;
            } else if (input[median].compareTo(target) < 0) {
                left = median + 1;
            } else {
                right = median - 1;
            }

        }
        return -1;
    }
}
