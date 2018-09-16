/*
ID: arjvik1
LANG: JAVA
TASK: sort
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class sort {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("sort.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
		int N = in.nextInt();
		int[] A = new int[N];
		for(int i = 0; i < N; i++)
			A[i] = in.nextInt();
		
		int moo = 0;
		boolean sorted = false;
		while (!sorted) {
			sorted = true;
				moo++;
				for(int i = 0; i <= N-2; i++) {
					if(A[i+1] < A[i]) {
						swap(A, i, i+1);
					}
				}
				for(int i = N-2; i >= 0; i--) {
					if(A[i+1] < A[i]) {
						swap(A, i, i+1);
					}
				}
				for(int i = 0; i <= N-2; i++) {
					if(A[i+1] < A[i]) {
						sorted = false;
					}
				}
		}
		out.println(moo);
		in.close();
		out.close();
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i]=a[j];
		a[j]=temp;
	}
}
