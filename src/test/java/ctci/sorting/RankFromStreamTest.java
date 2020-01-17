package ctci.sorting;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RankFromStreamTest {

    @Test
    void testRank() {
        RankFromStream rankFromStream = new RankFromStream();

        List.of(5, 1, 4, 4, 5, 9, 7, 13, 3).forEach(rankFromStream::track);

        assertThat(rankFromStream.getRankOfNumber(1)).isEqualTo(0);
        assertThat(rankFromStream.getRankOfNumber(3)).isEqualTo(1);
        assertThat(rankFromStream.getRankOfNumber(4)).isEqualTo(3);
        assertThat(rankFromStream.getRankOfNumber(13)).isEqualTo(8);
        assertThat(rankFromStream.getRankOfNumber(14)).isEqualTo(-1);
        assertThat(rankFromStream.getRankOfNumber(0)).isEqualTo(-1);
        assertThat(rankFromStream.getRankOfNumber(2)).isEqualTo(-1);
    }
}