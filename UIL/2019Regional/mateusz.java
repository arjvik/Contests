import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class mateusz {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("mateusz.dat"))), s = in;

		int NC = in.nextInt();
		in.nextLine();
		for (int CASE = 1; CASE <= NC; CASE++) {
			int n = in.nextInt();
			Rect[] rect = new Rect[n];
			for (int i = 0; i < n; i++)
				rect[i] = new Rect(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
			
			int area = 0;
			if (n >= 1)
				for (int i1 = 0; i1 < n; i1++)
					area += area(rect[i1]);
			if (n >= 2)
				for (int i1 = 0; i1 < n; i1++)
					for (int i2 = 0; i2 < n; i2++)
						area -= area(Rect.intersection(rect[i1], rect[i2]));
			if (n >= 3)
				for (int i1 = 0; i1 < n; i1++)
					for (int i2 = 0; i2 < n; i2++)
						for (int i3 = 0; i3 < n; i3++)
						area += area(Rect.intersection(rect[i1], rect[i2], rect[i3]));
			if (n >= 4)
				for (int i1 = 0; i1 < n; i1++)
					for (int i2 = 0; i2 < n; i2++)
						for (int i3 = 0; i3 < n; i3++)
							for (int i4 = 0; i4 < n; i4++)
						area -= area(Rect.intersection(rect[i1], rect[i2], rect[i3], rect[i4]));
			if (n >= 5)
				for (int i1 = 0; i1 < n; i1++)
					for (int i2 = 0; i2 < n; i2++)
						for (int i3 = 0; i3 < n; i3++)
							for (int i4 = 0; i4 < n; i4++)
								for (int i5 = 0; i5 < n; i5++)
						area += area(Rect.intersection(rect[i1], rect[i2], rect[i3], rect[i4], rect[i5]));
			if (n >= 6)
				for (int i1 = 0; i1 < n; i1++)
					for (int i2 = 0; i2 < n; i2++)
						for (int i3 = 0; i3 < n; i3++)
							for (int i4 = 0; i4 < n; i4++)
								for (int i5 = 0; i5 < n; i5++)
									for (int i6 = 0; i6 < n; i6++)
										area -= area(Rect.intersection(rect[i1], rect[i2], rect[i3], rect[i4], rect[i5], rect[i6]));
			if (n >= 7)
				for (int i1 = 0; i1 < n; i1++)
					for (int i2 = 0; i2 < n; i2++)
						for (int i3 = 0; i3 < n; i3++)
							for (int i4 = 0; i4 < n; i4++)
								for (int i5 = 0; i5 < n; i5++)
									for (int i6 = 0; i6 < n; i6++)
										for (int i7 = 0; i7 < n; i7++)
								area += area(Rect.intersection(rect[i1], rect[i2], rect[i3], rect[i4], rect[i5], rect[i6], rect[i7]));

			// AND SO ON SO FORTH. UNFORTUNATELY WE RAN OUT OF TIME DURING THE CONTEST.
			// THIS COULD HAVE BEEN DONE BETTER USING BINARY NUMERS AND BITMASKS.
			
		}

		in.close();
	}
	
	public static int area(Rect r) {
		if (r==null)return 0;return r.area();
	}
	
	public static class Interval {
		public final int a, b;

		public Interval(int a, int b) {
			this.a = Math.min(a, b);
			this.b = Math.max(a, b);
		}
		
		public static Interval intersect(Interval i1, Interval i2) {
			if (i1 == null || i2 == null) return null;
			int e1 = i1.a - i2.b,
				e2 = i1.b - i2.a;
			if (e1 * e2 >= 0) return null;
			if (e1 < e2) return new Interval(i1.a, i2.b);
			else		 return new Interval(i1.b, i2.a);
		}
		
	}
	public static class Rect {
		public final Interval x, y;

		public Rect(Interval x, Interval y) {
			this.x = x;
			this.y = y;
		}
		
		public Rect(int x, int y, int w, int h) {
			this(new Interval(x, x+w), new Interval(y, y+h));
		}
		
		public int area() {
			return (x.b-x.a)*(y.b-y.a);
		}
		
		public static Rect intersection(Rect r1, Rect r2) {
			if (r1 == null || r2 == null) return null;
			Interval x = Interval.intersect(r1.x, r2.x);
			Interval y = Interval.intersect(r1.y, r2.y);
			if (x == null || y == null) return null;
			return new Rect(x, y);
		}
		
		public static Rect intersection(Rect... r) {
			if (r.length == 0) throw new RuntimeException();
			if (r.length == 1) return r[0];
			Rect intersection = Rect.intersection(r[0], r[1]);
			for (int i = 2; i < r.length; i++)
				intersection = Rect.intersection(intersection, r[i]);
			return intersection;
		}
	}
	
}
