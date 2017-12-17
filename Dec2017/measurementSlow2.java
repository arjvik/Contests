/*
ID: arjvik1
LANG: JAVA
TASK: measurement
*/
import java.io.*;
import java.util.*;

public class measurementSlow2 {
	public static class Entry implements Comparable<Entry>{
		int date;
		int name;
		int addition;
		public Entry() {
			
		}
		public Entry(int date, int name, int addition) {
			this();
			this.date = date;
			this.name = name;
			this.addition = addition;
		}
		@Override
		public int compareTo(Entry o) {
			return Integer.compare(date, o.date);
		}
	}
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("measurement.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
		int lines = in.nextInt();
		int g = in.nextInt();
		List<Entry> entries = new ArrayList<>();
		Set<Integer> leaderboard = new HashSet<>();
		Map<Integer,Integer> output = new HashMap<>();
		output.put(-1, g);
		leaderboard.add(-1);
		for (int i = 0; i < lines; i++) {
			int date = in.nextInt();
			int name = in.nextInt();
			int ammt = in.nextInt();
			entries.add(new Entry(date,name,ammt));
			output.put(name, g);
			leaderboard.add(name);
		}
		entries.sort(null);		
		int count = 0;
		for (Entry e : entries) {
			output.put(e.name,output.get(e.name)+e.addition);
			if(leaderboard.contains(e.name)&&leaderboard.size()!=1){
				if(e.addition>0){
					count++;
					leaderboard.clear();
					leaderboard.add(e.name);
				}else{
					count++;
					leaderboard.remove(e.name);
				}
			}else{
				Set<Integer> newLeaderboard = getLeaderboard(output);
				if(!leaderboard.equals(newLeaderboard)){
					count++;
					leaderboard = newLeaderboard;
				}
			}
		}
		
		out.println(count);
		in.close();
		out.close();
	}
	private static Set<Integer> getLeaderboard(Map<Integer, Integer> output) {
		int max = Integer.MIN_VALUE;
		Set<Integer> ans = null;
		for (Map.Entry<Integer,Integer> e : output.entrySet()) {
			if(e.getValue()> max){
				max = e.getValue();
				ans = new HashSet<>();
				ans.add(e.getKey());
			}else if(e.getValue()==max){
				ans.add(e.getKey());
			}
		}
		return ans;
	}
}
