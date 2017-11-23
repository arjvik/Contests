/*
ID: arjvik1
LANG: JAVA
TASK: Triangles
*/
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import javax.xml.transform.stream.StreamSource;

@SuppressWarnings("unused")
public class Triangles {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("A.in")));
		PrintStream out = System.out;
		do{
			List<List<Integer>> triangles = new ArrayList<>();
			for (int i = 0; i < 6; i++) {
				List<Integer> triangle = new ArrayList<>();
				triangles.add(triangle);
				for (int j = 0; j < 3; j++) {
					triangle.add(in.nextInt());
				}
				in.nextLine();
			}
			calc(triangles);
		}while(!in.nextLine().equals("$"));
		in.close();
		out.close();
	}

	private static void calc(List<List<Integer>> triangles) {
		int max = 0;
		List<List<List<Integer>>> perms = permute(triangles);
		for (List<List<Integer>> perm : perms) {
			List<List<Integer>> triangle1 = rotate(perm.get(0));
			List<List<Integer>> triangle2 = rotate(perm.get(1));
			List<List<Integer>> triangle3 = rotate(perm.get(2));
			List<List<Integer>> triangle4 = rotate(perm.get(3));
			List<List<Integer>> triangle5 = rotate(perm.get(4));
			List<List<Integer>> triangle6 = rotate(perm.get(5));
			for (List<Integer> t1 : triangle1)
			for (List<Integer> t2 : triangle2)
			for (List<Integer> t3 : triangle3)
			for (List<Integer> t4 : triangle4)
			for (List<Integer> t5 : triangle5)
			for (List<Integer> t6 : triangle6)
				if(t1.get(0)==t2.get(2) &&
				   t2.get(0)==t3.get(2) &&
				   t3.get(0)==t4.get(2) &&
				   t4.get(0)==t5.get(2) &&
				   t5.get(0)==t6.get(2) &&
				   t6.get(0)==t1.get(2))
						max = Math.max(max, t1.get(1) +
											t2.get(1) +
											t3.get(1) +
											t4.get(1) +
											t5.get(1) +
											t6.get(1));
		}
		System.out.println((max==0)?"none":Integer.toString(max));
	}
	private static <T> List<List<T>> rotate(List<T> list) {
		List<List<T>> results = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			List<T> result = new ArrayList<>();
			results.add(result);
			result.addAll(list.subList(i, list.size()));
			result.addAll(list.subList(0, i));
		}
		return results;
	}

	private static <T> List<List<T>> permute(List<T> elements){
		if(elements.size()==1){
			ArrayList<List<T>> result = new ArrayList<>();
			result.add(elements);
			return result;
		}
		List<List<T>> result = new ArrayList<>();
		for (int i = 0; i < elements.size(); i++) {
			T first = elements.get(i);
			List<List<T>> firstLists = new ArrayList<>();
			List<T> sublist = new ArrayList<>(elements.subList(0, i));
			sublist.addAll(elements.subList(i+1, elements.size()));
			List<List<T>> permuted = permute(sublist);
			for (List<T> list : permuted) {
				List<T> firstList = new ArrayList<>();
				firstList.add(first);
				firstList.addAll(list);
				firstLists.add(firstList);
			}
			result.addAll(firstLists);
		}
		return result;
	}
	
	private static String tab(int n){
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < 4-n; i++) {
			b.append("\t");
		}
		return b.toString();
	}
	
	private static class Triangle {
		int a;
		int b;
		int c;
		public Triangle(int a, int b, int c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}		
	}
}