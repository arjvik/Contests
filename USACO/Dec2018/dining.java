/*
ID: arjvik1
LANG: JAVA
TASK: dining
*/
import java.io.*;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.*;

public class dining {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("dining.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dining.out")));
		int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
		Graph<Integer> graph = new Graph<>(false);
		for (int i = 1; i <= n; i++)
			graph.addNode(i);
		for (int i = 0; i < m; i++)
			graph.connect(in.nextInt()-1, in.nextInt()-1, in.nextInt());
		List<Graph<Integer>.DijkstrasNode> di = graph.dijkstras(n-1);
		int[] hp = new int[k], hy = new int[k], hdy = new int[k];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < k; i++) {
			hp[i]  = in.nextInt()-1;
			hy[i]  = in.nextInt();
			hdy[i] = di.get(hp[i]).dist - hy[i];
			max = Math.max(max, hdy[i]);
		}
		graph.addNode(-1);
		for (int i = 0; i < k; i++) {
			graph.connect(hp[i], n, hdy[i] + max);
		}
		List<Graph<Integer>.DijkstrasNode> dhi = graph.dijkstras(n);
		for (int i = 0; i < n-1; i++) {
			if (di.get(i).dist + max >= dhi.get(i).dist)
				out.println(1);
			else
				out.println(0);
		}
		in.close();
		out.close();
	}
	
	public static class Graph<T> {

		public class Node {
			public T value;
			public Map<Integer, Integer> edges;

			public Node(T value) {
				this.value = value;
				edges = new HashMap<>();
			}
		}

		public List<Node> nodes;

		public boolean directed;
		public int numNodes = 0;
		public int numEdges = 0;

		public Graph() {
			this(false);
		}

		public Graph(boolean directed) {
			nodes = new ArrayList<>();
			this.directed = directed;
		}

		public void addNode(T value) {
			nodes.add(new Node(value));
		}

		public void connect(int i, int j, int weight) {
			nodes.get(i).edges.put(j, weight);
			if (!directed)
				nodes.get(j).edges.put(i, weight);
		}

		public class DijkstrasNode extends Node {
			int dist = -1;
			boolean visited = false;
			DijkstrasNode previous;

			public DijkstrasNode(T value) {
				super(value);
			}

			public DijkstrasNode(Node node) {
				super(node.value);
				this.edges = node.edges;
			}
		}

		public void processBFS(int source, BiConsumer<Node, Integer> consumer) {
			Queue<Integer> q = new LinkedList<>();
			boolean[] visited = new boolean[nodes.size()];
			q.add(source);
			while (!q.isEmpty()) {
				int id = q.poll();
				if (visited[id])
					continue;
				visited[id] = true;
				Node n = nodes.get(id);
				consumer.accept(n, id);
				for (int c: n.edges.keySet())
					q.add(c);
			}
		}

		public void processDFS(int source, BiConsumer<Node, Integer> consumer) {
			Stack<Integer> q = new Stack<>();
			boolean[] visited = new boolean[nodes.size()];
			q.push(source);
			while (!q.isEmpty()) {
				int id = q.pop();
				if (visited[id])
					continue;
				visited[id] = true;
				Node n = nodes.get(id);
				consumer.accept(n, id);
				for (int c: n.edges.keySet())
					q.add(c);
			}
		}

		public List<DijkstrasNode> dijkstras(int source) {
			List<DijkstrasNode> djk = nodes.stream().map(DijkstrasNode::new).collect(Collectors.toList());
			djk.get(source).dist = 0;
			PriorityQueue<DijkstrasNode> q = new PriorityQueue<>((i, j) -> i.dist - j.dist);
			q.add(djk.get(source));
			int visitCount = 0;
			while (!q.isEmpty() && visitCount < djk.size()) {
				DijkstrasNode n = q.poll();
				if (n.visited)
					continue;
				n.visited = true;
				visitCount++;
				for (int child : n.edges.keySet()) {
					DijkstrasNode cn = djk.get(child);
					if (!cn.visited && (cn.dist == -1 || n.dist + n.edges.get(child) < cn.dist)) {
						if (cn.dist != -1)
							q.remove(cn);
						cn.dist = n.dist + n.edges.get(child);
						cn.previous = n;
						q.add(cn);
					}
				}
			}
			return djk;
		}

	}
}
