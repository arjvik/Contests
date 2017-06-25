/*
ID: arjvik1
LANG: JAVA
TASK: dualpal
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class dualpal2 {
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
		/*
		String s = Integer.toString(i, b);
		return new StringBuilder(s).reverse().toString().equals(s);
		/*/
		return isPalindrome(convertToBase(i,b));
		//*/
	}

	private static String convertToBase(int i, int b) {
		StringBuilder sb = new StringBuilder();
		int maxPower=0;
		while(pow(b,maxPower)<i)
			maxPower++;
		maxPower--;
		while(maxPower>-0){
			int remainder = i%pow(b,maxPower);
			sb.append(i-remainder);
			i=remainder;
			maxPower--;
		}
		return sb.toString();
	}

	private static boolean isPalindrome(String s) {
		return new StringBuilder(s).reverse().toString().equals(s);
	}

	private static boolean isPalindrome(int i) {
		int l = Integer.toString(i).length();
		for (int j = 1; j <= l/2; j++) {
			if(getDigitAt(i,j)!=getDigitAt(i,l+1-j))
				return false;
		}
		return true;
	}
	
	private static long getDigitAt(long i, int d){
		return i%pow(10,d) - 1%pow(10,d-1);
	}
	
	private static int pow(int i, int j){
		return (int) Math.pow(i,j);
	}
}
