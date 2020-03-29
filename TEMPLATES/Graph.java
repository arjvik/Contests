import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**

Minimized version of Graph:
(suitable for copy-paste into solution)

	@SuppressWarnings("unused")
	private static class Graph<T>{
		public class Node{
			public final T value;public int extra;
			public final Map<T,Integer>edges;
			public Node(T value){this.value=value;edges=new HashMap<>();}
			private Node(Node n){value=n.value;extra=n.extra;edges=n.edges;}}
		public final Map<T,Node>nodes;public final boolean directed;public int numNodes=0;public int numEdges=0;
		private Graph(boolean directed){nodes=new HashMap<>();this.directed=directed;}
		private Graph(boolean directed,int initialSize){nodes=new HashMap<>(initialSize,1);this.directed=directed;}
		public static<T>Graph<T>newGraph(boolean directed){return new Graph<>(directed);}
		public static Graph<Integer>newIntegerGraph(boolean directed,int numNodes){return newGraphWithValues(directed,numNodes,i->i);}
		public static Graph<Integer>newIntegerGraphOneIndexed(boolean directed,int numNodes){return newGraphWithValues(directed,numNodes,i->i+1);}
		public static<T>Graph<T>newGraphWithValues(boolean directed,int numNodes,Function<Integer,T>values){Graph<T>g=new Graph<>(directed,numNodes);for(int i=0;i<numNodes;i++)g.addNode(values.apply(i));return g;}
		public void addNode(T value){nodes.put(value,new Node(value));numNodes++;}
		public void connect(T n1,T n2,int weight){nodes.get(n1).edges.put(n2,weight);if(!directed)nodes.get(n2).edges.put(n1,weight);numEdges++;}
		public void removeEdge(T n1,T n2){nodes.get(n1).edges.remove(n2);if(!directed)nodes.get(n2).edges.remove(n1);numEdges--;}
		public class ExtendedNodeInfo{public final Node node,parent;public final int dist;public ExtendedNodeInfo(Node n,Node parent,int dist){this.node=n;this.parent=parent;this.dist=dist;}public String toString(){return String.format("%s, P=%s, D=%d",node.value,parent==null?"null":parent.value,dist);}}
		public void processSearchExtended(T source,Consumer<ExtendedNodeInfo>consumer,boolean bfs){Deque<ExtendedNodeInfo>q=new ArrayDeque<>();Set<T>visited=new HashSet<>();q.add(new ExtendedNodeInfo(nodes.get(source),null,0));while(!q.isEmpty()){ExtendedNodeInfo info=bfs?q.poll():q.removeLast();if(!visited.add(info.node.value))continue;consumer.accept(info);for(T child:info.node.edges.keySet())if(!visited.contains(child))q.add(new ExtendedNodeInfo(nodes.get(child),info.node,info.dist+info.node.edges.get(child)));}}
		public void processSearch(T source,Consumer<Node>consumer,boolean bfs){processSearchExtended(source,info->consumer.accept(info.node),bfs);}
		public int distUnweightedBFS(T source,T dest){Queue<ExtendedNodeInfo>q=new LinkedList<>();Set<T>visited=new HashSet<>();q.add(new ExtendedNodeInfo(nodes.get(source),null,0));while(!q.isEmpty()){ExtendedNodeInfo info=q.poll();if(!visited.add(info.node.value))continue;if(info.node.value.equals(dest))return info.dist;for(T child:info.node.edges.keySet())if(!visited.contains(child))q.add(new ExtendedNodeInfo(nodes.get(child),info.node,info.dist+1));}return-1;}
		public boolean containsCycle(){Map<T,Boolean>visited=new HashMap<>();T source=null;while((source=getFirstUnvisited(visited))!=null)if(containsCycleInternalDFS(source,visited))return true;return false;}
		private T getFirstUnvisited(Map<T,Boolean>visited){for(T t:nodes.keySet())if(!visited.containsKey(t))return t;return null;}
		private boolean containsCycleInternalDFS(T source,Map<T,Boolean>visited){visited.put(source,false);for(T child:nodes.get(source).edges.keySet())if(visited.containsKey(child)?!visited.get(child):containsCycleInternalDFS(child,visited))return true;visited.put(source,true);return false;}@SuppressWarnings("unchecked")
		public List<Node>topologicalSort(Comparator<T>comparator){if(comparator==null)comparator=(t1,t2)->t1 instanceof Comparable?((Comparable<T>)t1).compareTo(t2):t1.hashCode()-t2.hashCode();PriorityQueue<T>q=new PriorityQueue<T>(comparator);List<Node>topo=new ArrayList<>();Map<T,Integer>inDegree=new HashMap<>();for(T source:nodes.keySet())for(T dest:nodes.get(source).edges.keySet())inDegree.merge(dest,1,(i,j)->i+j);for(T id:nodes.keySet())if(!inDegree.containsKey(id))q.add(id);while(!q.isEmpty()){Node n=nodes.get(q.poll());topo.add(n);for(T id:n.edges.keySet())if(inDegree.computeIfPresent(id,(k,v)->v-1)==0)q.add(id);}return topo;}
		public class DijkstrasNode extends Node{int dist=-1;boolean visited=false;DijkstrasNode previous;public DijkstrasNode(Node node){super(node);}}
		public Map<T,DijkstrasNode>dijkstrasAll(T source){return dijkstrasInternal(source,null);}
		public int dijkstrasDist(T source,T dest){return dijkstrasInternal(source,dest).get(dest).dist;}
		public List<DijkstrasNode>dijkstrasPath(T source,T dest){DijkstrasNode end=dijkstrasInternal(source,dest).get(dest);List<DijkstrasNode>path=new ArrayList<>();path.add(end);while((end=end.previous)!=null);path.add(end);Collections.reverse(path);return path;}
		private Map<T,DijkstrasNode>dijkstrasInternal(T source,T dest){Map<T,DijkstrasNode>djk=nodes.values().stream().map(DijkstrasNode::new).collect(Collectors.toMap(n->n.value,n->n));djk.get(source).dist=0;PriorityQueue<DijkstrasNode>q=new PriorityQueue<>((i,j)->i.dist-j.dist);q.add(djk.get(source));int visitCount=0;while(!q.isEmpty()&&visitCount<djk.size()){DijkstrasNode n=q.poll();if(n.value.equals(dest)&&dest!=null)return djk;if(n.visited)continue;n.visited=true;visitCount++;for(T child:n.edges.keySet()){DijkstrasNode cn=djk.get(child);if(!cn.visited&&(cn.dist==-1||n.dist+n.edges.get(child)<cn.dist)){if(cn.dist!=-1)q.remove(cn);cn.dist=n.dist+n.edges.get(child);cn.previous=n;q.add(cn);}}}return djk;}
		public int[][]floydWarshal(Function<T,Integer>f){int[][]m=getAdjacencyMatrix(f);for(T k:nodes.keySet())for(T i:nodes.keySet())for(T j:nodes.keySet())if(m[f.apply(i)][f.apply(k)]!=Integer.MAX_VALUE&&m[f.apply(k)][f.apply(j)]!=Integer.MAX_VALUE)m[f.apply(i)][f.apply(j)]=Math.min(m[f.apply(i)][f.apply(j)],m[f.apply(i)][f.apply(k)]+m[f.apply(k)][f.apply(j)]);return m;}public int[][]getAdjacencyMatrix(Function<T,Integer>f){int[][]m=new int[numNodes][numNodes];for(int[]row:m)Arrays.fill(row,Integer.MAX_VALUE);for(T t:nodes.keySet()){m[f.apply(t)][f.apply(t)]=0;for(T c:nodes.get(t).edges.keySet())m[f.apply(t)][f.apply(c)]=nodes.get(t).edges.get(c);}return m;}
	}

