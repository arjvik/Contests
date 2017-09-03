/*
ID: arjvik1
LANG: JAVA
TASK: milk3
*/
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class milk3 {
	private static int a, b, c;
	private static Set<Solution> solutions = new TreeSet<>();
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("milk3.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		a = in.nextInt();
		b = in.nextInt();
		c = in.nextInt();
		solve(0,0,c,0);
		out.println(solutions.stream()
							 .filter(solution -> solution.x==0)
							 .mapToInt(solution -> solution.z)
							 .distinct()
							 .sorted()
							 .mapToObj(Integer::toString)
							 .collect(Collectors.joining(" ")));
		in.close();
		out.close();
	}
	
	private static void solve(int x, int y, int z, int t) {
		if(!solutions.add(new Solution(x, y, z)))
			return;
		if(x>0){
			if(b-y>x)
				solve(0,x+y,z,t+1);
			else
				solve(x+y-b,b,z,t+1);
			if(c-z>x)
				solve(0,y,x+z,t+1);
			else
				solve(x+z-c,y,c,t+1);
		}
		if(y>0){
			if(a-x>y)
				solve(x+y,0,z,t+1);
			else
				solve(a,x+y-a,z,t+1);
			if(c-z>y)
				solve(x,0,y+z,t+1);
			else
				solve(x,y+z-c,c,t+1);
		}
		if(z>0){
			if(a-x>z)
				solve(x+z,y,0,t+1);
			else
				solve(a,y,x+z-a,t+1);
			if(b-y>z)
				solve(x,y+z,0,t+1);
			else
				solve(x,b,y+z-b,t+1);
		}
	}

	public static class Solution implements Comparable<Solution> {
		int x, y, z;
		public Solution() {
			
		}
		public Solution(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getZ() {
			return z;
		}
		public void setZ(int z) {
			this.z = z;
		}
		@Override
		public int hashCode() {
			final int prime = 21;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			result = prime * result + z;
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
			Solution other = (Solution) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			if (z != other.z)
				return false;
			return true;
		}
		@Override
		public int compareTo(Solution o) {
			if(z<o.z)
				return -1;
			if(z>o.z)
				return 1;
			if(y<o.y)
				return -1;
			if(y>o.y)
				return 1;
			if(x<o.x)
				return -1;
			if(x>o.x)
				return 1;
			return 0;
		}
	}
}
