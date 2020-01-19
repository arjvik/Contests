import java.util.*;

/**

Minimized version of Tree:
(suitable for copy-paste into solution)

	@SuppressWarnings("unused")
	private static class Tree<T>{
		public Node root;public int numNodes;public final Map<T,Node>nodes=new HashMap<>();
		public class Node{public final T value;public int extra;public final List<Node>children;public Node parent;
			public Node heavyChild=null;public boolean isHeavy=false;public int subtreeSize=-1;public int depth=-1;
			public Node[]up;public Node(T value){this.value=value;children=new ArrayList<>();}}
		public static<T>Tree<T>newTree(){return new Tree<>();}
		public static<T>Tree<T>fromUnrootedGraph(Graph<T>g){Tree<T>t=new Tree<>();T rootID=g.nodes.keySet().iterator().next();t.root=t.addNode(rootID,null);g.processSearchExtended(rootID,info->t.addNode(info.node.value,t.nodes.get(info.parent.value)),true);return t;}
		public Node addNode(T value,Node parent){if(root==null){root=new Node(value);nodes.put(value,root);return root;}Node n=new Node(value);n.parent=parent;if(parent!=null)parent.children.add(n);nodes.put(value,n);numNodes++;return n;}
		public void precomputeDepthSubtreeSize(){precomputeDepthSubtreeSizeHelper(root,0);}
		private int precomputeDepthSubtreeSizeHelper(Node node,int depth){node.depth=depth;node.subtreeSize=1;for(Node child:node.children)node.subtreeSize+=precomputeDepthSubtreeSizeHelper(child,depth+1);return node.subtreeSize;}
		private int binaryLiftingLimit;@SuppressWarnings("unchecked")
		public void precomputeLCA_BinaryLifting(){precomputeDepthSubtreeSize();binaryLiftingLimit=(int)(Math.ceil(Math.log(numNodes)/Math.log(2)))+2;for(Node n:nodes.values()){n.up=new Tree.Node[binaryLiftingLimit];n.up[0]=n.parent;}for(Node n:nodes.values())for(int i=1;i<binaryLiftingLimit;i++)if(n.up[i-1]!=null)n.up[i]=n.up[i-1].up[i-1];}
		public Node computeLCA_BinaryLifting(Node a,Node b){if(binaryLiftingLimit==0)throw new RuntimeException("Must call precomputeLCA_BinaryLifting() at least once");if(a.depth>b.depth)return computeLCA_BinaryLifting(b,a);int diff=b.depth-a.depth;for(int i=0;i<binaryLiftingLimit;i++)if(((diff>>i)&1)==1)b=b.up[i];if(a==b)return a;for(int i=binaryLiftingLimit-1;i>=0;i--)if(a.up[i]!=null&&b.up[i]!=null&&a.up[i]!=b.up[i]){a=a.up[i];b=b.up[i];}return a.up[0];}
	}

*/
public class Tree<T> {
	public Node root;
	public int numNodes;
	public final Map<T, Node> nodes = new HashMap<>();

	public class Node {
		public final T value;
		public int extra;
		public final List<Node> children;
		public Node parent;
		//optional info:
		public Node heavyChild = null; // for Heavy-Light Decomp
		public boolean isHeavy = false;
		public int subtreeSize = -1; // including self
		public int depth = -1; //root is 0
		public Node[] up; // for binary lifting LCA
		
		public Node(T value) {
			this.value = value;
			children = new ArrayList<>();
		}
	}
	
	/** Create a new empty tree */
	public static <T> Tree<T> newTree() {
		return new Tree<>();
	}
	
	/** Create a new tree from an unrooted {@link Graph} */
	public static <T> Tree<T> fromUnrootedGraph(Graph<T> g) {
		Tree<T> t = new Tree<>();
		T rootID = g.nodes.keySet().iterator().next(); //first set
		t.root = t.addNode(rootID, null);
		g.processSearchExtended(rootID, info -> t.addNode(info.node.value, t.nodes.get(info.parent.value)), true);
		return t;
	}
	
	/** Add a new node to this tree with specified parent */
	public Node addNode(T value, Node parent) {
		if (root == null) {
			root = new Node(value);
			nodes.put(value, root);
			return root;
		}
		Node n = new Node(value);
		n.parent = parent;
		if (parent != null)
			parent.children.add(n);
		nodes.put(value, n);
		numNodes++;
		return n;
	}
	
	/** Precompute the depth and subtreeSize attributes of node */
	public void precomputeDepthSubtreeSize() {
		precomputeDepthSubtreeSizeHelper(root, 0);
	}
	
	/** Helper method, returns subtree size and sets depth */
	private int precomputeDepthSubtreeSizeHelper(Node node, int depth) {
		node.depth = depth;
		node.subtreeSize = 1;
		for (Node child : node.children)
			node.subtreeSize += precomputeDepthSubtreeSizeHelper(child, depth+1);
		return node.subtreeSize;
	}
	
	// extra var for Binary Lifting
	private int binaryLiftingLimit;
	
	/** Precompute Binary Lifting LCA's up[] array */
	@SuppressWarnings("unchecked")
	public void precomputeLCA_BinaryLifting() {
		precomputeDepthSubtreeSize();
		binaryLiftingLimit = (int) (Math.ceil(Math.log(numNodes)/Math.log(2)))+2; //avoid off-by-one if possible
		for (Node n : nodes.values()) {
			n.up = new Tree.Node[binaryLiftingLimit];
			n.up[0] = n.parent;
		}
		for (Node n : nodes.values())
			for (int i = 1; i < binaryLiftingLimit; i++)
				if (n.up[i-1] != null)
					n.up[i] = n.up[i-1].up[i-1];
	}
	
	/** Calculate the LCA of two nodes using Binary Lifting.
	 	<b>MUST CALL {@link #precomputeLCA_BinaryLifting()} BEFOREHAND!</b>*/
	public Node computeLCA_BinaryLifting(Node a, Node b) {
		if (binaryLiftingLimit == 0)
			throw new RuntimeException("Must call precomputeLCA_BinaryLifting() at least once");
		if (a.depth > b.depth)
			return computeLCA_BinaryLifting(b, a);
		//WLOG a.depth < b.depth
		int diff = b.depth-a.depth;
		// make a and b the same height
		for (int i = 0; i < binaryLiftingLimit; i++)
			if (((diff>>i)&1)==1) // if the i-th bit is 1
				b = b.up[i];  // we jump i up
		if (a == b) return a;
		// binary search the up jump until we have the right node
		for (int i = binaryLiftingLimit-1; i>=0; i--)
			if (a.up[i] != null && b.up[i] != null && a.up[i] != b.up[i]) {
				a = a.up[i];
				b = b.up[i];
			}
		return a.up[0];
	}
	
}
