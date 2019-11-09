package algorithms.course3.week2;

import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

public interface UnionFind<T> {

    T find(T element);

    void union(T element1, T element2);

    int numberOfSets();

    static <T> UnionFind<T> of(Collection<T> initialElements) {
        return new HashMapUnionFind<>(initialElements);
    }

    class HashMapUnionFind<T> implements UnionFind<T> {

        private final Map<T, Element<T>> elements;
        private int numberOfSets;

        HashMapUnionFind(Collection<T> initialElements) {
            this.elements = initialElements.stream()
                    .collect(Collectors.toMap(identity(), Element::new));
            this.numberOfSets = initialElements.size();
        }

        @Override
        public T find(T element) {
            Element<T> current = elements.get(element);
            if (current == null) throw new NoSuchElementException();
            while (!current.isRoot()) {
                current = elements.get(current.parent);
            }
            return current.id;
        }

        @Override
        public void union(T element1, T element2) {
            T parent1 = find(element1);
            T parent2 = find(element2);
            if (parent1.equals(parent2)) {
                // Do nothing, elements are already part of the same set
                return;
            }

            Element e1 = elements.get(parent1);
            Element e2 = elements.get(parent2);
            if (e1.size > e2.size) {
                e2.parent = e1.id;
                e1.size += e2.size;
            } else {
                e1.parent = e2.id;
                e2.size += e1.size;
            }
            numberOfSets--;
        }

        @Override
        public int numberOfSets() {
            return numberOfSets;
        }

        static class Element<T> {
            T id;
            T parent;
            int size;

            Element(T id) {
                this.id = id;
                this.parent = id;
                this.size = 1;
            }

            boolean isRoot() {
                return this.id.equals(this.parent);
            }

            @Override
            public String toString() {
                return "Element{" +
                        "id='" + id + '\'' +
                        ", parent='" + parent + '\'' +
                        ", size=" + size +
                        '}';
            }
        }
    }
}


