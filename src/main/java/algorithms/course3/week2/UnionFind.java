package algorithms.course3.week2;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

public interface UnionFind {

    String find(String element);

    void union(String element1, String element2);

    class HashMapUnionFind implements UnionFind {

        private final Map<String, Element> elements;

        HashMapUnionFind(List<String> initialElements) {
            elements = initialElements.stream()
                    .collect(Collectors.toMap(identity(), Element::new));
        }

        @Override
        public String find(String element) {
            Element current = elements.get(element);
            if (current == null) throw new NoSuchElementException();
            while (!current.isRoot()) {
                current = elements.get(current.parent);
            }
            return current.id;
        }

        @Override
        public void union(String element1, String element2) {
            Element e1 = elements.get(find(element1));
            Element e2 = elements.get(find(element2));
            if (e1.size > e2.size) {
                e2.parent = e1.id;
                e1.size += e2.size;
            } else {
                e1.parent = e2.id;
                e2.size += e1.size;
            }
        }

        static class Element {
            String id;
            String parent;
            int size;

            Element(String id) {
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


