/*
ID: arjvik1
LANG: JAVA
TASK: frac1
*/
import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class frac1 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("frac1.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		int n = in.nextInt();
		HashMap<Double, String> map = new HashMap<>();
		for (int d = 1; d <= n; d++)
			for (int i = 0; i <= d; i++)
				if (gcd(d, i) == 1)
					map.put((double)i/d, String.format("%d/%d", i, d));
		map.keySet().stream().mapToDouble(Double::doubleValue).sorted().mapToObj(map::get).forEachOrdered(out::println);
		in.close();
		out.close();
	}

	private static int gcd(int i, int j) {
		if (i == 0)
			return j; 
		else if (j == 0)
			return i;
		else if (i > j)
			return gcd(i-j, j);
		else
			return gcd(i, j-i);
	}
}
