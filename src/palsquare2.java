/*
ID: arjvik1
LANG: JAVA
TASK: palsquare
*/
import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

@SuppressWarnings("unused")
public class palsquare2 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("palsquare.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
		int B = in.nextInt();
		IntStream.rangeClosed(1, 300)
				.filter(i->{
					String square = Integer.toString(i*i,B);
					return new StringBuilder(square).reverse().toString().equals(square);
				})
				.forEachOrdered(i->out.println(Integer.toString(i,B).toUpperCase()+" "+Integer.toString(i*i,B).toUpperCase()));in.close();
		out.close();
	}
	
}
