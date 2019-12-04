import java.io.*;
import java.util.Arrays;

public class Day4_SecureContainer {
	public static void main(String[] args) throws IOException {
		int[] input = ADVENTOFCODEINPUT.readLines(4)
									   .flatMap(s -> Arrays.stream(s.split("-")))
									   .mapToInt(Integer::parseInt)
									   .toArray();
		System.out.println("Part 1: "+part1(input));
		System.out.println("Part 2: "+part2(input));
	}

	private static int part1(int[] input) {
		int count = 0;
		l:for (int i = input[0]; i <= input[1]; i++) {
			String s = Integer.toString(i);
			if (s.length() != 6) continue; // must be 6 digits
			if (!s.matches(".*(.)\\1.*")) continue; // may only repeat twice
			for (int j = 1; j < 6; j++) //digits must be non-descending
				if (Integer.parseInt(""+s.charAt(j)) < Integer.parseInt(""+s.charAt(j-1)))
					continue l;
			count++;
		}
		return count;
	}
	
	
	private static int part2(int[] input) {
		int count = 0;
		l:for (int i = input[0]; i <= input[1]; i++) {
			String s = Integer.toString(i);
			if (s.length() != 6) continue; // must be 6 digits
			x:{ // must have a group of two same digits but not more than that
				for (int n = 0; n < 10; n++) // look at all possible digits 0..9
					// for each, check if it has a group of size exactly 2
					if (s.matches(String.format(".*(?<!%d)%d%d(?!%d).*", n,n,n,n)))
						break x; // found one
				continue l; // the condition failed
			}
			for (int j = 1; j < 6; j++) // digits must be non-descending
				if (Integer.parseInt(""+s.charAt(j)) < Integer.parseInt(""+s.charAt(j-1)))
					continue l;
			count++;
		}
		return count;
	}
}
