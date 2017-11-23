/*
ID: arjvik1
LANG: JAVA
TASK: Chutes
*/
import java.io.*;
import java.util.*;


public class Chutes {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("chutes.dat")));
		int cases = in.nextInt();
		for (int i = 0; i < cases; i++) {
			int p = in.nextInt();
			long seed = in.nextLong();
			int c = in.nextInt() + in.nextInt();
			Map<Integer,Integer> chutes = new HashMap<>();
			for (int j = 0; j < c; j++) {
				chutes.put(in.nextInt(), in.nextInt());
			}
			run(p,seed,chutes);
		}
		in.close();
	}

	private static void run(int p, long seed, Map<Integer, Integer> chutes) {
		Random r = new Random(seed);
		int[] positions = new int[p];
		for (int i = 0; i < p; i++) {
			positions[i] = 1;
		}
		int rolls = 0;
		while(true){
			for (int i = 0; i < p; i++) {
				rolls++;
				int dice = r.nextInt(6)+1;
				positions[i] += dice;
				while(chutes.containsKey(positions[i])){
					positions[i] = chutes.get(positions[i]);
				}
				if(positions[i]>=64){
					System.out.println("Player " + "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(i) + " wins after " + rolls + " rolls!");
					return;
				}
			}
		}
	}
}
