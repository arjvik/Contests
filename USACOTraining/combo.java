/*
ID: arjvik1
LANG: JAVA
TASK: combo
*/
import java.io.*;
import java.util.*;

public class combo {
	private static int n;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("combo.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
		n = in.nextInt();
		int[][] presets = {
							{in.nextInt(),in.nextInt(),in.nextInt()},
							{in.nextInt(),in.nextInt(),in.nextInt()}
						  };
		Set<Combination> valid = new HashSet<>();
		for (int x = 0; x < presets.length; x++) {
			int[] preset = presets[x];
			for (int i = preset[0]-2; i <= preset[0]+2 ; i++) {
				int i1 = clamp(i);
				for (int j = preset[1]-2; j <= preset[1]+2 ; j++) {
					int j1 = clamp(j);
					for (int k = preset[2]-2; k <= preset[2]+2 ; k++) {
						int k1 = clamp(k);
						if(valid.add(new Combination(i1,j1,k1)))
							System.err.printf("Combination: %d, %d, %d (preset %d) \n",i1,j1,k1,x);
						else
							System.err.printf("Combination exists: %d, %d, %d (preset %d) \n",i1,j1,k1,x);
					}
				}
			}
			System.err.println("Switching presets");
		}
		out.println(valid.size());
		in.close();
		out.close();
	}
	private static int clamp(int x) {
		return (x<1)?clamp(x+n):((x>n)?clamp(x-n):x);
	}
	public static class Combination{
		private int a, b, c;
		public Combination() {
			
		}
		public Combination(int a, int b, int c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}
		public int getA() {
			return a;
		}
		public void setA(int a) {
			this.a = a;
		}
		public int getB() {
			return b;
		}
		public void setB(int b) {
			this.b = b;
		}
		public int getC() {
			return c;
		}
		public void setC(int c) {
			this.c = c;
		}
		@Override
		public int hashCode() {
			final int prime = 101;
			int result = 1;
			result = prime * result + a;
			result = prime * result + b;
			result = prime * result + c;
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
			Combination other = (Combination) obj;
			if (a != other.a)
				return false;
			if (b != other.b)
				return false;
			if (c != other.c)
				return false;
			return true;
		}
	}
}
