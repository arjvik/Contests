/*
ID: arjvik1
LANG: JAVA
TASK: Words
*/
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class Words {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("words.dat")));
		List<String> dictionary = new ArrayList<>();
		int n = in.nextInt();
		in.nextLine();
		for (int i = 0; i < n; i++) {
			String line = in.nextLine();
			StringTokenizer st = new StringTokenizer(line);
			while(st.hasMoreTokens()){
				dictionary.add(st.nextToken());
			}
		}
		dictionary.sort(null);
		n = in.nextInt();
		in.nextLine();
		for (int i = 0; i < n; i++) {
			String line = in.nextLine();
			StringTokenizer st = new StringTokenizer(line);
			int length = Integer.parseInt(st.nextToken());
			String suffix = st.nextToken();
			AtomicBoolean ab = new AtomicBoolean(false);
			dictionary.stream()
					  .sequential()
					  .filter(s -> s.length()==length)
					  .filter(s -> s.endsWith(suffix))
					  .peek(s -> ab.set(true))
					  .forEachOrdered(System.out::println);
			if(!ab.get()){
				System.out.println("NONE");
			}
			System.out.println();
		}
		in.close();
	}
}
