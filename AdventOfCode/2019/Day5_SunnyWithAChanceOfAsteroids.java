public class Day5_SunnyWithAChanceOfAsteroids {
	public static void main(String[] args) {
		System.out.println("Part 1: "+part1());
		System.out.println("Part 2: "+part2());
	}

	private static int part1() {
		return new IntcodeVM(5).run(1).get(0);
	}

	private static int part2() {
		return new IntcodeVM(5).run(5).get(0);
	}
}
