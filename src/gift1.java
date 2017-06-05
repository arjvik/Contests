/*
id: arjvik1
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class gift1 {	
	private static String[] names;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("gift1.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
		int NP = in.nextInt();
		in.nextLine();
		System.err.println("NP="+NP);
		names = new String[NP];
		for (int i = 0; i < NP; i++) {
			System.err.print("Name "+(i+1));
			names[i] = in.nextLine();
		}
		System.err.println("Done reading names");
		int[] actualMoneyGiven = new int[NP],
				moneyRecieved  = new int[NP];
		for (int i = 0; i < NP; i++) moneyRecieved[i] = 0;
		System.err.println("Reset arrays");
		for (int i = 0; i < NP; i++) {
			System.err.print("------------------------------------\nEnter name: ");
			String name = in.nextLine();
			int id = id(name);
			System.err.println("Name: "+name+" ID="+id);
			int originalMoneyGiven=in.nextInt();
			int numPeople = in.nextInt();
			in.nextLine();
			int remainderMoneyGiven = (numPeople!=0) ? originalMoneyGiven%numPeople : 0;
			actualMoneyGiven[id]=originalMoneyGiven-remainderMoneyGiven;
			System.err.println("\toriginalMoneyGiven="+originalMoneyGiven+
					"\n\tnumPeople="+numPeople+
					"\n\tremainderMoneyGiven="+remainderMoneyGiven+
					"\n\tactualMoneyGiven="+actualMoneyGiven[id]+
					"\nGiving money...");
			for (int j = 0; j < numPeople; j++) {
				System.err.print("\t\tName "+(j+1));
				String reciever=in.nextLine();
				int rID = id(reciever);
				moneyRecieved[rID]+=actualMoneyGiven[id]/numPeople;
			}
		}
		
		for (int i = 0; i < NP; i++) {
			int ans = moneyRecieved[i]-actualMoneyGiven[i];
			out.println(names[i]+" "+ans);
		}
		in.close();
		out.close();
	}
	private static int id(String name){
		for (int i = 0; i < names.length; i++) {
			if(names[i].equals(name))
				return i;
		}
		return -1;
	}
}