*/
public class Graph<T> {
	
	/** Class to represent a Node on a graph */
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

	/** Create a new {@link Graph} with 0 nodes to start with */
	public static <T> Graph<T> newGraph(boolean directed) {
		return new Graph<>(directed);
	}

	/** Create a new {@link Graph} with n nodes numbered 0..n-1 */
	public static Graph<Integer> newIntegerGraph(boolean directed, int numNodes) {
		return newGraphWithValues(directed, numNodes, i -> i);
	}

	/** Create a new {@link Graph} with n nodes numbered 1..n */
	public static Graph<Integer> newIntegerGraphOneIndexed(boolean directed, int numNodes) {
		return newGraphWithValues(directed, numNodes, i -> i + 1);
	}

	/** Create a new {@link Graph} with n nodes valued by a function f:0..n-1->T */
	public static <T> Graph<T> newGraphWithValues(boolean directed, int numNodes, Function<Integer, T> values) {
		Graph<T> g = new Graph<>(directed, numNodes);
		for (int i = 0; i < numNodes; i++)
			g.addNode(values.apply(i));
		return g;
	}

	/** Add a new node with specified value */
	public void addNode(T value) {
		nodes.put(value, new Node(value));
		numNodes++;
	}

	/** Add an edge between two nodes (and in reverse as well if undirected) */
	public void connect(T n1, T n2, int weight) {
		nodes.get(n1).edges.put(n2, weight);
		if (!directed)
			nodes.get(n2).edges.put(n1, weight);
		numEdges++;
	}

