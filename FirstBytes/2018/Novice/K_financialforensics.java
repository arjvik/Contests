/*
ID: arjvik1
LANG: JAVA
TASK: K_financialforensics
*/
//
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class K_financialforensics {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("K.txt")));
		final int numCases = in.nextInt();
		in.nextLine();
		for(int cnum = 0; cnum < numCases; cnum++) {
			String name = in.nextLine();
			double one = 0;
			double nine = 0;
			int count = 0;
			while(in.hasNextDouble()) {
				int l = lead((int)in.nextDouble());
				if(l==9)
					nine++;
				else if(l==1)
					one++;
				count++;
			}
			if(count == 0) {
				System.out.printf("%s MEETS BENFORD'S LAW.%n", name);
			} else if (one / count < .25) {
				System.out.printf("%s MUST BE INVESTIGATED FURTHER.%n", name);
			} else if(nine / count > 0.1) {
				System.out.printf("%s MUST BE INVESTIGATED FURTHER.%n", name);
			} else {
				System.out.printf("%s MEETS BENFORD'S LAW.%n", name);
			}
			in.nextLine();
		}
		in.close();
	}
	
	public static int lead(int i) {
		return i / ((int) Math.pow(10,(int) Math.log10(i)));
	}
}