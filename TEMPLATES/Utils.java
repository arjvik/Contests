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
}
