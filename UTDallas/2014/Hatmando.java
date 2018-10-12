/*
ID: arjvik1
LANG: JAVA
TASK: Hatmando
*/
import java.io.*;
import java.util.*;

public class Hatmando {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("Hatmando.in")));
		int NUM = in.nextInt();
		for (int i = 0; i < NUM; i++) {
			run(in);
		}
		in.close();
	}

	private static void run(Scanner in) {
		int r = in.nextInt(),
			c = in.nextInt(),
			b = in.nextInt();
		int[][] n = new int[r][c];
		Set<Pair> blocks = new HashSet<>();
		for (int i = 0; i < b; i++) {
			String[] s = in.next().split(":");
			blocks.add(new Pair(s[0].charAt(0)-'A',Integer.parseInt(s[1])-1));
		}
		for (int i = r-1; i >= 0; i--) {
			for (int j = c-1; j >= 0; j--) {
				if(blocks.contains(new Pair(i, j))){
					n[i][j] = 0;
				} else if (i==r-1 && j==c-1){
					n[i][j] = 1;
				} else {
					int s = 0;
					try {
						s+= n[i][j+1];
					} catch (ArrayIndexOutOfBoundsException ignored){}
					try {
						s+= n[i+1][j];
					} catch (ArrayIndexOutOfBoundsException ignored){}
					n[i][j] = s;
				}
			}
		}
		System.out.println(n[0][0]);
	}
	
	private static class Pair {
		int r;
		int c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}
		
	}
}
