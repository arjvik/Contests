import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class paintbarn_bad {
	private static final int SIZE = 10;
	
	private static int maxSum = 0;
	private static int maxX1 = -1; // inc
	private static int maxX2 = -1; // excl
	private static int maxY1 = -1;
	private static int maxY2 = -1;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("paintbarn.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out")));
		int n = in.nextInt();
		int k = in.nextInt();
		int[][] barn = new int[SIZE][SIZE];
		int[] debug = new int[4];
		for (int i = 0; i < n; i++) {
			int x1 = in.nextInt(), y1 = in.nextInt(), x2 = in.nextInt(), y2 = in.nextInt();
			for (int x = x1; x < x2; x++)
				for (int y = y1; y < y2; y++)
					barn[x][y]++;
		}
		
		System.out.println("Original Barn: ");
		print(barn);

		int[][] board = new int[SIZE][SIZE];
		for (int x = 0; x < SIZE; x++)
			for (int y = 0; y < SIZE; y++) {
				debug[barn[x][y]]++;
				if (barn[x][y] == k - 1)
					board[x][y] = 1;
				else if (barn[x][y] == k)
					board[x][y] = -1;
				else
					board[x][y] = 0;
			}
		
		print(board);

		findBest(board);
		
		System.out.printf("Selection 1: %d (%d, %d) - (%d, %d)%n", maxSum, maxX1, maxY1, maxX2, maxY2);

		for (int x = maxX1; x < maxX2; x++)
			for (int y = maxY1; y < maxY2; y++)
				barn[x][y]++;
		
		print(barn);

		for (int x = 0; x < SIZE; x++)
			for (int y = 0; y < SIZE; y++)
				if (barn[x][y] == k - 1)
					board[x][y] = 1;
				else if (barn[x][y] == k)
					board[x][y] = -1;
				else
					board[x][y] = 0;

		
		//no overlap
		for (int x = maxX1; x < maxX2; x++)
			for (int y = maxY1; y < maxY2; y++)
				board[x][y] = -1000;

		print(board);

		// round 2
		findBest(board);
		maxSum = 0;
		System.out.printf("Selection 2: %d (%d, %d) - (%d, %d)%n", maxSum, maxX1, maxY1, maxX2, maxY2);

		
		for (int x = maxX1; x < maxX2; x++)
			for (int y = maxY1; y < maxY2; y++)
				barn[x][y]++;

		print(barn);
		
		int ans = 0;
		
		for (int x = maxX1; x < maxX2; x++)
			for (int y = maxY1; y < maxY2; y++) {
				if (barn[x][y] == k)
					ans++;
			}

		// I am just testing my input complexity for now
		out.println(ans);
		in.close();
		out.close();
	}

	private static void findBest(int[][] board) {
		for (int y1 = 0; y1 < SIZE; y1++) {
			for (int y2 = y1; y2 < SIZE; y2++) {
//				// kadanes algo
//				// maybe cache all column sums - only 200^3
//
//				int start = 0, sum = 0;
//				int mSum = -8, mx1 = -1, mx2 = -1;
//				for (int x = 0; x < 200; x++) {
//					int bx = 0;
//					for (int i = y1; i < y2; i++)
//						bx += board[x][i];
//					sum += bx;
//					if (sum < 0) {
//						sum = 0;
//						start = x+1;
//					} else if (sum > mSum) {
//						maxSum = sum;
//						mx1 = start;
//						mx2 = x;
//					}
//				}
//				
//				//end kadanes
//				
//				if (mSum > maxSum) {
//					maxSum = mSum;
//					maxX1 = mx1;
//					maxX2 = mx2;
//					maxY1 = y1;
//					maxY2 = y2;
//				}
				
				//lets try brute force
				for (int x1 = 0; x1 < SIZE; x1++)
					for (int x2 = x1; x2 < SIZE; x2++) {
						int sum=0;
						for (int x = x1; x < x2; x++)
							for (int y = y1; y < y2; y++)
								sum+= board[x][y];
						if (sum > maxSum) {
							maxSum = sum;
							maxX1 = x1;
							maxX2 = x2;
							maxY1 = y1;
							maxY2 = y2;
						}
								
					}
						
						
				
			}
		}
	}

	private static void print(int[][] board) {
		for (int i = SIZE-1; i > 0; i--) {
			for (int j = 1; j < SIZE; j++)
				System.out.printf("%5d", board[i][j]);
			System.out.println();
		}
		System.out.println("\n");
	}
}
