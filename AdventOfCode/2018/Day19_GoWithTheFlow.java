/*
ID: arjvik1
LANG: JAVA
TASK: Day19_GoWithTheFlow
*/
import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class Day19_GoWithTheFlow {
	
	private static final int[] r = new int[6];
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		in.next();
		int ip = in.nextInt();
		List<Runnable> instructions = new ArrayList<>();
		String opName;
		while (in.hasNext() && !(opName = in.next()).equals("EOF")) {
			Op op = opMap.get(opName);
			int a = in.nextInt(),
				b = in.nextInt(),
				c = in.nextInt();
			instructions.add(() -> op.apply(a, b, c));
		}
		System.out.printf("Part 1: %d%n", part1(ip, instructions));
		System.out.printf("Part 2: %d%n", part2(ip, instructions));
		in.close();
	}
	
	private static int part2(int ip, List<Runnable> instructions) {
		r[0] = 1;
		r[1] = 0;
		r[2] = 0;
		r[3] = 0;
		r[4] = 0;
		r[5] = 0;
		return part1(ip, instructions);
	}

	private static int part1(int ip, List<Runnable> instructions) {
		for (r[ip] = 0; r[ip] < instructions.size() && r[ip] >= 0; r[ip]++)
			instructions.get(r[ip]).run();
		return r[0];
	}
	
	////////////////////////
	//OPCODE METHODS BELOW//
	////////////////////////

	@FunctionalInterface
	private static interface Op {
		void apply(int a, int b, int c);
	}
	
	private static final Map<String, Op> opMap = getMap();
	
	private static Map<String, Op> getMap() {
		Op[] ops = {
				Day19_GoWithTheFlow::addr, Day19_GoWithTheFlow::addi,
				Day19_GoWithTheFlow::mulr, Day19_GoWithTheFlow::muli,
				Day19_GoWithTheFlow::banr, Day19_GoWithTheFlow::bani,
				Day19_GoWithTheFlow::borr, Day19_GoWithTheFlow::bori,
				Day19_GoWithTheFlow::setr, Day19_GoWithTheFlow::seti,
				Day19_GoWithTheFlow::gtir, Day19_GoWithTheFlow::gtri,
				Day19_GoWithTheFlow::gtrr, Day19_GoWithTheFlow::eqir,
				Day19_GoWithTheFlow::eqri, Day19_GoWithTheFlow::eqrr
			};
		String[] names = {"addr", "addi", "mulr", "muli", "banr", "bani", "borr", "bori",
						  "setr", "seti", "gtir", "gtri", "gtrr", "eqir", "eqri", "eqrr"};
		
		if (ops.length != names.length)
			throw new RuntimeException("NO U");
		
		Map<String, Op> opMap = new HashMap<>();
		
		for (int i = 0; i < ops.length; i++)
			opMap.put(names[i], ops[i]);
		
		return opMap;

	}
	
	public static void addr(int a, int b, int c) {
		r[c] = r[a] + r[b];
	}


	public static void addi(int a, int b, int c) {
		r[c] = r[a] + b;
	}

	public static void mulr(int a, int b, int c) {
		r[c] = r[a] * r[b];
	}

	public static void muli(int a, int b, int c) {
		r[c] = r[a] * b;
	}

	public static void banr(int a, int b, int c) {
		r[c] = r[a] & r[b];
	}

	public static void bani(int a, int b, int c) {
		r[c] = r[a] & b;
	}

	public static void borr(int a, int b, int c) {
		r[c] = r[a] | r[b];
	}

	public static void bori(int a, int b, int c) {
		r[c] = r[a] | b;
	}

	public static void setr(int a, int b, int c) {
		r[c] = r[a];
	}

	public static void seti(int a, int b, int c) {
		r[c] = a;
	}

	public static void gtir(int a, int b, int c) {
		r[c] = a > r[b] ? 1 : 0;
	}

	public static void gtri(int a, int b, int c) {
		r[c] = r[a] > b ? 1 : 0;
	}

	public static void gtrr(int a, int b, int c) {
		r[c] = r[a] > r[b] ? 1 : 0;
	}

	public static void eqir(int a, int b, int c) {
		r[c] = a == r[b] ? 1 : 0;
	}

	public static void eqri(int a, int b, int c) {
		r[c] = r[a] == b ? 1 : 0;
	}

	public static void eqrr(int a, int b, int c) {
		r[c] = r[a] == r[b] ? 1 : 0;
	}
}
