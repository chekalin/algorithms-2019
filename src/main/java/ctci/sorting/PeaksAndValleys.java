package ctci.sorting;

/**
 * 10.11 Peaks and Valleys:
 * In an array of integers, a "peak" is an element which is greater than or equal to the adjacent integers and a
 * "valley" is an element which is less than or equal to the adjacent integers.
 * For example, in the array {S, 8, 6, 2, 3, 4, 6}, {8, 6} are peaks and {S, 2} are valleys.
 * Given an array of integers, sort the array into an alternating sequence of peaks and valleys.
 */
public class PeaksAndValleys {

    void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (i % 2 == 0 && array[i] < array[i + 1]) {
                swap(array, i, i + 1);
            } else if (i % 2 == 1 && array[i] > array[i + 1]) {
                swap(array, i, i + 1);
            }
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
