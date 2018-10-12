/*
ID: arjvik1
LANG: JAVA
TASK: cowsignal
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class cowsignal {
	static char[][] signal;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("cowsignal.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowsignal.out")));
		int m = in.nextInt(),
			n = in.nextInt(),
			k = in.nextInt();
		in.nextLine();
		signal = new char[m][];
		for (int i = 0; i < m; i++) {
			signal[i] = in.nextLine().toCharArray();
		}
		for (int a = 0; a < m; a++) {
			for (int b = 0; b < k; b++) {
				for (int c = 0; c < n; c++) {
					for (int d = 0; d < k; d++) {
						out.print(signal[a][c]);
					}
				}
				out.println();
			}
		}
		in.close();
		out.close();
	}
}
