
/*
ID: arjvik1
LANG: JAVA
TASK: Day16_ChronalClassification
*/
import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class Day16_ChronalClassification {
	private static final int[] r = new int[4];
	@SuppressWarnings("unchecked")
	private static final Set<Op>[] opcodeMap = new Set[16];

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.printf("Part 1: %d%nPart 2: %d%n", part1(in), part2(in));

		in.close();
	}

	private static int part2(Scanner in) {
		boolean[] selected = new boolean[16];
		while (Arrays.stream(opcodeMap).mapToInt(Set::size).sum() != 16) {
			for (int i = 0; i < 16; i++) {
				if (opcodeMap[i].size() != 1 || selected[i])
					continue;
				selected[i] = true;
				Op op = opcodeMap[i].stream().findAny().get();
				for (int j = 0; j < 16; j++)
					if (i != j)
						opcodeMap[j].remove(op);
			}
		}
//		printOpcodeMap();
		Op[] ops = IntStream.range(0, 16).mapToObj(i -> opcodeMap[i].stream().findAny().get()).toArray(Op[]::new);
		reset(r, new int[]{0,0,0,0});
		while (in.hasNextInt()) {
			int opcode = in.nextInt(),
					 a = in.nextInt(),
					 b = in.nextInt(),
					 c = in.nextInt();
			ops[opcode].apply(a, b, c);
		}
		return r[0];
	}

	private static void printOpcodeMap() {
		System.out.println("OpcodeMap:");
		for (int i = 0; i < 16; i++) {
			System.out.printf("Opcode %d: [%s]%n", i,
					opcodeMap[i].stream().mapToInt(Day16_ChronalClassification::findOpcodeIdx)
							.mapToObj(String::valueOf).collect(Collectors.joining(", ")));
		}
		System.out.println();
	}

	private static int findOpcodeIdx(Op op) {
		for (int i = 0; i < ops.length; i++)
			if (ops[i] == op)
				return i;
		return -1;
	}

	private static int part1(Scanner in) {
		int count = 0;
		while (true) {
			String line = in.nextLine();
			if (line.isEmpty())
				break;
			int[] start = parseArray(line);
			int opcode = in.nextInt(), a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
			in.nextLine();
			int[] end = parseArray(in.nextLine());
			in.nextLine();
			Set<Op> valid = new HashSet<>();
			for (Op op : ops) {
				reset(r, start);
				op.apply(a, b, c);
				if (eq(r, end))
					valid.add(op);
			}
			if (valid.size() >= 3)
				count++;
			if (opcodeMap[opcode] == null || opcodeMap[opcode].isEmpty())
				opcodeMap[opcode] = valid;
			else
				opcodeMap[opcode].retainAll(valid);
		}
		return count;
	}

	private static boolean eq(int[] a, int[] b) {
		return a[0] == b[0] && a[1] == b[1] && a[2] == b[2] && a[3] == b[3];
	}

	private static void reset(int[] a, int[] b) {
		a[0] = b[0];
		a[1] = b[1];
		a[2] = b[2];
		a[3] = b[3];
	}

	private static int[] parseArray(String s) {
		return new int[] { Integer.parseInt(s.substring(9, 10)), Integer.parseInt(s.substring(12, 13)),
				Integer.parseInt(s.substring(15, 16)), Integer.parseInt(s.substring(18, 19)), };
	}

	////////////////////////
	// OPCODE METHODS BELOW//
	////////////////////////

	@FunctionalInterface
	private static interface Op {
		void apply(int a, int b, int c);
	}

	private static final Op[] ops = { Day16_ChronalClassification::addr, Day16_ChronalClassification::addi,
			Day16_ChronalClassification::mulr, Day16_ChronalClassification::muli, Day16_ChronalClassification::banr,
			Day16_ChronalClassification::bani, Day16_ChronalClassification::borr, Day16_ChronalClassification::bori,
			Day16_ChronalClassification::setr, Day16_ChronalClassification::seti, Day16_ChronalClassification::gtir,
			Day16_ChronalClassification::gtri, Day16_ChronalClassification::gtrr, Day16_ChronalClassification::eqir,
			Day16_ChronalClassification::eqri, Day16_ChronalClassification::eqrr };

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
