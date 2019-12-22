import java.io.*;
import java.util.*;
public class Fibinary {
    public static final int[] F = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040};

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int NC = in.nextInt();
        for (int CN = 0; CN < NC; CN++) {
            int n = in.nextInt();
            System.out.println(fibinary(n));
        }
        in.close();
    }

    private static int[][] dp = new int[40][1000005];
    private static boolean[][] dp_calc = new boolean[40][1000005];
    private static int fibinary(int n) {
        int ins = Arrays.binarySearch(F, n);
        if (ins < 0)
            ins = -(ins + 1);
        else
            ins++;
        int ans =  fibinary(ins-1, n);
        
        return ans;
    }

    private static int fibinary(int i, int n) {
        int ans;
        if (n == 0) ans=1;
        if (n == 1) ans=2;
        else if (i < 0) ans=0;
        else if (n < 0) ans=0;
        else if (dp_calc[i][n]) ans=dp[i][n];
        else {
            ans = dp[i][n] = fibinary(i-1, n) + fibinary(i-1, n - F[i]);
            dp_calc[i][n] = true;
        }
        return ans;
    }
}