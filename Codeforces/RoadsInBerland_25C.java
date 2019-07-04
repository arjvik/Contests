
/*
ID: arjvik1
LANG: JAVA
TASK: RoadsInBerland
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class RoadsInBerland_25C {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[][] mat = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				mat[i][j] = in.nextInt();
		int r = in.nextInt();
		for (int road_idx = 0; road_idx < r; road_idx++) {
			int a = in.nextInt() - 1;
			int b = in.nextInt() - 1;
			int c = in.nextInt();
			mat[a][b] = Math.min(mat[a][b], c);
			mat[b][a] = Math.min(mat[b][a], c);
			for (int k = 0; k < n; k++)
				for (int i = 0; i < n; i++)
					for (int j = 0; j < n; j++)
						mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
			long sum = 0;
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					sum += mat[i][j];
			System.out.printf("%s%s", sum / 2, road_idx + 1 != r ? " " : "");
		}
		System.out.println();
		in.close();
	}
}
