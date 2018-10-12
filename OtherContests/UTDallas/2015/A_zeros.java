import java.io.File;

public class A_zeros {

	public static void main(String[] args) {
		Scanner in = new Scanner(new File("A.txt"));
		int n = in.nextInt();
		int CASE_NUM = 1;
		while(n != -1) {
			int s = 0;
			int k = 0;
			int d = 5;
			do {
				k = n / d;
				s += k;
				d *= 5;
			} while(k != 0);
			System.out.printf("Case %d: %d%n", CASE_NUM++, s);
			n = in.nextInt();
		}
	}

}
