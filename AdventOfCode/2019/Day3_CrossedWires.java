import java.io.*;
import java.util.*;

public class Day3_CrossedWires {
	
	public static void main(String[] args) throws IOException {
		System.out.println("Part 1: " + run(1));
		System.out.println("Part 2: " + run(2));
	}

	private static int run(int part) {
		List<Map<Point, Integer>> points = Arrays.asList(new HashMap<>(), new HashMap<>());
	
		List<String> wires = ADVENTOFCODEINPUT.readLineList(3);
		for (int wire = 0; wire < wires.size(); wire++) {
			int x = 0, y = 0, time = 0;
			for (String move : wires.get(wire).split(",")) {
				char dir = move.charAt(0);
				int num = Integer.parseInt(move.substring(1));
				for (int i = 0; i < num; i++) {
					time++;
					if (dir == 'R')
						x++;
					else if (dir == 'L')
						x--;
					else if (dir == 'U')
						y++;
					else if (dir == 'D')
						y--;
					else
						throw new RuntimeException();
					points.get(wire).put(new Point(x, y), time);
				}
			}
		}

		int min = Integer.MAX_VALUE;
		points.get(0).keySet().retainAll(points.get(1).keySet());
		for (Point p : points.get(0).keySet())
			if (part == 1)
				min = Math.min(min, Math.abs(p.x)+Math.abs(p.y));
			else
				min = Math.min(min, points.get(0).get(p)+points.get(1).get(p));
		return min;
	}
	
	public static class Point {
		public int x, y; public Point(int x, int y) { this.x = x; this.y = y; }
		public int getX() { return x; } public void setX(int x) { this.x = x; }
		public int getY() { return y; } public void setY(int y) { this.y = y; }
		@Override public int hashCode() { final int prime = 7759; int result = 1;
			result = prime * result + x; result = prime * result + y; return result; }
		@Override public boolean equals(Object obj) { if (this == obj) return true;
			if (obj == null) return false; if (getClass() != obj.getClass()) return false;
			Point other = (Point) obj; if (x != other.x) return false;
			if (y != other.y) return false; return true; }
		@Override public String toString() { return "(" + x + ", " + y + ")"; } }

}
