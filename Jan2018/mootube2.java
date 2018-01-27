/*
ID: arjvik1
LANG: JAVA
TASK: mootube2
*/
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

@SuppressWarnings("unused")
public class mootube2 {
	private static int n;
	private static int q;
	private static Node[] nodes;
	private static int min_r = Integer.MAX_VALUE;
	private static boolean DEBUG = false;
	private static List<Map<Integer,Integer>> similar;
	
	private static final int MAX = Integer.MAX_VALUE;
	
	private static final boolean USE_MIN_ID = false;
	
	public static void main(String[] args) throws IOException {
		
		Scanner in = new Scanner(new BufferedReader(new FileReader("mootube.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		n = in.nextInt();
		q = in.nextInt();
		nodes = new Node[n + 1]; //IGNORE nodes[0];
		similar = new ArrayList<Map<Integer,Integer>>(n + 1);
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
		similar.add(null);
		for (int i = 1; i <= n; i++) {
			similar.add(new HashMap<>());
			boolean[] visited = new boolean[n+1];
			run(i,i,MAX,similar.get(i),visited);
			/*for (Entry<Integer,Integer> entry: similar.get(i).entrySet()) {
				similar.get(entry.getKey()).put(i, entry.getValue());
			}*/
		}
		for(int i = 0; i < q; i++){
			final int k = in.nextInt();
			final int v = in.nextInt();
			out.println(similar.get(v).entrySet()
									.stream()
									.filter(e -> e.getValue()>=k)
									.count());
		}
		in.close();
		out.close();
	}
	private static void run(final int v, final int max_id, final int min_r, Map<Integer,Integer> map, final boolean[] visited) {
		Node n = nodes[v];
		visited[v] = true;
		for (Entry<Node, Integer> entry : n.connections.entrySet()) {
			if(visited[entry.getKey().id])
				continue;
			if(USE_MIN_ID&&entry.getKey().id>max_id)
				continue;
			map.put(entry.getKey().id, Math.min(min_r, entry.getValue()));
			run(entry.getKey().id, max_id, Math.min(min_r, entry.getValue()), map, visited);
		}
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
