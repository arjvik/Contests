/*
ID: arjvik1
LANG: JAVA
TASK: transform
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class transform {
	private static char[][] b4,af;
	private static int N;	
	public static void main(String[] args) throws IOException {
		//*
		Scanner in = new Scanner(new BufferedReader(new FileReader("transform.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
		N = in.nextInt();
		in.nextLine();
		b4 = new char[N][N];
		af = new char[N][N];
		for (int i = 0; i < N; i++) {
			b4[i]=in.nextLine().toCharArray();
		}
		for (int i = 0; i < N; i++) {
			af[i]=in.nextLine().toCharArray();
		}
		System.err.println(Arrays.deepToString(b4));
		System.err.println(Arrays.deepToString(af));
		int i=-1;
		if(rotate90(b4)) i=1;
		else if(rotate180(b4)) i=2;
		else if(rotate270(b4)) i=3;
		else if(reflection()) i=4;
		else if(combination()) i=5;
		else if(noChange()) i=6;
		else i=7;
		out.println(i);
		in.close();
		out.close();
		/*/
		Scanner in = new Scanner(new BufferedReader(new FileReader("transform.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
		N = in.nextInt();
		in.nextLine();
		b4 = new char[N][N];
		af = new char[N][N];
		for (int i = 0; i < N; i++) {
			b4[i]=in.nextLine().toCharArray();
		}
		for (int i = 0; i < N; i++) {
			af[i]=in.nextLine().toCharArray();
		}
		System.err.println(noChange());		
		//*/
	}
	private static boolean rotate90(char[][] b4) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if(af[j][N-i-1]!=b4[i][j])
					return false;
		return true;
	}
	private static boolean rotate180(char[][] b4) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if(af[i][j]!=b4[N-i-1][N-j-1])
					return false;
		return true;
	}
	private static boolean rotate270(char[][] b4) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if(af[i][j]!=b4[j][N-i-1])
					return false;
		return true;
	}
	private static boolean reflection() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if(af[i][j]!=b4[i][N-j-1])
					return false;
		return true;
	}
	private static boolean combination() {
		char[][] mid = new char[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				mid[i][j]=b4[i][N-j-1];
		return  rotate90(mid) ||
				rotate180(mid) || 
				rotate270(mid);
	}
	private static boolean noChange() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if(af[i][j]!=b4[i][j])
					return false;
		return true;
	}
}
