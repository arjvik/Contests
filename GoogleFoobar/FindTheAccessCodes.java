public class FindTheAccessCodes {

	/**
	 * Cool O(n^2) solution which outperforms the O(n^3) brute force solution!
	 * I iterate from the right side of the array going left, keeping track of
	 * the number of lucky *pairs*, not triplets starting from each index.
	 * When I encounter a lucky pair (i, j), I add the number of lucky pairs
	 * starting at j to my answer. This works because each lucky triplet is just
	 * two lucky pairs sharing the middle number. By doing this, I break the problem
	 * down into iterating all pairs rather than all triplets, cutting my runtime
	 * down by a factor of n. 
	 */
	public static int solution(int[] l) {
		int[] pairs = new int[l.length];
		int answer = 0;
		for (int i = l.length - 1; i >= 0; i--)
			for (int j = i+1; j < l.length; j++)
				if (l[j] % l[i] == 0) {
					answer += pairs[j];
					pairs[i]++;
				}
		return answer;
	}

	public static void main(String[] args) {
		int[][] in = {  { 1, 1, 1 },
						{ 1, 2, 3, 4, 5, 6 },
						{ 1, 2, 3, 4, 5, 6, 8, 12 },
						{ 4, 5, 6, 7, 8 }  };
		int[] out = { 1, 3, 13, 0 };
		for (int i = 0; i < in.length; i++)
			if (solution(in[i]) != out[i])
				throw new RuntimeException("Test case " + i + " failed");
		System.out.println("All test cases successful");
	}

}