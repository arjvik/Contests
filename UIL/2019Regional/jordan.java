//import java.io.*;
//import java.util.*;
//import java.util.stream.*;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;


import java.math.*;

@SuppressWarnings("unused")
public class jordan {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("jordan.dat"))), s = in;

		int NC = in.nextInt();
		for (int CASE = 1; CASE <= NC; CASE++) {
			BigInteger a = new BigInteger(in.next(), 17), b = new BigInteger(in.next(), 17),
					x = new BigInteger(in.next(), 17);
			loop:for (int i = 2;;i++) {
				if (b.equals(x)) {
					System.out.println(i); 
					break loop;
				} else if (b.compareTo(x) > 0) {
					System.out.println(a.toString(17).toUpperCase() + " " + b.toString(17).toUpperCase());
					break loop;
				}
				BigInteger c = a.add(b);
				a = b;
				b = c;
			}
		}

		in.close();
	}
}
