/*
ID: arjvik1
LANG: JAVA
TASK: homework
*/
import java.io.*;
import java.util.*;

public class homework {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("homework.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));
		int n = in.nextInt();
		int[] scores = new int[n];
		for (int i = 0; i < n; i++) {
			scores[i] = in.nextInt();
		}
		double maxScore = Double.NEGATIVE_INFINITY;
		TreeSet<Integer> maxK = new TreeSet<>();
		int sum = scores[n-1];
		int minQ = Integer.MAX_VALUE;
		for (int k = n - 2; k > 0; k--) {
			sum+=scores[k];
			minQ = Math.min(minQ, scores[k]);
			double score = (sum-minQ)/((double)(n-k-1));
			if(score>maxScore){
				maxK.clear();
				maxK.add(k);
				maxScore = score;
			}else if(score==maxScore){
				maxK.add(k);
			}
		}
		for (Integer integer : maxK) {
			out.println(integer);
		}
		in.close();
		out.close();
	}
}