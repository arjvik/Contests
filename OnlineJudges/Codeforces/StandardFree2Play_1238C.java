import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class StandardFree2Play_1238C {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int numQueries = in.nextInt();
		for (int q = 0; q < numQueries; q++) {
			int h = in.nextInt(),
				n = in.nextInt();
			TreeSet<Integer> extended = new TreeSet<>();
			extended.add(0); //zero is special, it never retracts
			for (int j = 0; j < n; j++)
				extended.add(in.nextInt());
			
			int cost = 0;
			int pos = h;
			falling:while (pos > 0) {
				boolean even = false;
				int bottom;
				for (bottom = pos; extended.contains(bottom-1); bottom--, even = !even);
				if (bottom == 0)
					break falling;
				if (even)
					cost++;
				pos = extended.lower(bottom)+1;
				extended.add(pos);
			}
			System.out.println(cost);
		}
	}
}
