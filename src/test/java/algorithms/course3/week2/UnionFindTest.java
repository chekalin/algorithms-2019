package algorithms.course3.week2;

import algorithms.course3.week2.UnionFind.HashMapUnionFind;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UnionFindTest {

    @Test
    void findThrowsExceptionWhenNoSuchElement() {
        HashMapUnionFind emptyUnionFind = new HashMapUnionFind(List.of());
        assertThrows(NoSuchElementException.class, () -> emptyUnionFind.find("a"));
    }

    @Test
    void findReturnsSameElementAfterInitialization() {
        HashMapUnionFind unionFind = new HashMapUnionFind(List.of("a"));
        assertThat(unionFind.find("a")).isEqualTo("a");
    }

    @Test
    void unionsIntoLargerSet() {
        HashMapUnionFind unionFind = new HashMapUnionFind(List.of("a", "b", "c"));

        unionFind.union("a", "b");
        unionFind.union("a", "c");

        assertThat(unionFind.find("c")).isNotEqualTo("c");
    }

    @Test
    void doesNothingWhenTryingToUnionElementsFromSameSet() {
        HashMapUnionFind unionFind = new HashMapUnionFind(List.of("a", "b", "c"));

        unionFind.union("a", "b");
        unionFind.union("b", "c");
        // both a and c are already connected via b
        unionFind.union("a", "c");

        assertThat(unionFind.numberOfSets()).isEqualTo(1);
    }

    @Test
    void numberOfSetsReturnsNumberOfElementsAfterInit() {
        HashMapUnionFind unionFind = new HashMapUnionFind(List.of("a", "b", "c"));
        assertThat(unionFind.numberOfSets()).isEqualTo(3);
    }

    @Test
    void numberOfSetsReturnsLessSetsAfterUnion() {
        HashMapUnionFind unionFind = new HashMapUnionFind(List.of("a", "b", "c"));

        unionFind.union("a", "b");

        assertThat(unionFind.numberOfSets()).isEqualTo(2);
    }
}