import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class RGBSubstring_1196D {
	private static List<Character> rgb = Arrays.asList('R', 'G', 'B');
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int numCases = in.nextInt();
		for (int caseNum = 0; caseNum < numCases; caseNum++) {
			int n = in.nextInt(),
				k = in.nextInt();
			int[] col = new int[n];
			String s = in.next();
			int idx = 0;
			for (char c : s.toCharArray())
				col[idx++] = rgb.indexOf(c);
			int minChanges = Integer.MAX_VALUE;
			for (int startColorTrial = 0; startColorTrial < 3; startColorTrial++) {
				int bColor = startColorTrial;
				int changes = 0;
				int eColor = startColorTrial;
				
				for (int i = 0; i < k; i++) {
					if (eColor != col[i])
						changes++;
					eColor = (eColor+1)%3;
				}
				minChanges = Math.min(changes, minChanges);
				
				for (int start = 1; start <= n-k; start++) {
					if (bColor != col[start-1])
						changes--;
					if (eColor != col[start+k-1])
						changes++;
					minChanges = Math.min(changes, minChanges);
					bColor = (bColor+1)%3;
					eColor = (eColor+1)%3;
				}
			}
			System.out.println(minChanges);
		} 
	}
	
}
