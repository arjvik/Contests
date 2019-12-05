import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class Day5_Test {
	public static void main(String[] args) throws IOException {
		System.out.println("Part 1: "+part1());
		System.out.println("Part 2: "+part2());
	}

	private static int part1() {
		return new IntcodeVM(ADVENTOFCODEINPUT.readNumList(5)).run(1);
	}
	
	private static int part2() {
		return new IntcodeVM(ADVENTOFCODEINPUT.readNumList(5)).run(5);
//		return new IntcodeVM("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9").run(1);
	}
	
	public static class IntcodeVM {
		private int pointer = 0;
		private List<Integer> tape;
		public IntcodeVM(List<Integer> tape) {
			this.tape = new ArrayList<>(tape);
		}
		public IntcodeVM(String s) {
			this(Arrays.stream(s.split(",")).map(Integer::parseInt).collect(Collectors.toList()));
		}
		public int run(int input) {
			while (true) {
				int ins = tape.get(pointer);
				if (ins % 100 == 99)
					return tape.get(0);
				int m1 = (ins/100)%10;
				int m2 = (ins/1000)%10;
				int m3 = (ins/10000)%10;
				if (ins % 100 == 1 || ins % 100 == 2) {
					int o1 = m1 == 0 ? tape.get(tape.get(pointer+1)) : tape.get(pointer+1);
					int o2 = m2 == 0 ? tape.get(tape.get(pointer+2)) : tape.get(pointer+2);
					if (ins%100 == 1)
						tape.set(tape.get(pointer+3), o1+o2);
					else
						tape.set(tape.get(pointer+3), o1*o2);
					pointer += 4;
				} else if (ins % 100 == 3) {
					tape.set(tape.get(pointer+1), input);
					pointer += 2;
				} else if (ins % 100 == 4) {
					int o1 = m1 == 0 ? tape.get(tape.get(pointer+1)) : tape.get(pointer+1);
					System.out.println(o1);
					pointer += 2;
				} else if (ins % 100 == 5 || ins % 100 == 6) {
					int o1 = m1 == 0 ? tape.get(tape.get(pointer+1)) : tape.get(pointer+1);
					int o2 = m2 == 0 ? tape.get(tape.get(pointer+2)) : tape.get(pointer+2);
					if ((o1 != 0) == (ins % 100 == 5))
						pointer = o2;
					else
						pointer += 3;
				} else if (ins % 100 == 7 || ins % 100 == 8) {
					int o1 = m1 == 0 ? tape.get(tape.get(pointer+1)) : tape.get(pointer+1);
					int o2 = m2 == 0 ? tape.get(tape.get(pointer+2)) : tape.get(pointer+2);
					if (ins % 100 == 7 ? (o1 < o2) : (o1 == o2))
						tape.set(tape.get(pointer+3), 1);
					else
						tape.set(tape.get(pointer+3), 0);
					pointer += 4;
				} else
					throw new RuntimeException();
			}
		}
	}
}
