package algorithms.course3.week3;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.*;

import static algorithms.util.AssignmentInputReader.getScanner;
import static org.assertj.core.api.Assertions.assertThat;

class HuffmanTreeTest {
    @Test
    void simpleCase() {
        int[] weights = {60, 25, 10, 5};
        Map<Integer, String> code = HuffmanTree.createFromSymbolWeights(weights).getCoding();
        assertThat(code.get(0)).isEqualTo("0");
        assertThat(code.get(1)).isEqualTo("10");
        assertThat(code.get(2)).isEqualTo("110");
        assertThat(code.get(3)).isEqualTo("111");
    }

    @Test
    void problem_set_1_input_random_1_10() throws FileNotFoundException {
        int[] weights = readInput("course3/week3/problem_set_1_input_random_1_10.txt");
        HuffmanTree tree = HuffmanTree.createFromSymbolWeights(weights);
        Collection<String> codes = tree.getCoding().values();
        Optional<String> max = codes.stream().max(Comparator.comparing(String::length));
        Optional<String> min = codes.stream().min(Comparator.comparing(String::length));
        assertThat(max).isPresent();
        assertThat(max.get().length()).isEqualTo(5);
        assertThat(min).isPresent();
        assertThat(min.get().length()).isEqualTo(2);
    }

    @Test
    void assignment() throws FileNotFoundException {
        int[] weights = readInput("course3/week3/week3_problem_set_1.txt");
        HuffmanTree tree = HuffmanTree.createFromSymbolWeights(weights);
        Collection<String> codes = tree.getCoding().values();
        Optional<String> max = codes.stream().max(Comparator.comparing(String::length));
        Optional<String> min = codes.stream().min(Comparator.comparing(String::length));
        assertThat(max).isPresent();
        System.out.println("max.get().length() = " + max.get().length());
        assertThat(min).isPresent();
        System.out.println("min.get().length() = " + min.get().length());
    }

    private int[] readInput(String filename) throws FileNotFoundException {
        Scanner scanner = getScanner(filename);
        int numberOfSymbols = Integer.parseInt(scanner.nextLine());
        int i = 0;
        int[] weights = new int[numberOfSymbols];
        while (scanner.hasNextLine()) {
            weights[i++] = Integer.parseInt(scanner.nextLine());
        }
        return weights;
    }


}