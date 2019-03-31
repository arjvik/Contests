import java.io.*;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.*;

@SuppressWarnings("unused")
public class cowland_bad {
	private static int[] values;

	public static void main(String[] args) throws IOException {
		FastScanner in = new FastScanner(new FileInputStream("cowland.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowland.out")));
		int n = in.nextInt();
		int q = in.nextInt();
		values = readArray(in, n);
		Graph<Integer> graph = new Graph<>(false, n, i -> i);
		for (int i = 0; i < n-1; i++)
			graph.connect(in.nextInt()-1, in.nextInt()-1, 1);
		
		
		// find center of graph
		int center;
		int[] d = new int[4];// ignore d[0]
		int[] par = new int[n];
		graph.processBFS(d[1], (node, i) -> d[2] = i);
		graph.processBFSwithParent(d[2], (p, i) -> { par[i]=p; d[3] = i; });
		int p = d[3]; int dep = 0;
		while (p != d[2]) { p = par[p]; dep++; }
		p=d[3];
		for (int i =0; i<dep/2;i++) p = par[p];
		center = p;
		//make tree
		TreeNode[] nodes = new TreeNode[n];
		nodes[center] = new TreeNode(center, null);
		graph.processBFSwithParent(center, (pa, i) -> {
			nodes[i] = new TreeNode(i, pa!=-1 ? nodes[pa] : null);
			if (pa != -1)
				nodes[pa].children.add(nodes[i]);
		});
		recurFillGrandchildren(nodes, nodes[center]);
//		System.err.printf("D1=%d D2=%d D3=%d%n", d[1]+1, d[2]+1, d[3]+1);
//		System.err.printf("Depth from D2-D3 (diameter) = %d%n", dep);
//		System.err.printf("Parents of D2 source traversal: %s%n", Arrays.toString(par));
//		System.err.printf("Center: %d%n", center+1);
		
		
		for (int i = 0; i < q; i++) {
			if (in.nextInt() == 1) { //update
				
				values[in.nextInt()-1] = in.nextInt();
				
			} else {
				
				/////////////////////////////
				//get xor
				TreeNode a = nodes[in.nextInt()-1];
				TreeNode b = nodes[in.nextInt()-1];
				TreeNode lca = nodes[center];
				lca:while (true) {
					for (TreeNode child : lca.children) {
						if (child.grandchildren.contains(a) && child.grandchildren.contains(b)) {
							lca = child;
							continue lca;
						}
					}
					break lca;
				}
				int x = 0;
				TreeNode t = a;
				while(t != lca) {
					x ^= values[t.id];
					t = t.parent;
				}
				t = b;
				while(t != lca) {
					x ^= values[t.id];
					t = t.parent;
				}
				out.println(x^values[lca.id]);
				////////////////////////////
				
			}
		}
		
