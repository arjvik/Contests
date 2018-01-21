/*
ID: arjvik1
LANG: JAVA
TASK: hayfeast
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class hayfeast {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("hayfeast.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));
		int n = in.nextInt();
		long m = in.nextLong();
		int flavor[] = new int[n];
		int spice[] = new int[n];
		for (int i = 0; i < n; i++) {
			flavor[i] = in.nextInt();
			spice[i] = in.nextInt();
		}
		int min = Integer.MAX_VALUE;
		loop:for (int start = 0; start < n; start++) {
			/*for (int end = start + 1; end < n; end++) {
				int max = Integer.MIN_VALUE;
				long flv = 0;
				for (int i = start; i <= end; i++) {
					max = Math.max(max, spice[i]);
					flv += flavor[i];
				}
				if(flv >= m)
					min = Math.min(min, max);
			}*/
			long flv = 0;
			int spc = Integer.MIN_VALUE;
			int i = start;
			while(flv < m){
				try{
				flv += flavor[i];
				}catch(ArrayIndexOutOfBoundsException e){ continue loop; } //hack to avoid crossing the array limit
				
				spc = Math.max(spc, spice[i]);
				i++;
			}
			min = Math.min(min, spc);
		}
		out.println(min);
		in.close();
		out.close();
	}
}
