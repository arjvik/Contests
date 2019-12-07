public class Day2_1202ProgramAlarm {
	
	private static IntcodeVM vm = new IntcodeVM(2);
	
	public static void main(String[] args) {
		System.out.println("Part 1: " + part1());
		System.out.println("Part 2: " + part2());
	}

	private static int part1() {
		return test(12, 2);
	}

	private static int part2() {
		for (int i = 0; i < 100; i++)
			for (int j = 0; j < 100; j++) {
				if (test(i, j) == 19690720)
					return 100 * i + j;
			}
		return -1;
	}
	
	private static int test(int x, int y) {
		vm.reset();
		vm.getTape().set(1, x);
		vm.getTape().set(2, y);
		vm.run();
		return vm.getTape().get(0);
	}
}
