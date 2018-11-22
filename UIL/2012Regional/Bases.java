/*
ID: arjvik1
LANG: JAVA
TASK: Bases
*/
import java.io.*;
import java.util.*;
import static java.lang.System.out;

@SuppressWarnings("unused")
public class Bases {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("bases.dat")));
		int n = in.nextInt();
		System.out.println("Base 10 Base 2  Base 4  Base 8  Base 12 Base 16");
		for (int i = 1; i <= n; i++) {
			System.out.print(justify(Integer.toString(i, 10)));
			System.out.print(justify(Integer.toString(i, 2)));
			System.out.print(justify(Integer.toString(i, 4)));
			System.out.print(justify(Integer.toString(i, 8)));
			System.out.print(justify(Integer.toString(i, 12)));
			System.out.println(justify(Integer.toString(i, 16)));
		}
		in.close();
		out.close();
	}
	private static String justify(String s){
		if(s.length()>8)
			System.err.println("Should not happen");
		while(s.length()<8)
			s=s.concat(" ");
		return s;
	}
}
