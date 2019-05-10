//import java.io.*;
//import java.util.*;
//import java.util.stream.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
@SuppressWarnings("unused")
public class gregory {
	static String freq, spc; static List<String> top10;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("gregory.dat"))), s = in;

		freq = in.nextLine().toUpperCase();
		spc = in.nextLine();
		top10 = new ArrayList<>();
		for (int i = 0; i < 10; i++)
			top10.addAll(Arrays.asList(in.nextLine().toLowerCase().split(",")));
		
		while (in.hasNextLine()) {
			String pass = in.nextLine();
			int sc = score(pass);
			String st;
			if (sc <= 0)
				st = "UNACCEPTABLE";
			else if (sc <= 15)
				st = "WEAK";
			else if (sc <= 30)
				st = "FAIR";
			else if (sc <= 45)
				st = "GOOD";
			else
				st = "STRONG";
			System.out.printf("%s:%d:%s%n", pass,sc,st);
		}

		in.close();
	}

	private static int score(String pass) {
		
		int score = 0;
		
		if(pass.length() < 8)
			return 0;
		if(top10.contains(pass.toLowerCase()))
			return 0;
		
		int cat = 0;
		if (pass.matches(".*[a-z].*")) cat++;
		if (pass.matches(".*[A-Z].*")) cat++;
		if (pass.matches(".*[0-9].*")) cat++;
		boolean b1 = false;
		for (char c : pass.toCharArray()) if ((c+"").matches("[^a-zA-Z0-9]") || (spc.contains(c+"")&&false)) {score+=3;b1=true;}
		if(b1) cat++;
		if (cat < 3) return 0;
		if (cat ==4) score += 5;
		
		score += pass.length()*2-16;
		
		int score1 = 0;
		
		score1 += (pass.length() - pass.replaceAll("[0-9]","").length())*2;
		score1 += (pass.length() - pass.toUpperCase().replaceAll("["+freq.substring(0,  13)+"]","").length())*1;
		score1 += (pass.length() - pass.toUpperCase().replaceAll("["+freq.substring(13, 26)+"]","").length())*2;
		
		
		score += score1;
		
		char[] cr = pass.toCharArray();
		for (int i = 1; i < pass.length(); i++) {
			if (cl(cr[i]) != cl(cr[i-1])) score+=2;
			if (cr[i] == cr[i-1]) score--;
		}
		for (int i = 2; i < pass.length(); i++) {
			if ((cr[i]-cr[i-1])*(cr[i-1]-cr[i-2]) == 1) score-=5;
		}
		
		return score;
		
	}
	
	public static int cl(char c) {
		if ((""+c).matches("[a-z]")) return 1;
		if ((""+c).matches("[A-Z]")) return 2;
		if ((""+c).matches("[0-9]")) return 3;
		else						 return 4;
	}
}
