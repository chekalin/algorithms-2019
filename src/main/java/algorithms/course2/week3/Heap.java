package algorithms.course2.week3;

import java.util.Comparator;

class Heap {

    private int[] heap;
    private Comparator<Integer> comparator;
    private int size = 0;

    private Heap(int maxSize, Comparator<Integer> comparator) {
        this.heap = new int[maxSize];
        this.comparator = comparator;
    }

    static Heap minHeap(int maxSize) {
        return new Heap(maxSize, Comparator.naturalOrder());
    }

    static Heap maxHeap(int maxSize) {
        return new Heap(maxSize, Comparator.reverseOrder());
    }

    void insert(int i) {
        int current = size;
        heap[size] = i;
        size++;
        while (greaterThan(heap[parentPos(current)], heap[current])) {
            swap(parentPos(current), current);
            current = parentPos(current);
        }
    }

    private void swap(int pos1, int pos2) {
        int temp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = temp;
    }

    private int leftChildPos(int pos) {
        return (2 * pos);
    }

    private int rightChildPos(int pos) {
        return (2 * pos) + 1;
    }

    private boolean isLeaf(int pos) {
        return pos >= (size / 2) && pos <= size;
    }

    private int parentPos(int pos) {
        return pos / 2;
    }

    int size() {
        return size;
    }

    int extract() {
        int min = heap[0];
        size--;
        heap[0] = heap[size];
        int current = 0;
        while (!isLeaf(current) &&
                (greaterThan(heap[current], heap[leftChildPos(current)]) || greaterThan(heap[current], heap[rightChildPos(current)]))) {
            if (greaterThan(heap[rightChildPos(current)], heap[leftChildPos(current)])) {
                swap(current, leftChildPos(current));
                current = leftChildPos(current);
            } else {
                swap(current, rightChildPos(current));
                current = rightChildPos(current);
            }
        }
        return min;
    }

    int peek() {
        return heap[0];
    }

    private boolean greaterThan(int value1, int value2) {
        return comparator.compare(value1, value2) > 0;
    }
}
