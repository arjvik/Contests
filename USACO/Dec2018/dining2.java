
/*
ID: arjvik1
LANG: JAVA
TASK: dining
*/
import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class dining2 {
	private static class Pair {
		public Pair(int dist, int end) {
			this.dist = dist;
			this.end = end;
		}

		public final int dist, end;
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("dining.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dining.out")));
		int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
		List<Map<Integer, Integer>> graph; // List idx=source value=map(dest->weight)
		graph = IntStream.range(0, n).mapToObj(i -> new HashMap<Integer, Integer>()).collect(Collectors.toList());
		for (int i = 0; i < m; i++) {
			int a = in.nextInt() - 1, b = in.nextInt() - 1, t = in.nextInt();
			graph.get(a).put(b, t);
			graph.get(b).put(a, t);
		}

		int[] distBarn = dijkstras(n, graph, n-1, Integer.MAX_VALUE);
		int maxDistToBarn = Arrays.stream(distBarn).max().getAsInt();
		
		Map<Integer, Integer> hPosYumMap = new HashMap<>();
		for (int i = 0; i < k; i++) {
			int pos = in.nextInt()-1, yum = in.nextInt();
			if (!hPosYumMap.containsKey(pos) || hPosYumMap.get(pos) < yum)
				hPosYumMap.put(pos, yum);
		}
		
		List<Integer> hPos = new ArrayList<>(hPosYumMap.keySet()),
					  hYum = hPos.stream().sequential().map(hPosYumMap::get).collect(Collectors.toList());
		k = hPos.size();
		int[][] distHay = new int[k][n];
		for (int i = 0; i < k; i++) {
			distHay[i] = /*bfs*/ dijkstras(n, graph, hPos.get(i), hYum.get(i) + maxDistToBarn);
		}
		
		cow: for (int i = 0; i < n - 1; i++) {
			int time = distBarn[i];
			for (int j = 0; j < k; j++)
				if (distHay[j][i] + distHay[j][n-1] - time <= hYum.get(j)) {
					out.println(1);
					continue cow;
				}
			out.println(0);
		}
		
		
		in.close();
		out.close();
	}

	private static int[] dijkstras(int n, List<Map<Integer, Integer>> graph, int source, int maxDist) {
		Queue<Pair> pq = new PriorityQueue<>((p1, p2) -> p1.dist - p2.dist);
		int vCount = 0;
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		pq.add(new Pair(0, source));
		while (vCount < n) {
			Pair p = pq.poll();
			if (p.dist > maxDist)
				return dist;
			if (dist[p.end] == Integer.MAX_VALUE)
				vCount++;
			dist[p.end] = Math.min(dist[p.end], p.dist);
			for (int newDest : graph.get(p.end).keySet())
				if (p.dist + graph.get(p.end).get(newDest) < dist[newDest])
					pq.add(new Pair(p.dist + graph.get(p.end).get(newDest), newDest));

		}
		return dist;
	}
	
	private static int[] bfs(int n, List<Map<Integer, Integer>> graph, int source, int maxDist) {
		Queue<Pair> q = new LinkedList<>(); //BFS uses a Q
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		q.add(new Pair(0, source));
		while (!q.isEmpty()) {
			Pair p = q.poll();
			dist[p.end] = Math.min(dist[p.end], p.dist);
			if (p.dist <= maxDist)
				for (int newDest : graph.get(p.end).keySet())
					if (p.dist + graph.get(p.end).get(newDest) < dist[newDest])
						q.add(new Pair(p.dist + graph.get(p.end).get(newDest), newDest));
		}
		return dist;
	}
}
