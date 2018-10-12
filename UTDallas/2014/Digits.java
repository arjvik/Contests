/*
ID: arjvik1
LANG: JAVA
TASK: Digits
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class Digits {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("Digits.in")));
		loop:while(true){
			int i = in.nextInt();
			if(i==0)
				break loop;
			run(i);
		}
		in.close();
	}

	private static void run(int i) {
		if (i<10){
			System.out.println(i);
			return;
		}
		System.out.print(i+" ");
		int p = 1;
		while(i>0){
			p *= i % 10;
			i /= 10;
		}
		run(p);
	}
}
