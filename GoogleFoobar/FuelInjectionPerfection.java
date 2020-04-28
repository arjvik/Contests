import java.math.BigInteger;

public class FuelInjectionPerfection {

	/**
	 * We see that we always want to divide by 2 if possible.
	 * This is because we can always replace ++/ with /+ and --/ with /-
	 * If we can't, we pick a move that will allow us to divide by
	 * 2 twice next (so select the neighbor that is 0 mod 4). The only
	 * exception is n=3, it is faster to subtract 1 twice than to
	 * add one and divide by 2 twice. 
	 */
	public static int solution(String s) {
		BigInteger n = new BigInteger(s);
		// here we have to add 1 to end with 1
		// infinte loop of division otherwise
		if (n.equals(BigInteger.ZERO)) return 1;
		int count = 0;
		while (n.compareTo(BigInteger.ONE) > 0) {
			if (!n.testBit(0))
				n = n.shiftRight(1);
			else if (!n.testBit(1) || n.equals(BigInteger.valueOf(3)))
				n = n.subtract(BigInteger.ONE);
			else
				n = n.add(BigInteger.ONE);
			count++;
		}
		return count;
	}

	public static void main(String[] args) {
		String[] in = { "15", "4", "43" };
		int[] out = { 5, 2, 8 };
		for (int i = 0; i < in.length; i++)
			if (solution(in[i]) != out[i])
				throw new RuntimeException("Test case " + i + " failed");
		System.out.println("All test cases successful");
	}

}