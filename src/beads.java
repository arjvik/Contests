/*
ID: arjvik1
LANG: JAVA
TASK: beads
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class beads {
	private static int N;
	private static char beads[];
	private static int max=0;
	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("beads.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
		System.err.print("Enter N: ");
		N=in.nextInt();
		in.nextLine();
		System.err.print("Enter beads: ");
		String beadString = in.nextLine().toLowerCase();
		in.close();
		beads=beadString.toCharArray();
		System.err.println("Beads: "+new String(beads));
		if(isEntireSameColor()){
			System.err.println("All same color - max="+N);
			out.println(N);
			out.close();
			return;
		}
		for (int i = 0; i < N; i++) {
			System.err.println("----------------------------------------------\nRotation #"+i+": "+new String(beads));
			updateMax();
			System.err.println("\tMax beads collected: "+max);
			rotate();
			Thread.sleep(0);
		}
		System.err.println("Final: "+max);
		out.println(max);
		out.close();
	}
	private static boolean isEntireSameColor(){
		char first;
		int i=0;
		do{
			if(i>N-1)
				return true;
			first=beads[i];
			i++;
		}while(first=='w');
		char searchFor= (first=='r') ? 'b' : 'r';
		for (char c : beads) {
			if(c==searchFor)
				return false;
		}
		return true;
	}
	private static void updateMax() {
		int left=0, right=0;
		char firstLeftChar, firstRightChar;
		int i=0;
		do{
			firstLeftChar=beads[i];
			i++;
		}while(firstLeftChar=='w');
		System.err.println("\tFirst left char: "+firstLeftChar);
		i=N-1;
		do{
			firstRightChar=beads[i];
			i--;
		}while(firstRightChar=='w');
		System.err.println("\tFirst right char: "+firstRightChar);
		char searchForLeft= (firstLeftChar=='r') ? 'b' : 'r';
		System.err.println("\tSearching for "+searchForLeft+" in left...");
		search:for (int j = 0; j < N; j++) {
			if(beads[j]==searchForLeft){
				System.err.println("\tStopping..found char "+beads[j]);
				break search;
			}else{
				left++;
			}
		}
		System.err.println("\tLeft: "+left);
		char[] rightbeads=Arrays.copyOfRange(beads,left,N);
		System.err.println("\tRight array: "+new String(rightbeads));
		char searchForRight= (firstRightChar=='r') ? 'b' : 'r';
		System.err.println("\tSearching for "+searchForRight+" in right...");
		search:for (int j = rightbeads.length-1; j>=0; j--) {
			if(rightbeads[j]==searchForRight){
				System.err.println("\tStopping..found char "+rightbeads[j]);
				break search;
			}else{
				right++;
			}
		}
		System.err.println("\tRight: "+right);
		System.err.println("\tTotal: "+(left+right));
		System.err.println("\tCurrent max: "+max);
		if(left+right>max){
			max=left+right;
			System.err.println("\tMax replaced");
		}
	}
	private static void rotate(){
		char temp = beads[N-1];
		for (int i = N-2; i >=0 ; i--) {
			beads[i+1]=beads[i];
		}
		beads[0]=temp;
	}
}
