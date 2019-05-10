
//import java.io.*;
//import java.util.*;
//import java.util.stream.*;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

@SuppressWarnings("unused")
public class callen {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("callen.dat"))), sxxx = in;

		while (in.hasNextLine()) {
			Scanner s = new Scanner(in.nextLine());
			int a1 = s.nextInt(), b1 = s.nextInt(), c = -Math.abs(s.nextInt()), a = Math.max(a1, b1),
					b = Math.min(a1, b1);
			int x1 = s.nextInt(), y1 = s.nextInt(), z = Math.abs(s.nextInt()), x = Math.min(x1, y1),
					y = Math.max(x1, y1);

			System.out.print("       Wind Speeds\n  Temps");
			for (int i = x; i <= y; i += z)
				System.out.printf("%7d", i);
			System.out.println();
			for (int j = a; j >= b; j += c) {
				System.out.printf("%7d", j);
				for (int i = x; i <= y; i += z)
					System.out.printf("%7.1f", f(j, i));
				System.out.println();
			}
			System.out.println("*************************");
		}

		in.close();
	}

	private static double f(int j, int i) {
		double t = j, s = Math.pow(i, .16);
		return 35.74 + 0.6215 * t - 35.75 * s + 0.4275 * t * s;
	}
}
