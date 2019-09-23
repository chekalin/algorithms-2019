package algorithms.course2.week3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class HeapTest {

    @Test
    void inserts() {
        Heap heap = Heap.minHeap(1);
        heap.insert(1);

        assertThat(heap.size()).isEqualTo(1);
    }

    @Test
    void extractsMin() {
        Heap heap = Heap.minHeap(1);
        heap.insert(2);

        int result = heap.extract();

        assertThat(result).isEqualTo(2);
        assertThat(heap.size()).isEqualTo(0);
    }

    @Test
    void extractsMinMaintainingOrder() {
        Heap heap = Heap.minHeap(3);
        heap.insert(3);
        heap.insert(1);
        heap.insert(2);

        assertThat(heap.extract()).isEqualTo(1);
        assertThat(heap.extract()).isEqualTo(2);
        assertThat(heap.extract()).isEqualTo(3);
    }

    @Test
    void extractsMaxMaintainingOrder() {
        Heap heap = Heap.maxHeap(3);
        heap.insert(3);
        heap.insert(1);
        heap.insert(2);

        assertThat(heap.extract()).isEqualTo(3);
        assertThat(heap.extract()).isEqualTo(2);
        assertThat(heap.extract()).isEqualTo(1);
    }

    @Test
    void randomArray() {
        Heap heap = Heap.minHeap(1000);
        for (int i = 0; i < 1000; i++) {
            heap.insert(new Random().nextInt());
        }
        List<Integer> expectedSorted = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            expectedSorted.add(heap.extract());
        }
        assertThat(expectedSorted).isSorted();
    }
}