
class PrintWriter {

	public PrintWriter(Object wildcard) {
		// TODO Auto-generated constructor stub
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
}
