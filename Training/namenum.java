/*
ID: arjvik1
LANG: JAVA
TASK: namenum
*/
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("unused")
public class namenum {
	private static final char[] num = {'2','2','2','3','3','3','4','4','4','5','5','5','6','6','6','7','0','7','7','8','8','8','9','9','9','0'};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("namenum.in"));
		String serial = in.readLine();
		BufferedReader dictin = new BufferedReader(new FileReader("dict.txt"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
		AtomicInteger i = new AtomicInteger(0);
		dictin.lines()
				.filter((s)->serial.equals(getSerialNumerAsString(s)))
				.peek((s)->i.incrementAndGet())
				.forEachOrdered(out::println)
				;
		if(i.get()==0)
			out.println("NONE");
		dictin.close();
		in.close();
		out.close();
	}
	private static String getSerialNumerAsString(String s){
		StringBuilder serialNumber = new StringBuilder();
		char[] charArray = s.toUpperCase().toCharArray();
		for (char c : charArray) {
			serialNumber.append(Character.toString(num[c-'A']));
		}
		return serialNumber.toString();
	}
}
