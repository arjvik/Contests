/*
ID: arjvik1
LANG: JAVA
TASK: beanmanager
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class beanmanager {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("beanmanager.dat")));
		int nc = in.nextInt();
		for(int cn = 0; cn < nc; cn++) {
			int n = in.nextInt();
			List<String> ls = new ArrayList<>();
			for(int i = 0; i < n; i++) {
			String a = in.next(),
					b = in.next(),
					c = in.next().substring(1),
					d = in.next();
			ls.add(String.format("%s %s %s $%04.2f", a,b,d,Double.parseDouble(c)));
			}
			ls.sort(null);
			ls.forEach(System.out::println);
			if (cn + 1 == nc)
				System.out.println();
		}
		in.close();
	}
}
