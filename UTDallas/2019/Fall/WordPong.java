import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class WordPong {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int NC = in.nextInt();
		for (int CN = 0; CN < NC; CN++) {
			String w = in.next();
			if (pong(w))
				System.out.printf("%s plays ping pong%n",w);
			else
				System.out.printf("%s does not play ping pong%n", w);
		}
		in.close();
	}
	public static boolean pong(String w) {
		String s = "qwertasdfgzxcvb";
		boolean b = s.contains(""+w.charAt(0));
		for (int i = 0; i < w.length(); i++) {
			if (b != s.contains(""+w.charAt(i)))
				return false;
			b = !b;
		}
		return true;
	}
}