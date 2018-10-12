/*
ID: arjvik1
LANG: JAVA
TASK: castingoutnines
*/
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@SuppressWarnings("unused")
public class castingoutnines {
	private static final BigInteger NINE = BigInteger.TEN.subtract(BigInteger.ONE);

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("castingoutnines.dat")));
		int NUM_CASES = in.nextInt();
		in.nextLine();
		while(NUM_CASES --> 0) {
			BigInteger i = new BigInteger(in.nextLine());
			int n = 0;
			while(i.compareTo(NINE) > 0){
				n++;
				BigInteger s = BigInteger.ZERO;
				BigInteger j = i;
				while(j.compareTo(BigInteger.ZERO) > 0) {
					BigInteger[] k = j.divideAndRemainder(BigInteger.TEN);
					s = s.add(k[1]);
					j = k[0];
				}
				i = s;
//				System.out.printf("---Iteration %d: %d%n",n, i.longValue());
			}
			System.out.println(n);
		}
		in.close();
	}
}
