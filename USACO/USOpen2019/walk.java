import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class walk {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("walk.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("walk.out")));
		int n = in.nextInt();
		int k = in.nextInt();
		int c = 0;
		int mg = n-k+1; //max group size by pigeonhole
		int nmg = n-mg; //number of elements other than mg;
		int[] p = new int[nmg*(nmg-1)/2 + mg*(n-mg)];
		for (int i = 1; i < n-mg; i++)
			for (int j = i + 1; j <= n-mg; j++, c++)
				p[c] = f(i,j);
		for (int i = 1; i <= n-mg; i++)
			for (int j = n-mg+1; j <=n; j++, c++)
				p[c] = f(i,j);
		Arrays.sort(p);
		out.println(2019201997-p[p.length-1]);
		in.close();
		out.close();
	}
	private static int f(int x, int y) { return 84*x+48*y; }

}
