import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**

Minimized version of Graph:
(suitable for copy-paste into solution)

	@SuppressWarnings("unused")
	private static final class Graph<T> {
		public class Node{
			public final T value;public int extra;
			public final Map<T,Integer>edges;
			public Node(T value){this.value=value;edges=new HashMap<>();}
			private Node(Node n){value=n.value;extra=n.extra;edges=n.edges;}}
		public final Map<T,Node>nodes;public final boolean directed;
		public int numNodes=0;public int numEdges=0;
		private Graph(boolean directed){nodes=new HashMap<>();this.directed=directed;}
		private Graph(boolean directed,int initialSize){nodes=new HashMap<>(initialSize,1);this.directed=directed;}
		public static<T>Graph<T>newGraph(boolean directed){return new Graph<>(directed);}
		public static<T,V>Graph<T>newGraphWithExtras(boolean directed){return new Graph<>(directed);}
		public static Graph<Integer>newIntegerGraph(boolean directed,int numNodes){return newGraph(directed,numNodes,i->i);}
		public static Graph<Integer>newIntegerGraphOneIndexed(boolean directed,int numNodes){return newGraph(directed,numNodes,i->i+1);}
		public static<T>Graph<T>newGraph(boolean directed,int numNodes,Function<Integer,T>values){Graph<T>g=new Graph<>(directed,numNodes);for(int i=0;i<numNodes;i++)g.addNode(values.apply(i));return g;}
		public void addNode(T value){nodes.put(value,new Node(value));numNodes++;}
		public void connect(T n1,T n2,int weight){nodes.get(n1).edges.put(n2,weight);if(!directed)nodes.get(n2).edges.put(n1,weight);numEdges++;}
		public void removeEdge(T n1,T n2){nodes.get(n1).edges.remove(n2);if(!directed)nodes.get(n2).edges.remove(n1);numEdges--;}
		public void processBFS(T source,Consumer<Node>consumer){Queue<T>q=new LinkedList<>();Set<T>visited=new HashSet<>();q.add(source);while(!q.isEmpty()){T id=q.poll();if(!visited.add(id))continue;Node n=nodes.get(id);consumer.accept(n);for(T child:n.edges.keySet())if(!visited.contains(child))q.add(child);}}
		public void processDFS(T source,Consumer<Node>consumer){Stack<T>q=new Stack<>();Set<T>visited=new HashSet<>();q.add(source);while(!q.isEmpty()){T id=q.pop();if(!visited.add(id))continue;Node n=nodes.get(id);consumer.accept(n);for(T child:n.edges.keySet())if(!visited.contains(child))q.add(child);}}
		public int distUnweightedBFS(T source,T dest){Queue<T>q=new LinkedList<>();Map<T,Integer>dist=new HashMap<>();q.add(source);dist.put(source,0);while(!q.isEmpty()){T id=q.poll();if(id==dest)return dist.get(id);Node n=nodes.get(id);for(T child:n.edges.keySet())if(!dist.containsKey(child)){dist.put(child,dist.get(id)+1);q.add(child);}}return-1;}
		public boolean containsCycle(){Map<T,Boolean>visited=new HashMap<>();T source=null;while((source=getFirstUnvisited(visited))!=null)if(containsCycleInternalDFS(source,visited))return true;return false;}
		private T getFirstUnvisited(Map<T,Boolean>visited){for(T t:nodes.keySet())if(!visited.containsKey(t))return t;return null;}
		private boolean containsCycleInternalDFS(T source,Map<T,Boolean>visited){visited.put(source,false);for(T child:nodes.get(source).edges.keySet())if(visited.containsKey(child)?!visited.get(child):containsCycleInternalDFS(child,visited))return true;visited.put(source,true);return false;}@SuppressWarnings("unchecked")
		public List<Node>topologicalSort(Comparator<T>comparator){if(comparator==null)comparator=(t1,t2)->((Comparable<T>)t1).compareTo(t2);PriorityQueue<T>q=new PriorityQueue<T>(comparator);List<Node>topo=new ArrayList<>();Map<T,Integer>inDegree=new HashMap<>();for(T source:nodes.keySet())for(T dest:nodes.get(source).edges.keySet())inDegree.merge(dest,1,(i,j)->i+j);for(T id:nodes.keySet())if(!inDegree.containsKey(id))q.add(id);while(!q.isEmpty()){Node n=nodes.get(q.poll());topo.add(n);for(T id:n.edges.keySet())if(inDegree.computeIfPresent(id,(k,v)->v-1)==0)q.add(id);}return topo;}
		public class DijkstrasNode extends Node{int dist=-1;boolean visited=false;DijkstrasNode previous;public DijkstrasNode(Node node){super(node);}}
		public Map<T,DijkstrasNode>dijkstras(T source,T dest){Map<T,DijkstrasNode>djk=nodes.values().stream().map(DijkstrasNode::new).collect(Collectors.toMap(n->n.value,n->n));djk.get(source).dist=0;PriorityQueue<DijkstrasNode>q=new PriorityQueue<>((i,j)->i.dist-j.dist);q.add(djk.get(source));int visitCount=0;while(!q.isEmpty()&&visitCount<djk.size()){DijkstrasNode n=q.poll();if(n.value==dest)return djk;if(n.visited)continue;n.visited=true;visitCount++;for(T child:n.edges.keySet()){DijkstrasNode cn=djk.get(child);if(!cn.visited&&(cn.dist==-1||n.dist+n.edges.get(child)<cn.dist)){if(cn.dist!=-1)q.remove(cn);cn.dist=n.dist+n.edges.get(child);cn.previous=n;q.add(cn);}}}return djk;}
		public int[][]floydWarshal(Function<T,Integer>f){int[][]m=getAdjacencyMatrix(f);for(T k:nodes.keySet())for(T i:nodes.keySet())for(T j:nodes.keySet())if(m[f.apply(i)][f.apply(k)]!=Integer.MAX_VALUE&&m[f.apply(k)][f.apply(j)]!=Integer.MAX_VALUE)m[f.apply(i)][f.apply(j)]=Math.min(m[f.apply(i)][f.apply(j)],m[f.apply(i)][f.apply(k)]+m[f.apply(k)][f.apply(j)]);return m;}
		public int[][]getAdjacencyMatrix(Function<T,Integer>f){int[][]m=new int[numNodes][numNodes];for(int[]row:m)Arrays.fill(row,Integer.MAX_VALUE);for(T t:nodes.keySet()){m[f.apply(t)][f.apply(t)]=0;for(T c:nodes.get(t).edges.keySet())m[f.apply(t)][f.apply(c)]=nodes.get(t).edges.get(c);}return m;}
	}

