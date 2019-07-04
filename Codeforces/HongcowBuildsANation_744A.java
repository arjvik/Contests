
/*
ID: arjvik1
LANG: JAVA
TASK: HongcowBuildsANation
*/
import java.io.*;
import java.util.*;

public class HongcowBuildsANation_744A {
	private static final class Pair { public final Integer x; public final Integer y; public Pair(Integer t1, Integer t2) { x = t1; y = t2; } }

	private static boolean[] visited;
	private static List<Integer>[] graph;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
		int[] govs = new int[k];
		for (int i = 0; i < k; i++)
			govs[i] = in.nextInt()-1;
		graph = new List[n];
		for (int i = 0; i < n; i++)
			graph[i] = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			int n1 = in.nextInt()-1, n2 = in.nextInt()-1;
			graph[n1].add(n2);
			graph[n2].add(n1);
		}
		visited = new boolean[n];
		int largestCountrySize = Integer.MIN_VALUE;
		int roads = 0;
		for (int gov : govs) {
			Pair p = visitGov(gov);
			roads += ((p.y*(p.y-1))/2 ) - (p.x/2);
			if(p.y > largestCountrySize)
				largestCountrySize = p.y;
		}
		int unconnectedNodes = 0;
		int unconnectedRoads = 0;
		for (int i = 0; i < n; i++) {
			if (visited[i]) continue;
			unconnectedNodes++;
			unconnectedRoads += graph[i].size();
		}
		unconnectedRoads /= 2;
		roads += (unconnectedNodes*(unconnectedNodes-1))/2 - unconnectedRoads;
		roads += unconnectedNodes * largestCountrySize;
		System.out.println(roads);
		in.close();
	}

	private static Pair visitGov(int node) {
		if (visited[node])
			return new Pair(0, 0);
		visited[node] = true;
		int roads = graph[node].size();
		int nodes = 1;
		for (int n : graph[node]) {
			Pair p = visitGov(n);
			roads += p.x;
			nodes += p.y;
		}
		return new Pair(roads, nodes);
	}
}
