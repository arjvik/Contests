import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class AyoubAndLostArray {
	public static long MOD = (long) (1e9+7);
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(), l = in.nextInt(), r = in.nextInt();
		int num0, num1, num2;
		num0 = num1 = num2 = (r-l+1)/3;
		switch(3* (l%3)+(r%3)) {
			case 1: num1++;
			case 0: num0++;
			case 2:	break;
			case 5: num2++;
			case 4: num1++;
			case 3: break;
			case 6: num0++;
			case 8: num2++;
			case 7: break;
		}
		long[][] dp = new long[n+1][3];
		dp[0][0] = 1;
		for (int i = 1; i <= n; i++) {
			dp[i][0] = mod(  mod(dp[i-1][0]*num0) + mod(dp[i-1][1]*num2) + mod(dp[i-1][2]*num1)  );
			dp[i][1] = mod(  mod(dp[i-1][0]*num1) + mod(dp[i-1][1]*num0) + mod(dp[i-1][2]*num2)  );
			dp[i][2] = mod(  mod(dp[i-1][0]*num2) + mod(dp[i-1][1]*num1) + mod(dp[i-1][2]*num0)  );
		}
		System.out.println(dp[n][0]);
	}
	public static long mod(long l) {
		return l % MOD;
	}
}
