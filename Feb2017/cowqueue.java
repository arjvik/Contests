/*
ID: arjvik1
LANG: JAVA
TASK: cowqueue
*/
import java.io.*;
import java.util.*;

public class cowqueue {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("cowqueue.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowqueue.out")));
		int n = in.nextInt();
		List<Entry> entries = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			entries.add(new Entry(in.nextInt(), in.nextInt()));
		}
		Collections.sort(entries);
		int timeFinished = 0;
		for (Entry entry : entries) {
			//System.out.format("Cow arrives at %d and will take %d minutes%n", entry.entry, entry.time);
			if(entry.entry < timeFinished){
				//System.out.format("\tQuestioning will begin when previous questioning finishes at %d minutes,%n",timeFinished);
				timeFinished += entry.time;
			}else{
				//System.out.format("\tQuestioning will start at %d minutes,%n",entry.entry);
				timeFinished = entry.entry + entry.time;
			}
			//System.out.format("\tand will finish at %d%n", timeFinished);
		}
		out.println(timeFinished);
		in.close();
		out.close();
	}
	public static class Entry implements Comparable<Entry>{
		int entry;
		int time;
		public Entry(int entry, int time) {
			this.entry = entry;
			this.time = time;
		}
		@Override
		public int compareTo(Entry o) {
			if(entry<o.entry)
				return -1;
			else if(entry>o.entry)
				return 1;
			else if(time<o.time)
				return -1;
			else if(time>o.time)
				return 1;
			return 0;
		}
		
	}
}