	/** Remove an edge between two nodes (and in reverse as well if undirected) */
	public void removeEdge(T n1, T n2) {
		nodes.get(n1).edges.remove(n2);
		if (!directed)
			nodes.get(n2).edges.remove(n1);
		numEdges--;
	}

	/** Struct to hold node, parent, and distance from source */
	public class ExtendedNodeInfo {
		public final Node node, parent;
		public final int dist;
		public ExtendedNodeInfo(Node n, Node parent, int dist) {
			this.node = n;
			this.parent = parent;
			this.dist = dist;
		}
		public String toString() {
			return String.format("%s, P=%s, D=%d", node.value, parent == null ? "null" : parent.value, dist);
		}
	}
	
	/** Perform a BFS/DFS from a source node and run a consumer of {@link ExtendedNodeInfo} */
	public void processSearchExtended(T source, Consumer<ExtendedNodeInfo> consumer, boolean bfs) {
		Deque<ExtendedNodeInfo> q = new ArrayDeque<>();
		Set<T> visited = new HashSet<>();
		q.add(new ExtendedNodeInfo(nodes.get(source), null, 0));
		while (!q.isEmpty()) {
			ExtendedNodeInfo info = bfs ? q.poll() : q.removeLast();
			if (!visited.add(info.node.value))
				continue;
			consumer.accept(info);
			for (T child : info.node.edges.keySet())
				if (!visited.contains(child))
					q.add(new ExtendedNodeInfo(nodes.get(child), info.node, info.dist + info.node.edges.get(child)));
		}
	}
	
	/** Perform a BFS/DFS from a source node and run a consumer of node */
	public void processSearch(T source, Consumer<Node> consumer, boolean bfs) {
		processSearchExtended(source, info -> consumer.accept(info.node), bfs);
	}
	
	/** Find the distance between two nodes on an <b>UNWEIGHTED</b> graph using BFS */
	public int distUnweightedBFS(T source, T dest) {
		Queue<ExtendedNodeInfo> q = new LinkedList<>();
		Set<T> visited = new HashSet<>();
		q.add(new ExtendedNodeInfo(nodes.get(source), null, 0));
		while (!q.isEmpty()) {
			ExtendedNodeInfo info = q.poll();
			if (!visited.add(info.node.value))
				continue;
			if (info.node.value.equals(dest))
				return info.dist;
			for (T child : info.node.edges.keySet())
				if (!visited.contains(child))
					q.add(new ExtendedNodeInfo(nodes.get(child), info.node, info.dist + 1));
		}
		return -1;
	}

