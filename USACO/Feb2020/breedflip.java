import java.io.*;

/*
 * Solved after contest for various reasons
 */

public class breedflip {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("breedflip.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("breedflip.out")));
		int n = in.nextInt();
		String s1 = in.next(), s2 = in.next(), cont = "";
		for (int i = 0; i < n; i++)
			cont += s1.charAt(i) != s2.charAt(i) ? "x" : "_";
		out.println((cont.split("_+").length) + (cont.startsWith("_")?-1:0));
		out.close();
	}
}
