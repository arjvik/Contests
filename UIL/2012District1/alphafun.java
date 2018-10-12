/*
ID: arjvik1
LANG: JAVA
TASK: alphafun
*/
import java.io.*;
import java.util.*;

public class alphafun {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("alphafun.dat")));
		List<String> words = new ArrayList<String>();
		while(in.hasNextLine()){
			String w = in.nextLine();
			if(w.equals("EOF"))
				break;
			words.add(w);
		}
		Comparator<String> c = ((a,b) -> {
			int i = a.charAt(1) - b.charAt(1);
			if(i!=0)
				return i;
			char p,q;
			if (a.length() < 4 && b.length() < 4) {
				p = ' '; q = ' ';
			} else if (a.length() < 4 && b.length() >= 4) {
				p = ' '; q = b.charAt(3);
			} else if (a.length() >= 4 && b.length() < 4) {
				p = a.charAt(3); q = ' ';
			} else {
				p = a.charAt(3); q = b.charAt(3);
			}
			i = p-q;
			if(i!=0)
				return i;
			i = a.charAt(a.length()-1) - b.charAt(b.length()-1);
			if(i!=0)
				return i;
			i = a.charAt(0) - b.charAt(0);
			if(i!=0)
				return i;
			return a.compareTo(b);
		});
		words.sort(c);
		words.forEach(System.out::println);
//		System.out.println(c.compare("BREAD","BROAD"));
		in.close();
	}
}
