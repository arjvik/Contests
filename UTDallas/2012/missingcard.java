/*
ID: arjvik1
LANG: JAVA
TASK: missingcard
*/
import java.io.*;
import java.util.*;

public class missingcard {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("missingcard.in")));
		String[] numbers = {"Ace","Two","Three","Four","Five","Six",
							"Seven","Eight","Nine","Ten","Jack","Queen","King"};
		String[] suits   = {"Spades","Clubs","Hearts","Diamonds"};
		Set<String> perms = new HashSet<>();
		for (String a : numbers) {
			for (String b: suits) {
				perms.add(a+" of "+b);
			}
		}
		
		while(in.hasNextLine()) {
			Set<String> s2 = new HashSet<>(perms);
			for (int i = 0; i < 51; i++) {
				s2.remove(in.nextLine());
			}
			System.out.printf("The missing card is: %s%n",s2.iterator().next());
			in.nextLine();
		}
		
		in.close();
	}
}
