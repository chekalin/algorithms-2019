package ctci.sorting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class GroupAnagramsTest {

    @Test
    void testGroup() {
        String[] group1 = {"abc", "cba"};
        String[] group2 = {"def", "fed"};

        String[] input = {group1[0], group2[0], group1[1], group2[1]};

        GroupAnagrams.sortAnagrams(input);

        if (Set.of(group1).contains(input[0])) {
            assertThat(Arrays.copyOfRange(input, 0, 2)).containsExactlyInAnyOrder(group1);
            assertThat(Arrays.copyOfRange(input, 2, input.length)).containsExactlyInAnyOrder(group2);
        } else {
            assertThat(Arrays.copyOfRange(input, 0, 2)).containsExactlyInAnyOrder(group2);
            assertThat(Arrays.copyOfRange(input, 2, input.length)).containsExactlyInAnyOrder(group1);
        }
    }
}