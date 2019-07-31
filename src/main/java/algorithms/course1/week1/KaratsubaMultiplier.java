package algorithms.course1.week1;

import java.math.BigDecimal;
import java.math.BigInteger;

class KaratsubaMultiplier {

    static BigInteger multiply(BigInteger x, BigInteger y) {
        if (x.compareTo(BigInteger.TEN) < 0 || y.compareTo(BigInteger.TEN) < 0) {
            return x.multiply(y);
        }
        int n = Math.max(x.toString().length(), y.toString().length());
        int halfN = (int) Math.ceil(n / 2);
        BigInteger tenToHalfN = BigInteger.TEN.pow(halfN);
        BigInteger a = x.divide(tenToHalfN);
        BigInteger b = x.mod(tenToHalfN);
        BigInteger c = y.divide(tenToHalfN);
        BigInteger d = y.mod(tenToHalfN);
        BigInteger ac = multiply(a, c);
        BigInteger bd = multiply(b, d);
        BigInteger abcd = multiply((a.add(b)), (c.add(d)));
        // (10 ^ n) * ac + (10 ^ (n / 2)) * (abcd - ac - bd) + bd
        return BigInteger.TEN.pow(halfN * 2).multiply(ac)
                .add(tenToHalfN.multiply(abcd.subtract(ac).subtract(bd)))
                .add(bd);
    }
}
