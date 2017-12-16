/*
ID: arjvik1
LANG: JAVA
TASK: measurement
*/
import java.io.*;
import java.util.*;


public class measurement {
	

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("measurement.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
		
		int lines = in.nextInt();
		List<Entry> entries = new ArrayList<>();
		for (int i = 0; i < lines; i++) {
			entries.add(new Entry(in.nextInt(),in.next(),in.nextInt()));
		}
		entries.sort(null);
		
		boolean a = false,
				b = false,
				c = false;
		int x = 7,
			y = 7,
			z = 7;
		int count = 0;
		for (Entry entry : entries) {
			
			
			switch(entry.name){
			case "Bessie":
				x+= entry.addition;
				break;
			case "Elsie":
				y+= entry.addition;
				break;
			case "Mildred":
				z+= entry.addition;
				break;
			default:
				throw new RuntimeException("Default case");	
			}
			
			boolean p = false,
					q = false,
					r = false;
			int max = x;
			p = true;
			if(y==max){
				q = true;
			}else if(y > max){
				max = y;
				p = false;
				q = true;
			}
			if(z==max){
				r = true;
			}else if(z > max){
				max = z;
				p = false;
				q = false;
				r = true;
			}
			
			if(a==p && b==q && c==r){
				//do nothing;
			}else{
				count++;
				a = p;
				b = q;
				c = r;
			}
			
		}
		out.println(count);
		in.close();
		out.close();
	}
	
	public static class Entry implements Comparable<Entry>{
		int date;
		String name;
		int addition;
		public Entry() {
			
		}
		public Entry(int date, String name, int addition) {
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
}
