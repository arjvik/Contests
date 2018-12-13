
/*
ID: arjvik1
LANG: JAVA
TASK: Day12_SubterraneanSustainability
*/
import java.io.*;
import java.util.*;

public class Day12_SubterraneanSustainability {
	public static class Quintet {
		public final boolean x1, x2, x3, x4, x5;

		public Quintet(boolean p1, boolean p2, boolean p3, boolean p4, boolean p5) {
			x1 = p1;
			x2 = p2;
			x3 = p3;
			x4 = p4;
			x5 = p5;
		}

		@Override
		public int hashCode() {
			final int prime = 10909;
			int result = 1;
			result = prime * result + (x1 ? 1231 : 1237);
			result = prime * result + (x2 ? 1231 : 1237);
			result = prime * result + (x3 ? 1231 : 1237);
			result = prime * result + (x4 ? 1231 : 1237);
			result = prime * result + (x5 ? 1231 : 1237);
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
			Quintet other = (Quintet) obj;
			if (x1 != other.x1)
				return false;
			if (x2 != other.x2)
				return false;
			if (x3 != other.x3)
				return false;
			if (x4 != other.x4)
				return false;
			if (x5 != other.x5)
				return false;
			return true;
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		Set<Integer> initial = new TreeSet<>();
		String initialString = in.nextLine().substring(15);
		for (int i = 0; i < initialString.length(); i++)
			if (initialString.charAt(i) == '#')
				initial.add(i);
		in.nextLine();
		Set<Quintet> posArrangements = new HashSet<>();
		String line;
		while (in.hasNextLine() && !(line = in.nextLine()).equals("EOF")) {
			if (line.endsWith("."))
				continue;
			boolean x1 = line.charAt(0) == '#', x2 = line.charAt(1) == '#', x3 = line.charAt(2) == '#',
					x4 = line.charAt(3) == '#', x5 = line.charAt(4) == '#';
			posArrangements.add(new Quintet(x1, x2, x3, x4, x5));
		}
		System.out.printf("Part 1: %d%n", part1(initial, posArrangements));
		System.out.printf("Part 2: %d%n", part2(initial, posArrangements));
		in.close();
	}

	private static int part1(Set<Integer> pos, final Set<Quintet> posArrangements) {
		for (long iter = 0; iter < 20; iter++) {
			pos = runIteration(pos, posArrangements);
		}
		return pos.stream().mapToInt(Integer::intValue).sum();
	}
	
	private static long part2(Set<Integer> pos, final Set<Quintet> posArrangements) {
		for (long iter = 0; iter < 1000; iter++) {
			pos = runIteration(pos, posArrangements);
		}
		int sum1000 = pos.stream().mapToInt(Integer::intValue).sum();
		int sum1001 = runIteration(pos, posArrangements).stream().mapToInt(Integer::intValue).sum();
		int delta = sum1001-sum1000;
		return sum1000 + (50000000000l-1000)*delta;
	}

	private static Set<Integer> runIteration(Set<Integer> pos, final Set<Quintet> posArrangements) {
		Set<Integer> newPos = new TreeSet<>();
		int min = pos.stream().mapToInt(i -> i).min().getAsInt() - 2;
		int max = pos.stream().mapToInt(i -> i).max().getAsInt() + 2;
		for (int i = min; i <= max; i++) {
			if (posArrangements.contains(new Quintet(pos.contains(i - 2), pos.contains(i - 1), pos.contains(i),
					pos.contains(i + 1), pos.contains(i + 2))))
				newPos.add(i);
		}
		return newPos;
	}

}