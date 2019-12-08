import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class Day7_AmplificationCircuit {
	
	
	public static void main(String[] args) throws Exception {
		System.out.println("Part 1: " + part1());
		System.out.println("Part 2: " + part2());
	}

	private static int part1() {
		IntcodeVM vm = new IntcodeVM(7);
		return Permutations.getAllPermutations(new ArrayList<>(Arrays.asList(0,1,2,3,4)))
					.stream()
					.map(perm -> new Pair<List<Integer>, Integer>(perm, 0))
					.map(pair -> new Pair<List<Integer>, Integer>(pair.x, vm.runAndReset(pair.x.get(0), pair.y).poll()))
					.map(pair -> new Pair<List<Integer>, Integer>(pair.x, vm.runAndReset(pair.x.get(1), pair.y).poll()))
					.map(pair -> new Pair<List<Integer>, Integer>(pair.x, vm.runAndReset(pair.x.get(2), pair.y).poll()))
					.map(pair -> new Pair<List<Integer>, Integer>(pair.x, vm.runAndReset(pair.x.get(3), pair.y).poll()))
					.map(pair -> new Pair<List<Integer>, Integer>(pair.x, vm.runAndReset(pair.x.get(4), pair.y).poll()))
					.mapToInt(Pair::getY)
					.max()
					.getAsInt();
	}

	private static int part2() throws Exception {
		IntcodeVM[] vms = new IntcodeVM[5];
		Thread[] threads = new Thread[vms.length];
		for (int i = 0; i < vms.length; i++) {
			vms[i] = new IntcodeVM(7);
			vms[i].setOutputOnlyNonZeros(false);
		}
		int bestAmp = Integer.MIN_VALUE;
		for (List<Integer> perm : Permutations.getAllPermutations(new ArrayList<>(Arrays.asList(5,6,7,8,9)))) {
			for (IntcodeVM vm : vms)
				vm.reset();
			for (int i = 0; i < vms.length-1; i++) {
				Queue<Integer> q = new LinkedBlockingDeque<>();
				vms[i].setOutput(q);
				vms[i+1].setInput(q);
			}
			Queue<Integer> loopback = new LinkedBlockingDeque<>();
			vms[0].setInput(loopback);
			vms[vms.length-1].setOutput(loopback);
			for (int i = 0; i < vms.length; i++)
				vms[i].getInput().add(perm.get(i));
			vms[0].getInput().add(0);
			for (int i = 0; i < threads.length; i++) {
				final int vmnum = i;
				threads[i] = new Thread(() -> vms[vmnum].run());
				threads[i].start();
			}
			for (int i = 0; i < threads.length; i++)
				threads[i].join();
			bestAmp = Math.max(bestAmp, loopback.poll());
		}
		return bestAmp;
	}
	
}
