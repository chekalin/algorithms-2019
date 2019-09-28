package algorithms.course2.week4;

import com.google.common.base.Stopwatch;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

import static algorithms.util.AssignmentInputReader.getScanner;

class TwoSumTest {

    @Test
    @Disabled("Slow")
    void assignment() throws FileNotFoundException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Scanner scanner = getScanner("course2/week4/week4_problem_set.txt");
        Set<Long> integers = new HashSet<>(1_400_000);
        int range = 10_000;
        Set<Long> foundSums = new HashSet<>();
        while (scanner.hasNextLine()) {
            if (integers.size() % 10_000 == 0)
                System.out.println(integers.size() / 10000 + "% in " + stopwatch.elapsed(TimeUnit.SECONDS) + "s");
            long nextNumber = Long.parseLong(scanner.nextLine());
            LongStream.rangeClosed(-range, range)
                    .filter(expectedSum -> expectedSum != nextNumber * 2 && integers.contains(expectedSum - nextNumber))
                    .forEach(foundSums::add);
            integers.add(nextNumber);
        }
        System.out.println(foundSums.size());
        System.out.println("Done in " + stopwatch.elapsed(TimeUnit.SECONDS) + "s");
    }

}
