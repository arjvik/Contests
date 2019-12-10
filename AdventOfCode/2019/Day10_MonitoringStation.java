import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class Day10_MonitoringStation {
	public static void main(String[] args) throws IOException {
		List<String> input = ADVENTOFCODEINPUT.readLineList(10);
		Set<Point> asteroids = new HashSet<>();
		for (int i = 0; i < input.size(); i++)
			for (int j = 0; j < input.get(0).length(); j++)
				if(input.get(i).charAt(j) == '#')
					asteroids.add(new Point(j, i));
		int bestDetected = 0;
		Map<Double, SortedSet<Point>> bestAngles = null;
		for (Point station : asteroids) {
			SortedMap<Double, SortedSet<Point>> angles = new TreeMap<>();
			for (Point other : asteroids)
				if (station != other) {
					double angle = (Math.atan2((double) (other.y-station.y), (double) (other.x-station.x))+5*Math.PI/2) % (2*Math.PI);
					if (angles.containsKey(angle))
						angles.get(angle).add(other);
					else {
						TreeSet<Point> points = new TreeSet<>((p1, p2) -> Double.compare(
								Math.sqrt((station.y - p1.y) * (station.y - p1.y)
										+ (station.x - p1.x) * (station.x - p1.x)),
								Math.sqrt((station.y - p2.y) * (station.y - p2.y)
										+ (station.x - p2.x) * (station.x - p2.x))));
						points.add(other);
						angles.put(angle, points);
					}
				}
			if (angles.size() > bestDetected) {
				bestDetected =  angles.size();
				bestAngles = angles;
			}
		}
		System.out.println("Part 1: "+bestDetected);
		LinkedList<Point> pointsDestroyed = new LinkedList<>();
		while (!bestAngles.isEmpty()) {
			List<Double> toClear = new LinkedList<>();
			for (double angle : bestAngles.keySet()) {
				Point toDestroy = bestAngles.get(angle).first();
				bestAngles.get(angle).remove(toDestroy);
				pointsDestroyed.add(toDestroy);
				if (bestAngles.get(angle).size() == 0)
					toClear.add(angle);
			}
			for (double angle : toClear)
				bestAngles.remove(angle);
		}
		Point p2 = pointsDestroyed.get(199); // bruh element 200 is at index 199
		System.out.println("Part 2: "+(100*p2.x+p2.y));
	}
}
