
import java.util.*;
import java.io.*;
import java.math.BigInteger;

@SuppressWarnings("unused")
public class H_tiling {

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new FileReader("H.txt")));
		while(in.hasNextInt()) {
			int a = in.nextInt();
			int b = in.nextInt();
			if(a > b) {
				int t = a;
				a = b;
				b = t;
			}
			loop:for(int i = a; i > 0; i--) {
				if(a % i == 0 && b % i == 0) {
					System.out.print(i);
					break loop;
				}
			}
			//EASY WAY:
			System.out.println("\t"+
				BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue()
			);
		}
	}

}
