/*
ID: arjvik1
LANG: JAVA
TASK: a1paper
*/
import java.io.*;
import java.util.*;

public class a1paper {
	private static final double PERIMETER_OF_A2 = 2.03010353025643560974862544;
	private static final int NUM_A30_PAGES = 536870912;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] papers = new int[n-1];
		for (int i = 0; i < n-1; i++) {
			papers[i] = in.nextInt();
		}
		int[] used = new int[n-1];
		int needed = NUM_A30_PAGES;
		for (int i = 0; i < n-1 && needed > 0; i++) {
			int perPage = pow(2,28-i);
			if(perPage*papers[i] <= needed) {
				used[i] = papers[i];
				needed -= perPage*papers[i];
			} else {
				int actual = needed/perPage;
				used[i] = actual;
				needed -= actual*perPage;
			}
		}
		
		if(needed>0){
			System.out.println("impossible");
			return;
		}
		
		double factor = 0d;
		for (int i = 0; i < n-1; i++) {
			factor += ((double)used[i])*Math.pow(2d, -0.5d*i);
		}
		
		factor -= Math.sqrt(2);
		
		factor /= 2d;
		System.err.printf("FACTOR: %.11f%n",factor);
		System.out.printf("%.11f%n",factor*PERIMETER_OF_A2);
	}
	private static int pow(int i, int j){
		return (int) Math.pow(i, j);
	}
}
