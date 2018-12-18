
/*
ID: arjvik1
LANG: JAVA
TASK: Day9_MarbleMania
*/
import java.io.*;
import java.util.*;

public class Day9_MarbleMania {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int players = in.nextInt();
		while (!in.hasNextInt())
			in.next();
		int marbles = in.nextInt();
		System.out.printf("Part 1: %d%n", part1(players, marbles));
		System.out.printf("Part 2: %d%n", part2(players, marbles));
		in.close();
	}

	private static long part1(int players, int marbles) {
		int player = 0;
		long[] score = new long[players];
		Node head = new Node(0);
		head.next = head.prev = head;
		for (int m = 1; m <= marbles; m++, player = (player + 1) % players) {
			if (!(m % 23 == 0)) {
				Node n = new Node(m);
				n.prev = head.next;
				n.next = head.next.next;
				n.prev.next = n;
				n.next.prev = n;
				head = n;
			} else {
				Node r = head.prev.prev.prev.prev.prev.prev.prev;
				score[player] += r.value + m;
				r.prev.next = r.next;
				r.next.prev = r.prev;
				head = r.next;
			}
		}
		return Arrays.stream(score).max().getAsLong();
	}

	private static long part2(int players, int marbles) {
		return part1(players, marbles * 100);
	}

	private static class Node {
		public final int value;
		public Node next, prev;

		public Node(int value) {
			this.value = value;
		}
	}
}
