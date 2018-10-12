/*
ID: arjvik1
LANG: JAVA
TASK: blocks
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class blocks {
	static String[][] words;
	static int[] alphabetCount = new int[26];
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("blocks.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("blocks.out")));
		int n = in.nextInt();
		in.nextLine();
		words = new String[n][2];
		for (int i = 0; i < n; i++) {
			String raw = in.nextLine().toLowerCase();
			String[] rawArray = raw.split(" ");
			words[i][0] = rawArray[0];
			words[i][1] = rawArray[1];
		}
		for (String[] array : words) {
			int[] alphabetCount1 = new int[26],
					alphabetCount2 = new int[26];
			String w1 = array[0],
					w2 = array[1];
			char[] c1 = w1.toCharArray();
			for (char c : c1) {
				alphabetCount1[c-'a']++;
			}
			char[] c2 = w2.toCharArray();
			for (char c : c2) {
				alphabetCount2[c-'a']++;
			}
			for (int i = 0; i < 26; i++) {
				alphabetCount[i] += Math.max(alphabetCount1[i], alphabetCount2[i]);
			}
		}
		for (int i : alphabetCount) {
			out.println(i);
		}
		in.close();
		out.close();
	}
}
