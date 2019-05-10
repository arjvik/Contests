import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class belle {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("belle.dat"))), s = in;

		int NC = in.nextInt();
		for (int CASE = 1; CASE <= NC; CASE++) {
			//System.out.print("Case "+CASE+": ");
			System.out.println(Math.max(in.nextInt(), in.nextInt()));
			//TODO Auto-generated code

		}

		in.close();
	}
}
