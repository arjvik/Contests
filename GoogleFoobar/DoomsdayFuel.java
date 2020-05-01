import java.util.*;

public class DoomsdayFuel {

	/**
	 * 
	 * O(n^6) algorithm to analytically solve a Markov Model.
	 * 
	 * I first define n^2 variables, where variable # ni+j represents the
	 * probability P(i, j) of starting from state i and eventually ending in state j
	 * eventually.
	 * 
	 * These variables are related by the following equation:
	 * 
	 * P(a, b) = Sum_{c} m[a][c] * P(c, b)
	 * 
	 */
	public static int[] solution(int[][] m) {
		final int n = m.length;
		Fraction[][] probabilities = new Fraction[n][n];
		for (int i = 0; i < n; i++) {
			int total = 0;
			for (int j = 0; j < n; j++)
				if (i != j)
					total += m[i][j];
			if (total == 0)
				// terminal state always transitions to itself
				m[i][i] = total = 1;
			else
				// we can ignore self transition otherwise
				// because it ends up collapsing to zero (geometric series)
				m[i][i] = 0;
			for (int j = 0; j < n; j++)
				probabilities[i][j] = new Fraction(m[i][j], total);
		}

		int numVars = n * n;
		Fraction[][] matrix = new Fraction[numVars][numVars + 1];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				for (int z = 0; z < numVars+1; z++)
					matrix[n * i + j][z] = Fraction.ZERO;
				for (int k = 0; k < n; k++)
					matrix[n * i + j][n*k+j] = probabilities[i][k];
				matrix[n * i + j][n * i + j] = Fraction.MINUS_ONE;
			}
		
//		System.out.println(Arrays.deepToString(matrix));
		print(matrix);
		
		Fraction[] solution = solve(matrix);
		System.out.println(Arrays.toString(solution));
		return null;
	}

	/**
	 * Solve a matrix using Gaussian-Jordan Elimination. Takes O(S^3 time) for an
	 * S * S matrix (which represents a system of S variables and S equations).
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
	
	private static void print(Fraction[][] matrix) {
		System.out.println("[");
		for (Fraction[] row : matrix)
			System.out.println("\t"+java.util.Arrays.toString(row));
		System.out.println("]");
	}

	/**
	 * Fraction class to manage arithmetic of rational numbers
	 */
	public static class Fraction {
		
		public static final Fraction ZERO = new Fraction(0, 1),
									 MINUS_ONE = new Fraction(-1, 1);
		
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

		public int hashCode() {
			final int prime = 31;
			int result = 1;
			return prime * numerator + denominator;
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
		solution(new int[][]{{0, 2, 1, 0, 0}, {0, 0, 0, 3, 4}, {0, 0, 0, 0, 0}, {0, 0, 0, 0,0}, {0, 0, 0, 0, 0}});
//		solution(new int[][] {{0,1,1},{1,0,1},{0,0,1}});
	}

}