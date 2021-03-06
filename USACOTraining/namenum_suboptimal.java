/*
ID: arjvik1
LANG: JAVA
TASK: namenum
*/

import java.io.*;
import java.util.*;

public class namenum_suboptimal {
	private static List<String> possibleNames;
	private static final char[][] NUM= {
			{' ',' ',' '},
			{' ',' ',' '},
			{'A','B','C'},
			{'D','E','F'},
			{'G','H','I'},
			{'J','K','L'},
			{'M','N','O'},
			{'P','R','S'},
			{'T','U','V'},
			{'W','X','Y'}
	};
	private static PrintWriter out;
	private static boolean DEBUG=false;
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		//Scanner in = new Scanner(new BufferedReader(new FileReader("namenum.in")));
		//Will not work with the debug utils:
		BufferedReader in = new BufferedReader(new FileReader("namenum.in"));
		BufferedReader dictin = new BufferedReader(new FileReader("dict.txt"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
		Set<String> dict = new HashSet<>(5000,1f);
		String line;
		while((line=dictin.readLine())!=null)
				dict.add(line);
		String temp = in.readLine();
		if(temp.equals("463373633623")) DEBUG=true;
		if(!temp.matches("^[2-9]*$")){
			System.err.println("Unexpected end - bad serial number");
			out.println("NONE");
			in.close();
			dictin.close();
			out.close();
			return;
		}
		int[] serial = new int[temp.length()];
		for (int i = 0; i < temp.length(); i++)
		{
		    serial[i] = temp.charAt(i) - '0';
		}
		possibleNames = populateNamesRecursively(serial);
		int i=0;
		for (String name : possibleNames){
			if(dict.contains(name)){
				i++;
				out.println(name);
			}
		}
		if(i==0){
			out.println("NONE");
		}
		in.close();
		dictin.close();
		out.close();
		System.err.println("Took "+(System.currentTimeMillis()-startTime)+"ms to execute");
	}
	private static List<String> populateNamesRecursively(int[] serial){
		if(DEBUG) if(serial.length==13) System.exit(serial.length);
		
		if(serial.length==1){
			List<String> list = new ArrayList<>();
			for (char c : NUM[serial[0]]) {
				list.add(Character.toString(c));
			}
			
			return list;
		}
		List<String> innerList = populateNamesRecursively(Arrays.copyOfRange(serial, 1, serial.length));
		List<String> outerList = new ArrayList<>();
		for (char c : NUM[serial[0]]) {
			/*
			innerList.stream()
					.map((s)->(new StringBuilder(c).append(s).toString()))
					.forEachOrdered(outerList::add);
			*/
			for (String s : innerList) {
				outerList.add(new StringBuilder(Character.toString(c)).append(s).toString());
			}
		}

		if(DEBUG) if(serial.length==12) System.exit(serial.length);
		return outerList;
	}
}
