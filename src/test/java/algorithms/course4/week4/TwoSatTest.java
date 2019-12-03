package algorithms.course4.week4;

import algorithms.course4.week4.TwoSat.Clause;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static algorithms.course4.week4.TwoSat.isSatisfiable;
import static algorithms.util.AssignmentInputReader.getScanner;
import static org.assertj.core.api.Assertions.assertThat;

class TwoSatTest {

    @Test
    void simple() {
        List<Clause> clauses = List.of(
                new Clause(1, 2),
                new Clause(2, -1),
                new Clause(-1, -2)
        );
        assertThat(isSatisfiable(clauses)).isTrue();
    }

    @Test
    void simpleNegative() {
        List<Clause> clauses = List.of(
                new Clause(1, 2),
                new Clause(2, -1),
                new Clause(1, -2),
                new Clause(-1, -2)
        );
        assertThat(isSatisfiable(clauses)).isFalse();
    }

    @Test
    void input_beaunus_1_2() throws FileNotFoundException {
        List<Clause> clauses = readClauses("course4/week4/input_beaunus_1_2.txt");
        assertThat(isSatisfiable(clauses)).isTrue();
    }

    @Test
    void input_beaunus_10_20() throws FileNotFoundException {
        List<Clause> clauses = readClauses("course4/week4/input_beaunus_10_20.txt");
        assertThat(isSatisfiable(clauses)).isFalse();
    }

    @Test
    @Disabled("slow")
    void assignment() throws FileNotFoundException {
        System.out.println("1: " + isSatisfiable(readClauses("course4/week4/2sat1.txt")));
        System.out.println("2: " + isSatisfiable(readClauses("course4/week4/2sat2.txt")));
        System.out.println("3: " + isSatisfiable(readClauses("course4/week4/2sat3.txt")));
        System.out.println("4: " + isSatisfiable(readClauses("course4/week4/2sat4.txt")));
        System.out.println("5: " + isSatisfiable(readClauses("course4/week4/2sat5.txt")));
        System.out.println("6: " + isSatisfiable(readClauses("course4/week4/2sat6.txt")));
    }

    private List<Clause> readClauses(String filename) throws FileNotFoundException {
        Scanner scanner = getScanner(filename);
        int expectedNumberOfClauses = Integer.parseInt(scanner.nextLine());
        LinkedList<Clause> clauses = new LinkedList<>();
        while(scanner.hasNextLine()) {
            String[] clause = scanner.nextLine().split(" ");
            clauses.add(new Clause(Integer.parseInt(clause[0]), Integer.parseInt(clause[1])));
        }
        assertThat(clauses.size()).isEqualTo(expectedNumberOfClauses);
        return clauses;
    }

}
