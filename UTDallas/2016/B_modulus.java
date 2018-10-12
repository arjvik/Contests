/*
ID: arjvik1
LANG: JAVA
TASK: Digits
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class B_modulus {
	public static void main(String[] args) throws IOException {
		List<Character> l = Arrays.asList( new Character[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' });
		Scanner in = new Scanner(new BufferedReader(new FileReader("B.txt")));
		int NC = in.nextInt();
		for (int i = 0; i < NC; i++) {
			System.out.print(in.nextInt()+" ");
			int b = in.nextInt();
			char[] n = rev(in.next()).toCharArray();
			long s = 0;
			for (char c : n) {
				s *= b;
				s += l.indexOf(c);
			}
			System.out.println(l.get((int) (s % (b-1))));
		}
	}
	
	public static String rev(String s) {
		return new StringBuilder(s).reverse().toString();
	}
}
