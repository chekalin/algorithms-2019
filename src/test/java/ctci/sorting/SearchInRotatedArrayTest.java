package ctci.sorting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SearchInRotatedArrayTest {

    @Test
    void testCase1() {
        int[] array = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        assertThat(SearchInRotatedArray.search(array, 5)).isEqualTo(8);
        assertThat(SearchInRotatedArray.search(array, 15)).isEqualTo(0);
        assertThat(SearchInRotatedArray.search(array, 14)).isEqualTo(11);
        assertThat(SearchInRotatedArray.search(array, 17)).isEqualTo(-1);
    }
}