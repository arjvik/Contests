/*
ID: arjvik1
LANG: JAVA
TASK: billboard
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class billboard {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("billboard.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("billboard.out")));
		int b1x1 = in.nextInt() + 1000,
			b1y1 = in.nextInt() + 1000,
			b1x2 = in.nextInt() + 1000,
			b1y2 = in.nextInt() + 1000,

			b2x1 = in.nextInt() + 1000,
			b2y1 = in.nextInt() + 1000,
			b2x2 = in.nextInt() + 1000,
			b2y2 = in.nextInt() + 1000,
			
			t1x1 = in.nextInt() + 1000,
			t1y1 = in.nextInt() + 1000,
			t1x2 = in.nextInt() + 1000,
			t1y2 = in.nextInt() + 1000;
		
		boolean[][] b = new boolean[2000][2000];
		int a = 0;
		for (int x = b1x1; x < b1x2; x++) {
			for (int y = b1y1; y < b1y2; y++) {
				b[x][y] = true;
				a++;
			}
		}
		for (int x = b2x1; x < b2x2; x++) {
			for (int y = b2y1; y < b2y2; y++) {
				b[x][y] = true;
				a++;
			}
		}
		for (int x = t1x1; x < t1x2; x++) {
			for (int y = t1y1; y < t1y2; y++) {
				if(b[x][y])
					a--;
			}
		}

		out.print(a);
		in.close();
		out.close();
	}
}
