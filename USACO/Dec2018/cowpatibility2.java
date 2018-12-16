/*
ID: arjvik1
LANG: JAVA
TASK: cowpatibility
*/
import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class cowpatibility2 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("cowpatibility.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowpatibility.out")));
		int n = in.nextInt();
		int[][] f = new int[n][5];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 5; j++)
				f[i][j] = in.nextInt();
			Arrays.sort(f[i]);
		}
		long count = 0;
		for (int i = 0; i < n; i++)
			search:for (int j = i+1; j < n; j++) {
				if (intersection(f[i], f[j]))
					continue search;
				count++;
			}
		out.println(count);
		in.close();
		out.close();
	}

	private static boolean intersection(int[] a, int[] b) {
		int p = 0,
			q = 0;
		do {
			if (a[p] == b[q])
				return true;
			else if (a[p] < b[q])
				p++;
			else
				q++;
		} while (p < a.length && q < a.length);
		return false;
	}
}
