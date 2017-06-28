/*
ID: arjvik1
LANG: JAVA
TASK: barn1
*/
import java.io.*;
import java.util.*;
/*
4 50 18
3
4
6
8
14
15
16
17
21
25
26
27
30
31
40
41
42
43
 */
@SuppressWarnings("unused")
public class barn1 {
	static boolean[] isOccupied,
					 boards;
	public static void main(String[] args) throws IOException {
		Scanner in =new Scanner(new BufferedReader(new FileReader("barn1.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
		int m = in.nextInt(),
			s = in.nextInt(),
			c = in.nextInt();
		isOccupied = new boolean[s];
		boards = new boolean[s];
		for (int i = 0; i < c; i++) {
			isOccupied[in.nextInt()] = true;
		}
		int firstCow = -1, lastCow=0;
		for (int i = 0; i < s; i++) {
			if(isOccupied[i]){
				if(firstCow==-1)
					firstCow = i;
				lastCow = i;
			}
		}
		ArrayList<OrderedPair> gaps = new ArrayList<>();
		int startGap = -1;
		for (int i = firstCow; i <= lastCow; i++) {
			boards[i] = true;
			if(!isOccupied[i]){
				if(startGap==-1){
					startGap = i;
				}
			} else {
				if(startGap!=-1){
					gaps.add(new OrderedPair(startGap,i));
					startGap = -1;
				}
			}
		}
		gaps.sort((i,j)->( ((i.y-i.x) > (j.y-j.x)) ? 1 : -1 ));
		for (int i = 0; i <= m-2 && i <= gaps.size(); i++) {
			OrderedPair pair = gaps.get(i);
			for (int j = pair.x; j < pair.y; j++) {
				boards[j] = false;
			}
		}
		int counter=0;
		for (int i = 0; i < s; i++) {
			if(boards[i])
				counter++;
		}
		out.println(counter);
		in.close();
		out.close();
	}
	private static class OrderedPair {
		public int x, y;

		public OrderedPair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
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

		@Override
		public String toString() {
			return "OrderedPair [x=" + x + ", y=" + y + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
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
			OrderedPair other = (OrderedPair) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}
}
