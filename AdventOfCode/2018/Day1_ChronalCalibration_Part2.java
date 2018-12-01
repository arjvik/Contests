/*
ID: arjvik1
LANG: JAVA
TASK: Day1_ChronalCalibration_Part2
*/
import java.io.*;
import java.util.*;

public class Day1_ChronalCalibration_Part2 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		Set<Long> freq = new HashSet<>();
		freq.add(0l);
		List<Long> chg = new ArrayList<>();
		long l = 0;
		while (in.hasNextLong()) {
			long c = in.nextLong();
			l += c;
			chg.add(c);
			if (!freq.add(l)) {
				System.out.println(l);
				System.exit(0);
			}
		}
		int idx = 0;
		do {
			l += chg.get(idx);
			idx = (idx+1) % chg.size();
		} while (!freq.contains(l));
		System.out.println(l);
		in.close();
	}
}
