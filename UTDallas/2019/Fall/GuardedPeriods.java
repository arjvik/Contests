import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class GuardedPeriods {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		in.nextLine();
		int[] start = Arrays.stream(in.nextLine().split("\\s+")).map(s -> s.split(":"))
				.mapToInt(s -> 60 * Integer.parseInt(s[0]) + Integer.parseInt(s[1])).toArray();
		int[] end = Arrays.stream(in.nextLine().split("\\s+")).map(s -> s.split(":"))
				.mapToInt(s -> 60 * Integer.parseInt(s[0]) + Integer.parseInt(s[1])).toArray();
		Arrays.sort(start);
		Arrays.sort(end);
		int pS = 0; int pE = 0;
		int count = 0;
		int ans = 0;
		for (int i = 0; i < 24*60; i++) {
			while (pS < start.length && i == start[pS]) {
				pS++;
				count++;
			}
			while (pE < end.length && i == end[pE]) {
				pE++;
				count--;
			}
			if (count > 0)
				ans++;
		}
		System.out.println(ans);
	}
}