
import java.util.*;
import java.io.*;

@SuppressWarnings("unused")
public class I_gnomes {

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new FileReader("I.txt")));
		int n = in.nextInt();
		System.out.println("Gnomes:");
		for(int i = 0; i < n; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			if(a <= b && b <= c)
				y();
			else if(a >= b && b >= c)
				y();
			else
				n();
		}
	}

	private static void n() {
		System.out.println("Unordered");
	}

	private static void y() {
		System.out.println("Ordered");
	}

}
