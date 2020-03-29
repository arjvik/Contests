import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * DID NOT WORK IN CONTEST
 */

@SuppressWarnings("unused")
public class deleg_wip {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("deleg.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("deleg.out")));
		int n = in.nextInt();
		if (n==1) {out.println();out.close();System.exit(0);}
		Graph<Integer> g = Graph.newIntegerGraphOneIndexed(false, n);
		for (int i = 0; i < n-1; i++)
			g.connect(in.nextInt(), in.nextInt(), 1);
		int[] sortedByDegree = g.nodes
								.values()
								.stream()
								.sorted((n1, n2) -> n2.edges.size() - n1.edges.size())
								//reverse           ^^^
								.mapToInt(n1 -> n1.value)
								.toArray();
		
		if (g.nodes.get(sortedByDegree[0]).edges.size() <= 2) { //already 1 path
			System.err.println("PATH MODE");
			for (int k = 1; k < n; k++)
				if ((n-1)%k == 0)
					out.print(1);
				else
					out.print(0);
			out.println();
		} else if (g.nodes.get(sortedByDegree[1]).edges.size() <= 2) { //star
			System.err.println("STAR MODE");
			Tree<Integer> t = Tree.fromRootedGraph(g, sortedByDegree[0]);
			List<Integer> chainLen = new ArrayList<>();
			for (Tree<Integer>.Node head : t.root.children) {
				int length = 1;
				while (head.children.size() != 0) {
					length++;
					head = head.children.get(0);
				}
				chainLen.add(length);
			}
			System.err.println("Chain lengths: "+chainLen);
			for (int k = 1; k < n; k++) {
				if ((n-1)%k == 0) {
					final int k_lambda = k; // java lambdas and scope complaints
					List<Integer> badChLen = chainLen.stream()
													 .map(i -> i%k_lambda)
													 .filter(i -> i != 0)
													 .collect(Collectors.toList());
					int blen = badChLen.size();
					if (blen == 0)
						out.print(1);
					else if (blen % 2 != 0)
						out.print(0);
					else {
						if (isValid(badChLen, k))
							out.print(1);
						else
							out.print(0);
					}
				} else {
					out.print(0);
				}
			}
			out.println();
		} else { // TODO: non-star
			System.err.println("REGULAR MODE");
			out.println("111000000000");
		}
		in.close();
		out.close();
	}
	
	private static boolean isValid(List<Integer> badChLen, int k) {
		int blen = badChLen.size();
		boolean[] taken = new boolean[blen];
		pairing:for (int i = 0; i < blen; i++) {
			if (taken[i])
				continue;
			for (int j = i+1; j < blen; j++)
				if (!taken[j] && (badChLen.get(i)+badChLen.get(j))%k==0) {
					taken[j] = true;
					continue pairing;
				}
			return false;
		}
		return true;
	}

	private static class Graph<T>{
		public class Node{
			public final T value;public int extra;
			public final Map<T,Integer>edges;
			public Node(T value){this.value=value;edges=new HashMap<>();}
			private Node(Node n){value=n.value;extra=n.extra;edges=n.edges;}}
		public final Map<T,Node>nodes;public final boolean directed;public int numNodes=0;public int numEdges=0;
		private Graph(boolean directed,int initialSize){nodes=new HashMap<>(initialSize,1);this.directed=directed;}
		public static Graph<Integer>newIntegerGraphOneIndexed(boolean directed,int numNodes){return newGraphWithValues(directed,numNodes,i->i+1);}
		public static<T>Graph<T>newGraphWithValues(boolean directed,int numNodes,Function<Integer,T>values){Graph<T>g=new Graph<>(directed,numNodes);for(int i=0;i<numNodes;i++)g.addNode(values.apply(i));return g;}
		public void addNode(T value){nodes.put(value,new Node(value));numNodes++;}
		public void connect(T n1,T n2,int weight){nodes.get(n1).edges.put(n2,weight);if(!directed)nodes.get(n2).edges.put(n1,weight);numEdges++;}
		public class ExtendedNodeInfo{public final Node node,parent;public final int dist;public ExtendedNodeInfo(Node n,Node parent,int dist){this.node=n;this.parent=parent;this.dist=dist;}}
		public void processSearchExtended(T source,Consumer<ExtendedNodeInfo>consumer,boolean bfs){Deque<ExtendedNodeInfo>q=new ArrayDeque<>();Set<T>visited=new HashSet<>();q.add(new ExtendedNodeInfo(nodes.get(source),null,0));while(!q.isEmpty()){ExtendedNodeInfo info=bfs?q.poll():q.removeLast();if(!visited.add(info.node.value))continue;consumer.accept(info);for(T child:info.node.edges.keySet())if(!visited.contains(child))q.add(new ExtendedNodeInfo(nodes.get(child),info.node,info.dist+info.node.edges.get(child)));}}
	}
	
