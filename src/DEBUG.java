import java.util.stream.Stream;

class PrintWriter {
	public PrintWriter(Object wildcard) {
		
	}
	public void println(int i){
		System.out.println(i);
	}
	public void println(String s){
		System.out.println(s);
	}
	public void println(Object o){
		System.out.println(o);
	}
	public void close(){
		//System.out.println("PrintWriter closed");
	}
	public void print(int i){
		System.out.print(i);
	}
	public void print(String s){
		System.out.print(s);
	}
	public void print(Object o){
		System.out.print(o);
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
	java.util.Scanner scan;
	public Scanner(Object wildcard) {
		scan=new java.util.Scanner(System.in);
	}
	public int nextInt() {
		return scan.nextInt();
	}
	public long nextLong() {
		return scan.nextLong();
	}
	public String nextLine() {
		return scan.nextLine();
	}
	public boolean hasNext() {
		return scan.hasNext();
	}
	public boolean hasNextLine() {
		return scan.hasNextLine();
	}
	public void close() {
		//scan.close();
		//System.out.println("Scanner closed");
	}
}

class BufferedReader{
	java.io.BufferedReader reader;
	public BufferedReader(Object wildcard) {
		//scan = new java.util.Scanner(System.in);
		reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
	}
	public String readLine(){
		return reader.readLine();
		//return scan.hasNextLine()?scan.nextLine():null;
		//String line;
		//return scan.hasNextLine()?(!(line=scan.nextLine()).equals("eof")?line:null):null;
	}
	public Stream<String> lines(){
		return reader.lines();
	}
	public void close(){
		//scan.close();
	}
}

class FileReader{
	public FileReader(String string) {
		
	}
}
