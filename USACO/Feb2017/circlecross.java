/*
ID: arjvik1
LANG: JAVA
TASK: circlecross
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class circlecross {
	private static final int N = 26;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("circlecross.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));
		char[] crossPoints;
		crossPoints = in.nextLine().toCharArray();
		/*for (int i = 0; i < crossPoints.length; i++) {
			char c = crossPoints[i];
			System.out.format("Char at position %d has value '%c' (%d) or ordinal %d%n",i,c,(int)c,c-'A');
		}*/
		int numCrosses = 0;
		int[][] entryExit = new int[N][2];
		boolean[] entryDone = new boolean[N];
		for (int i = 0; i < crossPoints.length; i++) {
			int cowNum = crossPoints[i]-'A';
			entryExit[cowNum][entryDone[cowNum]?1:0] = i;
			entryDone[cowNum] = true;
		}
		for (int i = 0; i < N; i++)
			for (int j = i+1; j < N; j++)
				if((entryExit[i][0]<entryExit[j][0] && entryExit[i][1]<entryExit[j][1] && entryExit[j][0]<entryExit[i][1])
					|| (entryExit[i][0]>entryExit[j][0] && entryExit[i][1]>entryExit[j][1] && entryExit[j][1]>entryExit[i][0])){
						numCrosses++;
						System.out.format("Cross found: i=%c (%d, %d); j=%c (%d, %d);%n",i+'A',entryExit[i][0],entryExit[i][1],j+'A',entryExit[j][0],entryExit[j][1]);
					}
		out.println(numCrosses);
		in.close();
		out.close();
	}
}
