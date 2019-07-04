/*
ID: arjvik1
LANG: JAVA
TASK: TheWayToHome
*/
import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class TheWayToHome_910A {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(),
			d = in.nextInt();
		in.nextLine();
		boolean[] lily = new boolean[n+1];
		int k = 1;
		for (char c : in.nextLine().toCharArray())
			lily[k++] = c == '1';
		
		int x = 1;
		int j = 0;
		loop:while (x != n) {
			j++;
			for (int i = Math.min(x + d, n); i > x; i--) {
				if (lily[i]) {
					x = i;
					continue loop;
				}
			}
			j = -1;
			x = n;
		}
		System.out.println(j);
		
		
		in.close();
	}
}
