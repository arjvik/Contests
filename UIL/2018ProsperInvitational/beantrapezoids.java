/*
ID: arjvik1
LANG: JAVA
TASK: beantrapezoids
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class beantrapezoids {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("beantrapezoids.dat")));
		int NUMCASES = in.nextInt();
		for (int CASENUM = 0; CASENUM < NUMCASES; CASENUM++) {
			double a = in.nextDouble(), b = in.nextDouble(), c = in.nextDouble();
			System.out.printf("%.2f%n", (a+b)*c/2);
		}
		in.close();
	}
}
