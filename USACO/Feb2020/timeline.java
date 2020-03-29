import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class timeline {
	private static int[] minDays;
	private static boolean[] visited;
	private static Map<Integer, Set<Pair<Integer, Integer>>> memoryIn, memoryOut;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("timeline.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("timeline.out")));
		int n = in.nextInt(),
			m = in.nextInt(),
			c = in.nextInt();
		
		minDays = new int[n];
		for (int i = 0; i < n; i++) minDays[i] = in.nextInt();
		visited = new boolean[n];
		
		memoryIn = new HashMap<>();
		for (int i = 0; i < c; i++) {
			int a = in.nextInt()-1,
				b = in.nextInt()-1,
				x = in.nextInt();
			memoryIn.computeIfAbsent(b, k -> new HashSet<>()).add(Pair.ofInt(a, x));
		}
		
		for (int i = 0; i < n; i++)
			out.println(calcMin(i));
		
		in.close();
		out.close();
	}

	private static int calcMin(int i) {
		if (!visited[i]) {
			// minDays[i] = Math.max(minDays[i], ____);
			for (Pair<Integer, Integer> p : memoryIn.getOrDefault(i, Collections.emptySet()))
				minDays[i] = Math.max(minDays[i], calcMin(p.x)+p.y);
			visited[i] = true;
		}
		return minDays[i];
	}
	
	public static class Pair<X, Y> {
		public X x;public Y y;public Pair(X x, Y y) {this.x = x;this.y = y;}
		public static Pair<Integer, Integer> ofInt(int x, int y) {return new Pair<Integer, Integer>(x,y);}
		public static <X,Y> Pair<X,Y> of(X x,Y y){return new Pair<X, Y>(x, y);}
		public X getX() {return x;} public void setX(X x) {this.x = x;}
		public Y getY() {return y;} public void setY(Y y) {this.y = y;}
		@Override public int hashCode() {final int prime = 31;int result = 1;
			result = prime * result + ((x == null) ? 0 : x.hashCode());
			result = prime * result + ((y == null) ? 0 : y.hashCode());return result;}
		@Override public boolean equals(Object obj) {
			if (this == obj) return true; if (obj == null) return false;
			if (getClass() != obj.getClass()) return false; Pair<?,?> other = (Pair<?,?>) obj;
			if (x == null) { if (other.x != null) return false; } else if (!x.equals(other.x)) return false;
			if (y == null) { if (other.y != null) return false; } else if (!y.equals(other.y)) return false; return true;}
		@Override public String toString() { return "Pair<" + x + ", " + y + ">"; }}

	
}
