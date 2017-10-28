/*
ID: arjvik1
LANG: JAVA
TASK: pprime
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class pprime {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("pprime.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		//TODO Auto-generated body
		in.close();
		out.close();
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
