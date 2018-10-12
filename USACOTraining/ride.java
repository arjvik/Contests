/*
ID: arjvik1
LANG: JAVA
TASK: ride
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class ride {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("ride.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
		char[] s1=in.nextLine().toUpperCase().toCharArray(),
				s2=in.nextLine().toUpperCase().toCharArray();
		int x='A'-1;
		int i1=1,
				i2=1;
		for (char c : s1)
			i1 *= c-x;
		for (char c : s2)
			i2 *= c-x;
		out.println( ((i1%47) == (i2%47)) ? "GO" : "STAY" );
		in.close();
		out.close();
	}
	
}