	/** Determine if graph has a cycle */
	public boolean containsCycle() {
		Map<T, Boolean> visited = new HashMap<>();
		T source = null;
		while ((source = getFirstUnvisited(visited)) != null)
			if (containsCycleInternalDFS(source, visited))
				return true;
		return false;
	}

	/** Helper method for {@link #containsCycle()} that finds the first unvisited node */
	private T getFirstUnvisited(Map<T, Boolean> visited) {
		for (T t : nodes.keySet())
			if (!visited.containsKey(t))
				return t;
		return null;
	}

	/** Helper method for {@link #containsCycle()} which finds the first unvisited node */
	private boolean containsCycleInternalDFS(T source, Map<T, Boolean> visited) {
		// false - seen only once; true - seen and exited
		visited.put(source, false);
		for (T child : nodes.get(source).edges.keySet())
			if (visited.containsKey(child) ? !visited.get(child) : containsCycleInternalDFS(child, visited))
				return true;
		visited.put(source, true);
		return false;
	}

	/** Performs a lexiographical topological sort using a provided comparator or natural ordering if null */
	@SuppressWarnings("unchecked")
	public List<Node> topologicalSort(Comparator<T> comparator) {
		if (comparator == null)
			comparator = (t1, t2) -> t1 instanceof Comparable ?
										((Comparable<T>) t1).compareTo(t2) :
										t1.hashCode() - t2.hashCode();
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

	/** Struct to store extra info about Dijkstras nodes including distance and previous node */
	public class DijkstrasNode extends Node {
		int dist = -1;
		boolean visited = false;
		DijkstrasNode previous;

		public DijkstrasNode(Node node) {
			super(node);
		}
	}
	
	/** Perform Dijkstras from a source node without a target end node */
	public Map<T, DijkstrasNode> dijkstrasAll(T source) {
		return dijkstrasInternal(source, null);
	}
	
	/** Perform Dijkstras to find the shortest distance between two nodes */
	public int dijkstrasDist(T source, T dest) {
		return dijkstrasInternal(source, dest).get(dest).dist;
	}
	
	/** Perform Dijkstras to find the shortest path between two nodes */
	public List<DijkstrasNode> dijkstrasPath(T source, T dest) {
		DijkstrasNode end = dijkstrasInternal(source, dest).get(dest);
		List<DijkstrasNode> path = new ArrayList<>();
		path.add(end);
		while ((end = end.previous) != null);
			path.add(end);
		Collections.reverse(path);
		return path;
	}

	/** Helper method to actually perform Dijkstras and return the entire graph of DijkstrasNodes */
	private Map<T, DijkstrasNode> dijkstrasInternal(T source, T dest) {
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
			if (n.value.equals(dest) && dest != null)
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

	/** Perform Floyd-Warshall to find the shortest path between all pairs of nodes,
		placing them into an adjacency matrix using a mapping function f:T->0..n-1 */
	public int[][] floydWarshal(Function<T, Integer> f) {
		int[][] m = getAdjacencyMatrix(f);
		for (T k : nodes.keySet())
			for (T i : nodes.keySet())
				for (T j : nodes.keySet())
					if (m[f.apply(i)][f.apply(k)] != Integer.MAX_VALUE && m[f.apply(k)][f.apply(j)] != Integer.MAX_VALUE)
						m[f.apply(i)][f.apply(j)] = Math.min(m[f.apply(i)][f.apply(j)], m[f.apply(i)][f.apply(k)] + m[f.apply(k)][f.apply(j)]);
		return m;
	}

	/** Return an adjacency matrix of the graph using a mapping function f:T->0..n-1 */ 
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
