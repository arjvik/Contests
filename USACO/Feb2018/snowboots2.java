/*
ID: arjvik1
LANG: JAVA
TASK: snowboots
*/
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class snowboots2 {
	private static int[] depth, b_depth, b_dist;
	private static boolean[] activated;
	private static int maxDepth = Integer.MIN_VALUE;
	private static List<Integer> sortedDepthIndexes;
	private static int sortedDepthIndexPos;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("snowboots.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
		int n = in.nextInt();
		int b = in.nextInt();
		depth = new int[n];
		activated = new boolean[n];
		b_depth = new int[b];
		b_dist = new int[b];
		for (int i = 0; i < n; i++) {
			depth[i] = in.nextInt();
			if(depth[i] > maxDepth)
				maxDepth = depth[i];
		}
		sortedDepthIndexes = IntStream.range(0, n-1).mapToObj(i->i).collect(Collectors.toList());
		sortedDepthIndexes.sort((i,j) -> depth[j] - depth[i]);
		Map<Integer, Integer> depth_dist = new TreeMap<>((i,j) -> j-i);
		for (int i = 0; i < b; i++) {
			b_depth[i] = in.nextInt();
			b_dist[i] = in.nextInt();
			depth_dist.put(b_depth[i], -1);
		}
		UnionFind uf = new UnionFind(n);
		for (int bd : depth_dist.keySet()) {
			int max_stretch = getMaxStretch(bd, uf, n);
			depth_dist.put(bd, max_stretch);
		}
		for (int i = 0; i < b; i++)
			out.println((depth_dist.get(b_depth[i]) >= b_dist[i]) ? 0 : 1);
		
		
		in.close();
		out.close();
	}

	private static int getMaxStretch(int bd, UnionFind uf, int n) {
		if(bd >= maxDepth)
			return 0;
		/*for(int i = 0; i < n; i++) {
			if(!activated(i) && depth[i] > bd) {
				activated[i] = true;
				if(activated(i-1))
					uf.union(i-1, i);
				if(activated(i+1))
					uf.union(i, i+1);
			}
		}*/
		for(int i; depth[(i = sortedDepthIndexes.get(sortedDepthIndexPos))] > bd; sortedDepthIndexPos++) {
			activated[i] = true;
			if(activated(i-1))
				uf.union(i-1, i);
			if(activated(i+1))
				uf.union(i, i+1);
		}
		return uf.maxSize;
	}
	
	public static boolean activated(int i) {
		return (i >= 0 && i < activated.length) ? activated[i] : false;
	}

	public static class UnionFind {
		private final int[] parent;
		private final int[] rank;
		private final int[] size;
		private int maxSize = 1;

		public UnionFind(int n) {
			parent = new int[n];
			Arrays.fill(parent, -1);
			rank = new int[n];
			size = new int[n];
			Arrays.fill(size, 1);
		}

		public void union(int i, int j) {
			int ri = root(i);
			int rj = root(j);
			if(ri == rj)
				return;
			if (rank[ri] > rank[rj]) {
				parent[rj] = ri;
				size[ri] += size[rj];
				if(size[ri] > maxSize)
					maxSize = size[ri];
			} else if (rank[rj] > rank[ri]) {
				parent[ri] = rj;
				size[rj] += size[ri];
				if(size[rj] > maxSize)
					maxSize = size[rj];
			} else {
				if (Math.random() > 0.5) {
					parent[rj] = ri;
					rank[ri]++;
					size[ri] += size[rj];
					if(size[ri] > maxSize)
						maxSize = size[ri];
				} else {
					parent[ri] = rj;
					rank[rj]++;
					size[rj] += size[ri];
					if(size[rj] > maxSize)
						maxSize = size[rj];
				}
			}
		}

		public boolean connected(int i, int j) {
			return root(i) == root(j);
		}

		private int root(int i) {
			if (parent[i] == -1)
				return i;
			else
				return (parent[i] = root(parent[i]));
		}

		public int size(int i) {
			return rank[i];
		}
	}


}
