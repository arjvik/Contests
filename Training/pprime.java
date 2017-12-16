/*
ID: arjvik1
LANG: JAVA
TASK: pprime
*/
import java.io.*;
import java.math.BigInteger;
import java.util.*;

@SuppressWarnings("unused")
public class pprime {
	private static final int _2 = 3;
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		main1(out);
		out.close();
	}
	public static void main1(PrintWriter out) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("pprime.in")));
		int a = in.nextInt();
		int b = in.nextInt();
		/* generate one digit palindrome: */
		for (int d1 = 5; d1 <= 9; d1+=2) {	/* only odd; evens aren't so prime */
				int palindrome = d1;
		        if(palindrome>b)
		        	return;
		        if(palindrome<a)
		        	continue;
		        if(BigInteger.valueOf(palindrome).isProbablePrime(_2))
		        	out.println(palindrome);
		}
		/* generate two digit palindrome: */
		for (int d1 = 1; d1 <= 9; d1+=2) {	/* only odd; evens aren't so prime */
		    int palindrome = 11*d1;
		        if(palindrome>b)
		        	return;
		        if(palindrome<a)
		        	continue;
		        if(BigInteger.valueOf(palindrome).isProbablePrime(_2))
		        	out.println(palindrome);
		}
		/* generate three digit palindrome: */
		for (int d1 = 1; d1 <= 9; d1+=2) {	/* only odd; evens aren't so prime */
		    for (int d2 = 0; d2 <= 9; d2++) {
		        	int palindrome = 101*d1 + 10*d2;
		        	if(palindrome>b)
		        		return;
		        	if(palindrome<a)
		        		continue;
		        	if(BigInteger.valueOf(palindrome).isProbablePrime(_2))
		        		out.println(palindrome);
		    }
		}
		/* generate four digit palindrome: */
		for (int d1 = 1; d1 <= 9; d1+=2) {	/* only odd; evens aren't so prime */
		    for (int d2 = 0; d2 <= 9; d2++) {
		        	int palindrome = 1001*d1 + 110*d2;
		        	if(palindrome>b)
		        		return;
		        	if(palindrome<a)
		        		continue;
		        	if(BigInteger.valueOf(palindrome).isProbablePrime(_2))
		        		out.println(palindrome);
		    }
		}
		/* generate five digit palindrome: */
		for (int d1 = 1; d1 <= 9; d1+=2) {	/* only odd; evens aren't so prime */
		    for (int d2 = 0; d2 <= 9; d2++) {
		        for (int d3 = 0; d3 <= 9; d3++) {
		        	int palindrome = 10001*d1 + 1010*d2 +100*d3;
		        	if(palindrome>b)
		        		return;
		        	if(palindrome<a)
		        		continue;
		        	if(BigInteger.valueOf(palindrome).isProbablePrime(_2))
		        		out.println(palindrome);
		        }
		    }
		}
		/* generate six digit palindrome: */
		for (int d1 = 1; d1 <= 9; d1+=2) {	/* only odd; evens aren't so prime */
		    for (int d2 = 0; d2 <= 9; d2++) {
		        for (int d3 = 0; d3 <= 9; d3++) {
			        int palindrome = 100001*d1 + 10010*d2 +1100*d3;
				    if(palindrome>b)
				    	return;
				    if(palindrome<a)
				    	continue;
					if(BigInteger.valueOf(palindrome).isProbablePrime(_2))
						out.println(palindrome);
		        }
		    }
		}
		/* generate seven digit palindrome: */
		for (int d1 = 1; d1 <= 9; d1+=2) {	/* only odd; evens aren't so prime */
		    for (int d2 = 0; d2 <= 9; d2++) {
		        for (int d3 = 0; d3 <= 9; d3++) {
			        for (int d4 = 0; d4 <= 9; d4++) {
			        	int palindrome = 1000001*d1 + 100010*d2 +10100*d3 + 1000*d4;
			        	if(palindrome>b)
			        		return;
			        	if(palindrome<a)
			        		continue;
			        	if(BigInteger.valueOf(palindrome).isProbablePrime(_2))
			        		out.println(palindrome);
			        }
		        }
		    }
		}
		/* generate eight digit palindrome: */
		for (int d1 = 1; d1 <= 9; d1+=2) {	/* only odd; evens aren't so prime */
		    for (int d2 = 0; d2 <= 9; d2++) {
		        for (int d3 = 0; d3 <= 9; d3++) {
			        for (int d4 = 0; d4 <= 9; d4++) {
			        	int palindrome = 10000001*d1 + 1000010*d2 +100100*d3 + 11000*d4;
		        		if(palindrome>b)
		        			return;
		        		if(palindrome<a)
		        			continue;
		        		if(BigInteger.valueOf(palindrome).isProbablePrime(_2))
		        			out.println(palindrome);
			        }
		        }
		    }
		}
		in.close();
	}
	//http://www.java67.com/2012/09/palindrome-java-program-to-check-number.html
	 public static boolean isPalindrome(int number) {
	        int palindrome = number; // copied number into variable
	        int reverse = 0;

	        while (palindrome != 0) {
	            int remainder = palindrome % 10;
	            reverse = reverse * 10 + remainder;
	            palindrome = palindrome / 10;
	        }

	        // if original and reverse of number is equal means
	        // number is palindrome in Java
	        if (number == reverse) {
	            return true;
	        }
	        return false;
	    }
	 //
}
