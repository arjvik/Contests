import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class Day11_ChronalCharge {
	private static final int MAX_SIZE = 100;
	private static int SIZE = 300;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("Day11_ChronalCharge.in")));
		int serial = in.nextInt();
		int[][] grid = getGrid(serial, SIZE);
		System.out.printf("Part 1: %s%n", part1(grid));
		System.out.printf("Part 2: %s%n", part2(grid));
		in.close();
	}

	private static String part2(int[][] grid) {
		int maxPower = -99, maxX = -1, maxY = -1, maxS = -1;
		for (int s = 1; s < MAX_SIZE; s++) {
			if (s % 10 == 0) System.err.printf("s=%d%n", s);
			for (int i = 0; i < SIZE - s + 1; i++)
				for (int j = 0; j < SIZE - s + 1; j++) {
					int totalPower = 0;
					for (int p = 0; p < s; p++)
						for (int q = 0; q < s; q++)
							totalPower += grid[i + p][j + q];
					if (totalPower > maxPower) {
						maxX = i + 1;
						maxY = j + 1;
						maxS = s;
						maxPower = totalPower;
					}
				}
		}
		return String.format("%d,%d,%d", maxX, maxY, maxS);
	}

	private static String part1(int[][] grid) {
		int maxPower = -99;
		int maxX = -1;
		int maxY = -1;
		for (int i = 0; i < SIZE - 2; i++) {
			for (int j = 0; j < SIZE - 2; j++) {
				int totalPower = grid[i + 0][j + 0] + grid[i + 0][j + 1] + grid[i + 0][j + 2] + grid[i + 1][j + 0]
						+ grid[i + 1][j + 1] + grid[i + 1][j + 2] + grid[i + 2][j + 0] + grid[i + 2][j + 1]
						+ grid[i + 2][j + 2];
				if (totalPower > maxPower) {
					maxX = i + 1;
					maxY = j + 1;
					maxPower = totalPower;
				}
			}
		}
		return String.format("%d,%d", maxX, maxY);
	}

	private static int[][] getGrid(int serial, int size) {
		int[][] grid = new int[size][size];
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				int rackID = i + 10;
				int power = hundreds((rackID * j + serial) * rackID) - 5;
				grid[i - 1][j - 1] = power;
			}
		}
		return grid;
	}

	private static int hundreds(int i) {
		return i / 100 - 10 * (i / 1000);
	}
}
