/*
ID: arjvik1
LANG: JAVA
TASK: Day1_ChronalCalibration
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class Day1_ChronalCalibration_Part1 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		long l = 0;
		while (in.hasNextLong())
			l += in.nextLong();
		System.out.println(l);
		in.close();
	}
}
