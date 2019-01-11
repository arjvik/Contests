/*
ID: arjvik1
LANG: JAVA
TASK: teamwork
*/
import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class teamwork {
	private static int[] skill;
	private static int n, k;
	private static int dp[];
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("teamwork.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("teamwork.out")));
		n = in.nextInt();
		k = in.nextInt();
		skill = new int[n];
		dp = new int[n];
		for (int i = 0; i < n; i++)
			skill[i] = in.nextInt();
		Arrays.fill(dp, -1);
		out.println(dp(0));
		in.close();
		out.close();
	}
	
	private static int dp(int x) {
		if (x == n)
			return 0;
		if (dp[x] != -1)
			return dp[x];
		int max = Integer.MIN_VALUE;
		int bestcow = Integer.MIN_VALUE;
		for (int i = 0; i < k && x+i<n; i++) {
			if (skill[x+i] > bestcow)
				bestcow = skill[x+i];
			max = Math.max(max, bestcow*(i+1) + dp(x+i+1));
		}
		dp[x] = max;
		return max;
	}
}
