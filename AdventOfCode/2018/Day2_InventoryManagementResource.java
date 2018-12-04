
/*
ID: arjvik1
LANG: JAVA
TASK: Day2_InventoryManagementResource
*/
import java.io.*;
import java.net.URL;
import java.util.*;

@SuppressWarnings("unused")
public class Day2_InventoryManagementResource {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		List<String> input = new ArrayList<>();
		String line;
		while (in.hasNextLine() && !(line = in.nextLine()).equals("EOF"))
			input.add(line);
		System.out.printf("Part 1: %d%nPart 2: %s%n", part1(input), part2(input));
		in.close();
	}

	private static int part1(List<String> input) {
		int doubleRep = 0, tripleRep = 0;
		for (String string : input) {
			int[] freq = new int[26];
			int two = 0, three = 0;
			for (char c : string.toCharArray()) {
				switch (++freq[c - 'a']) {
				case 2:
					two++;
					break;
				case 3:
					two--;
					three++;
					break;
				case 4:
					three--;
					break;
				}
			}
			if (two > 0)
				doubleRep++;
			if (three > 0)
				tripleRep++;
		}
		return doubleRep * tripleRep;
	}

	private static String part2(List<String> input) {
		String id1 = null, id2 = null;
		int n = -1;
		search: for (int i = 0; i < input.size(); i++) {
			String a = input.get(i);
			for (int j = i + 1; j < input.size(); j++) {
				String b = input.get(j);
				if ((n = diff(a, b)) != -1) {
					id1 = a;
					id2 = b;
					break search;
				}
			}
		}
		return id1.substring(0, n) + id1.substring(n + 1);
	}

	private static int diff(String a, String b) {
		if (a.length() != b.length() && !a.equals(b))
			throw new IllegalArgumentException("Strings must be same length but not equal");
		int diffChar = 0;
		int lastIdx = -1;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i) && (lastIdx = i) == i && ++diffChar > 1)
				return -1;
		}
		return lastIdx;
	}
}
