
import java.util.*;
import java.io.*;

@SuppressWarnings("unused")
public class F_parity {
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new FileReader("F.txt")));
		String line;
		while(!(line = in.nextLine()).equals("#")) {
			String k = line.substring(0, line.length()-1);
			System.out.print(k);
			int i = Integer.parseInt(k, 3);
			char c = line.substring(line.length()-1).toCharArray()[0];
			System.out.println((i%2 == 0) ^ (c == 'o') ? 0 : 1);
		}
	}
}
