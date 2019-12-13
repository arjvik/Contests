import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class Day12_NBodyProblem {
	
	/**
	 * Yes, this is some ugly code.
	 * Future me, change Point3D to a list of points,
	 * so we can for-loop over dimensions instead of repeating code
	 * @throws IOException 
	 */
	
	public static void main(String[] args) {
		System.out.println("Part 1: "+part1());
		System.out.println("Part 2: "+part2());
		
	}
	
	public static String part2() {
		List<Pair<Point3D, Point3D>> moons =
				ADVENTOFCODEINPUT.readLines(12)
								 .map(s -> s.split("[=,>]"))
								 .map(s -> Point3D.of(Integer.parseInt(s[1]),
										 			  Integer.parseInt(s[3]),
										 			  Integer.parseInt(s[5])))
								 .map(p -> Pair.of(p, Point3D.of(0, 0, 0)))
								 .collect(Collectors.toList());
		Set<Tuple8> states; int time;
		
		states = new HashSet<>();
		Tuple8 state = new Tuple8(moons.get(0).pos.x, moons.get(1).pos.x, moons.get(2).pos.x, moons.get(3).pos.x,
								  moons.get(0).vel.x, moons.get(1).vel.x, moons.get(2).vel.x, moons.get(3).vel.x);
		for (time = 0; states.add(state); time++) {
			for (int i = 0; i < moons.size(); i++) {
				Pair<Point3D, Point3D> m1 = moons.get(i);
				for (int j = i + 1; j < moons.size(); j++) {
					Pair<Point3D, Point3D> m2 = moons.get(j);
					if (m1.pos.x < m2.pos.x) { m1.vel.x++; m2.vel.x--; }
					if (m1.pos.x > m2.pos.x) { m1.vel.x--; m2.vel.x++; }
				}
			}
			for (Pair<Point3D, Point3D> m : moons)
				m.pos.add(m.vel);
			state = new Tuple8(moons.get(0).pos.x, moons.get(1).pos.x, moons.get(2).pos.x, moons.get(3).pos.x,
					  		   moons.get(0).vel.x, moons.get(1).vel.x, moons.get(2).vel.x, moons.get(3).vel.x);
		}
		int xAns = time;
		
		states = new HashSet<>();
		state = new Tuple8(moons.get(0).pos.y, moons.get(1).pos.y, moons.get(2).pos.y, moons.get(3).pos.y,
						   moons.get(0).vel.y, moons.get(1).vel.y, moons.get(2).vel.y, moons.get(3).vel.y);
		for (time = 0; states.add(state); time++) {
			for (int i = 0; i < moons.size(); i++) {
				Pair<Point3D, Point3D> m1 = moons.get(i);
				for (int j = i + 1; j < moons.size(); j++) {
					Pair<Point3D, Point3D> m2 = moons.get(j);
					if (m1.pos.y < m2.pos.y) { m1.vel.y++; m2.vel.y--; }
					if (m1.pos.y > m2.pos.y) { m1.vel.y--; m2.vel.y++; }
				}
			}
			for (Pair<Point3D, Point3D> m : moons)
				m.pos.add(m.vel);
			state = new Tuple8(moons.get(0).pos.y, moons.get(1).pos.y, moons.get(2).pos.y, moons.get(3).pos.y,
							   moons.get(0).vel.y, moons.get(1).vel.y, moons.get(2).vel.y, moons.get(3).vel.y);
		}
		int yAns = time;
		
		states = new HashSet<>();
		state = new Tuple8(moons.get(0).pos.z, moons.get(1).pos.z, moons.get(2).pos.z, moons.get(3).pos.z,
						   moons.get(0).vel.z, moons.get(1).vel.z, moons.get(2).vel.z, moons.get(3).vel.z);
		for (time = 0; states.add(state); time++) {
			for (int i = 0; i < moons.size(); i++) {
				Pair<Point3D, Point3D> m1 = moons.get(i);
				for (int j = i + 1; j < moons.size(); j++) {
					Pair<Point3D, Point3D> m2 = moons.get(j);
					if (m1.pos.z < m2.pos.z) { m1.vel.z++; m2.vel.z--; }
					if (m1.pos.z > m2.pos.z) { m1.vel.z--; m2.vel.z++; }
				}
			}
			for (Pair<Point3D, Point3D> m : moons)
				m.pos.add(m.vel);
			state = new Tuple8(moons.get(0).pos.z, moons.get(1).pos.z, moons.get(2).pos.z, moons.get(3).pos.z,
							   moons.get(0).vel.z, moons.get(1).vel.z, moons.get(2).vel.z, moons.get(3).vel.z);
		}
		int zAns = time;
		return String.format("lcm(%d, %d, %d)", xAns, yAns, zAns);
	}
	
	public static int part1() {
		List<Pair<Point3D, Point3D>> moons =
				ADVENTOFCODEINPUT.readLines(12)
								 .map(s -> s.split("[=,>]"))
								 .map(s -> Point3D.of(Integer.parseInt(s[1]),
										 			  Integer.parseInt(s[3]),
										 			  Integer.parseInt(s[5])))
								 .map(p -> Pair.of(p, Point3D.of(0, 0, 0)))
								 .collect(Collectors.toList());
		for (int time = 0; time < 1000; time++) {
			for (int i = 0; i < moons.size(); i++) {
				Pair<Point3D, Point3D> m1 = moons.get(i);
				for (int j = i + 1; j < moons.size(); j++) {
					Pair<Point3D, Point3D> m2 = moons.get(j);
					if (m1.pos.x < m2.pos.x) { m1.vel.x++; m2.vel.x--; }
					if (m1.pos.x > m2.pos.x) { m1.vel.x--; m2.vel.x++; }
					if (m1.pos.y < m2.pos.y) { m1.vel.y++; m2.vel.y--; }
					if (m1.pos.y > m2.pos.y) { m1.vel.y--; m2.vel.y++; }
					if (m1.pos.z < m2.pos.z) { m1.vel.z++; m2.vel.z--; }
					if (m1.pos.z > m2.pos.z) { m1.vel.z--; m2.vel.z++; }

				}
			}
			for (Pair<Point3D, Point3D> m : moons)
				m.pos.add(m.vel);
		}
		return moons.stream()
					.mapToInt(p -> p.pos.energy()*p.vel.energy())
					.sum();
	}
	
	private static class Point3D {
		public int x, y, z;
		public static Point3D of(int x, int y, int z) {
			Point3D p = new Point3D();
			p.x = x; p.y = y; p.z = z;
			return p; }
		public void add(Point3D vel) {
			x += vel.x; y += vel.y; z += vel.z; }
		public int energy() {
			return Math.abs(x)+Math.abs(y)+Math.abs(z);
		}
	}
	private static class Tuple8 {
		public final int x0, x1, x2, x3, v0, v1, v2, v3;

		public Tuple8(int x0, int x1, int x2, int x3, int v0, int v1, int v2, int v3) {
			this.x0 = x0;
			this.x1 = x1;
			this.x2 = x2;
			this.x3 = x3;
			this.v0 = v0;
			this.v1 = v1;
			this.v2 = v2;
			this.v3 = v3;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + v0;
			result = prime * result + v1;
			result = prime * result + v2;
			result = prime * result + v3;
			result = prime * result + x0;
			result = prime * result + x1;
			result = prime * result + x2;
			result = prime * result + x3;
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
			Tuple8 other = (Tuple8) obj;
			if (v0 != other.v0)
				return false;
			if (v1 != other.v1)
				return false;
			if (v2 != other.v2)
				return false;
			if (v3 != other.v3)
				return false;
			if (x0 != other.x0)
				return false;
			if (x1 != other.x1)
				return false;
			if (x2 != other.x2)
				return false;
			if (x3 != other.x3)
				return false;
			return true;
		}

	}
	public static class Pair<X, Y> {
		public X pos;public Y vel;public Pair(X x, Y y) {this.pos = x;this.vel = y;}
		public static <X,Y> Pair<X,Y> of(X x,Y y){return new Pair<X, Y>(x, y);} }

}
