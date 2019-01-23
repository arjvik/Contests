import java.io.*;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.*;

@SuppressWarnings("unused")
public class shortcut {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("shortcut.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));
		int n = in.nextInt(),
			m = in.nextInt(),
			t = in.nextInt();
		Graph<Integer> graph = new Graph<Integer>(true);
		for (int i = 0; i < n; i++)
			graph.addNode(in.nextInt());
		for (int i = 0; i < m; i++)
			graph.connect(in.nextInt()-1, in.nextInt()-1, in.nextInt());
		List<Graph<Integer>.DijkstrasNode> dist = graph.dijkstras(0);
		
		calculateCows(dist.get(0));
		
		int maxSaving = 0;
		for (int i = 1; i < n; i++) 
			maxSaving = Math.max(maxSaving, (dist.get(i).dist-t)*graph.nodes.get(i).value);
		out.println(maxSaving);
		in.close();
		out.close();
	}

	private static int calculateCows(Graph<Integer>.DijkstrasNode source) {
		for (Graph<Integer>.DijkstrasNode child : source.next)
			source.value += calculateCows(child);
		return source.value;
	}

	private static int[] readArray(Scanner in, int n) {
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = in.nextInt();
		return array;
	}

	private static class Graph<T> {

		public class Node {
			public T value;
			public int id;
			public Map<Integer, Integer> edges;

			public Node(T value, int id) {
				this.value = value;
				this.id = id;
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
			nodes.add(new Node(value, nodes.size()));
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
			Set<DijkstrasNode> next = new HashSet<>();

			public DijkstrasNode(T value, int id) {
				super(value, id);
			}

			public DijkstrasNode(Node node) {
				super(node.value, node.id);
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
			PriorityQueue<Pair<DijkstrasNode, Integer>> q = new PriorityQueue<>(
										(i, j) -> i.x.dist == j.x.dist ? i.y - j.y
																	   : j.x.previous.id - i.x.previous.id);
			
			q.add(new Pair<>(djk.get(source), source));
			int visitCount = 0;
			while (!q.isEmpty() && visitCount < djk.size()) {
				DijkstrasNode n = q.poll().x;
				if (n.visited)
					continue;
				n.visited = true;
				visitCount++;
				for (int child : n.edges.keySet()) {
					DijkstrasNode cn = djk.get(child);
					if (!cn.visited && (cn.dist == -1 || n.dist + n.edges.get(child) < cn.dist)) {
						if (cn.dist != -1)
							q.remove(new Pair<>(cn, child));
						cn.dist = n.dist + n.edges.get(child);
						if (cn.previous != null) {
							cn.previous.next.remove(cn);
						}
						cn.previous = n;
						n.next.add(cn);
						q.add(new Pair<>(cn, child));
					}
				}
			}
			return djk;
		}
		
		

	}
	
	public static class Pair<X, Y> {
		public X x;public Y y;public Pair(X x, Y y) {this.x = x;this.y = y;}
		public static Pair<Integer, Integer> ofInt(int x, int y) {return new Pair<Integer, Integer>(x,y);}
		public X getX() {return x;} public void setX(X x) {this.x = x;}
		public Y getY() {return y;} public void setY(Y y) {this.y = y;}
		@Override public int hashCode() {final int prime = 31;int result = 1;
			result = prime * result + ((x == null) ? 0 : x.hashCode());
			result = prime * result + ((y == null) ? 0 : y.hashCode());return result;}
		@Override public boolean equals(Object obj) {
			if (this == obj) return true; if (obj == null) return false;
			if (getClass() != obj.getClass()) return false; Pair<?,?> other = (Pair<?,?>) obj;
			if (x == null) { if (other.x != null) return false; } else if (!x.equals(other.x)) return false;
			if (y == null) { if (other.y != null) return false; } else if (!y.equals(other.y)) return false; return true;}
		@Override public String toString() { return "Pair<" + x + ", " + y + ">"; }
	}
}
