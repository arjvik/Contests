import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class FruitfulSeed {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		int y = in.nextInt();
		int d = in.nextInt();
		int lastDay = d - (x + 1);
		if (lastDay < 0) {
			System.out.println(0);
			in.close();
			return;
		}
		long profit = 0;

		long[] sprout = new long[d];
		sprout[0] = 1;
		for (int i = 0; i < d; i++) {
			long fruits = sprout[i];
			if (i <= lastDay)
				for (int j = i + x; j < d; j += y)
					sprout[j] += fruits;
			else
				profit += fruits;
		}
		
		System.out.println(profit);
		in.close();
	}
}