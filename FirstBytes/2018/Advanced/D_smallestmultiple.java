/*
ID: arjvik1
LANG: JAVA
TASK: D_smallestmultiple
*/
import java.io.*;
import java.math.*;
import java.util.*;

@SuppressWarnings("unused")
public class D_smallestmultiple {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("D.txt")));
		while(in.hasNextLine()) {
			String line = in.nextLine();
			// I have to prefix it like that because on my computer
			// I have overriden Scanner to make it read from console
			// always, even when passing it a file.
			java.util.Scanner s = new java.util.Scanner(line);
			BigInteger d = BigInteger.ONE;
			while(s.hasNext()) {
				d = lcm(d, new BigInteger(s.next()));
			}
			System.out.println(d);
		}
		in.close();
	}
	
	private static BigInteger lcm(BigInteger a, BigInteger b) {
		return a.multiply(b).divide(a.gcd(b));
	}
}
