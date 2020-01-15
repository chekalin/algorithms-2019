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
            int middle = left + (right - left) / 2;

            int i = 1;
            while (input[middle].isEmpty() && (middle - i >= left || middle + i < right)) {
                if (middle - i >= left && !input[middle - i].isEmpty()) {
                    middle = middle - i;
                } else if (middle + i < right && !input[middle + i].isEmpty()) {
                    middle = middle + i;
                }
                i++;
            }
            if (input[middle].isEmpty()) {
                return -1;
            }

            if (input[middle].equals(target)) {
                return middle;
            } else if (input[middle].compareTo(target) < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }

        }
        return -1;
    }
}
