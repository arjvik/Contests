/*
ID: arjvik1
LANG: JAVA
TASK: skidesign
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class skidesign {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("skidesign.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
		int n = in.nextInt();
		int[] hills = new int[n];
		for (int i = 0; i < n; i++) {
			hills[i] = in.nextInt();
		}
		int cost = Integer.MAX_VALUE;
		for (int tMin = 0; tMin < n; tMin++) {
			int tMax = tMin + 17;
			int currentCost = 0;
			for (int i : hills) {
				if(i < tMin){
					int diff = tMin - i;
					currentCost += diff * diff;
				}else if(i > tMax){
					int diff = i - tMax;
					currentCost += diff * diff;
				}
			}
			System.out.println("Cost for tMin="+tMin+" tMax="+tMax+" is $"+currentCost);
			if(currentCost < cost){
				cost = currentCost;
				System.out.println("Cost is "+cost);
			}
		}
		out.println(cost);
		in.close();
		out.close();
	}
}
