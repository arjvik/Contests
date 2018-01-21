/*
ID: arjvik1
LANG: JAVA
TASK: piepie
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class piepie {
	static ArrayList<Pie> bessieBaked;
	static ArrayList<Pie> elsieBaked;
	static int n;
	static int d;
	static Comparator<Pie> elsieFirst = (p,q) -> p.elsie!=q.elsie ? p.elsie - q.elsie : p.bessie - q.bessie,
					bessieFirst = (p,q) -> p.bessie!=q.bessie ? p.bessie - q.bessie : p.elsie - q.elsie;
	public static class Pie {
		int bessie; int elsie;
		public Pie() {}
		public Pie(int bessie, int elsie) {
			this.bessie = bessie;
			this.elsie = elsie;
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("piepie.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("piepie.out")));
		n = in.nextInt();
		d = in.nextInt();
		bessieBaked = new ArrayList<Pie>(n);
		elsieBaked  = new ArrayList<Pie>(n);
		for (int i = 0; i < n; i++) {
			bessieBaked.add(new Pie(in.nextInt(), in.nextInt()));
		}
		for (int i = 0; i < n; i++) {
			elsieBaked.add(new Pie(in.nextInt(), in.nextInt()));
		}
		bessieBaked.sort(bessieFirst);
		elsieBaked.sort(elsieFirst);
		boolean[] gifted = new boolean[2 * n];
		
		for (int i = 0; i < n; i++) {
			int j = run(gifted, i, 0);
			out.println(j == Integer.MAX_VALUE ? -1 : j);
		}
		
		in.close();
		out.close();
	}

	private static int run(boolean[] DO_NOT_EDIT, int i, int debug) {
		boolean[] gifted = Arrays.copyOf(DO_NOT_EDIT, DO_NOT_EDIT.length);
		gifted[i] = true;
		if(i<n){//bessie gifted pie i; now elsie is gifting pie j+n;
			if (bessieBaked.get(i).bessie == 0)
				return 0;
			int min = Integer.MAX_VALUE;
			int minPie = -1;
			int minTaste = bessieBaked.get(i).elsie;
			int j = 0;
			for(;j < n && elsieBaked.get(j).elsie < n; j++);
			for(;j < n && elsieBaked.get(j).elsie <= n + d; j++){
				if(gifted[j + n])
					continue;
				int out = run(gifted, j + n, debug+1);
				if (out != Integer.MAX_VALUE)
					min = Math.min(min, out + 1);
			}
			return min;
		}else{//elsie gifted pie i-n
			if (elsieBaked.get(i-n).elsie == 0)
				return 0;
			int min = Integer.MAX_VALUE;
			int minPie = -1;
			int minTaste = elsieBaked.get(i-n).bessie;
			int j = 0;
			for(;j < n && bessieBaked.get(j).bessie < n; j++);
			for(;j < n && bessieBaked.get(j).bessie <= n + d; j++){
				if(gifted[j])
					continue;
				int out = run(gifted, j, debug+1);
				if (out != Integer.MAX_VALUE)
					min = Math.min(min, out + 1);
			}
			return min;
		}
	}
}
