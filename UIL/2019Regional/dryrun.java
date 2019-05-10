import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class dryrun {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("dryrun.dat"))), s = in;

		int NC = in.nextInt();
		in.nextLine();
		for (int CASE = 1; CASE <= NC; CASE++) {
//			System.out.print("Case "+CASE+": ");
			System.out.println("I like " + in.nextLine() + ".");
		}

		in.close();
	}
}
