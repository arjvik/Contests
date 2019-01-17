import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class Cards {
	private static int dp[][][];
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		in.nextLine();
		int r,g,b,n; r=g=b=0;
		for (char c : in.nextLine().toCharArray())
			switch(c) {
				case 'R': r++; break;
				case 'G': g++; break;
				case 'B': b++; break;
			}
		n=r+g+b+1;
		dp = new int[n][n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				for (int k = 0; k < n; k++)
					dp[i][j][k] = -1;
		int x = dp(r,g,b);
		if ((x&4)>0)
			System.out.print("B");
		if ((x&2)>0)
			System.out.print("G");
		if ((x&1)>0)
			System.out.print("R");
		in.close();
	}
	
	public static int dp(int r, int g, int b) {
		if(dp[r][g][b] != -1)
			return dp[r][g][b];
		if (r==1&&g==0&&b==0)
			return dp[r][g][b]=1;
		if (r==0&&g==1&&b==0)
			return dp[r][g][b]=2;
		if (r==0&&g==0&&b==1)
			return dp[r][g][b]=4;
		if (r==0&&g==0&&b==0)
			return dp[r][g][b]=0;
		int n = 0;
		if (r >= 1 && g >= 1)
			n |= dp(r-1,g-1,b+1);
		if (r >= 1 && b >= 1)
			n |= dp(r-1,g+1,b-1);
		if (b >= 1 && g >= 1)
			n |= dp(r+1,g-1,b-1);
		if (r >= 2)
			n |= dp(r-1,g,b);
		if (g >= 2)
			n |= dp(r,g-1,b);
		if (b >= 2)
			n |= dp(r,g,b-1);
		return dp[r][g][b]=n;
	}

}
