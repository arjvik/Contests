
/*
ID: arjvik1
LANG: JAVA
TASK: Day9_MarbleMania
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class Day9_MarbleMania {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int players = in.nextInt();
		in.next(); in.next(); in.next(); in.next(); in.next();
		int marbles = in.nextInt();
		System.out.printf("Part 1: %d%n", part1(players, marbles));
		System.out.printf("Part 2: %d%n", part2(players, marbles));
		in.close();
	}

	private static int part1(int players, int marbles) {
		int player = 0;
		int[] score = new int[players];
		List<Integer> circle = new ArrayList<>();
		circle.add(0);
		int curr = 0;
		for (int m = 1; m <= marbles; m++, player = (player + 1) % players) {
			if (!(m % 23 == 0)) {
				curr = mod(curr + 2, circle.size());
				circle.add(curr, m);
			} else {
				curr = mod(curr - 7, circle.size());
				score[player] += circle.remove(curr) + m;
			}
		}
		return Arrays.stream(score).max().getAsInt();
	}
	
	private static int mod(int i, int m) {
		int a = i % m;
		while (a < 0)
			a += m;
		return a;
	}

	private static int part2(int players, int marbles) {
		return part1(players, marbles*100);
	}
}
