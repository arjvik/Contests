/*
ID: arjvik1
LANG: JAVA
TASK: beandoodle
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class beandoodle {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("beandoodle.dat")));
		int NUMCASES = in.nextInt();
		for (int CASENUM = 0; CASENUM < NUMCASES; CASENUM++) {
			int size = in.nextInt();
			int a = 0;
			int x = 4*(size-1) +1;
			while(x > 1) {
				rp("# ", a);
				rp("#",x);
				rp(" #",a);
				System.out.println();
				rp("# ",a);
				rp("#",1);
				rp(" ",x-2);
				rp("#",1);
				rp(" #",a);
				System.out.println();
				x-=4; a++;
			}
			x+=4;
			a--;
			rp("# ",a+1);
			rp("#",1);
			rp(" #",a+1);
			System.out.println();
			while(x <= 4*(size-1) +1) {
				rp("# ",a);
				rp("#",1);
				rp(" ",x-2);
				rp("#",1);
				rp(" #",a);
				System.out.println();
				rp("# ", a);
				rp("#",x);
				rp(" #",a);
				System.out.println();
				x+=4; a--;
			}
			if (CASENUM + 1 != NUMCASES)
				System.out.println();
		}
		in.close();
	}
	public static void rp(String s, int i) {
		for (int j = 0; j<i;j++) {
			System.out.print(s);
		}
	}
}
