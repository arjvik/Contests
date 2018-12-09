/*
ID: arjvik1
LANG: JAVA
TASK: Day8_MemoryManeuver
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class Day8_MemoryManeuver {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		System.out.printf("Part 1: %d%nPart 2: %d%n", part1(new Scanner(input)), part2(new Scanner(input)));
		in.close();
	}

	private static int part1(Scanner in) {
		int sum = 0;
		int n = in.nextInt();
		int m = in.nextInt();
		for (int i = 0; i < n; i++)
			sum += part1(in);
		for (int i = 0; i < m; i++)
			sum += in.nextInt();
		return sum;
	}
	
	private static int part2(Scanner in) {
		int n = in.nextInt();
		int m = in.nextInt();
		if (n == 0) {
			int sum = 0;
			for (int i = 0; i < m; i++)
				sum += in.nextInt();
			return sum;
		} else {
			int[] arr = new int[n];
			for (int i = 0; i < n; i++)
				arr[i] = part2(in);
			int sum = 0;
			for (int i = 0; i < m; i++) {
				int idx = in.nextInt();
				if (idx > 0 && idx <= n)
					sum += arr[idx-1];
			}
			return sum;
		}
	}
}
