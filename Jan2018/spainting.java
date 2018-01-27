/*
ID: arjvik1
LANG: JAVA
TASK: spainting
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class spainting {
	private static final String SPACES = "                                                               ";
	private static int n;
	private static int m;
	private static int k;
	private static int c;
	
	private static final long MOD = 1000000007;
	private static long count = 0;

	private static int DEBUG = 2;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("spainting.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spainting.out")));
		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		c = n + 1 - k;
		Canvas blank = new Canvas(n);
		for (int i = 0; i < n; i++) {
			blank.add(0);
		}
		Set<Canvas> exists = new HashSet<>();
		exists.add(blank);
		out.println(run(blank,exists,0));
		in.close();
		out.close();
	}
	private static int run(Canvas canvas, Set<Canvas> exists, final int debug_level) {
		if(DEBUG > 2){
			System.err.print(SPACES.substring(0,debug_level));
			System.err.printf("Running canvas = %s exists = %s%n",canvas,exists);
		}
		int count = 0;
		if(canvas.isComplete(debug_level))
			count++;
		for (int i = 1; i <= m; i++) {
			for (int j = 0; j < c; j++) {
				Canvas canvas2 = new Canvas(n);
				canvas2.addAll(canvas);
				canvas2.paint(i, j);
				if(exists.add(canvas2)){
					if (DEBUG > 0){
						boolean complete = canvas2.isComplete(debug_level);
						if(DEBUG > 2)
							System.err.print(SPACES.substring(0,debug_level+1));
						if(complete)
							System.err.printf("STRUCK GOLD! Canvas: %s:%n",canvas2);
						else
							System.err.printf("DANG IT(NC)! Canvas: %s%n",canvas2);
						
					}
					count+=run(canvas2,exists,debug_level+1);
					
				}
				else if (DEBUG > 2){
					System.err.print(SPACES.substring(0,debug_level+1));
					System.err.printf("Not running (loop) canvas = %s%n",canvas);
				}
					
			}
		}
		if(DEBUG > 2){
			System.err.print(SPACES.substring(0,debug_level));
			System.err.printf("Returning %d%n",count);
		}
		return count;
	}
	private static class Canvas extends ArrayList<Integer>{
		int nonblank;
		private static final long serialVersionUID = 1L;
		public Canvas() {
			super();
			nonblank=0;
		}
		public Canvas(Canvas c) {
			super(c);
			nonblank = c.nonblank;
		}
		public Canvas(int initialCapacity) {
			super(initialCapacity);
			nonblank=0;
		}
		public void paint(int color, int pos){
			for (int i = 0; i < k; i++) {
				if(set(i+pos,color) == 0)//old value was blank
					nonblank++; //we just painted a blank space
			}
		}
		public boolean isComplete(final int debug_level){
			if(DEBUG > 1){
				if(DEBUG > 2)
					System.err.print(SPACES.substring(0,debug_level));
				System.err.printf("Checking completedness of %s, nonblank=%d%n",Canvas.this,nonblank);
			}
			//return nonblank==size();
			for (Integer i : Canvas.this) {
				if(i==0){
					if(DEBUG > 1){
						if(DEBUG > 2)
							System.err.print(SPACES.substring(0,debug_level));
						System.err.printf("Returning false%n",Canvas.this,nonblank);
					}
					return false;
				}
			}
			if(DEBUG > 1){
				if(DEBUG > 2)
					System.err.print(SPACES.substring(0,debug_level));
				System.err.printf("Returning true%n",Canvas.this,nonblank);
			}
			return true;
		}
		public String toString(){
			StringBuilder sb = new StringBuilder();
			for (Integer i : Canvas.this) {
				sb.append("_ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(i));
				sb.append(' ');
			}
			return sb.toString();
		}
	}
	private static void inc(){
		count++;
		chk();
	}
	private static void inc(int i){
		count+=i;
		chk();
	}
	private static void chk() {
		if(count==MOD)
			count = 0;
	}
	private static String str(Canvas c){
		StringBuilder sb = new StringBuilder();
		for (Integer integer : c) {
			sb.append("_ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(integer));
		}
		return sb.toString();
	}
}
