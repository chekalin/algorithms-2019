package algorithms.course3.week3;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class HuffmanTree {
    private HuffmanTree leftSubTree;
    private HuffmanTree rightSubTree;
    private Integer symbolIndex;
    private long weight;

    static HuffmanTree createFromSymbolWeights(int[] symbolWeights) {
        LinkedList<HuffmanTree> symbols =
                IntStream.range(0, symbolWeights.length)
                        .mapToObj(i -> leafNode(i, symbolWeights[i]))
                        .sorted(Comparator.comparingLong(HuffmanTree::getWeight))
                        .collect(Collectors.toCollection(LinkedList::new));

        LinkedList<HuffmanTree> subTrees = new LinkedList<>();
        while (!symbols.isEmpty()) {
            HuffmanTree minimalSubtree1 = pollMinimal(symbols, subTrees);
            HuffmanTree minimalSubtree2 = pollMinimal(symbols, subTrees);
            subTrees.add(HuffmanTree.connect(minimalSubtree2, minimalSubtree1));
        }

        while (subTrees.size() > 1) {
            //noinspection ConstantConditions
            subTrees.add(HuffmanTree.connect(subTrees.poll(), subTrees.poll()));
        }
        return subTrees.poll();
    }

    private static HuffmanTree pollMinimal(LinkedList<HuffmanTree> queue1, LinkedList<HuffmanTree> queue2) {
        HuffmanTree candidate1 = queue1.peek();
        HuffmanTree candidate2 = queue2.peek();

        if (candidate1 == null) return queue2.poll();
        if (candidate2 == null) return queue1.poll();

        if (candidate1.getWeight() > candidate2.getWeight()) {
            return queue2.poll();
        } else {
            return queue1.poll();
        }
    }

    private static HuffmanTree leafNode(int index, int weight) {
        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.weight = weight;
        huffmanTree.symbolIndex = index;
        return huffmanTree;
    }

    private static HuffmanTree connect(HuffmanTree left, HuffmanTree right) {
        HuffmanTree newParent = new HuffmanTree();
        newParent.leftSubTree = left;
        newParent.rightSubTree = right;
        newParent.weight = left.weight + right.weight;
        return newParent;
    }

    private long getWeight() {
        return weight;
    }

    Map<Integer, String> getCoding() {
        Map<Integer, String> codes = new HashMap<>();
        addCodesWithPrefix("", codes);
        return codes;
    }

    private void addCodesWithPrefix(String prefix, Map<Integer, String> codes) {
        if (isLeafNode()) {
            codes.put(symbolIndex, prefix);
        } else {
            leftSubTree.addCodesWithPrefix(prefix + "0", codes);
            rightSubTree.addCodesWithPrefix(prefix + "1", codes);
        }
    }

    private boolean isLeafNode() {
        return symbolIndex != null;
    }
}
