/*
ID: arjvik1
LANG: JAVA
TASK: haybales
*/
import java.io.*;
import java.util.*;

public class haybales {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("haybales.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
		int n = in.nextInt(),
			q = in.nextInt();
		List<Integer> haybaleList = new ArrayList<>();
		Set<Integer> haybaleSet= new HashSet<>();
		for(int i = 0; i < n; i++){
			int j = in.nextInt();
			haybaleList.add(j);
			haybaleSet.add(j);
		}
		haybaleList.sort(null);
		System.err.println("Sorted: "+haybaleList);
		for (int i = 0; i < q; i++){
			int a = in.nextInt(),
				b = in.nextInt();
			if(a>haybaleList.get(haybaleList.size()-1)){
				out.println(0);
				continue;
			}
			int x = Collections.binarySearch(haybaleList, a);
			if(x<0) x = -1*((x+1));
			int y = Collections.binarySearch(haybaleList, b);
			if(y<0) y = (-1*((y+1)))-1;
			System.err.println("Checking between x="+x+" and y="+y);
			int count = 0;
			for (int j = haybaleList.get(Math.min(x, haybaleList.size()-1)); j <= haybaleList.get(Math.min(y, haybaleList.size()-1)); j++) {
				System.err.println("\tChecking j="+j);
				if(haybaleSet.contains(j)){
					System.err.println("\t\tFound!");
					count++;
				}
			}
			out.println(count);
		}
		in.close();
		out.close();
	}
}
