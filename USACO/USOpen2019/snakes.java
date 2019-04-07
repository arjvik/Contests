import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class snakes {
	public static int dp[][], A[];
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("snakes.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snakes.out")));
		int n = in.nextInt(),
			k = in.nextInt();
		dp = new int[n][k+1];
		for (int i = 0; i < dp.length; i++) Arrays.fill(dp[i],-1);
		A = readArray(in, n);
		out.println(dp(n-1,k));
		in.close();
		out.close();
	}
	
	private static int dp(int n, int k) {
		if (dp[n][k] != -1)
			return dp[n][k];
		if (n <= k)
			return dp[n][k] = 0;
		if (k == 0) {
			int sum = 0, max = 0;
			for (int i = 0; i <=n; i++) {
				sum += A[i];
				max = Math.max(max, A[i]);
			}
			return dp[n][k] =  (n+1)*max-sum;	
		}
		int count = 0, sum = 0, max = 0, best = Integer.MAX_VALUE;
		for (int i = n; i >= k; i--) {
			sum += A[i];
			max = Math.max(max, A[i]);
			count++;
			int waste = count*max-sum;
			best = Math.min(best, waste+dp(i-1,k-1));
		}
		return dp[n][k] = best;
	}

	public static int[] readArray(Scanner in, int n) {
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = in.nextInt();
		return array;
	}
}
