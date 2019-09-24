package algorithms.course2.week3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface MedianMaintainer {
    void add(int i);

    int median();

    class SortedListMedianMaintainer implements MedianMaintainer {

        private List<Integer> list = new ArrayList<>();

        @Override
        public void add(int i) {
            list.add(i);
        }

        @Override
        public int median() {
            Collections.sort(list);
            return list.get((list.size() - 1) / 2);
        }
    }

    class HeapMedianMaintainer implements MedianMaintainer {

        private Heap lowHeap;
        private Heap highHeap;

        HeapMedianMaintainer(int maxSize) {
            lowHeap = Heap.maxHeap(maxSize / 2 + maxSize % 2);
            highHeap = Heap.minHeap(maxSize / 2 + maxSize % 2);
        }

        @Override
        public void add(int i) {
            if (i < lowHeap.peek()) {
                lowHeap.insert(i);
            } else if (i > highHeap.peek()) {
                highHeap.insert(i);
            } else {
                lowHeap.insert(i);
            }
            rebalance();
        }

        private void rebalance() {
            if (highHeap.size() > lowHeap.size()) {
                lowHeap.insert(highHeap.extract());
            } else if (lowHeap.size() > highHeap.size() + 1) {
                highHeap.insert(lowHeap.extract());
            }
        }

        @Override
        public int median() {
            return lowHeap.peek();
        }
    }
}
