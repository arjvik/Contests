import java.util.*;

@SuppressWarnings("unused")
public class A_bulletins {

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new FileReader("A.txt")));
		while(true) {
			int n = in.nextInt();
			if(n==0)
				break;
			int w = in.nextInt();
			int h = in.nextInt();
			int[][] depth = new int[w][h];
			int maxDepth = 0;
			int notArea = w*h;
			int maxArea = 0;
			for(int casenum = 0; casenum < n; casenum++) {
				int x1 = in.nextInt(),
					y1 = in.nextInt(),
					x2 = in.nextInt(),
					y2 = in.nextInt();
				for(int i = x1; i < x2; i++) {
					for(int j = y1; j < y2; j++) {
						if(depth[i][j] == 0)
							notArea--;
						depth[i][j]++;
						if(depth[i][j] > maxDepth) {
							maxDepth = depth[i][j];
							maxArea = 1;
						} else if(depth[i][j] == maxDepth) {
							maxArea++;
						}
					}
				}
			}
			System.out.printf("%d %d %d%n", notArea, maxDepth, maxArea);
		}
	}

}
