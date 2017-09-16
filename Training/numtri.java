/*
ID: arjvik1
LANG: JAVA
TASK: numtri
*/
import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class numtri {
	private static int r;
	private static int leaf_counter = 0;
	private static int node_counter;
	//private static final Set<Integer> squares = Collections.unmodifiableSet(IntStream.range(1, 2002).map(i -> i*i).boxed().collect(Collectors.toSet()));
													
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		Scanner in = new Scanner(new BufferedReader(new FileReader("numtri.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
		r = in.nextInt();
		int numElements = (r*(r+1))/2;
		node_counter = numElements;
		int[] tree = new int[numElements+1];
		int[] highest = new int[numElements+1];
		for (int i = 1; i <= numElements; i++) {
			tree[i] = in.nextInt();
		}
		for (int n = numElements; n > 0; n--) {
			int child = getChild(n);
			if(child == -1)
				highest[n]=tree[n];
			else
				highest[n]=tree[n]+Math.max(highest[child],highest[child+1]);
		}
		out.println(highest[1]);
		in.close();
		out.close();
		System.out.format("Took %.3f seconds",((System.currentTimeMillis()-startTime)/1000d));
	}
	private static int getChild(int i){
		if(leaf_counter<r){
			leaf_counter++;
			return -1;
		}
		node_counter--;
		if(isTriangular(node_counter))
			node_counter--;
		return node_counter;
	}
	private static boolean isTriangular(int n) { //https://stackoverflow.com/questions/2913215/fastest-method-to-define-whether-a-number-is-a-triangular-number
		int n1 = (8*n)+1;
		int tst = (int)(Math.sqrt(n1) + 0.5);
		return tst*tst == n1;
	}
}
