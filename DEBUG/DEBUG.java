import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.util.stream.Stream;

class PrintWriter implements Closeable {
	private static PrintStream oldOut;
	private static boolean warn = false;
	static{
		oldOut = System.out;
		System.setOut(new PrintStream(System.out) {
			@Override
			public void println(String s) {
				if(!warn) {
						oldOut.print("PLEASE DO NOT USE SYSTEM.OUT! USE REGULAR OUT INSTEAD!\n");
					System.err.print("PLEASE DO NOT USE SYSTEM.OUT! USE REGULAR OUT INSTEAD!\n");
					warn = true;
				}
				super.println(s);
			}
			@Override
			public void println(int i) {
				if(!warn) {
						oldOut.print("PLEASE DO NOT USE SYSTEM.OUT! USE REGULAR OUT INSTEAD!\n");
					System.err.print("PLEASE DO NOT USE SYSTEM.OUT! USE REGULAR OUT INSTEAD!\n");
					warn = true;
				}
				super.println(i);
			}
		});
	}
	public PrintWriter(Object wildcard) {
		
	}
	public void println(int i){
		oldOut.println(i);
	}
	public void println(String s){
		oldOut.println(s);
	}
	public void println(Object o){
		oldOut.println(o);
	}
	public void println(char c){
		oldOut.println(c);
	}
	public void close(){
		//oldOut.println("PrintWriter closed");
	}
	public void print(int i){
		oldOut.print(i);
	}
	public void print(String s){
		oldOut.print(s);
	}
	public void print(Object o){
		oldOut.print(o);
	}
	public void print(char c){
		System.out.print(c);
	}
	public void println() {
		System.out.println();
	}
}

class BufferedWriter{
	public BufferedWriter(Object wildcard) {
		
	}
}

class FileWriter{
	public FileWriter(String string) {
		
	}
}

class Scanner{
	static{
		System.setProperty("DEBUG","DEBUG");
	}
	java.util.Scanner scan;
	public Scanner(Object wildcard) {
		scan=new java.util.Scanner(System.in);
		System.err.println("Reading input from \"file\"...");
	}
	public Scanner(String string) {
		//Don't read from console when scanning String
		scan=new java.util.Scanner(string);
	}
	public int nextInt() {
		return scan.nextInt();
	}
	public long nextLong() {
		return scan.nextLong();
	}
	public double nextDouble() {
		return scan.nextDouble();
	}
	public boolean nextBoolean() {
		return scan.nextBoolean();
	}
	public String nextLine() {
		return scan.nextLine();
	}
	public String next() {
		return scan.next();
	}
	public boolean hasNext() {
		return scan.hasNext();
	}
	public boolean hasNextLine() {
		return scan.hasNextLine();
	}
	public boolean hasNextInt() {
		return scan.hasNextInt();
	}
	public boolean hasNextLong() {
		return scan.hasNextLong();
	}
	public boolean hasNextDouble() {
		return scan.hasNextDouble();
	}
	public boolean hasNextBoolean() {
		return scan.hasNextBoolean();
	}
	public void close() {
		//scan.close();
		//System.out.println("Scanner closed");
	}
	
}

class BufferedReader{
	java.io.BufferedReader reader;
	public BufferedReader(Object wildcard) {
		reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
	}
	public String readLine() throws IOException{
		String line = reader.readLine();
		return "eof".equals(line)?null:line;
	}
	public Stream<String> lines(){
		return reader.lines();
	}
	public void close(){
	}
}

class FileReader{
	public FileReader(String string) {
		
	}
}
