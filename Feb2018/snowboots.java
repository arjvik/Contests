/*
ID: arjvik1
LANG: JAVA
TASK: snowboots
*/
import java.io.*;
import java.util.*;


@SuppressWarnings("unused")
public class snowboots {
	private static int[] depth;
//	private static int[] max_forward;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("snowboots.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
		int n = in.nextInt();
		int b = in.nextInt();
		depth = new int[n];
//		max_forward = new int[n];
		for(int i = 0; i < n; i++){
			int d = in.nextInt();
			depth[i] = d;
//			for (int j = 0; j <= i; j++)
//				if(max_forward[j] < d)
//					max_forward[j] = d;
		}
		for (int i = 0; i < b; i++) {
			out.println(run(in.nextInt(),in.nextInt(),0)?1:0);
		}
		
		in.close();
		out.close();
	}
	private static boolean run(int max_depth, int step_size, int pos /** 0-based index **/) {
		if(pos>=depth.length-1)
			return true;
//		if(max_forward[pos]<=max_depth)
//			return true;
		for (int i = step_size; i > 0; i--)
			if(pos+i<depth.length && depth[pos+i]<=max_depth && run(max_depth, step_size, pos+i))
				return true;
		return false;
	}
}
