/*
ID: arjvik1
LANG: JAVA
TASK: cowpatibility
*/
import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.*;

@SuppressWarnings("unused")
public class cowpatibility {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("cowpatibility.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowpatibility.out")));
		HashMap<Object, Integer> m1 = new HashMap<>(),
									   m2 = new HashMap<>(),
									   m3 = new HashMap<>(),
									   m4 = new HashMap<>(),
									   m5 = new HashMap<>();
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			int[] fl = new int[5];
			for (int j = 0; j < 5; j++)
				fl[j] = in.nextInt();
			Arrays.sort(fl);
			int a = fl[0],
				b = fl[1],
				c = fl[2],
				d = fl[3],
				e = fl[4];
			
			BiFunction<Integer, Integer, Integer> f = (j,k) -> j+k;

			m1.merge(set(a), 1, f);
			m1.merge(set(b), 1, f);
			m1.merge(set(c), 1, f);
			m1.merge(set(d), 1, f);
			m1.merge(set(e), 1, f);

			m2.merge(set(a,b), 1, f);
			m2.merge(set(a,c), 1, f);
			m2.merge(set(a,d), 1, f);
			m2.merge(set(a,e), 1, f);
			m2.merge(set(b,c), 1, f);
			m2.merge(set(b,d), 1, f);
			m2.merge(set(b,e), 1, f);
			m2.merge(set(c,d), 1, f);
			m2.merge(set(c,e), 1, f);
			m2.merge(set(d,e), 1, f);

			m3.merge(set(a,b,c), 1, f);
			m3.merge(set(a,b,d), 1, f);
			m3.merge(set(a,b,e), 1, f);
			m3.merge(set(a,c,d), 1, f);
			m3.merge(set(a,c,e), 1, f);
			m3.merge(set(a,d,e), 1, f);
			m3.merge(set(b,c,d), 1, f);
			m3.merge(set(b,c,e), 1, f);
			m3.merge(set(b,d,e), 1, f);
			m3.merge(set(c,d,e), 1, f);

			m4.merge(set(a,b,c,d), 1, f);
			m4.merge(set(a,b,c,e), 1, f);
			m4.merge(set(a,b,d,e), 1, f);
			m4.merge(set(a,c,d,e), 1, f);
			m4.merge(set(b,c,d,e), 1, f);

			m5.merge(set(a,b,c,d,e), 1, f);
		}
		
		int i1 = m1.keySet().stream().mapToInt(m1::get).map(x -> x*(x-1)/2).sum();
		int i2 = m2.keySet().stream().mapToInt(m2::get).map(x -> x*(x-1)/2).sum();
		int i3 = m3.keySet().stream().mapToInt(m3::get).map(x -> x*(x-1)/2).sum();
		int i4 = m4.keySet().stream().mapToInt(m4::get).map(x -> x*(x-1)/2).sum();
		int i5 = m5.keySet().stream().mapToInt(m5::get).map(x -> x*(x-1)/2).sum();
		out.print(n*(n-1)/2 - i1 + i2 - i3 + i4 - i5);
		
		in.close();
		out.close();
	}
	private static Object set(int... vals) {
		return new Set(vals);
	}

	private static class Set {
		public final int[] arr;
		public Set(int[] arr) {
			this.arr = arr;
		}
		@Override
		public int hashCode() {
			return hash(arr);
		}
		@Override
		public boolean equals(Object other) {
			if (other instanceof Set)
				return Arrays.equals(arr, ((Set) other).arr);
			return false;
		}
	}
				
	private static int hash(int[] vals) {
		final int K=104723, M=104729;
		long hash = 0;
		for (int i : vals) {
			hash = (hash*K+i)%M;
		}
		return (int) hash;
	}
}
