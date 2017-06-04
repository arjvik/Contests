/*
ID: arjvik1
LANG: JAVA
TASK: test
*/
import java.io.*;
import java.util.*;

public class test {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("test.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.out")));
		int i = in.nextInt(),
				j = in.nextInt(),
				ans = i+j;
		out.println(ans);
		in.close();
		out.close();
	}
}
