/*
ID: arjvik1
LANG: JAVA
TASK: friday
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class friday {
	private static final int SATURDAY=0,SUNDAY=1,MONDAY=2,TUESDAY=3,WEDNESDAY=4,THURSDAY=5,FRIDAY=6,
			JAN=0,FEB=1,MAR=2,APR=3,MAY=4,JUN=5,JUL=6,AUG=7,SEP=8,OCT=9,NOV=10,DEC=11,
			monthLength[]={31,-1,31,30,31,30,31,31,30,31,30,31};
	private static int currDay = MONDAY,
			currDate=1,
			currMonth=JAN,
			currYear=1900,
			count[] = new int[7];
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("friday.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
		System.err.print("How many years to check: ");
		final int finalYear=1899+in.nextInt();
		while(currYear<=finalYear){
			if(currDate==13){
				count[currDay]++;
				debug();
			}
			if(currMonth!=FEB){
				if(currDate<monthLength[currMonth]){
					currDate++;
				}else{
					currDate=1;
					if(currMonth!=DEC){
						currMonth++;
					}else{
						currMonth=JAN;
						currYear++;
					}
				}
			}else{
				if(currDate<28){
					currDate++;
				}else if(currDate==29){
					currMonth++;
					currDate=1;
				}else /*if(currDate==28)*/{
					if(isLeap(currYear)){
						currDate++;
					}else{
						currMonth++;
						currDate=1;
					}
				}
			}
			
			currDay=(currDay!=6) ? currDay+1 : SATURDAY;
		}
		for (int i = 0; i < 6; i++) {
			out.print(count[i]+" ");
		}
		out.println(count[FRIDAY]);
		in.close();
		out.close();
	}
	private static boolean isLeap(int year){
		return (year%100!=0) ? year%4==0 : year%400==0;
	}
	private static void debug(){
		String day;
		switch(currDay){
		case SATURDAY:
			day="SATURDAY";
			break;
		case SUNDAY:
			day="SUNDAY";
			break;
		case MONDAY:
			day="MONDAY";
			break;
		case TUESDAY:
			day="TUESDAY";
			break;
		case WEDNESDAY:
			day="WEDNESDAY";
			break;
		case THURSDAY:
			day="THURSDAY";
			break;
		case FRIDAY:
			day="FRIDAY";
			break;
		default:
			return;
		}
		String month;
		switch(currMonth){
		case JAN:
			month="JANUARY";
			break;
		case FEB:
			month="FEBRUARY";
			break;
		case MAR:
			month="MARCH";
			break;
		case APR:
			month="APRIL";
			break;
		case MAY:
			month="MAY";
			break;
		case JUN:
			month="JUNE";
			break;
		case JUL:
			month="JULY";
			break;
		case AUG:
			month="AUGUST";
			break;
		case SEP:
			month="SEPTEMBER";
			break;
		case OCT:
			month="OCTOBER";
			break;
		case NOV:
			month="NOVEMBER";
			break;
		case DEC:
			month="DECEMBER";
			break;
		default:
			return;
		}
		System.err.println("The 13th occured on "+day+", "+month+" "+currYear+" for time #"+count[currDay]);
	}
}