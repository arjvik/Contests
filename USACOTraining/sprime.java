/*
ID: arjvik1
LANG: JAVA
TASK: sprime
*/
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class sprime {
	private static final int CERTAINTY = 1000;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("sprime.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		int n = in.nextInt();
		List<TreeSet<Integer>> primes = new ArrayList<>();
		primes.add(new TreeSet<>(Arrays.asList(2,3,5,7)));
		for(int i = 1; i < n; i++) {
			TreeSet<Integer> newPrimes = new TreeSet<>();
			for (Integer oldPrime : primes.get(i-1)) {
				for(int k = 1; k < 10; k++){
					int probPrime = k+10*oldPrime;
					if(prime(probPrime))
						newPrimes.add(probPrime);
				}
			}
			primes.add(newPrimes);
		}
		primes.get(n-1)
			  .forEach(out::println);
		in.close();
		out.close();
	}
	public static boolean prime(int i) {
		return new BigInteger(Integer.toString(i)).isProbablePrime(CERTAINTY);
	}
}
