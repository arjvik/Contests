import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class RandomMood_102644A {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		double p = in.nextDouble();
		double[][] m = {{ 1-p,   p },
						{   p, 1-p }};
		double[][] e = exp(m, n);
		System.out.println(e[0][0]);
		in.close();
	}
	public static double[][] exp(double[][] m, int pow) {
		if (pow == 0)
			return new double[][]{{1, 0}, {0, 1}};
		double[][] x = exp(m, pow/2);
		double a = x[0][0],
			   b = x[0][1],
			   c = x[1][0],
			   d = x[1][1];
		double[][] x2 = {{ a*a+b*c, a*b+b*d },
						 { a*c+c*d, b*c+d*d }};
		if (pow % 2 == 0)
			return x2;
		a = x2[0][0];
		b = x2[0][1];
		c = x2[1][0];
		d = x2[1][1];
		double e = m[0][0],
			   f = m[0][1],
			   g = m[1][0],
			   h = m[1][1];
		return new double[][] {{ a*e+b*g, a*f+b*h },
							   { c*e+d*g, c*f+d*h }};
	}
}
