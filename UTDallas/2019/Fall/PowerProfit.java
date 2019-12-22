import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class PowerProfit {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] p = new int[n];
		for (int i = 0; i < n; i++)
			p[i] = in.nextInt();
		
		int min = Integer.MAX_VALUE;
		int best = 0;
		for (int i = 0; i < n; i++) {
			min = Math.min(min, p[i]);
			best = Math.max(best, p[i]-min);
		}
		System.out.println(best);
		in.close();
	}
}