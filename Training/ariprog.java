/*
ID: arjvik1
LANG: JAVA
TASK: ariprog
*/
import java.io.*;
import java.util.*;

public class ariprog {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("ariprog.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
		int n = in.nextInt();
		int m = in.nextInt();
		long start = System.currentTimeMillis();
		Set<Integer> bisquaresSet = new HashSet<>();
		List<Integer> bisquares = new ArrayList<>(/*((m*(m-1)) / 2) + m*/);
		for (int p1 = 0; p1 <= m; p1++) {
			int p = p1 * p1;
			for (int q = 0; q <= m; q++) {
				int x = (p + (q * q));
				if(!bisquaresSet.contains(x)){
					bisquares.add(x);
					bisquaresSet.add(x);
				}
			}
		}
		bisquares.sort(null);
		System.err.println("Done calculating bisquares: length "+bisquares.size()+"\n\tTook "+((System.currentTimeMillis()-start)/1000d)+"s");
		Set<Pair> progs = new TreeSet<Pair>();
		for (int i = 0; i < bisquares.size() - (n-1); i++) {
			int a0 = bisquares.get(i);
		//for (int a0 : bisquaresSet) {
			prog: for (int j = i+1; j < bisquares.size(); j++) {
				int a1 = bisquares.get(j);
			//prog: for (int a1 : bisquaresSet) {
				int d = a1 - a0;
				//if(d<=0){
				//	System.out.printf("THIS SHOULD NOT HAPPEN d=%d, i=%d, bs(i)=%d, j=%d, bs(j)=%d\n",d,i,bisquares.get(i),j,bisquares.get(j));
				//	continue prog;
				//}
				int next = a0 + (n-1)*d;
				for (int k = 0; k < n-2; k++, next -= d) {
					if(!bisquaresSet.contains(next))
						continue prog;
				}
				progs.add(new Pair(a0, d));
			}
			//if(i%1==0)
			//	System.out.printf("On outer iteration %d\n",i);
		}
		if(progs.size()==0){
			out.println("NONE");
		}else{
			for (Pair pair : progs) {
				out.println(pair.toString());
			}
		}
		in.close();
		out.close();
		System.err.println("Entire program took "+((System.currentTimeMillis()-start)/1000d)+"ms");
	}
	private static class Pair implements Comparable<Pair> {
		private int a0, d;
		public Pair(int a0, int d) {
			this.a0 = a0;
			this.d = d;
		}
		@Override
		public String toString() {
			return a0 + " " + d;
		}
		@Override
		public int compareTo(Pair o) {
			if(o.d > d)
				return -1;
			else if(o.d < d)
				return 1;
			else if(o.a0 > a0)
				return -1;
			else if(o.a0 < a0)
				return 1;
			else
				return 0;
		}
	}
}
