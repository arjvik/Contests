/*
ID: arjvik1
LANG: JAVA
TASK: Day7_TheSumOfItsParts
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class Day7_TheSumOfItsParts {
	public static class Node {
		public char name;
		public boolean visited_p1 = false;
		public List<Node> inEdges = new ArrayList<>();
	}
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		Node[] graph = new Node[26];
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new Node();
			graph[i].name = (char) ('A'+i);
		}
		String line;
		while (!(line = in.nextLine()).equals("EOF")) {
			int edgeSource = line.charAt(36) - 'A';
			int edgeDest = line.charAt(5) - 'A';
			graph[edgeSource].inEdges.add(graph[edgeDest]);
		}
		System.out.printf("Part 1: %s%n", part1(graph));
		in.close();
	}
	private static String part1(Node[] graph) {
		List<Node> ans = new ArrayList<>();
		int visited = 0;
		while (visited < graph.length) {
			search:for (int i = 0; i < graph.length; i++) {
				if (graph[i].visited_p1)
					continue search;
				for (Node edgeSource : graph[i].inEdges)
					if (!edgeSource.visited_p1)
						continue search;
				Node source = graph[i];
				ans.add(source);
				source.visited_p1 = true;
				visited++;
				break search;
			}
		}
		String s = "";
		for (Node n : ans)
			s += n.name;
		return s;
	}
}
