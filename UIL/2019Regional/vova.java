//import java.io.*;
//import java.util.*;
//import java.util.stream.*;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

@SuppressWarnings("unused")
public class vova {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("vova.dat"))), s = in;

		int NC = in.nextInt();
		in.nextLine();
		for (int CASE = 1; CASE <= NC; CASE++) {
			long k = in.nextLong() - in.nextLong() + in.nextLong() - in.nextLong() - in.nextLong();
			System.out.println("Case #"+CASE+": "+(k%2l==0l ? "YES" : "NO"));
			//Unfortunately, this did not work, despite being sound in theory. We likely missed an edge case.
		}

		in.close();
	}
}
