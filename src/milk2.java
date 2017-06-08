/*
ID: arjvik1
LANG: JAVA
TASK: milk2
*/
import java.io.*;
import java.util.*;

public class milk2 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("milk2.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
		boolean[] largeMilking = new boolean[1000000];
		int N = in.nextInt();
		int currEnd=0;
		for (int i = 0; i < N; i++) {
			int start = in.nextInt();
			int end = in.nextInt();
			for (int j = start; j < end; j++) {
				largeMilking[j]=true;
			}
			if(end>currEnd){
				currEnd=end;
			}
		}
		boolean[] milking=Arrays.copyOf(largeMilking, currEnd+1);
		largeMilking=null;
		boolean lastState=false;
		int startMilk=-1, startIdle=-1,
			maxMilk=0,maxIdle=0;
		for (int i = 0; i < milking.length; i++) {
			boolean b = milking[i];
			if(b==lastState){
				continue;
			}
			
			if(b==true){
				startMilk=i;
				if(startIdle!=-1){
					int idleTime = i-startIdle;
					if(maxIdle<idleTime){
						maxIdle=idleTime;
					}
				}
			}else{
				startIdle=i;
				if(startMilk!=-1){
					int milkTime = i-startMilk;
					if(maxMilk<milkTime){
						maxMilk=milkTime;
					}
				}
			}
			lastState=b;
		}
		out.println(maxMilk+" "+maxIdle);
		in.close();
		out.close();
	}
}
