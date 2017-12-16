/*
ID: arjvik1
LANG: JAVA
TASK: shuffle
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class shuffle {
	static int[] shuffle;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("shuffle.in")));
		java.io.PrintWriter out = new java.io.PrintWriter(new java.io.BufferedWriter(new java.io.FileWriter("shuffle.out")));
		int n = in.nextInt();
		shuffle = new int[n];
		for (int i = 0; i < n; i++) {
			shuffle[in.nextInt()-1] = i;
		}
		int[] cows = new int[n];
		for (int i = 0; i < n; i++) {
			cows[i] = in.nextInt();
		}
		cows = unshuffle(unshuffle(unshuffle(cows))); //3 times
		for (int i = 0; i < n ; i++) {
			out.println(cows[i]);
		}
		in.close();
		out.close();
	}
	public static int[] unshuffle(final int[] cows){
		int[] uns = new int[cows.length];
		for (int i = 0; i < cows.length; i++) {
			uns[shuffle[i]] = cows[i];
		}
		return uns;
	}
}
