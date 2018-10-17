/*
ID: arjvik1
LANG: JAVA
TASK: L_romannumeral
*/
//
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class L_romannumeral {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("L.txt")));
		while(in.hasNextLine()) {
			String r = in.nextLine().toUpperCase();
			int n = 0;
			for(int i = 0; i < r.length() - 1; i++) {
				if(d(r.charAt(i)) < d(r.charAt(i+1)))
					n -= d(r.charAt(i));
				else
					n += d(r.charAt(i));
			}
			n += d(r.charAt(r.length() - 1));
			System.out.println(n);
		}
		in.close();
	}
	
	private static int d(char c) {
		switch(c) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		default:
			throw new RuntimeException("FIX METHOD: `int d(char c)`");
		}
	}
}
