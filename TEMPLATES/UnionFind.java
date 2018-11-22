
/*
ID: arjvik1
LANG: JAVA
TASK: UnionFind
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class UnionFind {
	private final int[] parent;
	private final int[] rank;

	public UnionFind(int n) {
		parent = new int[n];
		Arrays.fill(parent, -1);
		rank = new int[n];
	}

	public void union(int i, int j) {
		int ri = root(i);
		int rj = root(j);
		if (rank[ri] > rank[rj]) {
			parent[rj] = ri;
		} else if (rank[rj] > rank[ri]) {
			parent[ri] = rj;
		} else {
			if (Math.random() > 0.5) {
				parent[rj] = ri;
				rank[ri]++;
			} else {
				parent[ri] = rj;
				rank[rj]++;
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

}
