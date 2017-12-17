/*
ID: arjvik1
LANG: JAVA
TASK: measurement
*/
import java.io.*;
import java.util.*;

public class measurement2 {
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
	public static class IntPair {
		int max;
		int howMany;
		IntPair(int max, int howMany) {
			this.max=max;
			this.howMany=howMany;
		}
	}
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("measurement.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
		int lines = in.nextInt();
		int g = in.nextInt();
		List<Entry> entries = new ArrayList<>();
		Map<Integer,Integer> output = new HashMap<>();
		for (int i = 0; i < lines; i++) {
			int date = in.nextInt();
			int name = in.nextInt();
			int ammt = in.nextInt();
			entries.add(new Entry(date,name,ammt));
			output.put(name, g);
		}
		entries.sort(null);		
		int count = 0;
		int max = g;
		int howMany = output.size();
		for (Entry e : entries) {
			output.put(e.name, output.get(e.name)+e.addition);
			int oldOut = output.get(e.name);
			int newOut = oldOut + e.addition;
			if(oldOut == max){
				if(newOut>oldOut){
					max = newOut;
					count++;
					howMany = 1;
				}else{
					if(howMany == 1){
						System.out.println("Defaulting back");
						count++;
						IntPair i = getLeaderboard(output);
						howMany = i.howMany;
						max = i.max;
					}else{
						count++;
						howMany--;
					}
				}
			}else{
				if(newOut>max){
					max = newOut;
					count++;
					howMany = 1;
				}else if(newOut==max){
					count++;
					howMany++;
				}
			}
		}
		
		out.println(count);
		in.close();
		out.close();
	}
	private static IntPair getLeaderboard(Map<Integer, Integer> output) {
		int max = Integer.MIN_VALUE;
		int howMany = -1;
		for (Map.Entry<Integer,Integer> e : output.entrySet()) {
			if(e.getValue()> max){
				max = e.getValue();
				howMany = 1;
			}else if(e.getValue()==max){
				howMany++;
			}
		}
		return new IntPair(max, howMany);
	}
}
