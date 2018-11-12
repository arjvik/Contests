/*
ID: arjvik1
LANG: JAVA
TASK: beanchecker2
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class beanchecker {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("beanchecker.dat")));
		int NUMCASES = in.nextInt();
		in.nextLine();
		for (int CASENUM = 0; CASENUM < NUMCASES; CASENUM++) {
			String name = in.next();
			while(!in.hasNextInt()) {
				name += " "+in.next();
			}
			int w = in.nextInt();
			if(name.toLowerCase().contains("e") && w%2 ==1)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
		in.close();
	}
}
