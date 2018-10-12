/*
ID: arjvik1
LANG: JAVA
TASK: mootube
*/
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class mootube3 {
	private static int n;
	private static int q;
	private static Node[] nodes;
	private static int min_r = Integer.MAX_VALUE;
	private static boolean DEBUG = false;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("mootube.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		n = in.nextInt();
		q = in.nextInt();
		nodes = new Node[n + 1]; //IGNORE nodes[0];
		for (int i = 0; i < n - 1; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int r = in.nextInt();
			if(r<min_r)
				min_r = r;
			if(nodes[a] == null)
				nodes[a] = new Node(a);
			if(nodes[b] == null)
				nodes[b] = new Node(b);
			nodes[a].connections.put(nodes[b], r);
			nodes[b].connections.put(nodes[a], r);
		}
		for (int i = 0; i < q; i++) {
			boolean[] visited = new boolean[n+1];
			int k = in.nextInt();
			int v = in.nextInt();
			if(k <= min_r)
				out.println(n-1);
			else
				out.println(run(k,v,visited,0) - 1);
		}
		in.close();
		out.close();
	}
	private static int run(final int k, final int v, final boolean[] visited, final int debug_level) {
		if(DEBUG){
			System.err.print("                                                               ".substring(0,debug_level));
			System.err.print("Running ");
			if(debug_level == 0)
				System.err.printf("k=%d ",k);
			System.err.printf("v=%d visited=%s%n",v,Arrays.toString(visited));
			
		}
		int i = 1;
		Node n = nodes[v];
		visited[v] = true;
		for (Entry<Node, Integer> entry : n.connections.entrySet()) {
			if(entry.getValue()<k)
				continue;
			if(visited[entry.getKey().id])
				continue;
			i += run(k,entry.getKey().id,visited,debug_level+1);
		}
		if(DEBUG){
			System.err.print("                                                                ".substring(0,debug_level));
			System.err.printf("returning %d%n",i);
		}
		return i;
	}
	public static class Node{
		int id;
		Map<Node,Integer> connections;
		public Node() {
			
		}
		public Node(int id) {
			this();
			this.id = id;
			this.connections = new HashMap<>();
		}
		public Node(int id, Map<Node,Integer> connections) {
			this();
			this.id = id;
			this.connections = new HashMap<>(connections);
		}
	}
	
	

}
