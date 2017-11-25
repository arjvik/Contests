/*
ID: arjvik1
LANG: JAVA
TASK: Remnants
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class Remnants {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("remnants.dat")));
		while(in.hasNextInt()){
			System.out.println((int) Math.ceil(
					(3*in.nextInt()) / 14d
			));
		}
		in.close();
	}
}
