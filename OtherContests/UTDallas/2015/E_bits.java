import java.io.File;

public class E_bits {
	public static void main(String[] args) {
		Scanner in = new Scanner(new File("E.txt"));
		int n = in.nextInt();
		for(int kkk = 0; kkk<n;kkk++) {
			int x = in.nextInt();
			int p = x;
			int s = 0;
			while(x != 0) {
				s += x;
				x /= 2;
			}
			System.out.println(p + " " + s);
		}
	}

}
