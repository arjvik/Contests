/*
ID: arjvik1
LANG: JAVA
TASK: UnionFind
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class UnionFind {
	private final int[] id;
	private final int[] size;
	
	public UnionFind(int n) {
		id = new int[n];
		Arrays.fill(id, -1);
		size = new int[n];
		Arrays.fill(size, 1);
	}
	
	public void union(int i, int j) {
		int ri = root(i);
		int rj = root(j);
		if(size[ri] > size[rj]) {
			id[rj] = ri;
			size[rj] += size[ri];
		} else {
			id[ri] = rj;
			size[ri] += size[rj];
		}
	}
	
	public boolean connected(int i, int j) {
		return root(i) == root(j);
	}

	private int root(int i) {
		if(id[i] == -1)
			return i;
		else
			return (id[i] = root(id[i]));
	}
	
	public int size(int i) {
		return size[i];
	}
}
