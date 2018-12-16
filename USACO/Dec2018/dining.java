
/*
ID: arjvik1
LANG: JAVA
TASK: dining
*/
import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class dining {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("dining.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dining.out")));
		int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
		int[][] adj = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(adj[i], 100000);
			adj[i][i] = 0;
		}
		for (int i = 0; i < m; i++) {
			int a = in.nextInt() - 1, b = in.nextInt() - 1, t = in.nextInt();
			if (adj[a][b] > t)
				adj[a][b] = t;
			if (adj[b][a] > t)
				adj[b][a] = t;
		}
		// floyd-warshal
		for (int l = 0; l < n; l++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					adj[i][j] = Math.min(adj[i][j], adj[i][l] + adj[l][j]);
//		printAdj(adj);
		Map<Integer, Integer> hPosYum = new HashMap<>();
		for (int i = 0; i < k; i++) {
			int pos = in.nextInt()-1, yum = in.nextInt();
			if (!hPosYum.containsKey(pos) || hPosYum.get(pos) < yum)
				hPosYum.put(pos, yum);
		}
		cow: for (int i = 0; i < n - 1; i++) {
			int time = adj[i][n - 1];
//			System.err.printf("Cow %d - without eating - %d time%n", i, time);
			for (int hPos : hPosYum.keySet())
				if (adj[i][hPos] + adj[hPos][n - 1] - time <= hPosYum.get(hPos)) {
//					System.err.printf("---Haybale @%d (%d):%n    %d time to get there%n"
//							+ "    %d time to go to barn%n    %d time total%n"
//							+ "    %d time difference%n",
//							hPos, hPosYum.get(hPos),
//							adj[i][hPos], adj[hPos][n - 1],
//							adj[i][hPos] + adj[hPos][n - 1],
//							adj[i][hPos] + adj[hPos][n - 1] - time);
					out.println(1);
					continue cow;
				}
			out.println(0);
		}
		in.close();
		out.close();
	}

	private static void printAdj(int[][] adj) {
		for (int i = 0; i < adj.length; i++) {
			if (i == 0)
				System.err.print("[[");
			else
				System.err.print(" [");
			for (int j = 0; j < adj.length - 1; j++) {
				if (adj[i][j] >= 100000)
					System.err.print("inf, ");
				else
					System.err.printf("%3d, ", adj[i][j]);
			}
			System.err.printf("%3d]", adj[i][adj.length - 1]);
			if (i == adj.length - 1)
				System.err.print("]");
			System.err.println("]");
		}
	}
}
