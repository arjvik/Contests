/*
ID: arjvik1
LANG: JAVA
TASK: Unwrap
*/
import java.io.*;
import java.util.*;

public class Unwrap {
	private static boolean[][] visited;
	private static int n;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("unwrap.dat")));
		n = in.nextInt();
		int[][]  spiral = new int[n][n];
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				spiral[i][j] = in.nextInt();
		List<Integer> list = new ArrayList<>();
		calc(spiral,list);
		printList(list);
		in.close();
	}

	private static void calc(int[][] spiral, List<Integer> list) {
		int row = 0;
		int col = 0;
		int dir = 0;
		while(list.size()<n*n){
			list.add(spiral[row][col]);
			visited[row][col] = true;
			switch(dir){
			case 0:
				if(check(row,col+1)){
					col++;
				}else{
					row++;
					dir = 1;
				}
				break;
			case 1:
				if(check(row+1,col)){
					row++;
				}else{
					col--;
					dir = 2;
				}
				break;
			case 2:
				if(check(row,col-1)){
					col--;
				}else{
					row--;
					dir = 3;
				}
				break;
			case 3:
				if(check(row-1,col)){
					row--;
				}else{
					col++;
					dir = 0;
				}
				break;
			}
		}
	}

	private static boolean check(int row, int col) {
		return row >= 0 && row < n && col >= 0 && col < n && !visited[row][col];
	}

	private static void printList(List<Integer> list) {
		int i = 0;
		for (Integer integer : list) {
			i++;
			if(i%10==0)
				System.out.println(integer);
			else
				System.out.print(integer+" ");
		}
	}
}
