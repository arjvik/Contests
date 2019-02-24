
public class SegmentTreeNode {
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

	public int sum(int leftIdx, int rightIdx) {
		if (leftIdx < startInclusive || rightIdx > endExclusive)
			throw new RuntimeException("Invalid indexes");
		else if (leftIdx == rightIdx)
			return 0;
		else if (leftIdx == startInclusive && rightIdx == endExclusive)
			return value;
		else if (leftIdx < split && rightIdx <= split)
			return left.sum(leftIdx, rightIdx);
		else if (leftIdx >= split && rightIdx > split)
			return right.sum(leftIdx, rightIdx);
		else
			return left.sum(leftIdx, split) + right.sum(split, rightIdx);
	}
}
