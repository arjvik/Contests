/*
ID: arjvik1
LANG: JAVA
TASK: beanflowq
*/
import java.io.*;
import java.util.*;

public class beanflow {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("beanflow.dat")));
		int NUMCASES = in.nextInt();
		for (int CASENUM = 0; CASENUM < NUMCASES; CASENUM++) {
			int n = in.nextInt();
			int[][] mat = new int[n][n]; //input output
			for(int i = 0; i<n; i++)
				for(int j = 0; j<n; j++)
					mat[i][j] = in.nextInt();
			
			int[] dp = new int[n];
			Arrays.fill(dp, -1);
			System.out.println(recur(mat, n-1, dp, n));
			System.err.println(Arrays.toString(dp));
			
		}
		in.close();
	}

	private static int recur(final int[][] mat, final int node, int[] dp, final int n) {
		if(node == 0)
			return Integer.MAX_VALUE;
		if(dp[node] != -1)
			return dp[node];
		int flow = 0;
		for(int in = 0; in < n; in++) {
			if(mat[in][node] != 0) {
				flow += Math.min(mat[in][node], recur(mat, in, dp, n));
			}
		}
		dp[node] = flow;
		return flow;
	}
}
