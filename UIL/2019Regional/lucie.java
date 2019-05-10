//import java.io.*;
//import java.util.*;
//import java.util.stream.*;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;


@SuppressWarnings("unused")
public class lucie {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("lucie.dat")));
		in.useDelimiter("[\\s,]+");
		int NC = in.nextInt();
		for (int CASE = 1; CASE <= NC; CASE++) {
			//System.out.print("Case "+CASE+": ");
			int r = in.nextInt(), s = in.nextInt();
			int[][] m = new int[r][s];
			for (int i = 0; i < r; i++)
				for (int j = 0; j < s; j++)
					m[i][j] = in.nextInt();
			int[] total = new int[s];
			for (int j = 0; j < s; j++) {
				int sum = 0;
				for (int i = 0; i < r; i++)
					sum += m[i][j];
				total[j] = sum;
			}
			int[] total2 = new int[r];
			for (int i = 0; i < r; i++) {
				int sum = 0;
				for (int j = 0; j < s; j++)
					sum += m[i][j];
				total2[i] = sum;
			}
			for (int j = 0; j < s; j++) {
				for (int i = 0; i < r; i++)
					System.out.print(m[i][j]+",");
				System.out.println(total[j]);
			}
			for (int i = 0; i < r; i++) {
				System.out.print(total2[i]);
				if (i != r-1)
					System.out.print(",");
			}
			System.out.println("\n"
					+ "************");
			//TODO Auto-generated code

		}

		in.close();
	}
}
