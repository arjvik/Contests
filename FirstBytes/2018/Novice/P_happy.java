/*
ID: arjvik1
LANG: JAVA
TASK: P_happy
*/
//
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class P_happy {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("P.txt")));
		int NUMCASES = in.nextInt();
		for(int casenum = 0; casenum < NUMCASES; casenum++) {
			int i = in.nextInt();
			final int ii = i;
			while(i != 37 && i != 1) {
				int s = 0;
				while(i > 0) {
					s += (i%10)*(i%10);
					i /= 10;
				}
				i = s;
			}
			System.out.printf("%d is %s%n", ii, i==1?"happy":"sad");
		}
		in.close();
	}
}
