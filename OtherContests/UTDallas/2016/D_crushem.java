
import java.util.*;
import java.io.*;

@SuppressWarnings("unused")
public class D_crushem {

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new FileReader("D.txt")));
		loop:while(true) {
			int n = in.nextInt();
			in.nextLine();
			if(n==0)
				break loop;
			List<List<Integer>> grid = new ArrayList<>();
			for(int i = 0; i < 10; i++)
				grid.add(new ArrayList<>());
			
			for(int l = 0; l < 12; l++) {
				String s = in.nextLine();
				for(int i = 0; i < 10; i++)
					grid.get(i).add(c2i(s.charAt(i)));
			}
			grid.forEach(Collections::reverse);
			
			
			for(int iter = 0; iter < n; iter++) {
				final int col = in.next().charAt(0) - 'a';
				final int row = in.nextInt() - 1;
				int color1;
				try {
					color1 = grid.get(col).get(row);
				} catch (Exception e) { color1 = -1; }
				final int color = color1;
				if(setNull(grid, col, row, color) 
						< 3) {
					grid.forEach(c -> {
						for (int i = 0; i < c.size(); i++) {
							if(c.get(i) == null)
								c.set(i, color);
						}
					});
				}
				grid.forEach(c -> c.removeIf(Objects::isNull));
				grid.removeIf(List::isEmpty);
			}
			
			System.out.println(grid.stream()
									.mapToInt(List::size)
									.sum());
			
			
		}
		in.close();
	}

	private static void printGrid(List<List<Integer>> grid) {
		for(int l = 11; l >= 0; l--) {
			final int l2 = l;
			grid.forEach(col -> {
				try {
					System.out.print(i2c(col.get(l2)));
				} catch (Exception e) { System.out.print(" "); }
			});
			System.out.println();
		}
	}
	
	private static int setNull(List<List<Integer>> grid, int col, int row, int color) {
		if(col < 0 || col >= grid.size() || row < 0 || row >= grid.get(col).size())
			return 0;
		int changed = 0;
		if((grid.get(col).get(row) != null) && (grid.get(col).get(row) == color)) {
			grid.get(col).set(row, null);
			changed++;
			changed += setNull(grid, col+1, row, color);
			changed += setNull(grid, col-1, row, color);
			changed += setNull(grid, col, row+1, color);
			changed += setNull(grid, col, row-1, color);
		}
		return changed;
	}

	public static int c2i(char c) {
		switch (c) {
		case 'R':
			return 1;
		case 'B':
			return 2;
		case 'Y':
			return 3;
		default:
			return -1;
		}
	}
	
	public static char i2c(int i) {
		switch (i) {
		case 1:
			return 'R';
		case 2:
			return 'B';
		case 3:
			return 'Y';
		default:
			return ' ';
		}
	}

}
