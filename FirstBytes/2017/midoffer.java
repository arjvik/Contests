
import java.util.*;
import java.io.*;

@SuppressWarnings("unused")
public class midoffer {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int k = in.nextInt();
		in.nextLine();
		for (int i = 0; i < k; i++) {
			String[] c = in.nextLine().split(", ");
			java.util.Scanner s = new java.util.Scanner(c[3]);
			double x = s.nextDouble(),
				   y = s.nextDouble(),
				   z = s.nextDouble();
			mid(c[0],c[1],c[2], x,y,z);
			if (i + 1 != k)
				System.out.println();
		}

	}
	
	private static class Pair {
		public String s;
		public double d;
		public Pair(String s, double d) {
			this.s = s;
			this.d = d;
		}
		
	}
	
	private static void mid(String p, String q, String r, double x, double y, double z) {
		Pair p1 = new Pair(p,x),
			 p2 = new Pair(q,y),
			 p3 = new Pair(r,z);
		ArrayList<Pair> s = new ArrayList<>(Arrays.asList(p1,p2,p3));
		s.sort((f,g) -> (int)((f.d-g.d)*10000));
		Pair pm = s.get(1);
		System.out.printf("%s %.2f", pm.s, pm.d);
	}

}
