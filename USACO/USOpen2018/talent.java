/*
ID: arjvik1
LANG: JAVA
TASK: talent
*/
import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class talent {
	public static class Team {
		public Team(int weight, int talent) {
			this.w = weight;
			this.t = talent;
		}
		public int w;
		public int t;
		public double ratio() {
			return (double) t/w;
		}
		public String toString() {
			return String.format("Team (w=%d, t=%d, ratio=%.2f)", w,t,ratio());
		};
	}
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("talent.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("talent.out")));
		int N = in.nextInt(),
			W = in.nextInt();
		Team overweight = new Team(6969,-6969);
		TreeSet<Team> underweight = new TreeSet<>((t, u) -> t.w - u.w);
		for (int i = 0; i < N; i++) {
			System.err.println("Processing cow "+i);
			int w = in.nextInt(),
				t = in.nextInt();
			if (w >= W) {
				Team ind = new Team(w,t);
				Team comb = new Team(overweight.w+w, overweight.t+t);
				if (ind.ratio() > overweight.ratio())
					overweight = ind;
				if (comb.ratio() > overweight.ratio())
					overweight = comb;
				ArrayList<Team> newUnderweight = new ArrayList<>();
				for (Team team : underweight) {
					Team newTeam = new Team(team.w+w, team.t+t);
					if (newTeam.w >= W && newTeam.ratio() > overweight.ratio())
							overweight = newTeam;
					else if (newTeam.w < W)
						addIf(underweight, newUnderweight, newTeam);
				}
				underweight.addAll(newUnderweight);
			} else {
				ArrayList<Team> newUnderweight = new ArrayList<>();
				Team comb = new Team(overweight.w+w, overweight.t+t);
				if (comb.ratio() > overweight.ratio())
					overweight = comb;
				for (Team team : underweight) {
					Team newTeam = new Team(team.w+w, team.t+t);
					if (newTeam.w >= W && newTeam.ratio() > overweight.ratio())
							overweight = newTeam;
					else if (newTeam.w < W)
						addIf(underweight, newUnderweight, newTeam);
				}
				underweight.addAll(newUnderweight);
				addIf(underweight, underweight, new Team(w,t));
			}
		}
		out.println((int)(overweight.ratio()*1000));
		in.close();
		out.close();
	}
	
	private static void addIf(TreeSet<Team> underweight, Collection<Team> newUnderweight, Team newTeam) {
//		Team ceil = underweight.ceiling(newTeam);
//		if (ceil == null || ceil.ratio() < newTeam.ratio())
			newUnderweight.add(newTeam);
	}
	
}
