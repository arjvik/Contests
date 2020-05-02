import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class DoomsdayFuel {

	/**
	 * 
	 * O(n^4) algorithm to analytically solve a Markov Model:
	 * 
	 * Quick notation definitions:
	 * Let t be the number of terminal states, and let u=n-t be the number of non-terminal states. 
	 * In addition, let T be the set of terminal states, and U be the set of non-terminal states.
	 * 
	 * We define u*t variables, where variable P(i, j) represents the probability
	 * of starting from non-terminal state i and eventually ending in terminal state j eventually.
	 * 
	 * These variables are related by the following equation:
	 * 
	 * For all (i in U, j in T): 
	 * P(i, j) = m[i][j] + Sum_{k in U; k != i} m[i][k]*P(k, j)
	 * 
	 * As each P(i, j) is only in an equation with other values of P(k, j), we solve t independent
	 * u*u systems. Each system takes O(u^3) to solve, so our total complexity ends up being
	 * O(t*u^3) which is proportional to O(n^4).
	 * 
	 */
	public static int[] solution(int[][] m) {
		final int n = m.length;
		
		List<Integer> terminalStates = new ArrayList<>();
		List<Integer> nonTerminalStates = new ArrayList<>();
		// transitions[u in U][s in S] is the probability of transitioning from u to s
		Map<Integer, List<Fraction>> transitions = new HashMap<>();
		
		// Sort the states into terminal and non-terminal
		// and convert the counts into fractional probabilities
		for (int i = 0; i < n; i++) {
			int total = 0;
			for (int j = 0; j < n; j++)
				if (i != j)
					total += m[i][j];
			if (total != 0) {
				// we can ignore self transition because it ends up collapsing to zero (geometric series)
				// it works out cleaner later to make this probability -1 (when solving the matrix)
				m[i][i] = -total;
				List<Fraction> probabilities = new ArrayList<>();
				for (int j = 0; j < n; j++)
					probabilities.add(new Fraction(m[i][j], total));
				transitions.put(i, probabilities);
				nonTerminalStates.add(i);
			} else
				terminalStates.add(i);
		}

		// size of sets T and U
		final int T = terminalStates.size(),
				  U = nonTerminalStates.size();
		
		// If state 0 is terminal we can break out immediately
		// Otherwise we will end up in a weird state
		if (terminalStates.get(0) == 0) {
			int[] answer = new int[T+1];
			answer[0] = 1;
			answer[T] = 1;
			return answer;
		}
		
		// For each terminal state, we set up a system of equations
		// solving for the probability of reaching that state
		// The answer for this state is the value of our first variable
		List<Fraction> answers = new ArrayList<Fraction>();
		for (int t : terminalStates) {
			Fraction[][] matrix = new Fraction[U][U+1];
			for (int i = 0; i < U; i++) {
				int u = nonTerminalStates.get(i);
				for (int j = 0; j < U; j++) {
					int v = nonTerminalStates.get(j);
					matrix[i][j] = transitions.get(u).get(v);
				}
				matrix[i][U] = transitions.get(u).get(t).negate();
			}
			// Converts matrix into Reduced Row Echelon Form - O(U^3)
			solve(matrix);
			answers.add(matrix[0][U]);
		}
		
		// Format answer into common denominator fraction
		int lcm = 1;
		for (Fraction f : answers)
			lcm = lcm(lcm, f.denominator);
		int[] commonDenom = new int[T+1];
		for (int i = 0; i < T; i++)			
			commonDenom[i] = answers.get(i).numerator * (lcm / answers.get(i).denominator);
		commonDenom[answers.size()] = lcm;
		return commonDenom;
	}

	/**
	 * Solve a matrix using Gaussian-Jordan Elimination. Takes O(S^3 time) for an
	 * S*(S+1) matrix (which represents a system of S variables and S equations).
	 * Uses Gaussian matrix operations to RREF the matrix. Works in place but modifies
	 * the provided matrix.
	 */
	public static Fraction[] solve(Fraction[][] matrix) {
		// REF the matrix to make it upper triangular
		for (int i = 0; i < matrix.length; i++) {
			scaleRow(matrix, i, matrix[i][i].reciprocal());
			for (int j = i + 1; j < matrix.length; j++)
				subtractRow(matrix, j, matrix[j][i], i);
		}
		// RREF the matrix to make it an augmented identity matrix
		for (int i = matrix.length - 1; i >= 0; i--)
			for (int j = matrix.length - 1; j > i; j--)
				subtractRow(matrix, i, matrix[i][j], j);
		// Take solutions from the augmented column
		Fraction[] ans = new Fraction[matrix.length];
		for (int i = 0; i < ans.length; i++)
			ans[i] = matrix[i][matrix[i].length - 1];
		return ans;
	}

	/** Scale a row by a certain constant */
	public static void scaleRow(Fraction[][] matrix, int row, Fraction scale) {
		for (int i = 0; i < matrix[row].length; i++)
			matrix[row][i] = matrix[row][i].multiply(scale);
	}

	/** Subtract a scaled version of the source row from another row */
	public static void subtractRow(Fraction[][] matrix, int row, Fraction scale, int source) {
		for (int i = 0; i < matrix[row].length; i++)
			matrix[row][i] = matrix[row][i].subtract(matrix[source][i].multiply(scale));
	}

	private static int lcm(int a, int b) {
		return a / gcd(a, b) * b;
	}

	private static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
	
	/**
	 * Fraction class to manage arithmetic of rational numbers
	 */
	public static class Fraction {
		
		public final int numerator, denominator;

		public Fraction(int numerator, int denominator) {
			int gcd = gcd(numerator, denominator);
			if (denominator < 0 != gcd < 0)
				gcd *= -1;
			this.numerator = numerator / gcd;
			this.denominator = denominator / gcd;
		}

		public Fraction add(Fraction that) {
			return new Fraction(this.numerator * that.denominator + that.numerator * this.denominator,
					this.denominator * that.denominator);
		}

		public Fraction subtract(Fraction that) {
			return new Fraction(this.numerator * that.denominator - that.numerator * this.denominator,
					this.denominator * that.denominator);
		}

		public Fraction multiply(Fraction that) {
			return new Fraction(this.numerator * that.numerator, this.denominator * that.denominator);
		}

		public Fraction reciprocal() {
			return new Fraction(denominator, numerator);
		}
		
		public Fraction negate() {
			return new Fraction(-numerator, denominator);
		}

		public int hashCode() {
			return 563 * numerator + denominator;
		}

		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Fraction other = (Fraction) obj;
			if (numerator != other.numerator || denominator != other.denominator)
				return false;
			return true;
		}

		public String toString() {
			return String.format(denominator == 1 ? "%3d" : "%1d/%1d", numerator, denominator);
		}
	}

	public static void main(String[] args) {
		int[][][] in = {
				// Test Case 1
				{{0, 1, 0, 0, 0, 1},
				 {4, 0, 0, 3, 2, 0},
				 {0, 0, 0, 0, 0, 0},
				 {0, 0, 0, 0, 0, 0},
				 {0, 0, 0, 0, 0, 0},
				 {0, 0, 0, 0, 0, 0}},
				// Test Case 2
				{{0, 2, 1, 0, 0},
				 {0, 0, 0, 3, 4},
				 {0, 0, 0, 0, 0},
				 {0, 0, 0, 0, 0},
				 {0, 0, 0, 0, 0}},
				// Simple hand-solved case
				{{0, 1, 1},
			     {1, 0, 1},
				 {0, 0, 1}},
				// Trivial case where 0 is terminal
				{{0, 0},
				 {0, 0}}
		};
		int[][] out = {
				{0, 3, 2, 9, 14},
				{7, 6, 8, 21},
				{1, 1},
				{1, 0, 1}
		};
		for (int i = 0; i < in.length; i++)
			if (!Arrays.equals(solution(in[i]), out[i]))
				throw new RuntimeException("Test case " + i + " failed");
		System.out.println("All test cases successful");
	}

}