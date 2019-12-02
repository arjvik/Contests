import java.util.*;
import java.io.*;

public class Day2_1202ProgramAlarm {
	
	private static int[] a = ADVENTOFCODEINPUT.readLines(2)
			 								  .flatMap(s -> Arrays.stream(s.split(",")))
			 								  .mapToInt(Integer::parseInt)
			 								  .toArray();
	
	public static void main(String[] args) throws IOException {
		System.out.println("Part 1: " + part1());
		System.out.println("Part 2: " + part2());
	}

	private static int part1() {
		return test(12, 2, a);
	}

	private static int part2() {
		for (int i = 0; i < 100; i++)
			for (int j = 0; j < 100; j++) {
				if (test(i, j, a) == 19690720)
					return 100 * i + j;
			}
		return -1;
	}

	private static int test(int x, int y, int[] aa) {
		int[] a = Arrays.copyOf(aa, aa.length);
		a[1] = x;
		a[2] = y;
		for (int i = 0; i < a.length; i += 4) {
			int opcode = a[i];
			if (opcode == 99)
				break;
			int in1 = a[i + 1];
			int in2 = a[i + 2];
			int out = a[i + 3];
			if (opcode == 1)
				a[out] = a[in1] + a[in2];
			else if (opcode == 2)
				a[out] = a[in1] * a[in2];
			else
				throw new RuntimeException();
		}
		return a[0];
	}
}
