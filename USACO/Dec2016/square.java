/*
ID: arjvik1
LANG: JAVA
TASK: square
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class square {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("square.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("square.out")));
		int ax1 = in.nextInt(),
			ay1 = in.nextInt(),
			ax2 = in.nextInt(),
			ay2 = in.nextInt(),
			bx1 = in.nextInt(),
			by1 = in.nextInt(),
			bx2 = in.nextInt(),
			by2 = in.nextInt();
		int x = max(ax1,ax2, bx1, bx2) - min(ax1,ax2, bx1, bx2);
		int y = max(ay1,ay2, by1, by2) - min(ay1,ay2, by1, by2);
		out.println(sq(max(x,y)));
		in.close();
		out.close();
	}
	private static int max(int...i){
		int max=i[0];
		for (int j = 1; j < i.length; j++) 
			if(i[j]>max)
				max=i[j];
		return max;
	}
	private static int min(int...i){
		int min=i[0];
		for (int j = 1; j < i.length; j++) 
			if(i[j]<min)
				min=i[j];
		return min;
	}
	private static int sq(int i){
		return i*i;
	}
}
