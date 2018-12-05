/*
ID: arjvik1
LANG: JAVA
TASK: Day4_ReposeRecord
*/
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("unused")
public class Day4_ReposeRecord {
	private static final Map<Integer, int[]> sleepCount = new HashMap<>();
	private static final Map<Integer, Integer> sleepiness = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		List<String> input = new ArrayList<>();
		String line;
		while (!(line = in.nextLine()).equals("EOF"))
			input.add(line);
		input.sort(null);
		System.out.println("----");
		System.out.printf("Part 1: %d%nPart 2: %d%n", part1(input), part2(input));
		in.close();
	}

	private static Object part1(List<String> input) {
		int currentGuard = -1;
		int asleepSince = -1;
		for (String line : input) {
			int time = time(line);
			String text = msg(line);
			if (text.startsWith("falls asleep")) {
				asleepSince = time;
			} else if (text.startsWith("wakes up")) {
				while (asleepSince < time) {
					sleptAt(currentGuard, asleepSince);
					asleepSince++;
				}
			} else {
				currentGuard = Integer.parseInt(text.substring(7).split(" ")[0]);
			}
		}
		
		int guard = -1;
		int max = -1;
		for (int id : sleepiness.keySet()) {
			if (sleepiness.get(id) > max) {
				guard = id;
				max = sleepiness.get(id);
			}
		}
		
		int[] count = sleepCount.get(guard);
		int idx = -1;
		max = -1;
		for (int i = 0; i < 60; i++) {
			if (count[i] > max) {
				idx = i;
				max = count[i];
			}
		}
		return guard*idx;
	}
	
	private static void sleptAt(int currentGuard, int time) {
		if (!sleepCount.containsKey(currentGuard)) {
			sleepCount.put(currentGuard, new int[60]);
			sleepiness.put(currentGuard, 0);
		}
		sleepCount.get(currentGuard)[time]++;
		sleepiness.put(currentGuard, sleepiness.get(currentGuard)+1);
	}

	private static int time(String line) {
		return Integer.parseInt(line.split("\\] ")[0].substring(15));
	}

	private static String msg(String line) {
		return line.split("\\] ")[1];
	}
	
	private static int part2(List<String> input) {
		int max = -1;
		int res = -1;
		for (int id : sleepCount.keySet()) {
			for (int min = 0; min < 60; min++) {
				if (sleepCount.get(id)[min] > max) {
					max = sleepCount.get(id)[min];
					res = id*min;
				}
			}
		}
		return res;
	}
}
