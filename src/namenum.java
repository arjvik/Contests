/*
ID: arjvik1
LANG: JAVA
TASK: namenum
*/

import java.io.*;
import java.util.*;

public class namenum {
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
	public static void main(String[] args) throws IOException {
		
		/**//***//**//***//**//***/long startTime=System.currentTimeMillis();
		
		//Scanner in = new Scanner(new BufferedReader(new FileReader("namenum.in")));
		//Will not work with the debug utils:
		BufferedReader in = new BufferedReader(new FileReader("namenum.in"));
		BufferedReader dictin = new BufferedReader(new FileReader("dict.txt"));
		
		out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
		Set<String> dict = new HashSet<>(5000,1f);
		String line;
		while((line=dictin.readLine())!=null)
				dict.add(line);
		
		/**//***//**//***//**//***/System.err.println("Reading dictionary took "+(System.currentTimeMillis()-startTime)+"ms");
		/**//***//**//***//**//***/startTime=System.currentTimeMillis();
		
		String temp = in.readLine();
		System.err.println("Serial="+temp);
		if(!temp.matches("^[2-9]*$")){
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
		
		/**//***//**//***//**//***/System.err.println("Serializing serial number took "+(System.currentTimeMillis()-startTime)+"ms");
		/**//***//**//***//**//***/startTime=System.currentTimeMillis();
		
		possibleNames = populateNamesRecursively(serial);
		
		/**//***//**//***//**//***/System.err.println("Recursion took "+(System.currentTimeMillis()-startTime)+"ms");
		/**//***//**//***//**//***/startTime=System.currentTimeMillis();
		
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
		
		/**//***//**//***//**//***/System.err.println("Printing took "+(System.currentTimeMillis()-startTime)+"ms");
		
	}
	private static List<String> populateNamesRecursively(int[] serial){
		/**//***//**//***//**//***/long startTime = System.currentTimeMillis();
		/**//***//**//***//**//***/String msg = "\tRecursion level "+serial.length;
		
		if(serial.length==1){
			List<String> list = new ArrayList<>();
			for (char c : NUM[serial[0]]) {
				list.add(Character.toString(c));
			}

			/**//***//**//***//**//***/System.err.println(msg+"\t time "+(System.currentTimeMillis()-startTime)+"ms \t size="+list.size());
			
			return list;
		}
		List<String> innerList = populateNamesRecursively(Arrays.copyOfRange(serial, 1, serial.length));
		List<String> outerList = new ArrayList<>();
		for (char c : NUM[serial[0]]) {
			/*
			innerList.stream()
					.map((s)->(new StringBuffer(c).append(s).toString()))
					.forEachOrdered(outerList::add);
			/*/
			for (String s : innerList) {
				outerList.add(new StringBuffer(c).append(s).toString());
			}
			//*/
		}
		
		/**//***//**//***//**//***/System.err.println(msg+"\t time "+(System.currentTimeMillis()-startTime)+"ms \t size "+outerList.size());
		
		return outerList;
	}
}
