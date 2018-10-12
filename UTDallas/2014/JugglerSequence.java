/*
ID: arjvik1
LANG: JAVA
TASK: H
*/
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class JugglerSequence {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("H.in")));
		PrintWriter out = new PrintWriter(System.out);
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			long a = in.nextLong();
			long b = a;
			ArrayList<Long> s = new ArrayList<>();
			s.add(b);
			while(b!=1){
				b = s(b);
				s.add(b);
			}
			out.println(s.stream().map(l->l.toString()).collect(Collectors.joining(" ")));
		}
		in.close();
		out.close();
	}

	private static long s(long b) {
		return  (long) Math.floor(Math.pow(b, (b%2==0)?(0.5):(1.5)));
	}
}
