import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
Utility class containing many useful methods

Minimized version suitable for copy-paste:


*/
public class Utils {
	public static int[] readArray(Scanner in, int n) {
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = in.nextInt();
		return array;
	}

	public static int[][] readArray2d(Scanner in, int n, int m) {
		int[][] array = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				array[i][j] = in.nextInt();
		return array;
	}
	
	public static long[] readArrayLong(Scanner in, int n) {
		long[] array = new long[n];
		for (int i = 0; i < n; i++)
			array[i] = in.nextLong();
		return array;
	}

	public static long[][] readArray2dLong(Scanner in, int n, int m) {
		long[][] array = new long[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				array[i][j] = in.nextLong();
		return array;
	}
	
	public static <T> void printArray(T[] array) {
		System.out.println(Arrays.asList(array));
	}
	
	public static <T> void printArray2d(T[][] array) {
		System.out.println("{");
		for (T[] row : array) {
			System.out.print("\t");
			printArray(row);
			System.out.println(",");
		}
		System.out.println("}");
	}
	
	public static void printArray(int[] array) {
		System.out.println(IntStream.of(array).mapToObj(String::valueOf).collect(Collectors.joining(",", "[", "]")));
	}
	
	public static void printArray2d(int[][] array) {
		System.out.println("{");
		for (int[] row : array) {
			System.out.print("\t");
			printArray(row);
			System.out.println(",");
		}
		System.out.println("}");
	}
	
	public static void printArray(long[] array) {
		System.out.println(LongStream.of(array).mapToObj(String::valueOf).collect(Collectors.joining(",", "[", "]")));
	}
	
	public static void printArray2d(long[][] array) {
		System.out.println("{");
		for (long[] row : array) {
			System.out.print("\t");
			printArray(row);
		}
		System.out.println("}");
	}

}
