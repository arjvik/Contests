/*
ID: arjvik1
LANG: JAVA
TASK: crossroad
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class crossroad {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("crossroad.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crossroad.out")));
		int numCrosses = 0;
		int n = in.nextInt();
		int[] currentPosition = new int[11];
		assert currentPosition[0] == 0;
		for (int i = 0; i < n; i++) {
			int cowNum = in.nextInt();
			int position = in.nextInt() + 1;
			if(currentPosition[cowNum]!=0 && currentPosition[cowNum]!=position)
				numCrosses++;
			currentPosition[cowNum]=position;
		}
		out.println(numCrosses);
		in.close();
		out.close();
	}
}
