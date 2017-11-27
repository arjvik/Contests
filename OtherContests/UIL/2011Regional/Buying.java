/*
ID: arjvik1
LANG: JAVA
TASK: Buying
*/
import java.io.*;
import java.util.*;

public class Buying {
	private static double OLD_GAS = gasCost(12);
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("buying.dat")));
		int m = in.nextInt();
		in.nextLine();
		for (int i = 0; i < m; i++) {
			String line = in.nextLine();
			StringTokenizer st = new StringTokenizer(line,"--");
			String _name = st.nextToken();
			int mpg = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			int _purchaseprice = price - 4500;
			double new_gas = gasCost(mpg);
			double _gas_price = OLD_GAS - new_gas;
			System.out.printf("%s $%d $%.2f%n",_name,_purchaseprice,_gas_price);
		}
		
		in.close();
	}
	
	private static double gasCost(int mpg){
		return 63600d/mpg;
	}
}
