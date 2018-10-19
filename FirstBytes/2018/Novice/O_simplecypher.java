/*
ID: arjvik1
LANG: JAVA
TASK: O_simplecypher
*/
//
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class O_simplecypher {
	final static char[] alpha ={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("O.txt")));
		int NUMCASES = in.nextInt();
		in.nextLine();
		for(int casenum = 0; casenum < NUMCASES; casenum++) {
			int s = 1*in.nextInt();
			String message = in.nextLine().substring(1);			
			for(char c : message.toCharArray()) {
				int i = c - 'A';
				if(c == ' ') {
					System.out.print(c);
					continue;
				}
				i += s;
				i = mod(i,26);
				System.out.print(alpha[i]);
			}
			System.out.println();
		}
		in.close();
	}
	private static int mod(int i, int m) {
		int r = i % m;
		while(r < 0)
			r += m;
		return r;
	}
}
