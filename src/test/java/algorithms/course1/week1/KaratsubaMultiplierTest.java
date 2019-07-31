package algorithms.course1.week1;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

class KaratsubaMultiplierTest {

    @Test
    void multiplies() {
        assertThat(KaratsubaMultiplier.multiply(BigInteger.valueOf(2L), BigInteger.valueOf(2L))).isEqualTo(BigInteger.valueOf(4L));
        assertThat(KaratsubaMultiplier.multiply(BigInteger.valueOf(10L), BigInteger.valueOf(10L))).isEqualTo(BigInteger.valueOf(100L));
        assertThat(KaratsubaMultiplier.multiply(BigInteger.valueOf(1000L), BigInteger.valueOf(1000L))).isEqualTo(BigInteger.valueOf(1000000L));
        assertThat(KaratsubaMultiplier.multiply(new BigInteger("5678"), new BigInteger("1234"))).isEqualTo(new BigInteger("7006652"));
        BigInteger x = new BigInteger("3141592653589793238462643383279502884197169399375105820974944592");
        BigInteger y = new BigInteger("2718281828459045235360287471352662497757247093699959574966967627");
        assertThat(KaratsubaMultiplier.multiply(x, y))
                .isEqualTo(x.multiply(y));

    }
}