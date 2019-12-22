/*
ID: arjvik1
LANG: JAVA
TASK: milkorder
*/
import java.io.*;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.*;

public class milkorder {
	public static void main(String[] args) throws IOException {
//		Scanner in = new Scanner(new BufferedReader(new FileReader("milkorder.in")));
		FastScanner in = new FastScanner(new FileInputStream("milkorder.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
		int n = in.nextInt(),
			m = in.nextInt();
		int[][] obs = new int [m][];
		for (int i = 0; i < m; i++)
			obs[i] = readArray(in, in.nextInt());
		
		Function<Integer, Integer> iden = i -> i+1;
		Graph<Integer> graph = new Graph<Integer>(true, n, iden);
		
		int begin = 0;
		int end = m-1;
		int mid=0;
		
		while (begin < end) {
			int oldMid = mid;
			mid = begin+(end-begin)/2; //avoid overflow
			if (mid > oldMid) {
				for (int i = oldMid+1; i <= mid; i++)
					for (int j = 0; j < obs[i].length-1; j++)
						graph.connect(obs[i][j], obs[i][j+1], 1);
			} else {
				graph = new Graph<>(true, n, iden);
				for (int i = 0; i<= mid; i++)
					for (int j = 0; j < obs[i].length-1; j++)
						graph.connect(obs[i][j], obs[i][j+1], 1);
			}
			
			if (graph.containsCycle()) {
				end = mid-1;
			} else {
				for (int j = 0; j < obs[mid+1].length-1; j++)
					graph.connect(obs[mid+1][j], obs[mid+1][j+1], 1);
				if (graph.containsCycle())
					end = begin-1;
				else
					begin=mid+1;
			}
			
		}
		int k = mid;
		graph = new Graph<>(true, n, iden);
		for (int i = 0; i<= k; i++)
			for (int j = 0; j < obs[i].length-1; j++)
				graph.connect(obs[i][j], obs[i][j+1], 1);
		
		out.println(graph.topologicalSort()
						 .stream()
						 .map(node -> node.value)
						 .map(i -> Integer.toString(i))
						 .collect(Collectors.joining(" ")));
		out.close();
	}

	private static int[] readArray(FastScanner in, int n) {
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = in.nextInt()-1;
		return array;
	}
	
	//-------------GRAPH CLASS------------------
	
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
				for (int c: n.edges.keySet())
					q.add(c);
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
				for (int c: n.edges.keySet())
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
			PriorityQueue<Integer> q = new PriorityQueue<Integer>((i,j) -> i-j);
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

	//-------------FAST SCANNER------------------
	
	@SuppressWarnings("unused")
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
