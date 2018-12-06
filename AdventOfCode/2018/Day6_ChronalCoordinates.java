import java.awt.Point;
import java.io.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

@SuppressWarnings("unused")
public class Day6_ChronalCoordinates {
	private static final int MAX_DIST = 10000;
	private static final int MAX_SIZE = 401;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		List<Point> input = new ArrayList<>();
		String line;
		while (!(line = in.nextLine()).equals("EOF")) {
			String[] l = line.split(", ");
			input.add(new Point(Integer.parseInt(l[0]), Integer.parseInt(l[1])));
		}
		System.out.printf("Part 1: %d%n", part1(input));
		System.out.printf("Part 2: %d%n", part2(input));
		in.close();
	}

	private static int part1(List<Point> input) {
		@SuppressWarnings("unchecked")
		Set<Point>[] newNodes = new Set[input.size()];
		for (int i = 0; i < input.size(); i++) {
			Set<Point> s = new HashSet<Point>();
			s.add(input.get(i));
			newNodes[i] = s;
		}
		int[][] closest = new int[MAX_SIZE][MAX_SIZE];
		int[] area = new int[MAX_SIZE];
		for (int i = 0; i < MAX_SIZE; i++)
			Arrays.fill(closest[i], -2);
		while (!isEmpty(newNodes)) {
			for (int i = 0; i < newNodes.length; i++) {
				for (Point p : newNodes[i]) {
					if (closest[p.x][p.y] == -2) {
						closest[p.x][p.y] = i;
						area[i]++;
					} else if (closest[p.x][p.y] != -1) {
						area[closest[p.x][p.y]]--;
						closest[p.x][p.y] = -1;
					}
				}
			}
			@SuppressWarnings("unchecked")
			Set<Point>[] children = new Set[input.size()];
			for (int i = 0; i < newNodes.length; i++) {
				children[i] = new HashSet<>();
				for (Point p : newNodes[i]) {
					if (canReach(closest, p.x, p.y + 1))
						children[i].add(new Point(p.x, p.y + 1));
					if (canReach(closest, p.x, p.y - 1))
						children[i].add(new Point(p.x, p.y - 1));
					if (canReach(closest, p.x + 1, p.y))
						children[i].add(new Point(p.x + 1, p.y));
					if (canReach(closest, p.x - 1, p.y))
						children[i].add(new Point(p.x - 1, p.y));
				}
			}
			newNodes = children;
		}
		Set<Integer> inf = new HashSet<>();
		for (int i = 0; i < MAX_SIZE; i++) {
			inf.add(closest[i][0]);
			inf.add(closest[0][i]);
			inf.add(closest[i][MAX_SIZE - 1]);
			inf.add(closest[MAX_SIZE - 1][i]);
		}
		int max = -99;
		for (int i = 0; i < area.length; i++) {
			if (inf.contains(i))
				continue;
			max = Math.max(max, area[i]);
		}
		return max;
	}

	private static boolean canReach(int[][] closest, int x, int y) {
		return x >= 0 && x < MAX_SIZE && y >= 0 && y < MAX_SIZE && closest[x][y] == -2;
	}

	private static boolean isEmpty(Set<Point>[] newNodes) {
		return Arrays.stream(newNodes).map(Set::size).mapToInt(i -> i).sum() == 0;
	}

	private static int part2(List<Point> input) {
		int a = 0;
		for (int i = 0; i < MAX_SIZE; i++)
			for (int j = 0; j < MAX_SIZE; j++) {
				int sum = 0;
				for (int p = 0; p < input.size() && sum < MAX_DIST; p++)
					sum += Math.abs(i - input.get(p).x) + Math.abs(j - input.get(p).y);
				if (sum < MAX_DIST)
					a++;
			}
		return a;
	}
}
