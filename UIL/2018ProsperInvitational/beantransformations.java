/*
ID: arjvik1
LANG: JAVA
TASK: beantransformations
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class beantransformations {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("beantransformations.dat")));
		int NUMCASES = in.nextInt();
		for (int CASENUM = 0; CASENUM < NUMCASES; CASENUM++) {
			int x = in.nextInt();
//			String num = "";
			int num = 0;
			int coeff = 1;
			while(x>0) {
				int a = x & 15;
				x = x >> 4;
				int h = ((a&8)>>3) + ((a&4)>>2) + ((a&2)>>1) +  (a&1);
				num += coeff * h;
				coeff*=16;
			}
			System.out.println(num);
		}
		in.close();
	}
}
