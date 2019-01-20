/*
ID: arjvik1
LANG: JAVA
TASK: visitfj
*/
import java.io.*;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.*;

@SuppressWarnings("unused")
public class visitfj {
	private static int n;
	private static int[][] grass;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("visitfj.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("visitfj.out")));
		n = in.nextInt();
		int t = in.nextInt();
		grass = readArray2d(in, n, n);
		grass[0][0] = 0;
		Graph<Void> graph = new Graph<Void>(true);
		for (int i = 0; i < n*n*6; i++)
			graph.addNode(null);
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (r != 0) {
					graph.connect(compress(r, c, 0, 1), compress(r-1, c, 1, 0), t);
					graph.connect(compress(r, c, 1, 1), compress(r-1, c, 2, 0), t);
					graph.connect(compress(r, c, 2, 1), compress(r-1, c, 0, 0), t);
				}
				if (r != n-1) {
					graph.connect(compress(r, c, 0, 1), compress(r+1, c, 1, 0), t);
					graph.connect(compress(r, c, 1, 1), compress(r+1, c, 2, 0), t);
					graph.connect(compress(r, c, 2, 1), compress(r+1, c, 0, 0), t);
				}
				if (c != 0) {
					graph.connect(compress(r, c, 0, 1), compress(r, c-1, 1, 0), t);
					graph.connect(compress(r, c, 1, 1), compress(r, c-1, 2, 0), t);
					graph.connect(compress(r, c, 2, 1), compress(r, c-1, 0, 0), t);
				}
				if (c != n-1) {
					graph.connect(compress(r, c, 0, 1), compress(r, c+1, 1, 0), t);
					graph.connect(compress(r, c, 1, 1), compress(r, c+1, 2, 0), t);
					graph.connect(compress(r, c, 2, 1), compress(r, c+1, 0, 0), t);
				}
				graph.connect(compress(r, c, 0, 0), compress(r, c, 0, 1), grass[r][c]);
				graph.connect(compress(r, c, 1, 0), compress(r, c, 1, 1), 0);
				graph.connect(compress(r, c, 2, 0), compress(r, c, 2, 1), 0);
			}
		}
		
		List<Graph<Void>.DijkstrasNode> dn = graph.dijkstras(compress(0, 0, 0, 0));
		int dist = Math.min( Math.min(
				dn.get(compress(n-1,n-1,0,1)).dist,
				dn.get(compress(n-1,n-1,1,1)).dist),
				dn.get(compress(n-1,n-1,2,1)).dist
		);
		out.println(dist);
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
			numNodes++;
		}

		public void connect(int i, int j, int weight) {
			nodes.get(i).edges.put(j, weight);
			if (!directed)
				nodes.get(j).edges.put(i, weight);
			numEdges++;
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
	
	private static int compress(int r, int c, int mod, int in) {
		int coord = r*n+c;
		return coord*6+mod*2+in;
	}
	
	private static int[] readArray(Scanner in, int n) {
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = in.nextInt();
		return array;
	}

	private static int[][] readArray2d(Scanner in, int n, int m) {
		int[][] array = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				array[i][j] = in.nextInt();
		return array;
	}
}
