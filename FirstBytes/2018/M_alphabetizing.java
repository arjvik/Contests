/*
ID: arjvik1
LANG: JAVA
TASK: M
*/
//
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class M_alphabetizing {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("M.txt")));
		TreeMap<String, String> map = new TreeMap<>();
		final int nc = in.nextInt();
		in.nextLine();
		for(int cn = 0; cn < nc; cn++) {
			String s = in.nextLine();
			String t = s.toLowerCase()
						.replaceAll("-","")
						.replaceAll("'","")
						.replaceAll("\\.","");
			if(t.startsWith("a "))
				t=t.replace("a ", "");
			else if(t.startsWith("an "))
				t=t.replace("an ", "");
			else if(t.startsWith("the "))
				t=t.replace("the ", "");
			map.put(t, s);
//			System.out.printf("(%s, %s)%n", t, s);
		}
//		System.out.println("------------------");
		in.close();
		for(String k : map.keySet()) {
			System.out.println(map.get(k));
		}
	}
}
