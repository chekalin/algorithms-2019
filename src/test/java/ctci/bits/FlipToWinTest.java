package ctci.bits;

import org.junit.jupiter.api.Test;

import static ctci.bits.FlipToWin.longestSequence;
import static org.assertj.core.api.Assertions.assertThat;

class FlipToWinTest {

    @Test
    void longestSequenceOfOnesIsOneForZero() {
        assertThat(longestSequence(0)).isEqualTo(1);
    }

    @Test
    void longestSequenceOfAllOnesIs32() {
        assertThat(longestSequence(~0)).isEqualTo(Integer.SIZE);
    }

    @Test
    void longestSequenceIsExistingSequencePlusOne() {
        assertThat(longestSequence(1)).isEqualTo(2);
    }

    @Test
    void longestSequenceIsExistingSequencePlusOneWhenOneIsAnywhere() {
        assertThat(longestSequence(0b010)).isEqualTo(2);
    }

    @Test
    void longestSequenceIsExistingSequencePlusOneWhenExistingSequenceIsMoreThanOneLong() {
        assertThat(longestSequence(0b011)).isEqualTo(3);
    }

    @Test
    void longestSequenceIsExistingLongestSequencePlusOneWhenMoreThanOneSequence() {
        assertThat(longestSequence(0b011100011)).isEqualTo(4);
        assertThat(longestSequence(0b011000111)).isEqualTo(4);
    }

    @Test
    void longestSequenceCombingTwoOtherSequences() {
        assertThat(longestSequence(0b101)).isEqualTo(3);
    }

    @Test
    void longestSequenceCombingTwoOtherSequencesOutOfThree() {
        assertThat(longestSequence(0b11101101)).isEqualTo(6);
    }

    @Test
    void longestSequenceOfNegativeNumber() {
        // -2 is represented as 11111111111111111111111111111110
        assertThat(longestSequence(-2)).isEqualTo(32);
    }

    @Test
    void longestSequenceOfMaxInt() {
        assertThat(longestSequence(Integer.MAX_VALUE)).isEqualTo(Integer.SIZE);
    }

    @Test
    void longestSequenceOfExample() {
        assertThat(longestSequence(1775)).isEqualTo(8);
    }

}