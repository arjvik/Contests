/*
ID: arjvik1
LANG: JAVA
TASK: Etch
*/
import java.io.*;
import java.util.*;

public class Etch {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("etch.dat")));
		int numcases = in.nextInt();
		for (int i = 0; i < numcases; i++) {
			int r = in.nextInt()-1;
			int c = in.nextInt()-1;
			in.nextLine();
			boolean[][] b = new boolean[15][25];
			b[r][c] = true;
			String line = in.nextLine();
			StringTokenizer st = new StringTokenizer(line);
			while(st.hasMoreTokens()){
				String t = st.nextToken();
				char d = t.charAt(0);
				int n = Integer.parseInt(t.substring(1));
				if(d=='U'){
					loop:for (int j = 0; j < n; j++) {
						r--;
						if(r<0){
							r++;
							break loop;
						}
						b[r][c] = true;
					}
				}else if(d=='D'){
					loop:for (int j = 0; j < n; j++) {
						r++;
						if(r>14){
							r--;
							break loop;
						}
						b[r][c] = true;
					}
				}else if(d=='L'){
					loop:for (int j = 0; j < n; j++) {
						c--;
						if(c<0){
							c++;
							break loop;
						}
						b[r][c] = true;
					}
				}else if(d=='R'){
					loop:for (int j = 0; j < n; j++) {
						c++;
						if(c>24){
							c--;
							break loop;
						}
						b[r][c] = true;
					}
				}else{
					System.err.println("?");
				}
			}
			print(b);
		}
		in.close();
	}

	private static void print(boolean[][] b) {
		for (boolean[] cs : b) {
			for (boolean c : cs) {
				System.out.print(c?"*":".");
			}
			System.out.println();
		}
	}
}
