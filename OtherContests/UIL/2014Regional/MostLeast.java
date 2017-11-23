/*
ID: arjvik1
LANG: JAVA
TASK: MostLeast
*/
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class MostLeast {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("mostleast.dat")));
		int num = in.nextInt();
		in.nextLine();
		for (int i = 0; i < num; i++) {
			String str = in.nextLine();
			StringTokenizer st = new StringTokenizer(str);
			Map<String,Integer> freq = new HashMap<>();
			while(st.hasMoreTokens()){
				String w = st.nextToken().replaceAll("[.,?]", "");
				freq.put(w, freq.getOrDefault(w, 0)+1);
			}
			int maxf = 0;
			List<String> maxw = new ArrayList<>();
			int minf = Integer.MAX_VALUE;
			List<String> minw = new ArrayList<>();
			for (Entry<String, Integer> e : freq.entrySet()) {
				if(e.getValue()==maxf){
					maxw.add(e.getKey());
				}else if(e.getValue()>maxf){
					maxf = e.getValue();
					maxw = new ArrayList<>();
					maxw.add(e.getKey());
				}
				if(e.getValue()==minf){
					minw.add(e.getKey());
				}else if(e.getValue()<minf){
					minf = e.getValue();
					minw = new ArrayList<>();
					minw.add(e.getKey());
				}
			}
			Collections.sort(maxw);
			Collections.sort(minw);
			System.out.print(maxf+" ");
			for (String string : maxw) {
				System.out.print(string+" ");
			}
			System.out.println();
			System.out.print(minf+" ");
			for (String string : minw) {
				System.out.print(string+" ");
			}
			System.out.println();
		}
		in.close();
	}
}
