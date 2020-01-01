package ctci.bits;

/**
 * Question 5.3 from Cracking the Coding Interview
 * You have an integer and you can flip exactly one bit from a 0 to 1. Write code to find the length of the longest
 * sequence of 1s you could create.
 * <p>
 * EXAMPLE
 * Input:   1775 or 11011101111
 * Output:  8
 */
class FlipToWin {

    static int longestSequence(int n) {
        if (n == ~0) return Integer.SIZE;
        int count = 0;
        int previousCount = 0;
        int max = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            } else {
                previousCount = (n & 2) == 0 ? 0 : count;
                count = 0;
            }
            max = Math.max(count + previousCount + 1, max);
            n >>>= 1;
        }
        return max;
    }
}
