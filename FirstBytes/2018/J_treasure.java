/*
ID: arjvik1
LANG: JAVA
TASK: J
*/
//
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class J_treasure {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("J.txt")));
		int nc = in.nextInt();
		for(int CASEN = 0; CASEN<nc;CASEN++) {
			
			
			
			
			final int r = in.nextInt();
			final int c = in.nextInt();
			in.nextLine();
			int sr = -1,
				sc = -1,
				tr = -1,
				tc = -1;
			for(int rr = 0; rr<r; rr++) {
				char[] l =in.nextLine().toCharArray();
				for(int cc = 0; cc < c;cc++) {
					switch(l[cc]) {
					case '*':
						sr = rr;
						sc = cc;
						break;
					case 'X':
						tr = rr;
						tc = cc;
						break;
					}
				}
			}
			int ns = tr - sr;
			int ew = tc - sc;
			
			if(ns != 0) {
				if(ns > 0)
					System.out.printf("%d south ", ns);
				else
					System.out.printf("%d north ", -ns);
			}
			if(ew != 0) {
				if(ew > 0)
					System.out.printf("%d east%n", ew);
				else
					System.out.printf("%d west%n", -ew);
			} else {
				System.out.println();
			}
			
			
			
			
			
			
			
			
			
		}
		in.close();
	}
}
