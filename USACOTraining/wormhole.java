/*
ID: arjvik1
LANG: JAVA
TASK: wormhole
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class wormhole {
	private static final int X=0,Y=1;
	private static int[] pairings;
	private static int[][] coordinates;
	private static int counter = 0;
	private static int totalPairings = 0;
	private static int n;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("wormhole.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
		n = in.nextInt();
		pairings = new int[n+1];
		coordinates = new int[n+1][2];
		for (int i = 1; i <= n; i++) {
			coordinates[i][X] = in.nextInt();
			coordinates[i][Y] = in.nextInt();
		}
		pairings();
		out.println(counter);
		in.close();
		out.close();
	}
	private static void pairings(){
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = i+1;
		}
		pairings(array);
	}
	private static void pairings(int[] array) {
		if(array.length==0){
			process();
			return;
		}
		int base = array[0];
		for (int i = 1; i < array.length; i++) {
			int current = array[i];
			pairings[base] = current;
			pairings[current] = base;int[] newArray = new int[array.length-2];
			for (int j = 1, k = 0; j < array.length; j++) {
				int l = array[j];
				if(l!=current)
					newArray[k++]=l; 
			}
			pairings(newArray);
		}
	}
	private static void process() {
		totalPairings++;
		for (int i = 1; i <= n; i++) {
			if(isInfiniteLoopFromWormhole(i)){
				counter++;
				return;
			}
		}
	}
	private static boolean isInfiniteLoopFromWormhole(int x) {
		boolean[] visited = new boolean[n+1];
		int i = x;
		while (!visited[i]) {
			visited[i] = true;
			i = getNextWormhole(pairings[i]);
			if(i==-1)
				return false;
		}
		return true;
	}
	private static int getNextWormhole(int x) {
		int currMin = Integer.MAX_VALUE;
		int index = -1;
		for (int i = 1; i <= n; i++) {
			int[] wh = coordinates[i];
			if(wh[Y]!=coordinates[x][Y])
				continue;
			if(wh[X]<=coordinates[x][X])
				continue;
			if(wh[X]<currMin){
				currMin = wh[X];
				index = i;
			}
		}
		return index;
	}
}
