import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinionTaskScheduling {
	
	public static int[] solution(int[] data, int n) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i : data)
			map.merge(i, 1, (a, b) -> a + b);
		return Arrays.stream(data).filter(i -> map.get(i) <= n).toArray();
	}

	public static void main(String[] args) {
		int[][] data = { { 1, 2, 3 }, { 1, 2, 2, 3, 3, 3, 4, 5, 5 }, { 1, 2, 3 } };
		int[] n = { 0, 1, 6 };
		int[][] out = { {}, { 1, 4 }, { 1, 2, 3 } };
		for (int i = 0; i < data.length; i++)
			if (!Arrays.equals(solution(data[i], n[i]), out[i]))
				throw new RuntimeException("Test case " + i + " failed");
		System.out.println("All test cases successful");
	}
	
}
