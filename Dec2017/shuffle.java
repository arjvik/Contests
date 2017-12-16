/*
ID: arjvik1
LANG: JAVA
TASK: shuffle
*/
import java.io.*;
import java.util.*;

public class shuffle {
	private static final int INITIAL_SHUFFLES = 500;
	private static final int NUM_SHUFFLES = 200;
	static int[] shuffle;
	static int n;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("shuffle.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
		n = in.nextInt();
		shuffle = new int[n];
		for (int i = 0; i < n; i++) {
			shuffle[i] = in.nextInt()-1;
		}
		
		BitSet b = new BitSet(n);
		b.flip(0, n);
		//System.out.println(toString(b));
		for (int i = 0; i < INITIAL_SHUFFLES; i++) {
			b = shuffle1(b);
			//System.out.println(toString(b));
		}
		BitSet c = shuffle1(b);
		for (int i = 0; i < NUM_SHUFFLES; i++) {
			b = shuffle1(b);
			c.and(b);
		}
		int count=0;
		for (int i = 0; i < n; i++) {
			if(c.get(i))
				count++;
		}
		out.println(count);
		in.close();
		out.close();
	}
	private static char[] toString(BitSet b) {
		char[] c = new char[n];
		for (int i = 0; i < b.length(); i++) {
			c[i] = b.get(i)?'+':'-';
		}
		return c;
	}
	private static BitSet shuffle1(BitSet b) {
		BitSet ans = new BitSet(n);
		for (int i = 0; i < shuffle.length; i++) {
			if(b.get(i)){
				ans.set(shuffle[i]);
			}
		}
		return ans;
	}
}
