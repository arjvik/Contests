/*
ID: arjvik1
LANG: JAVA
TASK: H
*/
//
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class H_digraphs {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("H.txt")));
		int n = in.nextInt();
		in.nextLine();
		for(int i =0;i<n;i++) {
			String s = in.nextLine().replaceAll("sc", "__")
									.replaceAll("ng", "__")
									.replaceAll("ck", "__")
									.replaceAll("ph", "__")
									.replaceAll("sh", "__")
									.replaceAll("th", "__")
									.replaceAll("wh", "__")
									.replaceAll("qu", "__")
									.replaceAll("SC", "__")
									.replaceAll("NG", "__")
									.replaceAll("CK", "__")
									.replaceAll("PH", "__")
									.replaceAll("SH", "__")
									.replaceAll("TH", "__")
									.replaceAll("WH", "__")
									.replaceAll("QU", "__")
									.replaceAll("sC", "__")
									.replaceAll("nG", "__")
									.replaceAll("cK", "__")
									.replaceAll("pH", "__")
									.replaceAll("sH", "__")
									.replaceAll("tH", "__")
									.replaceAll("wH", "__")
									.replaceAll("qU", "__")
									.replaceAll("Sc", "__")
									.replaceAll("Ng", "__")
									.replaceAll("Ck", "__")
									.replaceAll("Ph", "__")
									.replaceAll("Sh", "__")
									.replaceAll("Th", "__")
									.replaceAll("Wh", "__")
									.replaceAll("Qu", "__");
			System.out.println(s);
		}
		in.close();
	}
}
