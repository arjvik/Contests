/*
ID: arjvik1
LANG: JAVA
TASK: Message
*/
import java.io.*;
import java.util.*;

public class Message {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("message.dat")));
		int cases = in.nextInt();
		for (int i = 0; i < cases; i++) {
			int len = in.nextInt();
			in.nextLine();
			String s = in.nextLine();
			StringTokenizer st = new StringTokenizer(s);
			while(st.hasMoreTokens()){
				String w = st.nextToken();
				w = w.replaceAll("[.,]", "");
				if(w.length()==len)
					System.out.print(w+" ");
			}
			System.out.println();
		}
		in.close();
	}
}