*/
public class Graph<T> {
	public class Node {
		public final T value;
		public int extra;
		public final Map<T, Integer> edges;

		public Node(T value) {
			this.value = value;
			edges = new HashMap<>();
		}

		private Node(Node n) {
			value = n.value;
			extra = n.extra;
			edges = n.edges;
		}
	}

	public final Map<T, Node> nodes;

	public final boolean directed;
	public int numNodes = 0;
	public int numEdges = 0;

	private Graph(boolean directed) {
		nodes = new HashMap<>();
		this.directed = directed;
	}

	private Graph(boolean directed, int initialSize) {
		nodes = new HashMap<>(initialSize, 1);
		this.directed = directed;
	}

	public static <T> Graph<T> newGraph(boolean directed) {
		return new Graph<>(directed);
	}

	public static <T, V> Graph<T> newGraphWithExtras(boolean directed) {
		return new Graph<>(directed);
	}

	public static Graph<Integer> newIntegerGraph(boolean directed, int numNodes) {
		return newGraph(directed, numNodes, i -> i);
	}

	public static Graph<Integer> newIntegerGraphOneIndexed(boolean directed, int numNodes) {
		return newGraph(directed, numNodes, i -> i + 1);
	}

	public static <T> Graph<T> newGraph(boolean directed, int numNodes, Function<Integer, T> values) {
		Graph<T> g = new Graph<>(directed, numNodes);
		for (int i = 0; i < numNodes; i++)
			g.addNode(values.apply(i));
		return g;
	}

	public void addNode(T value) {
		nodes.put(value, new Node(value));
		numNodes++;
	}

	public void connect(T n1, T n2, int weight) {
		nodes.get(n1).edges.put(n2, weight);
		if (!directed)
			nodes.get(n2).edges.put(n1, weight);
		numEdges++;
	}

	public void removeEdge(T n1, T n2) {
		nodes.get(n1).edges.remove(n2);
		if (!directed)
			nodes.get(n2).edges.remove(n1);
		numEdges--;
	}

	public void processBFS(T source, Consumer<Node> consumer) {
		Queue<T> q = new LinkedList<>();
		Set<T> visited = new HashSet<>();
		q.add(source);
		while (!q.isEmpty()) {
			T id = q.poll();
			if (!visited.add(id))
				continue;
			Node n = nodes.get(id);
			consumer.accept(n);
			for (T child : n.edges.keySet())
				if (!visited.contains(child))
					q.add(child);
		}
	}

