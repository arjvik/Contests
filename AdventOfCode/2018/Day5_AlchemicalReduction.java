/*
ID: arjvik1
LANG: JAVA
TASK: Day5_AlchemicalReduction
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class Day5_AlchemicalReduction {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		System.out.printf("Part 1: %d%n", part1(input));
		System.out.printf("Part 2: %d%n", part2(input));
		in.close();
	}
	
	private static int part2(String input) {
		int min = Integer.MAX_VALUE;
		for (char c = 'A'; c <= 'Z'; c++) {
			System.err.print(c);
			int i = part1(input.replaceAll(Character.toString(c), "")
							   .replaceAll(Character.toString((char) (c+(32))), ""));
			if (i < min) {
				min = i;
			}
		}
		System.err.println();
		return min;
	}

	private static boolean canReact(String s, int i) {
		return (i+1<s.length()) && Math.abs(s.charAt(i) - s.charAt(i+1)) == 32;
	}
	
	private static String react(String s, int i) {
		return s.substring(0, i) + s.substring(i+2);
	}

	private static int part1(String input) {
		for (int i = 0; i < input.length() - 1; i++) {
			if (canReact(input, i)) {
				while (canReact(input, i)) {
					input = react(input, i);
					if (i != 0)
						i--;
				}
				i = 0;
			}
		}
		return input.length();
	}
	
}