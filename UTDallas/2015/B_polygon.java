/*
ID: arjvik1
LANG: JAVA
TASK: B_polygon
*/
import java.io.*;
import java.math.BigInteger;
import java.util.*;

@SuppressWarnings("unused")
public class B_polygon {
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("B.txt")));
		while(in.hasNextDouble())
			run(in.nextDouble(), in.nextDouble(),
				in.nextDouble(), in.nextDouble(),
				in.nextDouble(), in.nextDouble());
		in.close();
	}

	private static void run(double x1, double y1, double x2, double y2,	double x3, double y3) {
		String[] s = cent(x1, y1, x2, y2, x3, y3).split(" ");
		double xc = Double.parseDouble(s[0]);
		double yc = Double.parseDouble(s[1]);
		double a1 = ang(x1, y1, xc, yc, x2, y2);
		double a2 = ang(x2, y2, xc, yc, x3, y3);
		double a3 = ang(x3, y3, xc, yc, x1, y1);
		double a = gcf(a1, a2, a3, 2*Math.PI);
		System.out.println(360/a);
	}

	private static double gcf(double a1, double a2, double a3, double d) {
		return BigInteger.valueOf((long) (1e6*a1)).gcd(
				BigInteger.valueOf((long) (1e6*a2))).gcd(
				BigInteger.valueOf((long) (1e6*a3))).gcd(
				BigInteger.valueOf((long) (1e6*d ))).intValue()/1.0e6;
	}

	private static double ang(double x1, double y1, double xc, double yc, double x2, double y2) {
		double s1 = (y1-yc)/(x1-xc);
		double s2 = (y2-yc)/(x2-xc);
		double a = Math.atan(s1) - Math.atan(s2);
		return Math.abs(a);
	}

	private static String cent(double x1, double y1, double x2, double y2, double x3, double y3) {
//		double x = (
//				(x1*x1 - x2*x2)/(2*(y2-y1)) 
//				+((y3-y1)/2)
//				-(x2*x2-x3*x3)/(2*(y3-y2))
//			);
//		y = (x * (-x1 + x2))/(y1 - y2) + (x1*x1 - x2*x2 + y1*y1 - y2*y2)/(2 * y1 - 2 * y2)
//		return x+" "+y;

		/*********************/
		/*********************/
		/*** PLEASE FIX ME ***/
		/*** FILL IN CODE  ***/
		/*** FOR CENTROID  ***/
		/*********************/
		/*********************/
		
		return null;
	}
}
