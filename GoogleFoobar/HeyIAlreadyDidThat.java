import java.util.Arrays;
import java.util.HashMap;

public class HeyIAlreadyDidThat {

	public static int solution(String n, int b) {
		HashMap<String, Integer> seen = new HashMap<>();
		int iter = 0;
		while (!seen.containsKey(n)) {
			seen.put(n, iter++);
			n = next(n, b);
		}
		return iter - seen.get(n);
	}

	private static String next(String n, int b) {
		char[] digits = n.toCharArray();
		Arrays.sort(digits);
		int x = Integer.parseInt(new String(digits), b);
		reverse(digits);
		int y = Integer.parseInt(new String(digits), b);
		String z = Integer.toString(y - x, b);
		if (z.length() < n.length())
			z = "00000000000000000000000000000000".substring(0, n.length() - z.length()) + z;
		return z;
	}

	private static void reverse(char[] a) {
		for (int i = 0; i < a.length / 2; i++) {
			char tmp = a[i];
			a[i] = a[a.length - 1 - i];
			a[a.length - 1 - i] = tmp;
		}
	}
	
	public static void main(String[] args) {
		String[] n = {"1211", "210022"};
		int[] b = {10, 3};
		int[] out = {1, 3};
		for (int i = 0; i < n.length; i++)
			if (solution(n[i], b[i]) != out[i])
				throw new RuntimeException("Test case " + i + " failed");
		System.out.println("All test cases successful");
	}
	
}