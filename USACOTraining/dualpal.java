/*
ID: arjvik1
LANG: JAVA
TASK: dualpal
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class dualpal {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("dualpal.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
		int n = in.nextInt(),
			s = in.nextInt();
		int i = s+1;
		for (int count = 0; count < n; i++) {
			if(isDualPal(i)){
				out.println(i);
				count++;
			}
		}
		in.close();
		out.close();
	}

	private static boolean isDualPal(int i) {
		boolean pal1=false;
		for(int b=2;b<=10;b++){
			if(isPalindromeInBase(i,b))
				if(!pal1)
					pal1 = true;
				else
					return true;
		}
		return false;
	}

	private static boolean isPalindromeInBase(int i, int b) {
		String s = Integer.toString(i, b);
		return new StringBuilder(s).reverse().toString().equals(s);
	}
}
