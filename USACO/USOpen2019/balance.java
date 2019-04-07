import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings({"unused","resource"})
public class balance {
	public static int n;
	public static HashSet<String> visited = new HashSet<String>();
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("balance.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balance.out")));
		if (System.getProperty("DEBUG") != null && Boolean.TRUE) {
			System.out.println(dp("11111000001111100000111110000011111000000000011111000001111100000111110000011111"));
			System.exit(0);
		}
		n = in.nextInt();
		in.nextLine();
		String s = in.nextLine().replace(" ","");
		if (s.length() != 2*n)
			throw new RuntimeException("bad length");
		out.println(dp(s));
		in.close();
		out.close();
	}
	private static long dp(String start) {
			Queue<Pair<String, Long>> q = new LinkedList<>();
			if (tie(start)==0)
				return 0;
			q.add(new Pair<>(start,0l));
			visited.add(start);
			while (!q.isEmpty()) {
				Pair<String, Long> p = q.poll();
				String s = p.x;
				long y = p.y + 1;
				loop:for (int i = 0; i < s.length()-1; i++) {
					if (s.charAt(i) == s.charAt(i+1))
						continue;
					String s2 = swap(s,i);
					if (!visited.add(s2))
						continue loop;
					if (tie(s2)==0) {
						System.err.println(s2);
						return y;
					}
					q.add(new Pair<>(s2,y));
				}
			}
			return -1;
	}
	private static String swap(String s, int i) {
		return new StringBuffer(s.length()).append(s.substring(0,i)).append(s.charAt(i+1)).append(s.charAt(i)).append(s.substring(i+2)).toString();
	}
	private static long side(String s) {
		long inversions = 0;
		long zeros = 0;
		long ones = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '0') {
				zeros++;
			} else {
				if (zeros != 0) {
					inversions += ones * zeros;
					zeros = ones = 0;
				}
				ones++;
			}
		}
		if (zeros != 0) {
			inversions += ones * zeros;
			zeros = ones = 0;
		}
		return inversions;
	}
	private static long tie(String s) {
		return side(s.substring(0,s.length()/2))-side(s.substring(s.length()/2));
	}
	
	
	public static class Pair<X, Y> {
		public X x;public Y y;public Pair(X x, Y y) {this.x = x;this.y = y;}
		public static Pair<Integer, Integer> ofInt(int x, int y) {return new Pair<Integer, Integer>(x,y);}
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
