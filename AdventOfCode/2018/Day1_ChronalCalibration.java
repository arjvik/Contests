
/*
ID: arjvik1
LANG: JAVA
TASK: Day1_ChronalCalibration
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class Day1_ChronalCalibration {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		List<Integer> input = new ArrayList<>();
		while (in.hasNextInt())
			input.add(in.nextInt());
		System.out.printf("Part 1: %d%nPart 2: %d%n", part1(input), part2(input));
		in.close();
	}

	private static int part1(List<Integer> input) {
		return input.stream().mapToInt(i->i).sum();
	}
	
	private static int part2(List<Integer> input) {
		Set<Integer> freq = new HashSet<>();
		int curr = 0;
		for (int idx = 0; freq.add(curr); idx = ++idx%input.size())
			curr += input.get(idx);
		return curr;
	}
}
