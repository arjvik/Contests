/*
ID: arjvik1
LANG: JAVA
TASK: Apartments
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class Apartments {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("apartments.dat")));
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			int m = in.nextInt();
			int occupied = 0, unoccupied = 0;
			double sum = 0;
			for (int j = 0; j < m; j++) {
				in.nextInt();
				int k = in.nextInt();
				if(k!=0){
					sum += k;
					occupied++;
				}else{
					unoccupied++;
				}
			}
			System.out.printf("%s %d%n",((sum/occupied)>4d) ? "NO" : "YES" , unoccupied);
		}
		in.close();
	}
}
