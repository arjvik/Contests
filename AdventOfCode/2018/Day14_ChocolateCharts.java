
/*
ID: arjvik1
LANG: JAVA
TASK: Day14_ChocolateCharts
*/
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("unused")
public class Day14_ChocolateCharts {
	
	private static ArrayList<Integer> sb;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		System.out.printf("Part 1: %s%n", part1(input));
		System.out.printf("Part 2: %d%n", part2(input));
		in.close();
	}

	private static String part1(int input) {
		sb = new ArrayList<>(input + 20);
		sb.add(3);
		sb.add(7);
		int e1 = 0, e2 = 1;
//		printScoreboard(sb, e1, e2);
		while (sb.size() < input + 10) {
			int sum = sb.get(e1) + sb.get(e2);
			int s1 = sum / 10, s2 = sum % 10;
			if (s1 != 0)
				sb.add(s1);
			sb.add(s2);
			e1 = (e1 + sb.get(e1) + 1) % sb.size();
			e2 = (e2 + sb.get(e2) + 1) % sb.size();
//			printScoreboard(sb, e1, e2);
		}
		String ans = "";
		for (int i = 0; i < 10; i++)
			ans += sb.get(input + i);
		return ans;
	}

	private static void printScoreboard(ArrayList<Integer> sb, int e1, int e2) {
		System.out.println(
				IntStream.range(0, sb.size()).mapToObj(i -> 
						(i == e1) ? String.format("(%d)", sb.get(i)) :
						(i == e2) ? String.format("[%d]", sb.get(i)) :
									String.format(" %d ", sb.get(i)))
						.collect(Collectors.joining(" "))
				);
	}
	
	private static int part2(int input) {
		part1(100_000_000);
		String scores = sb.stream().map(String::valueOf).collect(Collectors.joining());
		String inputStr = Integer.toString(input);
		return scores.indexOf(inputStr);
	}
}
