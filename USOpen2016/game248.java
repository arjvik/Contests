/*
ID: arjvik1
LANG: JAVA
TASK: game248
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class game248 {
	private static final String NAME = "262144";
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader(NAME+".in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(NAME+".out")));
		int n = in.nextInt();
		ArrayList<Integer> nums = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			nums.add(in.nextInt());
		}
		
		int max = -1;
		boolean increased = false;
		for (int i = 0; i < nums.size()-1; i++) {
			ArrayList<Integer> nums2 = new ArrayList<>(nums);
			if(nums2.get(i) != nums2.get(i+1))
				continue;
			nums2.set(i, nums2.get(i) + 1);
			nums2.remove((int)(i+1));
			int m = run(nums2,i);
			if(m>max)
				max = m;
			increased = true;
		}		
		if(!increased){
			max = -1;
			for (Integer i : nums) {
				if(i>max)
					max = i;
			}
		}
		out.println(max);
		
		in.close();
		out.close();
	}

	private static int run(ArrayList<Integer> nums, int pos) {
		int max = -1;
		boolean increased = false;
		for (int i = 0; i < nums.size()-1; i++) {
			ArrayList<Integer> nums2 = new ArrayList<>(nums);
			if(nums2.get(i) != nums2.get(i+1))
				continue;
			nums2.set(i, nums2.get(i) + 1);
			nums2.remove((int)(i+1));
			int m = run(nums2,i);
			if(m>max)
				max = m;
			increased = true;
		}		
		if(!increased){
			max = -1;
			for (Integer i : nums) {
				if(i>max)
					max = i;
			}
		}
		return max;
	}
}
