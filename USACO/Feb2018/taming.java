/*
ID: arjvik1
LANG: JAVA
TASK: taming
*/
import java.io.*;
import java.util.*;

public class taming {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("taming.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));
		Random r = new Random(92148862);
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			out.println(r.nextInt(50)+1);
		}
		in.close();
		out.close();
	}
}
