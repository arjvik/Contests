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
		System.out.println("PrintWriter closed");
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
}

class Scanner{
	java.util.Scanner scan;
	
	public Scanner(Object wildcard) {
		scan=new java.util.Scanner(System.in);
	}

	public int nextInt() {
		return scan.nextInt();
	}

	public String nextLine() {
		return scan.nextLine();
	}
	
	public void close() {
		scan.close();
		System.out.println("Scanner closed");
	}
}

class BufferedReader{

	public BufferedReader(Object wildcard) {
		
	}
}

class FileReader{

	public FileReader(String string) {
		
	}
	
}