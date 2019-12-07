import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Day6_UniversalOrbitMap {
	
	/**
	 * Look, I know this code sucks.
	 * I shouldn't have to create the graph twice,
	 * once for directed (part 1) and once for undirected (part 2).
	 * I should really be checking if I have already visited a node in
	 * part 1. But it works, and more importantly, highlighted that I need
	 * a new Graph class with a cleaner API (which takes T as the key rather
	 * than node ID) so I don't need name2idx again.
	 * 
	 * If I ever need this code again, I should really nuke it and start over.
	 * Keep the same idea but use better execution
	 */
	
	public static void main(String[] args) throws IOException {
		System.out.println("Part 1: " + part1());
		System.out.println("Part 2: " + part2());
	}
	
	public static int part1() {
		Graph<String> g = new Graph<>(true);
		Map<String, List<String>> in = ADVENTOFCODEINPUT.readLineList(6)
				.stream().collect(Collectors.groupingBy(s -> s.substring(0,3), Collectors.mapping(s -> s.substring(4), Collectors.toList())));
		Map<String, Integer> name2idx = new HashMap<>();
		add("COM", in, g, name2idx);
		return numOrbits(g, g.nodes.get(name2idx.get("COM")));
	}
	
	public static int part2() {
		Graph<String> g = new Graph<>();
		Map<String, List<String>> in = ADVENTOFCODEINPUT.readLineList(6)
				.stream().collect(Collectors.groupingBy(s -> s.substring(0,3), Collectors.mapping(s -> s.substring(4), Collectors.toList())));
		Map<String, Integer> name2idx = new HashMap<>();
		add("COM", in, g, name2idx);
		return g.dijkstras(name2idx.get("YOU")).get(name2idx.get("SAN")).dist-2;
	}

	private static void add(String name, Map<String, List<String>> in, Graph<String> g, Map<String, Integer> name2idx) {
		if (!name2idx.containsKey(name)) {
			name2idx.put(name, g.numNodes);
			g.addNode(name);
			for (String child : in.getOrDefault(name, Collections.emptyList())) {
				add(child, in, g, name2idx);
				g.connect(name2idx.get(name), name2idx.get(child), 1);
			}
		}
	}
	
	public static int numNodes(Graph<String> g, Graph<String>.Node root) {
		int i = 1;
		for (int next : root.edges.keySet()) {
			Graph<String>.Node nextNode = g.nodes.get(next);
			i += numNodes(g, nextNode);
		}
		return i;
	}
	public static int numOrbits(Graph<String> g, Graph<String>.Node root) {
		int i = numNodes(g, root) - 1;
		for (int next : root.edges.keySet()) {
			Graph<String>.Node nextNode = g.nodes.get(next);
			i += numOrbits(g, nextNode);
		}
		return i;
	}
}
