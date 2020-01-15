package ctci.sorting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SortedMatrixSearchTest {

    @Test
    void searchInSquareMatrix() {
        int[][] matrix = {
                {1, 3, 5},
                {4, 8, 12},
                {9, 13, 25}
        };
        assertThat(SortedMatrixSearch.search(matrix, 12)).contains(new int[]{1, 2});
        assertThat(SortedMatrixSearch.search(matrix, 1)).contains(new int[]{0, 0});
        assertThat(SortedMatrixSearch.search(matrix, 5)).contains(new int[]{0, 2});
        assertThat(SortedMatrixSearch.search(matrix, 25)).contains(new int[]{2, 2});
        assertThat(SortedMatrixSearch.search(matrix, 26)).isEmpty();
        assertThat(SortedMatrixSearch.search(matrix, 6)).isEmpty();
    }

    @Test
    void searchInColumnMatrix() {
        int[][] matrix = {
                {1},
                {4},
                {9}
        };
        assertThat(SortedMatrixSearch.search(matrix, 4)).contains(new int[]{1, 0});
        assertThat(SortedMatrixSearch.search(matrix, 9)).contains(new int[]{2, 0});
        assertThat(SortedMatrixSearch.search(matrix, 2)).isEmpty();
        assertThat(SortedMatrixSearch.search(matrix, 11)).isEmpty();
    }
}