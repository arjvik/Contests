/*
ID: arjvik1
LANG: JAVA
TASK: B_orderup
*/
import java.io.*;
import java.util.*;

public class B_orderup {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("B.txt")));
		int n = in.nextInt();
		in.nextLine();
		while(n != 0) {
			List<String> names = new ArrayList<>();
			for(int i = 0; i < n; i++)
				names.add(in.nextLine());
			List<String> copy = new ArrayList<>(names);
			names.sort((a, b) -> {
				if(a.charAt(0) != b.charAt(0))
					return a.charAt(0) - b.charAt(0);
				if(a.charAt(1) != b.charAt(1))
					return a.charAt(1) - b.charAt(1);
				return copy.indexOf(a) - copy.indexOf(b);
			});
			
			names.forEach(System.out::println);
			
			n = in.nextInt();
			in.nextLine();
			if(n != 0)
				System.out.println();
		}
		in.close();
	}
}
