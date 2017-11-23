/*
ID: arjvik1
LANG: JAVA
TASK: Braille
*/
import java.io.*;
import java.util.*;
import static java.lang.System.out;

@SuppressWarnings("unused")
public class Braille {
	private static boolean numberflag = false,
						   capsflag = false,
						   allcapsflag = false;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("braille.dat")));
		int num_cases = in.nextInt();
		in.nextLine();
		for (int i = 0; i < num_cases; i++) {
			/*List<BraillePiece> row1 = new ArrayList<>(),
							   row2 = new ArrayList<>(),
							   row3 = new ArrayList<>();*/
			String r1 = in.nextLine();
			String r2 = in.nextLine();
			String r3 = in.nextLine();
			List<BrailleChar> list = new ArrayList<>();
			for (int j = 0; j < r1.length()/2; j++) {
				String p1 = r1.substring(2*j, 2*j+2);
				String p2 = r2.substring(2*j, 2*j+2);
				String p3 = r3.substring(2*j, 2*j+2);
				BrailleChar bc = new BrailleChar(p1.charAt(0)=='1', p2.charAt(0)=='1', p3.charAt(0)=='1', p1.charAt(1)=='1', p2.charAt(1)=='1', p3.charAt(1)=='1');
				System.out.print(bc.toString());
			}
			System.out.println();
		}
		in.close();
		out.close();
	}
	private static class BrailleChar {
		boolean b1, b2, b3, b4, b5, b6;

		public BrailleChar(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6) {
			super();
			this.b1 = b1;
			this.b2 = b2;
			this.b3 = b3;
			this.b4 = b4;
			this.b5 = b5;
			this.b6 = b6;
		}
		
		public String toString(){
			int flag = setFlag();
			if(flag==1)
				return "";
			else if(flag==2)
				return " ";
			else if(!b1 && b2 && !b3 && !b4 && b5 && b6)
				return ".";
			else if(numberflag){
				if(b1 && !b2 && !b3 && !b4 && !b5 && !b6)
					return "1";
				else if(b1 && b2 && !b3 && !b4 && !b5 && !b6)
					return "2";
				else if(b1 && !b2 && !b3 && b4 && !b5 && !b6)
					return "3";
				else if(b1 && !b2 && !b3 && b4 && b5 && !b6)
					return "4";
				else if(b1 && !b2 && !b3 && !b4 && b5 && !b6)
					return "5";
				else if(b1 && b2 && !b3 && b4 && !b5 && !b6)
					return "6";
				else if(b1 && b2 && !b3 && b4 && b5 && !b6)
					return "7";
				else if(b1 && b2 && !b3 && !b4 && b5 && !b6)
					return "8";
				else if(!b1 && b2 && !b3 && b4 && !b5 && !b6)
					return "9";
				else if(!b1 && b2 && !b3 && b4 && b5 && !b6)
					return "0";
				else
					return "?";
			}
			String a;
			if(!b3 && !b6){
				if(b1 && !b2 && !b4 && !b5)
					a= "a";
				else if(b1 && b2 && !b4 && !b5)
					a= "b";
				else if(b1 && !b2 && b4 && !b5)
					a= "c";
				else if(b1 && !b2 && b4 && b5)
					a= "d";
				else if(b1 && !b2 && !b4 && b5)
					a= "e";
				else if(b1 && b2 && b4 && !b5)
					a= "f";
				else if(b1 && b2 && b4 && b5)
					a= "g";
				else if(b1 && b2 && !b4 && b5)
					a= "h";
				else if(!b1 && b2 && b4 && !b5)
					a= "i";
				else if(!b1 && b2 && b4 && b5)
					a= "j";
				else
					a= "?";
			} else if(b3 && !b6){
				if(b1 && !b2 && !b4 && !b5)
					a= "k";
				else if(b1 && b2 && !b4 && !b5)
					a= "l";
				else if(b1 && !b2 && b4 && !b5)
					a= "m";
				else if(b1 && !b2 && b4 && b5)
					a= "n";
				else if(b1 && !b2 && !b4 && b5)
					a= "o";
				else if(b1 && b2 && b4 && !b5)
					a= "p";
				else if(b1 && b2 && b4 && b5)
					a= "q";
				else if(b1 && b2 && !b4 && b5)
					a= "r";
				else if(!b1 && b2 && b4 && !b5)
					a= "s";
				else if(!b1 && b2 && b4 && b5)
					a= "t";
				else
					a= "?";
			} else if(b3 && b6){
				if(b1 && !b2 && !b4 && !b5)
					a= "u";
				else if(b1 && b2 && !b4 && !b5)
					a= "v";
				else if(b1 && !b2 && b4 && !b5)
					a= "x";
				else if(b1 && !b2 && b4 && b5)
					a= "y";
				else if(b1 && !b2 && !b4 && b5)
					a= "z";
				else if(!b1 && b2 && b4 && b5)
					a= "w";
				else
					a= "?";
			} else {
				a= "?";
			}
			if(capsflag || allcapsflag){
				a= a.toUpperCase();
				capsflag = false;
			}
			return a;
		}

		private int setFlag() {
			if(!b1 && !b2 && !b3 && !b4 && b5 && b6){
				numberflag = false;
				capsflag = false;
				allcapsflag = false;
				return 1;
			}
			if(!b1 && !b2 && b3 && b4 && b5 && b6){
				numberflag = true;
				capsflag = false;
				allcapsflag = false;
				return 1;
			}
			if(!b1 && !b2 && !b3 && !b4 && !b5 && b6){
				numberflag = false;
				if(capsflag){
					capsflag = false;
					allcapsflag = true;
				}else {
					capsflag = true;
					allcapsflag = false;
				}
				return 1;
			}
			if(!b1 && !b2 && !b3 && !b4 && !b5 && !b6){
				numberflag = false;
				return 2;
			}
			return 0;
		}
		
	}
}
