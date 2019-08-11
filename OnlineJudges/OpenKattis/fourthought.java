/*
ID: arjvik1
LANG: JAVA
TASK: fourthought
*/
import java.io.*;
import java.util.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@SuppressWarnings("unused")
public class fourthought {
	@SuppressWarnings("serial")
	private static final HashMap<Integer, String> map = new HashMap<Integer,String>(){{
		put(16,"4 / 4 * 4 * 4 = 16");
		put(8,"4 / 4 * 4 + 4 = 8");
		put(24,"4 * 4 + 4 + 4 = 24");
		put(9,"4 / 4 + 4 + 4 = 9");
		put(0,"4 / 4 / 4 / 4 = 0");
		put(-8,"4 - 4 * 4 + 4 = -8");
		put(7,"4 - 4 / 4 + 4 = 7");
		put(68,"4 * 4 * 4 + 4 = 68");
		put(1,"4 / 4 * 4 / 4 = 1");
		put(4,"4 / 4 / 4 + 4 = 4");
		put(-16,"4 - 4 * 4 - 4 = -16");
		put(-1,"4 - 4 / 4 - 4 = -1");
		put(-60,"4 - 4 * 4 * 4 = -60");
		put(32,"4 * 4 + 4 * 4 = 32");
		put(17,"4 / 4 + 4 * 4 = 17");
		put(15,"4 * 4 - 4 / 4 = 15");
		put(60,"4 * 4 * 4 - 4 = 60");
		put(256,"4 * 4 * 4 * 4 = 256");
		put(2,"4 / 4 + 4 / 4 = 2");
		put(-7,"4 / 4 - 4 - 4 = -7");
		put(-15,"4 / 4 - 4 * 4 = -15");
		put(-4,"4 / 4 / 4 - 4 = -4");	
	}};
	private static final Scanner in = new Scanner(System.in);
	
	//{'16': '4 // 4 * 4 * 4 = 16', '8': '4 // 4 * 4 + 4 = 8', '24': '4 * 4 + 4 + 4 = 24', '9': '4 // 4 + 4 + 4 = 9', '0': '4 // 4 // 4 // 4 = 0', '-8': '4 - 4 * 4 + 4 = -8', '7': '4 - 4 // 4 + 4 = 7', '68': '4 * 4 * 4 + 4 = 68', '1': '4 // 4 * 4 // 4 = 1', '4': '4 // 4 // 4 + 4 = 4', '-16': '4 - 4 * 4 - 4 = -16', '-1': '4 - 4 // 4 - 4 = -1', '-60': '4 - 4 * 4 * 4 = -60', '32': '4 * 4 + 4 * 4 = 32', '17': '4 // 4 + 4 * 4 = 17', '15': '4 * 4 - 4 // 4 = 15', '60': '4 * 4 * 4 - 4 = 60', '256': '4 * 4 * 4 * 4 = 256', '2': '4 // 4 + 4 // 4 = 2', '-7': '4 // 4 - 4 - 4 = -7', '-15': '4 // 4 - 4 * 4 = -15', '-4': '4 // 4 // 4 - 4 = -4'}
	
	public static void main(String[] args) throws IOException {
		if(false){
			populate();
			return;
		}
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			int k = in.nextInt();
			System.out.println(map.getOrDefault(k, "no solution"));
		}
		
	}
	
	private static void populate() {
		try {
			char[] ops = {'+','-','*','/'};
			ScriptEngine s = new ScriptEngineManager().getEngineByName("js");
			/*for (List<Character> list : runs) {
				String t = "4 "+c[0]+" 4 "+c[1]+" 4 "+c[2]+" 4 ";
				Integer i = (Integer) s.eval(t);
				map.put(i, t+"= "+i);
			}*/
			for (char x : ops) {
				for (char y : ops) {
					for (char z : ops) {
						String t = "4 "+x+" 4 "+y+" 4 "+z+" 4 ";
						System.out.print(t+"= ");
						int i = in.nextInt();
						map.put(i, t+"= "+i);
					}
				}
			}
			printMap();
		} /*catch (ScriptException e) {
			throw new RuntimeException(e);
		}*/ finally{}
	}
	private static void printMap() {
		System.out.println("private static frinal HashMap<Integer, String> map = new HashMap<>(){{");
		for (Map.Entry<Integer, String> e : map.entrySet()) {
			System.out.println("put("+e.getKey()+",\""+e.getValue()+"\");");
		}
		System.out.println("}};");
	}
}