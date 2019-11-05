package algorithms.course3.week1;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

import static algorithms.util.AssignmentInputReader.getScanner;
import static org.assertj.core.api.Assertions.assertThat;

class SchedulerTest {

    private static Comparator<Job> weightLengthDifferenceComparator = Comparator
            .comparingInt((ToIntFunction<Job>) job -> job.weight - job.length)
            .thenComparingInt(value -> value.weight)
            .reversed();

    private static Comparator<Job> weightLengthRatioComparator = Comparator
            .comparingDouble((ToDoubleFunction<Job>) job -> job.weight / job.length)
            .thenComparingInt(value -> value.weight)
            .reversed();

    @Test
    void readInput() throws FileNotFoundException {
        List<Job> schedule = readJobs("course3/week1/problem_set_1.txt");

        schedule.sort(weightLengthDifferenceComparator);
        System.out.println("weightedCompletionTimes using difference = " + getWeightedCompletionTimes(schedule));

        schedule.sort(weightLengthRatioComparator);
        System.out.println("weightedCompletionTimes using ratio = " + getWeightedCompletionTimes(schedule));
    }

    @Test
    void testCase_1_10() throws FileNotFoundException {
        List<Job> schedule = readJobs("course3/week1/input_random_1_10.txt");

        schedule.sort(weightLengthDifferenceComparator);
        assertThat(getWeightedCompletionTimes(schedule)).isEqualTo(74649);

        schedule.sort(weightLengthRatioComparator);
        assertThat(getWeightedCompletionTimes(schedule)).isEqualTo(72468);
    }

    private int getWeightedCompletionTimes(List<Job> schedule) {
        List<Integer> completionTimes = new ArrayList<>(schedule.size());
        completionTimes.add(schedule.get(0).length);
        for (int i = 1; i < schedule.size(); i++) {
            int length = schedule.get(i).length;
            Integer previousCompletionTime = completionTimes.get(i - 1);
            completionTimes.add(length + previousCompletionTime);
        }
        return IntStream.range(0, schedule.size()).map(i -> {
            int weight = schedule.get(i).weight;
            Integer completionTime = completionTimes.get(i);
            return weight * completionTime;
        }).sum();
    }

    private List<Job> readJobs(String filename) throws FileNotFoundException {
        Scanner scanner = getScanner(filename);
        int numberOfEntries = Integer.parseInt(scanner.nextLine());
        List<Job> schedule = new ArrayList<>(numberOfEntries);
        while (scanner.hasNextLine()) {
            String[] weightAndLength = scanner.nextLine().split(" ");
            schedule.add(new Job(Integer.parseInt(weightAndLength[0]), Integer.parseInt(weightAndLength[1])));
        }
        return schedule;
    }

    private static class Job {
        int weight;
        int length;

        Job(int weight, int length) {
            this.weight = weight;
            this.length = length;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Job job = (Job) o;
            return weight == job.weight &&
                    length == job.length;
        }

        @Override
        public int hashCode() {
            return Objects.hash(weight, length);
        }

        @Override
        public String toString() {
            return "Job{" +
                    "weight=" + weight +
                    ", length=" + length +
                    '}';
        }
    }
}
