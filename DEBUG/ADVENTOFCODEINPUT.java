import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.net.ssl.HttpsURLConnection;

public class ADVENTOFCODEINPUT {
	public static final int YEAR = 2019;
	private static String sessionCookie;

	public static Stream<String> readLines(int day) {
		try {
			URL url = new URL(String.format("https://adventofcode.com/%d/day/%d/input", YEAR, day));
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Cookie", String.format("session=%s", getSessionCookie()));
			int responseCode = con.getResponseCode();
			if (responseCode == HttpsURLConnection.HTTP_OK) {
				java.io.BufferedReader in = new java.io.BufferedReader(new InputStreamReader(con.getInputStream()));
				return in.lines();
			}
			throw new RuntimeException("Bad response: "+responseCode);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static IntStream readNums(int day) {
		return readLines(day).map(Integer::parseInt).mapToInt(Integer::intValue);
	}
	
	public static List<String> readLineList(int day) {
		return readLines(day).collect(Collectors.toList());
	}
	
	public static List<Integer> readNumList(int day) {
		return readLines(day).map(Integer::parseInt).collect(Collectors.toList());
	}
	
	private static String getSessionCookie() {
		try {
			if (sessionCookie == null) {
				Properties properties = new Properties();
				properties.load(new BufferedInputStream(new FileInputStream("AdventOfCode/adventofcode.properties")));
				sessionCookie = properties.getProperty("adventofcode.session");
			}
			return sessionCookie;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
