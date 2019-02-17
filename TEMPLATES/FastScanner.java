import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;


/**

FAST INPUT READER CLASS
Shamelessly stolen from Matt Fontaine
http://www.usaco.org/current/data/sol_disrupt_platinum_open18.html

Minimized version (suitable for copy-paste):

private static class FastScanner{private InputStream s;private byte[]b=new byte[1024];private int c;private int n;
	public FastScanner(InputStream s){this.s=s;}
	int read(){if(c>=n){c=0;try{n=s.read(b);}catch(IOException e){}if(n<=0)return-1;}return b[c++];}
	boolean isSpaceChar(int c){return c==' '||c=='\n'||c=='\r'||c=='\t'||c==-1;}
	boolean isEndline(int c){return c=='\n'||c=='\r'||c==-1;}
	int nextInt(){return Integer.parseInt(next());}
	long nextLong(){return Long.parseLong(next());}
	double nextDouble(){return Double.parseDouble(next());}
	String next(){int c=read();while(isSpaceChar(c))
		c=read();StringBuilder res=new StringBuilder();do{res.appendCodePoint(c);c=read();}while(!isSpaceChar(c));return res.toString();}
	String nextLine(){int c=read();while(isEndline(c))
		c=read();StringBuilder res=new StringBuilder();do{res.appendCodePoint(c);c=read();}while(!isEndline(c));return res.toString();}
}
*/
class FastScanner {
	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;

	public FastScanner(InputStream stream) {
		this.stream = stream;
	}

	int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	boolean isSpaceChar(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	boolean isEndline(int c) {
		return c == '\n' || c == '\r' || c == -1;
	}

	int nextInt() {
		return Integer.parseInt(next());
	}

	long nextLong() {
		return Long.parseLong(next());
	}

	double nextDouble() {
		return Double.parseDouble(next());
	}

	String next() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
	}

	String nextLine() {
		int c = read();
		while (isEndline(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isEndline(c));
		return res.toString();
	}
}