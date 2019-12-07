import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day7_Test {
	
	
	public static void main(String[] args) {
		System.out.println("Part 1: " + part1());
		System.out.println("Part 2: " + part2());
	}

	private static int part1() {
		IntcodeVM vm = new IntcodeVM(7);
		return Permutations.getAllPermutations(new ArrayList<>(Arrays.asList(0,1,2,3,4)))
					.stream()
					.map(perm -> new Pair<List<Integer>, Integer>(perm, 0))
					.map(pair -> new Pair<List<Integer>, Integer>(pair.x, vm.runAndReset(pair.x.get(0), pair.y).get(0)))
					.map(pair -> new Pair<List<Integer>, Integer>(pair.x, vm.runAndReset(pair.x.get(1), pair.y).get(0)))
					.map(pair -> new Pair<List<Integer>, Integer>(pair.x, vm.runAndReset(pair.x.get(2), pair.y).get(0)))
					.map(pair -> new Pair<List<Integer>, Integer>(pair.x, vm.runAndReset(pair.x.get(3), pair.y).get(0)))
					.map(pair -> new Pair<List<Integer>, Integer>(pair.x, vm.runAndReset(pair.x.get(4), pair.y).get(0)))
					.mapToInt(Pair::getY)
					.max()
					.getAsInt();
	}

	private static int part2() {
		return -1;
	}
	
}