		out.close();
	}

	private static Set<TreeNode> recurFillGrandchildren(TreeNode[] nodes, TreeNode source) {
		source.grandchildren.add(source);
		for (TreeNode child : source.children)
			source.grandchildren.addAll(recurFillGrandchildren(nodes, child));
		return source.grandchildren;
	}

	private static class TreeNode {
		public int id;
		public TreeNode parent;
		public List<TreeNode> children = new ArrayList<>();
		public Set<TreeNode> grandchildren = new HashSet<>();
		public TreeNode(int id, TreeNode parent) {
			this.id = id;
			this.parent = parent;
		}
	}

	public static int[] readArray(FastScanner in, int n) {
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = in.nextInt();
		return array;
	}

	// ----------------GRAPH CLASS---------------------------

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

		public Graph(boolean directed, int numNodes, T value) {
			this(directed, numNodes, i -> value);
		}

		public Graph(boolean directed, int numNodes, Function<Integer, T> values) {
			nodes = new ArrayList<>(numNodes);
			this.directed = directed;
			for (int i = 0; i < numNodes; i++)
				addNode(values.apply(i));
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

		public void processBFS(int source, BiConsumer<Node, Integer> consumer) {
			Queue<Integer> q = new LinkedList<>();
			boolean[] visited = new boolean[numNodes];
			q.add(source);
			while (!q.isEmpty()) {
				int id = q.poll();
				if (visited[id])
					continue;
				visited[id] = true;
				Node n = nodes.get(id);
				consumer.accept(n, id);
				for (int c : n.edges.keySet())
					q.add(c);
			}
		}

		public void processBFSwithParent(int source, BiConsumer<Integer, Integer> consumer /* parent, vertex */) {
			Queue<Pair<Integer, Integer>> q = new LinkedList<>();
			boolean[] visited = new boolean[numNodes];
			q.add(Pair.ofInt(-1, source));
			while (!q.isEmpty()) {
				Pair<Integer, Integer> p = q.poll();
				if (visited[p.y])
					continue;
				visited[p.y] = true;
				Node n = nodes.get(p.y);
				consumer.accept(p.x, p.y);
				for (int c : n.edges.keySet())
					q.add(Pair.ofInt(p.y, c));
			}
		}

		public void processDFS(int source, BiConsumer<Node, Integer> consumer) {
			Stack<Integer> q = new Stack<>();
			boolean[] visited = new boolean[numNodes];
			q.push(source);
			while (!q.isEmpty()) {
				int id = q.pop();
				if (visited[id])
					continue;
				visited[id] = true;
				Node n = nodes.get(id);
				consumer.accept(n, id);
				for (int c : n.edges.keySet())
					q.add(c);
			}
		}

		public boolean containsCycle() {
			int[] visited = new int[numNodes];
			int source = -1;
			while ((source = getFirstUnvisited(visited)) != -1)
				if (containsCycleInternalDFS(source, visited))
					return true;
			return false;
		}

		private int getFirstUnvisited(int[] visited) {
			for (int i = 0; i < visited.length; i++)
				if (visited[i] == 0)
					return i;
			return -1;
		}

		private boolean containsCycleInternalDFS(int source, int[] visited) {
			visited[source] = 1;
			for (int child : nodes.get(source).edges.keySet())
				if (visited[child] == 1 || visited[child] == 0 && containsCycleInternalDFS(child, visited))
					return true;
			visited[source] = 2;
			return false;
		}

		public List<Node> topologicalSort() {
			PriorityQueue<Integer> q = new PriorityQueue<Integer>((i, j) -> i - j);
			List<Node> topo = new ArrayList<>();
			int[] inDegree = new int[numNodes];
			for (int i = 0; i < numNodes; i++)
				for (int j : nodes.get(i).edges.keySet())
					inDegree[j]++;
			for (int i = 0; i < numNodes; i++)
				if (inDegree[i] == 0)
					q.add(i);
			while (!q.isEmpty()) {
				Node n = nodes.get(q.poll());
				topo.add(n);
				for (int i : n.edges.keySet()) {
					inDegree[i]--;
					if (inDegree[i] == 0)
						q.add(i);
				}
			}
			return topo;
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
	

	
	//pair class
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
	
	private static class FastScanner{private InputStream s;private byte[]b=new byte[1024];private int c;private int n;
	public FastScanner(InputStream s){this.s=s;}
	int read(){if(c>=n){c=0;try{n=s.read(b);}catch(IOException e){}if(n<=0)return-1;}return b[c++];}
	boolean isSpaceChar(int c){return c==' '||c=='\n'||c=='\r'||c=='\t'||c==-1;}
	boolean isEndline(int c){return c=='\n'||c=='\r'||c==-1;}
	int nextInt(){return Integer.parseInt(next());}
	long nextLong(){return Long.parseLong(next());}
	double nextDouble(){return Double.parseDouble(next());}
	String next(){int c=read();while(isSpaceChar(c))
		c=read();StringBuilder res=new StringBuilder();do{res.appendCodePoint(c);c=read();}while(!isSpaceChar(c));return res.toString();}
	String nextLine(){int c=read();while(isEndline(c))
		c=read();StringBuilder res=new StringBuilder();do{res.appendCodePoint(c);c=read();}while(!isEndline(c));return res.toString();}
}

}
