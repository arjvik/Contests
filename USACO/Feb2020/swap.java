import java.io.*;

/*
 * Solved after contest for various reasons
 */

public class swap {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("swap.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
		int n  = in.nextInt(),
			k  = in.nextInt(),
			a1 = in.nextInt()-1,
			a2 = in.nextInt()-1,
			b1 = in.nextInt()-1,
			b2 = in.nextInt()-1;
		int[] perm = new int[n];
		for (int i = 0; i < n; i++)
			perm[i] = i;
		int[] res = perm.clone();
		for (int i = a1, j = a2; i < j; i++, j--)
			swap(perm, i, j);
		for (int i = b1, j = b2; i < j; i++, j--)
			swap(perm, i, j);
		while (k > 0) {
			if ((k&1) > 0)
				res = apply(perm, res); //apply perm to res
			perm = apply(perm, perm);
			k >>= 1;
		}
		for (int p : res)
			out.println(p+1);
		out.close();
	}
	
	private static int[] apply(int[] perm, int[] order) {
		int[] ans = new int[perm.length];
		for (int i = 0; i < perm.length; i++)
			ans[i] = order[perm[i]];
		return ans;
	}
	
	private static void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

}