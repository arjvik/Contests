import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class TheFairNutAndString_1084C {
	public static long MOD = (long) (1e9+7);
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String s = in.nextLine().replaceAll("[^ab]", "");
		long x = 0, b = 0;
		for (char c : s.toCharArray())
			if (c == 'a')
				x = mod(x + b + 1);
			else
				b = x;
		System.out.println(x);
	}
	public static long mod(long l) {
		return l % MOD;
	}
}
