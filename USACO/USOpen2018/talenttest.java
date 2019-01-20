/*
ID: arjvik1
LANG: JAVA
TASK: talenttest
*/
import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class talenttest {
	public static void main(String[] args) throws IOException {
		Random r = new Random();
		int n = r.nextInt(250-150)+150, w = r.nextInt(1000);
		System.out.printf("%d %d%n", n,w);
		for (int i=0; i<n;i++)
			System.out.printf("%d %d%n", r.nextInt(1_000), r.nextInt(1_000));
	}
}
