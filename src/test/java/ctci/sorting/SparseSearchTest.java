package ctci.sorting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SparseSearchTest {

    @Test
    void testSearch() {
        String[] array = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        assertThat(SparseSearch.search(array, "ball")).isEqualTo(4);
        assertThat(SparseSearch.search(array, "at")).isEqualTo(0);
        assertThat(SparseSearch.search(array, "dad")).isEqualTo(10);
        assertThat(SparseSearch.search(array, "asdf")).isEqualTo(-1);
    }
}