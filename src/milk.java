/*
ID: arjvik1
LANG: JAVA
TASK: milk
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class milk {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("milk.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
		int required = in.nextInt();
		int numFarmers = in.nextInt();
		int[] capacity = new int[numFarmers],
				price  = new int[numFarmers];
		for (int i = 0; i < numFarmers; i++) {
			price[i] = in.nextInt();
			capacity[i] = in.nextInt();
		}
		sort(capacity,price);
		int currCost = 0;
		for (int i = 0; required>0; i++) {
			if(required > capacity[i]){
				required -= capacity[i];
				currCost += price[i]*capacity[i];
			} else {
				currCost += price[i]*required;
				required = 0;
			}
		}
		out.println(currCost);
		in.close();
		out.close();
	}

	private static void sort(int[] capacity, int[] price) {
		for (int i = 0; i < price.length; i++) {
			for (int j = i+1; j < price.length; j++) {
				if(price[i]>price[j]){
					int temp = price[i];
					price[i] = price[j];
					price[j] = temp;
					temp = capacity[i];
					capacity[i] = capacity[j];
					capacity[j] = temp;
				}
			}
		}
	}
}
