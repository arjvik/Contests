import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Day1_Test {
	public static void main(String[] args) throws IOException {
		System.out.println("Part 1: "+part1());
		System.out.println("Part 2: "+part2());
	}

	private static int part1() {
		return ADVENTOFCODEINPUT.readNums(1)
								.map(i -> i/3-2)
								.sum();
	}
	
	private static int part2() {
		return ADVENTOFCODEINPUT.readNums(1)
								.flatMap(i -> IntStream.iterate(i, f -> f/3-2)
													   .skip(1)
													   .takeWhile(f -> f>0))
								.sum();
	}
}
