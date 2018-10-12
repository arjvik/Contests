/*
ID: arjvik1
LANG: JAVA
TASK: Deexpansion
*/
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;

@SuppressWarnings("unused")
public class Deexpansion {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("deexpansion.dat")));
		String bits = in.nextLine();
	    SortedMap<String,Charset> map = Charset.availableCharsets();
	    for (Entry<String, Charset> e : map.entrySet()) {
	    	String s = new String(
				    new BigInteger(bits, 2).toByteArray(),
				    e.getValue()
				);
	    	System.out.println(e.getKey());
			System.out.println(s);
		}
		in.close();
	}
}
