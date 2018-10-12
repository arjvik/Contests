/*
ID: arjvik1
LANG: JAVA
TASK: CDs
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class CDs {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("cds.dat")));
		int parties = in.nextInt();
		for (int i = 0; i < parties; i++) {
			int songs = in.nextInt();
			int count = 1;
			int currentTime = 0;
			for (int j = 0; j < songs; j++) {
				int sec = 60*in.nextInt() + in.nextInt();
				currentTime+=sec;
				if(currentTime > 20*60){
					count++;
					currentTime = sec;
				}
			}
			System.out.printf("PARTY #%d: %d%n",i+1,count);
		}
		in.close();
	}
}
