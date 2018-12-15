
/*
ID: arjvik1
LANG: JAVA
TASK: bphoto
*/
import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class bphoto {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("bphoto.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bphoto.out")));
		int n = in.nextInt();
		int[] height = new int[n];
		for (int i = 0; i < n; i++)
			height[i] = in.nextInt();
		int[] sortedIdx = IntStream.range(0, n).mapToObj(Integer::valueOf)
											   .sorted((i, j) -> height[j] - height[i])
											   .mapToInt(Integer::intValue).toArray();
		SegmentTreeNode root = new SegmentTreeNode(0, n);
		int unbalanced = 0;
		for (int idx : sortedIdx) {
			int l = root.count(0, idx);
			int r = root.count(idx+1, n);
			if (Math.max(l, r) > 2*Math.min(l, r))
				unbalanced++;
			root.increment(idx);
		}
		out.println(unbalanced);
		in.close();
		out.close();
	}

	public static class SegmentTreeNode {
		public final int startInclusive, endExclusive, split;
		public int value;
		public final SegmentTreeNode left, right;

		public SegmentTreeNode(int start, int end) {
			startInclusive = start;
			endExclusive = end;
			if (end - start == 1) {
				split = -1;
				left = null;
				right = null;
			} else if (end - start == 2) {
				split = start + (end - start) / 2;
				left = new SegmentTreeNode(start, split);
				right = new SegmentTreeNode(split, end);
			} else {
				split = start + (end - start) / 2 + 1;
				left = new SegmentTreeNode(start, split);
				right = new SegmentTreeNode(split, end);
			}
		}

		public void increment(int idx) {
			if (idx < startInclusive || idx >= endExclusive)
				return;
			value++;
			if (left != null)
				left.increment(idx);
			if (right != null)
				right.increment(idx);
		}

		public int count(int leftIdx, int rightIdx) {
//			System.out.printf("count [%d, %d) - node ([%d, %d)%n",
//					leftIdx, rightIdx, startInclusive, endExclusive);
			if (leftIdx < startInclusive || rightIdx > endExclusive)
				throw new RuntimeException("Error between Keyboard and Chair");
			else if (leftIdx == rightIdx)
				return 0;
			else if (leftIdx == startInclusive && rightIdx == endExclusive)
				return value;
			else if (leftIdx < split && rightIdx <= split)
				return left.count(leftIdx, rightIdx);
			else if (leftIdx >= split && rightIdx > split)
				return right.count(leftIdx, rightIdx);
			else
				return left.count(leftIdx, split) + right.count(split, rightIdx);
		}

		public static void runTests() {
			SegmentTreeNode root = new SegmentTreeNode(0, 10);
			as(root.count(0, 10)==0);
			as(root.count(0, 1)==0);
			as(root.count(0, 5)==0);
			as(root.count(0, 6)==0);
			as(root.count(0, 9)==0);
			root.increment(3);
			as(root.count(0, 2)==0);
			as(root.count(0, 3)==0);
			as(root.count(0, 4)==1);
			as(root.count(0, 6)==1);
			as(root.count(0, 7)==1);
			as(root.count(6, 10)==0);
		}
		
		private static void as(boolean b) {
			if (!b)
				throw new AssertionError();
		}
	}
}
