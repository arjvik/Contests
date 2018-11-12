/*
ID: arjvik1
LANG: JAVA
TASK: beanparser
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class beanparser {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("beanparser.dat")));
		int nc = in.nextInt();
		in.nextLine();
		for(int cn = 0; cn < nc; cn++) {
			String s = in.nextLine().toLowerCase();
			System.out.println(s.split("bean").length-1 + (s.endsWith("bean")?1:0));
		}
		//TODO Auto-generated code
		in.close();
	}
}
