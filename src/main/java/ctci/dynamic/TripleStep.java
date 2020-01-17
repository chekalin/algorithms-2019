package ctci.dynamic;

/**
 * 8.1 Triple Step:
 * A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3 steps at a time. Implement a
 * method to count how many possible ways the child can run up the stairs.
 */
class TripleStep {

    static int count(int numberOfSteps) {
        if (numberOfSteps < 3) return numberOfSteps;
        int[] solutions = new int[numberOfSteps + 1];
        solutions[0] = 1;
        solutions[1] = 1;
        solutions[2] = 2;
        for (int i = 3; i <= numberOfSteps; i++) {
            solutions[i] = solutions[i - 1] + solutions[i - 2] + solutions[i - 3];
        }
        return solutions[numberOfSteps];
    }

}
