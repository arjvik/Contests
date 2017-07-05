/*
ID: arjvik1
LANG: JAVA
TASK: citystate
*/
import java.io.*;
import java.util.*;

public class citystate_suboptimal {
	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("citystate.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));
		int n = in.nextInt();
		in.nextLine();
		String[][] cities = new String[n][2];
		Map<String,List<Integer>> state = new HashMap<>();
		for (int i = 0; i < n; i++) {
			String[] temp = in.nextLine().toUpperCase().split(" ");
			cities[i][0] = temp[0].substring(0,2);
			cities[i][1] = temp[1];
			if(state.containsKey(cities[i][1])){
				state.get(cities[i][1]).add(i);
			}else{
				ArrayList<Integer> list = new ArrayList<>();
				list.add(i);
				state.put(cities[i][1],list);
			}
		}
		Set<UnorderedPair> pairs = new HashSet<>();
		for (int i = 0; i < cities.length; i++) {
			if(state.containsKey(cities[i][0].substring(0, 2))){
				List<Integer> list = state.get(cities[i][0]);
				for (Integer i2 : list)
					if(cities[i2][0].startsWith(cities[i][1])&&i!=i2&&!cities[i][1].equals(cities[i2][1]))
						pairs.add(new UnorderedPair(i,i2));
			}
		}		
		out.println(pairs.size());
		in.close();
		out.close();
	}

	private static class UnorderedPair implements Comparable<UnorderedPair>{
		private int x,y;
		Set<Integer> set;

		private UnorderedPair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
			set = new HashSet<>();
			set.add(x);
			set.add(y);
		}

		@Override
		public int hashCode() {
			return set.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			UnorderedPair other = (UnorderedPair) obj;
			if (set == null) {
				if (other.set != null)
					return false;
			} else if (!set.equals(other.set))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "UnorderedPair [x=" + x + ", y=" + y + "]";
		}

		@Override
		public int compareTo(UnorderedPair o) {
			return equals(o)?0:1;
		}
	}
}
