
/*
ID: arjvik1
LANG: JAVA
TASK: snowboots
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class snowboots {
	private static int[] depth, b_depth, b_dist;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("snowboots.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
		int n = in.nextInt();
		int b = in.nextInt();
		depth = new int[n];
		b_depth = new int[n];
		b_dist = new int[n];
		for (int i = 0; i < n; i++)
			depth[i] = in.nextInt();
		Map<Integer, Integer> depth_dist = new TreeMap<>();
		for (int i = 0; i < b; i++) {
			b_depth[i] = in.nextInt();
			b_dist[i] = in.nextInt();
			depth_dist.put(b_depth[i], -1);
		}
		for (int bd : depth_dist.keySet()) {
			int max_stretch = getMaxStretch(bd);
			depth_dist.put(bd, max_stretch + 1);
		}
		for (int i = 0; i < b; i++)
			out.println((depth_dist.get(b_depth[i]) > b_dist[i]) ? 0 : 1);
		
		in.close();
		out.close();
	}

	private static int getMaxStretch(int bd) {
		// the longest stretch of depths greater than bd
		int maxStretch = 0;
		int stretch = 0;
		for(int i = 0; i < depth.length; i++) {
			if(depth[i] > bd) {
				stretch++;
			} else {
				if(stretch > maxStretch)
					maxStretch = stretch;
				stretch = 0;
			}
		}
		if(stretch > maxStretch)
			maxStretch = stretch;
		return maxStretch;
	}

}
