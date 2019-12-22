import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class CoolOfficers {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int L = in.nextInt();
		int R = in.nextInt();
		int[] fact = new int[R+1];
		for (int i = 2; i <= R; i++)
			if (fact[i] == 0) {
				fact[i] = 1;
				for (int k = i; k <= R; k += i)
					if (fact[k] == 0)
						fact[k] = i;
			}
		int count = 0;
		for (int i = L; i <= R; i++) {
			HashMap<Integer, Integer> factors = new HashMap<>();
			int k = i;
			while (k > 1) {
				int f = fact[k];
				if (f == 1) f = k;
				if (factors.containsKey(f))
					factors.put(f, factors.get(f)+1);
				else
					factors.put(f, 1);
				k /= f;
			}
			if (almostSqr(factors))
				count++;
			
		}
		System.out.println(count);
	}

	private static boolean almostSqr(HashMap<Integer, Integer> factors) {
		boolean oddFound = false;
		for (int f : factors.keySet())
			if (factors.get(f)%2 != 0) {
				if (!oddFound)
					oddFound = true;
				else
					return false;
			}
		return oddFound;
	}
}