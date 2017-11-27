/*
ID: arjvik1
LANG: JAVA
TASK: Superhero
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class Superhero {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("superhero.dat")));
		while (in.hasNextLine()) {
			String line = in.nextLine();
			int padding = line.length() - (line.trim().length());
			String paddingSpace = getPadding(padding);
			System.out.println(new StringBuffer().append(line).append(paddingSpace).reverse().toString());
		}
		in.close();
	}

	private static String getPadding(int padding) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < padding; i++) {
			sb.append(' ');
		}
		return sb.toString();
	}
}
