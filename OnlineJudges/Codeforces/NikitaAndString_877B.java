/*
ID: arjvik1
LANG: JAVA
TASK: NikitaAndString
*/
import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class NikitaAndString_877B {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		int n = s.length();
		int[] numA = new int[n+1];
		int[] numB = new int[n+1];
		for (int i = 1; i <= n; i++) {
			if (s.charAt(i-1) == 'a') {
				numA[i] = numA[i-1]+1;
				numB[i] = numB[i-1];
			} else {
				numA[i] = numA[i-1];
				numB[i] = numB[i-1]+1;
			}
		}
		int max = -1;
		for (int i = 0; i <= n; i++) {
			for (int j = i; j <= n; j++) {
				int i1 = numB[i]-numB[0],
					i2 = numA[j]-numA[i],
					i3 = numB[n]-numB[j],
					c  = n - (i1+i2+i3);
				if (max < c)
					max = c;
			}
		}
		System.out.println(max);
		in.close();
	}
	
	public static int count(String s, char c) {
		return s.chars().map(d -> c==d?1:0).sum();
	}
}
