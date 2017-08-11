/*
ID: arjvik1
LANG: JAVA
TASK: paint
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class paint {
	static boolean[] painted = new boolean[100];
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("paint.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("paint.out")));
		int a = in.nextInt(),
			b = in.nextInt(),
			c = in.nextInt(),
			d = in.nextInt();
		int count = b-a;
		for(int i = a; i < b; i++)
			painted[i] = true;
		for(int i = c; i < d; i++)
			if(!painted[i])
				count++;
		out.println(count);
		in.close();
		out.close();
	}
}
