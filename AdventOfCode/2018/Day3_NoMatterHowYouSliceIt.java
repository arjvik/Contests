
/*
ID: arjvik1
LANG: JAVA
TASK: Day3_NoMatterHowYouSliceIt
*/
import java.io.*;
import java.net.URL;
import java.util.*;

@SuppressWarnings("unused")
public class Day3_NoMatterHowYouSliceIt{
	private static class Claim {
		final int id, x, y, w, h;

		public Claim(int id, int x, int y, int w, int h) {
			this.id = id;
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		}
		
		public Claim(String input) {
			String[] split = input.split(" ");
			id = Integer.parseInt(split[0].substring(1));
			String[] coords = split[2].split(",");
			x = Integer.parseInt(coords[0]);
			y = Integer.parseInt(coords[1].substring(0, coords[1].length()-1));
			String[] dims = split[3].split("x");
			w = Integer.parseInt(dims[0]);
			h = Integer.parseInt(dims[1]);
		}
	}
	private static final Map<String, Integer> cloth = new HashMap<>();
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		List<Claim> input = new ArrayList<>();
		String line;
		while (in.hasNextLine() && !(line = in.nextLine()).equals("EOF"))
			input.add(new Claim(line));
		System.out.printf("Part 1: %d%nPart 2: %d%n", part1(input), part2(input));
		in.close();
	}

	private static long part1(List<Claim> input) {
		for (Claim c : input) {
			for (int i = c.x; i < c.x + c.w; i++) {
				for (int j = c.y; j < c.y + c.h; j++) {
					String f = fmt(i,j);
					if (cloth.containsKey(f))
						cloth.put(f, cloth.get(f)+1);
					else
						cloth.put(f, 1);
				}
			}
		}
		return cloth.values()
					.stream()
					.filter(i -> i>1)
					.count();
	}

	private static String fmt(int i, int j) {
		return String.format("(%d,%d)", i, j);
	}

	private static int part2(List<Claim> input) {
		search:for (Claim c : input) {
			for (int i = c.x; i < c.x + c.w; i++) {
				for (int j = c.y; j < c.y + c.h; j++) {
					String f = fmt(i,j);
					if (cloth.get(f) > 1)
						continue search;
				}
			}
			return c.id;
		}
		return -1;
	}

}