	public void processDFS(T source, Consumer<Node> consumer) {
		Stack<T> q = new Stack<>();
		Set<T> visited = new HashSet<>();
		q.add(source);
		while (!q.isEmpty()) {
			T id = q.pop();
			if (!visited.add(id))
				continue;
			Node n = nodes.get(id);
			consumer.accept(n);
			for (T child : n.edges.keySet())
				if (!visited.contains(child))
					q.add(child);
		}
	}

	public int distUnweightedBFS(T source, T dest) {
		Queue<T> q = new LinkedList<>();
		Map<T, Integer> dist = new HashMap<>();
		q.add(source);
		dist.put(source, 0);
		while (!q.isEmpty()) {
			T id = q.poll();
			if (id == dest)
				return dist.get(id);
			Node n = nodes.get(id);
			for (T child : n.edges.keySet())
				if (!dist.containsKey(child)) {
					dist.put(child, dist.get(id) + 1);
					q.add(child);
				}
		}
		return -1;
	}

	public boolean containsCycle() {
		Map<T, Boolean> visited = new HashMap<>();
		T source = null;
		while ((source = getFirstUnvisited(visited)) != null)
			if (containsCycleInternalDFS(source, visited))
				return true;
		return false;
	}

	private T getFirstUnvisited(Map<T, Boolean> visited) {
		for (T t : nodes.keySet())
			if (!visited.containsKey(t))
				return t;
		return null;
	}

	private boolean containsCycleInternalDFS(T source, Map<T, Boolean> visited) {
		// false - seen only once; true - seen and exited
		visited.put(source, false);
		for (T child : nodes.get(source).edges.keySet())
			if (visited.containsKey(child) ? !visited.get(child) : containsCycleInternalDFS(child, visited))
				return true;
		visited.put(source, true);
		return false;
	}

	// nullable for natural ordering
	@SuppressWarnings("unchecked")
	public List<Node> topologicalSort(Comparator<T> comparator) {
		if (comparator == null)
			comparator = (t1, t2) -> ((Comparable<T>) t1).compareTo(t2);
		PriorityQueue<T> q = new PriorityQueue<T>(comparator);
		List<Node> topo = new ArrayList<>();
		Map<T, Integer> inDegree = new HashMap<>();
		for (T source : nodes.keySet())
			for (T dest : nodes.get(source).edges.keySet())
				inDegree.merge(dest, 1, (i, j) -> i + j);
		for (T id : nodes.keySet())
			if (!inDegree.containsKey(id)) // in-degree of 0
				q.add(id);
		while (!q.isEmpty()) {
			Node n = nodes.get(q.poll());
			topo.add(n);
			for (T id : n.edges.keySet())
				if (inDegree.computeIfPresent(id, (k, v) -> v - 1) == 0)
					q.add(id);
		}
		return topo;
	}

	public class DijkstrasNode extends Node {
		int dist = -1;
		boolean visited = false;
		DijkstrasNode previous;

		public DijkstrasNode(Node node) {
			super(node);
		}
	}

	public Map<T, DijkstrasNode> dijkstras(T source, T dest) {
		Map<T, DijkstrasNode> djk = nodes.values()
										 .stream()
										 .map(DijkstrasNode::new)
										 .collect(Collectors.toMap(n -> n.value, n -> n));
		djk.get(source).dist = 0;
		PriorityQueue<DijkstrasNode> q = new PriorityQueue<>((i, j) -> i.dist - j.dist);
		q.add(djk.get(source));
		int visitCount = 0;
		while (!q.isEmpty() && visitCount < djk.size()) {
			DijkstrasNode n = q.poll();
			if (n.value == dest)
				return djk;
			if (n.visited)
				continue;
			n.visited = true;
			visitCount++;
			for (T child : n.edges.keySet()) {
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

	public int[][] floydWarshal(Function<T, Integer> f) {
		int[][] m = getAdjacencyMatrix(f);
		for (T k : nodes.keySet())
			for (T i : nodes.keySet())
				for (T j : nodes.keySet())
					if (m[f.apply(i)][f.apply(k)] != Integer.MAX_VALUE && m[f.apply(k)][f.apply(j)] != Integer.MAX_VALUE)
						m[f.apply(i)][f.apply(j)] = Math.min(m[f.apply(i)][f.apply(j)], m[f.apply(i)][f.apply(k)] + m[f.apply(k)][f.apply(j)]);
		return m;
	}

	public int[][] getAdjacencyMatrix(Function<T, Integer> f) {
		int[][] m = new int[numNodes][numNodes];
		for (int[] row : m)
			Arrays.fill(row, Integer.MAX_VALUE);
		for (T t : nodes.keySet()) {
			m[f.apply(t)][f.apply(t)] = 0;
			for (T c : nodes.get(t).edges.keySet())
				m[f.apply(t)][f.apply(c)] = nodes.get(t).edges.get(c);
		}
		return m;
	}

}
