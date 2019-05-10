//import java.io.*;
//import java.util.*;
//import java.util.stream.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class kavya {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("kavya.dat"))), sxxx = in;

		while (in.hasNextLine()) {
			String s = in.nextLine().replaceAll("[^a-zA-Z]", "").toUpperCase();
			HashMap<Character, Integer> hm = new HashMap<>();
			for(char c : s.toCharArray())
				if (hm.containsKey(c))
					hm.put(c, 1+hm.get(c));
				else
					hm.put(c, 1);
			
			List<Character> l = hm.keySet().stream().collect(Collectors.toList());
			l.sort((c, d) -> {
				if (hm.get(c) != hm.get(d))
					return hm.get(d) - hm.get(c);
				return s.indexOf(c) - s.indexOf(d);
			});
			for (int i = 0; i < l.size(); i++) {
				for (int j = 0; j < hm.get(l.get(i)); j++)
					System.out.print((l.get(i)));
			}
			System.out.println();
		}

		in.close();
	}
}
