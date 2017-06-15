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
	public static void main(String[] args) throws IOException {
		
		/**//***//**//***//**//***/long startTime=System.currentTimeMillis();
		
		Scanner in = new Scanner(new BufferedReader(new FileReader("namenum.in")));
		Scanner dictin = new Scanner(new BufferedReader(new FileReader("dict.txt")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
		Set<String> dict = new HashSet<>(5000,1f);
		while(dictin.hasNextLine()){
			String line=dictin.nextLine();
			if(line.equals("eof"))
				break;
			if(line.matches("^[A-PR-Y]*$"))
				dict.add(line);
		}
		
		/**//***//**//***//**//***/System.err.println("Reading dictionary took "+(System.currentTimeMillis()-startTime)+"ms");
		/**//***//**//***//**//***/startTime=System.currentTimeMillis();
		
		String temp = Long.toString(in.nextLong());
		if(!temp.matches("^[2-9]*$")){
			out.println("NONE");
			in.close();
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
		out.close();
		
		/**//***//**//***//**//***/System.err.println("Printing took "+(System.currentTimeMillis()-startTime)+"ms");
		
	}
	public static List<String> populateNamesRecursively(int[] serial){
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
			innerList.stream()
					.map((s)->(c+s))
					.forEachOrdered(outerList::add);
		}
		return outerList;
	}
}
