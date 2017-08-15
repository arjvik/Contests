/*
ID: arjvik1
LANG: JAVA
TASK: crypt1
*/
import java.io.*;
import java.util.*;

public class crypt1 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("crypt1.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
		int n = in.nextInt();
		int[] digits = new int[n];
		Set<Integer> digitSet = new HashSet<>();
		for (int i = 0; i < n; i++) {
			digits[i] = in.nextInt();
			digitSet.add(digits[i]);
		}
		int count = 0;
		for (int a : digits) {
			for (int b : digits) {
				for (int c : digits) {
					for (int d : digits) {
						for (int e : digits) {
							int x = 100*a+10*b+c;
							int y = 10*d+e;
							int p1 = e*x;
							int p2 = d*x;
							int ans = x*y;
							if( p1 >= 100 && p1 < 1000 &&
								p2 >= 100 && p2 < 1000 &&
								ans >= 1000 && ans < 10000 &&
								isValid(digitSet,p1) &&
								isValid(digitSet, p2) &&
								isValid(digitSet, ans)){
								count++;
							}
						}
					}
				}
			}
		}
		out.println(count);
		in.close();
		out.close();
	}

	private static boolean isValid(Set<Integer> digitSet, int i) {
		if(i==0)
			return true;
		if(!digitSet.contains(i%10))
			return false;
		return isValid(digitSet, i/10);
	}
}
