/*
ID: arjvik1
LANG: JAVA
TASK: speeding
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class speeding {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("speeding.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("speeding.out")));
		int[] limit = new int[100], speed = new int[100];
		int n = in.nextInt(), m = in.nextInt();
		for(int x = 0, i = 0; x < n; x++){
			int length = in.nextInt();
			int limit0 = in.nextInt();
			for(int j = 0; j < length; i++, j++){
				limit[i] = limit0;
			}
		}
		for(int x = 0, i = 0; x < m; x++){
			int length = in.nextInt();
			int speed0 = in.nextInt();
			for(int j = 0; j < length; i++, j++){
				speed[i] = speed0;
			}
		}
		int max = 0;
		for (int i = 0; i < 100; i++){
			if(speed[i]-limit[i]>max)
				max = speed[i]-limit[i];
		}
		out.println(max);
		in.close();
		out.close();
	}
}
