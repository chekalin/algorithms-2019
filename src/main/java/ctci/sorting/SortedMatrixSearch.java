package ctci.sorting;

import java.util.Optional;

/**
 * 10.9 Sorted Matrix Search: Given an M x N matrix in which each row and each column is sorted in ascending order,
 * write a method to find an element.
 */
public class SortedMatrixSearch {

    static Optional<int[]> search(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return Optional.empty();
        int row = matrix.length - 1;
        int column = 0;
        while (row >= 0 && column < matrix[0].length) {
            if (matrix[row][column] == target) {
                return Optional.of(new int[]{row, column});
            } else if (matrix[row][column] > target) {
                row--;
            } else {
                column++;
            }
        }
        return Optional.empty();
    }
}
