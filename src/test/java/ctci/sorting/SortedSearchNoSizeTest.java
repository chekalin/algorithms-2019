package ctci.sorting;

import ctci.sorting.SortedSearchNoSize.Listy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SortedSearchNoSizeTest {

    @Test
    void binarySearch() {
        Listy listy = new Listy(new int[]{1, 2, 3, 4, 5});
        assertThat(SortedSearchNoSize.binarySearch(listy, 4)).isEqualTo(3);
        assertThat(SortedSearchNoSize.binarySearch(listy, 6)).isEqualTo(-1);
        assertThat(SortedSearchNoSize.binarySearch(listy, 1)).isEqualTo(0);
    }
}