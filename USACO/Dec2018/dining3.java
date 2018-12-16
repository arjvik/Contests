/*
ID: arjvik1
LANG: JAVA
TASK: dining
*/
import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class dining3 {
	/*public static class DijkstrasNode {
		public final int from, to, dist;
		public final boolean neg;
		public DijkstrasNode(int from, int to, int dist, boolean neg) {
			this.from = from;
			this.to = to;
			this.dist = dist;
			this.neg = neg;
		}
	}*/
	private static class Node {
		public final int id;
		public final Map<Node, Integer> connections;
		public int dist = Integer.MAX_VALUE;
		public boolean visitedHay = false;
		public Node(int i) {
			id=i;
			connections = new HashMap<>();
		}
	}
	
	private static Node[] graph;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("dining.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dining.out")));
		int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
		graph = new Node[n+k];
		for (int i = 0; i < n; i++)
			graph[i] = new Node(i);
		for (int i = 0; i < m; i++) {
			int a = in.nextInt()-1,
				b = in.nextInt()-1,
				t = in.nextInt()*10;                    // *10 lets us have halfway times to force the neg edge to be prefered
			graph[a].connections.put(graph[b], t);
			graph[b].connections.put(graph[a], t);
		}
		for (int i = 0; i < k; i++) {
			int h = in.nextInt()-1,
				y = in.nextInt()*10;
			
			graph[n+i] = new Node(-1*i-1);
			graph[h].connections.put(graph[n+i], -y-5); /************** MAYBE `-y-1` INSTEAD??***/
			graph[n+i].connections.put(graph[h], -y-5); // ^^^here too^^^
			
			for (Node node : graph[h].connections.keySet()) {
				node.connections.put(graph[n+i], graph[h].connections.get(node));
				graph[n+i].connections.put(node, graph[h].connections.get(node));
			}
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.dist - n2.dist);
		graph[n-1].dist = 0;
		pq.add(graph[n-1]);
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			for (Node peer : node.connections.keySet()) {
				if (!node.visitedHay || node.connections.get(peer) >= 0) {
					if (peer.dist > node.dist + node.connections.get(peer)) {
						peer.dist = node.dist + node.connections.get(peer);
						peer.visitedHay = node.visitedHay || node.connections.get(peer) <= 0;
						pq.add(peer);
					}
				}
					
			}
		}
		
		for (int i = 0; i < n-1; i++ ) {
			out.println(graph[i].visitedHay ? 1 : 0);
		}
		
		in.close();
		out.close();
	}
}
