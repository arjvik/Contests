import java.io.*;
import java.math.*;

public class help {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("help.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("help.out")));
		int n = in.nextInt();
		int[] event = new int[2*n];
		for (int i = 0; i < n; i++) {
			event[in.nextInt()-1] = 1;
			event[in.nextInt()-1] = -1;
		}
		@SuppressWarnings("unused")
		int starts = 0, ends = 0;
		BigInteger ans = BigInteger.ZERO;
		for (int i = 0; i < 2*n; i++) {
			if (event[i] == -1)
				ends++;
			else {
				starts++;
				ans = ans.multiply(two)
						 .add(two.modPow(BigInteger.valueOf(ends), mod))
						 .mod(mod);
			}
		}
		out.println(ans.toString());
		in.close();
		out.close();
	}
	private static final BigInteger mod = BigInteger.valueOf((long) 1e9+7),
									two = BigInteger.valueOf(2);
}
