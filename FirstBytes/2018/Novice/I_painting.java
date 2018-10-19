/*
ID: arjvik1
LANG: JAVA
TASK: I
*/
//
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class I_painting {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("I.txt")));
		int num = in.nextInt();
		in.nextLine();
		for(int i = 0;i<num;i++) {
			String c = in.nextLine();
			double d = 0;
			while(in.hasNextInt()) {
				d += in.nextInt();
			}
			in.nextLine();
			d /= 12;
			d *= 10;
			d /= 400;
			System.out.printf("%d gallon(s) of %s%n", (int) Math.ceil(d), c);
		}
		in.close();
	}
}
