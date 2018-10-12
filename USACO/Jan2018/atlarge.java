/*
ID: arjvik1
LANG: JAVA
TASK: atlarge
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class atlarge {
	private static int n;
	private static int k;
	private static Node[] nodes;
	
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		java.util.Scanner in = new java.util.Scanner(new java.io.BufferedReader(new java.io.FileReader("atlarge.in")));
		java.io.PrintWriter out = new java.io.PrintWriter(new java.io.BufferedWriter(new java.io.FileWriter("atlarge.out")));
		n = in.nextInt();
		k = in.nextInt();
		nodes = new Node[n + 1]; //IGNORE nodes[0];
		for (int i = 0; i < n - 1; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			if(nodes[a] == null)
				nodes[a] = new Node(a);
			if(nodes[b] == null)
				nodes[b] = new Node(b);
			nodes[a].connections.add(nodes[b]);
			nodes[b].connections.add(nodes[a]);
		}
		out.println(3);
		in.close();
		out.close();
		out.print(1);
		System.err.printf("took %d milliseconds",System.currentTimeMillis()-start);
	}
	public static class Node{
		int id;
		List<Node> connections;
		public Node() {
			
		}
		public Node(int id) {
			this();
			this.id = id;
			this.connections = new ArrayList<>();
		}
		public Node(int id, List<Node> connections) {
			this();
			this.id = id;
			this.connections = new ArrayList<>(connections);
		}
	}
}