import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class Day18_SettlersOfTheNorthPole {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		char[][] grid = new char[50][];
		String line;
		for (int i = 0; i < grid.length; i++) {
			grid[i] = in.nextLine().toCharArray();
		}
		System.out.printf("Part 1: %d%n", run(grid, 10));
		System.out.printf("Part 2: %d%n", run(grid, 1000000000));
		in.close();
	}

	private static int run(char[][] grid, int iterCount) {
//		printGrid(grid, 0);
		for (int iter = 0; iter < iterCount; iter++) {
			char[][] newGrid = new char[grid.length][grid.length];
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid.length; j++) {
					switch (grid[i][j]) {
					case '.':
						if (adj(grid, i+1, j, '|') +
							adj(grid, i-1, j, '|') +
							adj(grid, i, j+1, '|') +
							adj(grid, i, j-1, '|') +
							adj(grid, i+1, j+1, '|') +
							adj(grid, i-1, j+1, '|') +
							adj(grid, i+1, j-1, '|') +
							adj(grid, i-1, j-1, '|') >= 3)
							newGrid[i][j] = '|';
						else
							newGrid[i][j] = '.';
						break;
					case '|':
						if (adj(grid, i+1, j, '#') +
							adj(grid, i-1, j, '#') +
							adj(grid, i, j+1, '#') +
							adj(grid, i, j-1, '#') +
							adj(grid, i+1, j+1, '#') +
							adj(grid, i-1, j+1, '#') +
							adj(grid, i+1, j-1, '#') +
							adj(grid, i-1, j-1, '#') >= 3)
							newGrid[i][j] = '#';
						else
							newGrid[i][j] = '|';
						break;
					case '#':
						if (adj(grid, i+1, j, '#') +
							adj(grid, i-1, j, '#') +
							adj(grid, i, j+1, '#') +
							adj(grid, i, j-1, '#')  +
							adj(grid, i+1, j+1, '#') +
							adj(grid, i-1, j+1, '#') +
							adj(grid, i+1, j-1, '#') +
							adj(grid, i-1, j-1, '#') >= 1 &&
							adj(grid, i+1, j, '|') +
							adj(grid, i-1, j, '|') +
							adj(grid, i, j+1, '|') +
							adj(grid, i, j-1, '|') +
							adj(grid, i+1, j+1, '|') +
							adj(grid, i-1, j+1, '|') +
							adj(grid, i+1, j-1, '|') +
							adj(grid, i-1, j-1, '|') >= 1)
							newGrid[i][j] = '#';
						else
							newGrid[i][j] = '.';
						break;
					default: throw new IllegalArgumentException("EBKAC");
					}
				}
			}
			grid = newGrid;
//			printGrid(grid, iter+1);
		}
		int tree = 0,
			lumber = 0;
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid.length; j++)
				switch (grid[i][j]) {
				case '|':
					tree++;
					break;
				case '#':
					lumber++;
					break;
				}
		return tree*lumber;
	}

	private static void printGrid(char[][] grid, int iter) {
		System.out.printf("Iteration %d%n", iter);
		for (char[] arr : grid) {
			for (char c : arr)
				System.out.print(c);
			System.out.println();
		}
		System.out.println();
	}

	private static int adj(char[][] grid, int i, int j, char c) {
		return (i >= 0 &&
				i < grid.length &&
				j >= 0 &&
				j < grid.length &&
				grid[i][j] == c)
				? 1 : 0;
	}
}
