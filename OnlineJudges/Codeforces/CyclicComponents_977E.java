import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class CyclicComponents_977E {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int v = in.nextInt(),
			e = in.nextInt();
		UnionFind uf = new UnionFind(v);
		for (int i = 0; i < e; i++)
			uf.unionAndIncrement(in.nextInt()-1, in.nextInt()-1);
		System.out.println(uf.ans());
		in.close();
	}
	
	public static class UnionFind {
		private final int[] parent;
		private final int[] depth;
		
		private final int[] numEdges;
		private final int[] numVertices;
		private Set<Integer> allRoots;

		public UnionFind(int n) {
			parent = new int[n];
			Arrays.fill(parent, -1);
			depth = new int[n];
			numEdges = new int[n];
			numVertices = new int[n];
			Arrays.fill(numVertices, 1);
			allRoots = new HashSet<>(n, 1f);
			for (int i = 0; i < n; i++)
				allRoots.add(i);
		}

		public void unionAndIncrement(int i, int j) {
			int ri = root(i);
			int rj = root(j);
			if (ri == rj) {
				// Already in the same connected component
				numEdges[ri]++;
			} else if (depth[ri] > depth[rj]) {
				// Move J to I's connected component
				parent[rj] = ri;
				// Transfer stats from J to I
				numEdges[ri] += numEdges[rj] + 1;
				numVertices[ri] += numVertices[rj];
				// J is no longer a root
				allRoots.remove(rj);
			} else if (depth[rj] > depth[ri]) {
				// Move I to J's connected component
				parent[ri] = rj;
				// Transfer stats from I to J
				numEdges[rj] += numEdges[ri] + 1;
				numVertices[rj] += numVertices[ri];
				// I is no longer a root
				allRoots.remove(ri);
			} else {
				if (Math.random() > 0.5) {
					// Move J to I's connected component
					parent[rj] = ri;
					depth[ri]++;
					// Transfer stats from J to I
					numEdges[ri] += numEdges[rj] + 1;
					numVertices[ri] += numVertices[rj];
					// J is no longer a root
					allRoots.remove(rj);
				} else {
					// Move I to J's connected component
					parent[ri] = rj;
					depth[rj]++;
					// Transfer stats from I to J
					numEdges[rj] += numEdges[ri] + 1;
					numVertices[rj] += numVertices[ri];
					// I is no longer a root
					allRoots.remove(ri);
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
		
		public int ans() {
			int c = 0;
			for (int i : allRoots)
				// Connected component of size N is cyclic iff. it has exactly N edges
				if (numVertices[i] == numEdges[i])
					c++;
			return c;
		}

	}
}
