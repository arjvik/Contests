/*
ID: arjvik1
LANG: JAVA
TASK: Selection
*/
import java.io.*;
import java.util.*;

public class Selection {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("selection.dat")));
		int n = in.nextInt();
		in.nextLine();
		for (int i = 0; i < n; i++) {
			String line = in.nextLine();
			StringTokenizer st = new StringTokenizer(line);
			List<Integer> array = new ArrayList<Integer>();
			while(st.hasMoreTokens())
				array.add(Integer.parseInt(st.nextToken()));
			SelectionSort(array);
			System.out.println();
		}
		in.close();
	}

	private static void SelectionSort(List<Integer> array) {
		for (int i = 0; i < array.size() - 1; i++) {
			int min = Integer.MAX_VALUE;
			int min_index = -1;
			for (int j = i; j < array.size(); j++) {
				if(array.get(j) < min){
					min = array.get(j);
					min_index = j;
				}
			}
			int temp = array.get(i);
			array.set(i, array.get(min_index));
			array.set(min_index, temp);
			printArray(array);
		}
	}

	private static void printArray(List<Integer> array) {
		for (int i = 0; i < array.size() - 1; i++)
			System.out.print(array.get(i)+" ");
		System.out.println(array.get(array.size() - 1));
	}
}
