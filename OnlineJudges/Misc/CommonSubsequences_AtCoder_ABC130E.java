import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class CommonSubsequences_AtCoder_ABC130E {
	static int[] s, t, dp[];
	final static int MOD = (int) (1e9 + 7);

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(), m = in.nextInt();
		s = readArray(in, n);
		t = readArray(in, m);
		dp = new int[n][m];
		for (int i = 0; i < n; i++)
			Arrays.fill(dp[i], -1);
		System.out.println(dp(n - 1, m - 1));
	}

	private static int dp(int i, int j) {
		if (i == -1 || j == -1)
			return 1;
		if (dp[i][j] == -1) {
			if (s[i] == t[j])
				dp[i][j] = (dp(i,j-1)+dp(i-1,j))%MOD;
			else
				dp[i][j] = (((dp(i,j-1)+dp(i-1,j))%MOD)-dp(i-1,j-1))%MOD;
		}
		return (dp[i][j] + MOD)%MOD;
	}
	
	public static int[] readArray(Scanner in, int n) {
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = in.nextInt();
		return array;
	}
	
}

