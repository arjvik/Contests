
import java.util.*;
import java.io.*;

@SuppressWarnings("unused")
public class findingawayout {

	public static void main(String[] args) throws Throwable{
		Scanner in = new Scanner(System.in);
		int NUM_CASES = in.nextInt();
		for (int i = 0; i < NUM_CASES; i++) {
			int r = in.nextInt(),
				c = in.nextInt(),
				n = 0;
			in.nextLine();
			if(r > 2 && c > 2) {
				char[][] a = new char[r][c];
				for (int j = 0; j < r; j++)
					a[j] = in.nextLine().replaceAll(" ", "").toCharArray();
				for(int x = 1; x < r-1; x++)
					for(int y = 1; y < c-1; y++)
						if(
							f(a[x][y-1]) &&
							f(a[x][y+1]) &&
							f(a[x-1][y]) &&
							f(a[x+1][y]) &&
							!f(a[x][y]))
							n++;
				if(n == 0)
					System.out.print("No Portals Found");
				else
					System.out.printf("%d Portals Found", n);
			} else
				System.out.print("No Portals Found");
			if(i+1 != NUM_CASES)
				System.out.println();
		}
	}
	
	private static boolean f(char c) {
		return c == '*';
	}

}
