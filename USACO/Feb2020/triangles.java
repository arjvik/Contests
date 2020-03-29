import java.io.*;

/*
 * Solved after contest for various reasons
 */

public class triangles {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("triangles.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
		int n = in.nextInt();
		int[] x = new int[n], y = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = in.nextInt();
			y[i] = in.nextInt();
		}
		int max_area = -1;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				for (int k = 0; k < n; k++)
					if (i != j && j != k && k != i)
						if (y[i] == y[j] && x[j] == x[k])
							max_area = Math.max(max_area, Math.abs(x[j]-x[i])*Math.abs(y[k]-y[j]));
		out.println(max_area);
		out.close();
	}
}