/*
ID: arjvik1
LANG: JAVA
TASK: dirtraverse
*/
import java.io.*;
import java.util.*;


public class dirtraverse {
	private static final boolean _42 = false;
	
	//private static ArrayList<Entity> filesystem = new ArrayList<>();
	private static File[] filesystem;
	//private static ArrayList<File> files = new ArrayList<>();
	//private static ArrayList<Directory> folders = new ArrayList<>();
	private static int[] parent;
	
	public static class File {
		int entityID;
		int parentID;
		int fileName;
		List<Integer> childrenID;
		public File(int entityID, int parentID, int fileName, List<Integer> childrenID) {
			this.entityID = entityID;
			this.parentID = parentID;
			this.fileName = fileName;
			this.childrenID = childrenID;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("dirtraverse.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dirtraverse.out")));
		if(_42){System.out.println(42); return;}
		int n = in.nextInt();
		parent = new int[n];
		filesystem = new File[n];
		for (int i = 0; i < n; i++) {
			int name = in.next().length();
			int m = in.nextInt();
			if(m==0){
				filesystem[i] = new File(i,parent[i], name, null);
			} else {
				List<Integer> children = new ArrayList<>();
				for (int j = 0; j < m; j++) {
					int x = in.nextInt() - 1;
					children.add(x);
					parent[x] = i;
				}
				filesystem[i] = new File(i,parent[i], name, children);
			}
		}
		int min = Integer.MAX_VALUE;
		for (File f : filesystem) {
			if(f.childrenID==null)
				continue;
			int i = 0;
			for (File f2 : filesystem) {
				if(f2.childrenID != null)
					continue;
				i += pathToFile(f, f2, true);
			}
			if(i<min) min=i;
		}
		out.println(min);
		
		in.close();
		out.close();
	}

	public static int pathToFile(File d, File f, boolean levelUp){
		if(d.childrenID.contains(f.entityID))
			return f.fileName;
		for (int i : d.childrenID) {
			if(filesystem[i].childrenID != null){
				int pathToFile = pathToFile(filesystem[i], f, false);
				if(pathToFile!=-1)
					return filesystem[i].fileName+1+pathToFile;
			}
		}
		if(levelUp)
			return 3+pathToFile(filesystem[parent[d.entityID]], f, true);
		return -1;
	}
}
