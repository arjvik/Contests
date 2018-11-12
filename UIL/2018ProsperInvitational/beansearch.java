/*
ID: arjvik1
LANG: JAVA
TASK: beansearch
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class beansearch {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("beansearch.dat")));
		int NUMCASES = in.nextInt();
		for (int CASENUM = 0; CASENUM < NUMCASES; CASENUM++) {
			int r=in.nextInt();
			int c=in.nextInt();
			in.nextLine();
			char[][] maze = new char[r][];
			for(int i = 0; i < r; i++) {
				maze[i] = in.nextLine().toCharArray();
			}
			List<String> words = new ArrayList<>();
			int nw = in.nextInt();
			in.nextLine();
			while(nw --> 0)
				words.add(in.nextLine());
			for (String w : words) {
				if(contains(maze,w,r,c))
					System.out.println("FOUND");
				else
					System.out.println("NOT FOUND");
			}
		}
		in.close();
	}

	private static boolean contains(char[][] maze, String word, int r, int c) {
		for(int i = 0; i<r; i++) {
			String line = "";
			for(int j = 0; j<c;j++) {
				line += maze[i][j];
			}
			if(line.contains(word))
				return true;
			if(rev(line).contains(word))
				return true;
		}
		for(int i = 0; i<c; i++) {
			String line = "";
			for(int j = 0; j<r;j++) {
				line += maze[j][i];
			}
			if(line.contains(word))
				return true;
			if(rev(line).contains(word))
				return true;
		}
		for(int i = 0; i<r; i++) {
			String line = "";
			for(int j = 0; i+j<r;j++) {
				line += maze[i+j][j];
			}
			if(line.contains(word))
				return true;
			if(rev(line).contains(word))
				return true;
		}
		for(int i = 0; i<r; i++) {
			String line = "";
			for(int j = 0; i-j>=0;j++) {
				line += maze[j][i-j];
			}
			if(line.contains(word))
				return true;
			if(rev(line).contains(word))
				return true;
		}
		return false;
	}

	private static String rev(String line) {
		return new StringBuilder(line).reverse().toString();
	}
}
