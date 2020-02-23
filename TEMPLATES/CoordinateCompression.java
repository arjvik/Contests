import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoordinateCompression {
	private List<Integer> coords;

	public CoordinateCompression(int... coordinates) {
		this(coordinates, false);
	}

	public CoordinateCompression(int[] coordinates, boolean sort) {
		List<Integer> coords = new ArrayList<>(coordinates.length);
		for (int c : coordinates)
			coords.add(c);
		this.coords = coords;
		if (sort)
			coords.sort(null);
	}

	public CoordinateCompression(List<Integer> coordinates) {
		this.coords = coordinates;
	}

	public int compress(int x) {
		return Collections.binarySearch(coords, x);
	}

	public int decompress(int c) {
		return coords.get(c);
	}
	
	public int size() {
		return coords.size();
	}
}