import java.math.BigInteger;

public class Prime {
	public static boolean isPrime(int i, int certainty) {
		if (certainty == 0)
			certainty = 5;
		return BigInteger.valueOf(i).isProbablePrime(certainty);
	}
	public static int nextPrime(int i) {
		return BigInteger.valueOf(i).nextProbablePrime().intValue();
	}
}
