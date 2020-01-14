package ctci.sorting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SortedMergeTest {
    @Test
    void mergeSequential() {
        int[] a = {1, 2, 3, 4, 0, 0, 0};
        int[] b = {5, 6, 7};
        SortedMerge.merge(a, b);
        assertThat(a).isSorted();
    }

    @Test
    void mergeAlternating() {
        int[] a = {1, 3, 5, 7, 0, 0, 0};
        int[] b = {2, 4, 6};
        SortedMerge.merge(a, b);
        assertThat(a).isSorted();
    }

    @Test
    void mergeEmpty() {
        int[] a = {};
        int[] b = {};
        SortedMerge.merge(a, b);
        assertThat(a).isSorted();
    }

    @Test
    void mergeIntoEmpty() {
        int[] a = {0, 0};
        int[] b = {3, 4};
        SortedMerge.merge(a, b);
        assertThat(a).isSorted();
    }
}