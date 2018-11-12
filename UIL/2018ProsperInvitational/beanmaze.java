/*
ID: arjvik1
LANG: JAVA
TASK: beanmaze
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class beanmaze {
	public static class Pair {
		final int x,y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int hashCode() {
			final int prime = 131;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("beanmaze.dat")));
		int NUMCASES = in.nextInt();
		for (int CASENUM = 0; CASENUM < NUMCASES; CASENUM++) {
			int r=in.nextInt();
			int c=in.nextInt();
			in.nextLine();
			char[][] maze = new char[r][];
			int sr=-1, sc=-1, br=-1, bc=-1;
			for(int i = 0; i < r; i++) {
				String s = in.nextLine();
				maze[i] = s.toCharArray();
				if(s.contains("S")) {
					sr = i;
					sc = s.indexOf("S");
				}
				if(s.contains("B")) {
					br = i;
					bc = s.indexOf("B");
				}
			}
			if(recur(maze, new HashSet<Pair>(), sr, sc, br, bc, r, c,0)) 
				System.out.println("POSSIBLE");
			else
				System.out.println("NOT POSSIBLE");
		}
		in.close();
	}
	private static boolean recur(char[][] maze, Set<Pair> visitedOld,
			int r, int c, int br, int bc, int tr, int tc, int debug) {
//		System.out.print("#");
//		rp(" ", debug);
//		System.out.printf("recur(maze, visitedOld, r=%d, c=%d, br=%d, bc=%d, tr=%d, tc=%d, debug=%d)%n",
//				r, c, br, bc, tr, tc, debug);
		Pair p = new Pair(r,c);
		if(visitedOld.contains(p))
			return false;
		if(r==br && c==bc)
			return true;
		if(maze[r][c] == '#')
			return false;
		Set<Pair> visited = new HashSet<>(visitedOld);
		visited.add(p);
		return  (r+1 < tr && recur(maze, visited, r+1, c, br, bc, tr, tc, debug+1)) ||
				(c+1 < tc && recur(maze, visited, r, c+1, br, bc, tr, tc, debug+1)) ||
				(r-1 >= 0 && recur(maze, visited, r-1, c, br, bc, tr, tc, debug+1)) ||
				(c-1 >= 0 && recur(maze, visited, r, c-1, br, bc, tr, tc, debug+1));
	}
	public static void rp(String s, int i) {
		for (int j = 0; j<i;j++) {
			System.out.print(s);
		}
	}
}
