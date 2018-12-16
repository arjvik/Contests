/*
ID: arjvik1
LANG: JAVA
TASK: cowpatibility
*/
import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class cowpatibility {
	private static final Set<Integer> EMPTYSET = new HashSet<>();

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("cowpatibility.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowpatibility.out")));
		int n = in.nextInt();
		long total = (long) n * (n-1) / 2;
		long count = 0;
		Map<Integer, Set<Integer>> mFlavCows = new HashMap<>();
		for (int cow = 0; cow < n; cow++) {
			Set<Integer> pairs = new HashSet<>();
			for (int x = 0; x < 5; x++) {
				int f = in.nextInt();
				if (mFlavCows.containsKey(f)) {
					pairs.addAll(mFlavCows.get(f));
					mFlavCows.get(f).add(cow);
				} else {
					mFlavCows.put(f, new HashSet<>());
					mFlavCows.get(f).add(cow);
				}
			}
			count += pairs.size();
		}
		out.println(total - count);
		in.close();
		out.close();
	}
}