	private static class Tree<T>{
		public Node root;public int numNodes;public final Map<T,Node>nodes=new HashMap<>();
		public class Node{public final T value;public int extra;public final List<Node>children;public Node parent;
			public Node heavyChild=null;public boolean isHeavy=false;public int subtreeSize=-1;public int depth=-1;
			public Node[]up;public Node(T value){this.value=value;children=new ArrayList<>();}}
		public static<T>Tree<T>newTree(){return new Tree<>();}
		public static<T>Tree<T>fromUnrootedGraph(Graph<T>g){T rootID=g.nodes.keySet().iterator().next();return fromRootedGraph(g,rootID);}
		public static <T> Tree<T> fromRootedGraph(Graph<T> g, T rootID){Tree<T> t = new Tree<>();t.root = t.addNode(rootID, null);g.processSearchExtended(rootID, info->{if (!info.node.value.equals(rootID))t.addNode(info.node.value,t.nodes.get(info.parent.value));},true);return t;}
		public Node addNode(T value,Node parent){if(root==null){root=new Node(value);nodes.put(value,root);return root;}Node n=new Node(value);n.parent=parent;if(parent!=null)parent.children.add(n);nodes.put(value,n);numNodes++;return n;}
		public void precomputeDepthSubtreeSize(){precomputeDepthSubtreeSizeHelper(root,0);}
		private int precomputeDepthSubtreeSizeHelper(Node node,int depth){node.depth=depth;node.subtreeSize=1;for(Node child:node.children)node.subtreeSize+=precomputeDepthSubtreeSizeHelper(child,depth+1);return node.subtreeSize;}
		private int binaryLiftingLimit;@SuppressWarnings("unchecked")
		public void precomputeLCA_BinaryLifting(){precomputeDepthSubtreeSize();binaryLiftingLimit=(int)(Math.ceil(Math.log(numNodes)/Math.log(2)))+2;for(Node n:nodes.values()){n.up=new Tree.Node[binaryLiftingLimit];n.up[0]=n.parent;}for(Node n:nodes.values())for(int i=1;i<binaryLiftingLimit;i++)if(n.up[i-1]!=null)n.up[i]=n.up[i-1].up[i-1];}
		public Node computeLCA_BinaryLifting(Node a,Node b){if(binaryLiftingLimit==0)throw new RuntimeException("Must call precomputeLCA_BinaryLifting() at least once");if(a.depth>b.depth)return computeLCA_BinaryLifting(b,a);int diff=b.depth-a.depth;for(int i=0;i<binaryLiftingLimit;i++)if(((diff>>i)&1)==1)b=b.up[i];if(a==b)return a;for(int i=binaryLiftingLimit-1;i>=0;i--)if(a.up[i]!=null&&b.up[i]!=null&&a.up[i]!=b.up[i]){a=a.up[i];b=b.up[i];}return a.up[0];}
	}
}
