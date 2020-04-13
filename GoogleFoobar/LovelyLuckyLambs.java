public class LovelyLuckyLambs {
	public static void main(String[] args) {
		int[] in = { 143, 10 };
		int[] out = { 3, 1 };
		for (int i = 0; i < in.length; i++)
			if (solution(in[i]) != out[i])
				throw new RuntimeException("Test case " + i + " failed");
		System.out.println("All test cases successful");
	}

	/**
	 * # TODO: CURRENTLY NOT WORKING!
	 * 
	 * Cool O(1) solution using math! I'm proud of this!
	 *
	 * The problem boils down to this:
	 * 
	 * Given a sequence a[i] such that a[0]=0 and a[1]=1,
	 * a[i-2] + a[i-1] <= a[i] <= 2*a[i]
	 * 
	 * We are to find the maximum value of n such that
	 * Sum_{i = 1..n} a[i] <= LAMBS,
	 * where LAMBS is given as input, in the following two scenarios:
	 * 1. You are as stingy as possible, and a[i]=a[i-2]+a[i-1] (fibbonacci sequence)
	 * 2. You are as generous as possible, and a[i]=min(2*a[i], remaining_lambs) (exponential sequence)
	 * 
	 * See the comments on stingy() and generous() to see my O(1) algorithm.
	 * 
	 */
	public static int solution(int lambs) {
		return stingy(lambs) - generous(lambs);
		// oneliner version (for fun):
		// return (int) Math.floor(Math.log((lambs+1)*Math.sqrt(5)+.5)/Math.log((Math.sqrt(5)+1)/2))-2 - ((int) Math.floor(Math.log(lambs+1)/Math.log(2)) + (lambs + 1 -(int) Math.pow(2, (int) Math.floor(Math.log(lambs+1)/Math.log(2))) >= (int) (3*Math.pow(2, (int) Math.floor(Math.log(lambs+1)/Math.log(2))-2)) ? 1 : 0));
	}

	public static int generous(int lambs) {
		if (lambs == 1) return 1;
		if (lambs == 2) return 2;
		// The first n henchmen are paid 1, 2, 4, ..., 2^(n-1) lambs respectively
		// This sums to a total of (2^n)-1 lambs for the henchmen paid in maximum
		// This stores the greatest value of n for which all henchmen can be paid in full
		// This is equal to the largest power of two less than or equal to lambs+1
		int n = (int) Math.floor(Math.log(lambs+1)/Math.log(2));
		// leftover stores the amount of lambs remaining after paying n lambs in full
		int leftover = lambs + 1 -(int) Math.pow(2, n);
		// minimumLeftover calculates the minimum amount of lambs which can be paid
		// to the last henchman so he doesn't revolt
		// This is equal to 2^(n-2) + 2^(n-1) = 3*2^(n-2) 
		int minimumLeftover = (int) (3*Math.pow(2, n-2));
		// If leftover is more than the minimum, we can pay an additional henchman
		// We add this to the number of henchmen we already have
		return n + (leftover >= minimumLeftover ? 1 : 0);
	}
	
	public static int stingy(int lambs) {
		if (lambs == 1) return 1;
		if (lambs == 2) return 2;
		// Constant phi (golden ratio, equal to lim_{n -> infinity} F_n / F_(n-1)
		final double phi = (Math.sqrt(5)+1)/2;
		// Note that the henchmen are being payed in the order of the Fibbonacci sequence
		// (F_0 = 0, F_1 = 1, F_n = F_(n-2) + F_(n-1) for all n>1)
		// We are trying to find the largest n such that
		// Sum_{i=1..n} F_i <= lambs
		// We can derive (and prove via induction) that this sum is equal to F_(i+2)-1
		// We know that F_n = floor((phi^n)/(sqrt(5)) + .5) (derived from the closed-form formula)
		// We can reverse this to find the inverse function I(f) = floor(log_phi(f*sqrt(5) + .5))
		// Interestingly, this gives us the index of the largest Fibbonacci number less than f
		int inverse = (int) Math.floor(Math.log((lambs+1)*Math.sqrt(5)+.5)/Math.log(phi));
		// Thus, our answer is I(lambs+1)-2
		return inverse-2;
	}
	
	/**
	 * Brute force attempt because my solution just won't work...
	 */
	public static int solutionBF(int n) {
		return stingyBF(n) - generousBF(n);
	}
	
	public static int generousBF(int lambs) {
		if (lambs == 1) return 1;
		if (lambs == 2) return 2;
		int n = 0, i = 0;
		while (n + Math.pow(2, i) <= lambs)
			n += Math.pow(2, i++);
		if (lambs - n >= 3*(int)(Math.pow(2, i-2)))
			i++;
		return i;
	}
	
	public static int stingyBF(int lambs) {
		if (lambs == 1) return 1;
		if (lambs == 2) return 2;
		int f1 = 1, f2 = 1, s = 2, i = 2;
		while (s + f1 + f2 <= lambs) {
			int f3 = f1 + f2;
			s += f3;
			f1 = f2;
			f2 = f3;
			i++;
		}
		return i;
	}
	
}
