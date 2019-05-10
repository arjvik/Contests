//import java.io.*;
//import java.util.*;
//import java.util.stream.*;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

@SuppressWarnings("unused")
public class nishi {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("nishi.dat"))), s = in;

		int NC = in.nextInt();
		in.nextLine();
		for (int CASE = 1; CASE <= NC; CASE++) {
			//System.out.print("Case "+CASE+": ");
			String i = in.next();
			String op = in.next();
			String j = in.next();
			double ans;
			int sigs = Math.min(sig(i), sig(j));
			if (op.equals("X")) {
				ans = Double.parseDouble(i) * Double.parseDouble(j);
			} else {
				ans = Double.parseDouble(i) / Double.parseDouble(j);
			}
			int exp = (int) Math.floor(Math.log10(ans));
			double arg = ans / Math.pow(10, exp);
			System.out.printf("%."+(sigs-1)+"fE%d%n",arg, exp);
			
			//TODO Auto-generated code

		}

		in.close();
	}
	public static int sig(String s) {
		while(s.startsWith("0"))
			s = s.substring(1);
		if (s.contains("."))
			return s.length()-1;
		return s.replaceAll("0*$", "").length();
	}
}
