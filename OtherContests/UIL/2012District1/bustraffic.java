/*
ID: arjvik1
LANG: JAVA
TASK: bustraffic
*/
import java.io.*;
import java.util.*;

public class bustraffic {
	public static void main(String[] args) throws IOException {
		Scanner inn = new Scanner(new BufferedReader(new FileReader("bustraffic.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bustraffic.out")));
		int NUM_CASES = inn.nextInt();
		inn.nextLine();
		for (int CASE_NUM = 0; CASE_NUM < NUM_CASES; CASE_NUM++) {
			String line = inn.nextLine();
			java.util.Scanner in = new java.util.Scanner(line);
			List<Integer> pass = new ArrayList<>();
			pass.add(in.nextInt());
			int i = 0;
			while(in.hasNextInt()) {
				pass.add(pass.get(i)+in.nextInt()+in.nextInt());
				i++;
			}
			List<Integer> seg = null;
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < pass.size(); j++) {
				if(pass.get(j) == min) {
					seg.add(j);
				} else if(pass.get(j) < min) {
					seg = new ArrayList<>();
					seg.add(j);
					min = pass.get(j);
				}
			}
			seg.forEach(c -> System.out.print((char)('A'+c)+" "));
			System.out.println(min);
		}
		inn.close();
		out.close();
	}
}
