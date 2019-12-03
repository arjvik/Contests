import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
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
		return readLines(day).flatMap(s -> Arrays.stream(s.split(",")))
							 .mapToInt(Integer::parseInt);
	}
	
	public static Stream<String> readWords(int day) {
		return readLines(day).flatMap(s -> Arrays.stream(s.split(",")));
	}
	
	public static List<String> readLineList(int day) {
		return readLines(day).collect(Collectors.toList());
	}
	
	public static List<Integer> readNumList(int day) {
		return readLines(day).flatMap(s -> Arrays.stream(s.split(",")))
							 .map(Integer::parseInt)
							 .collect(Collectors.toList());
	}
	
	public static List<String> readWordList(int day) {
		return readWords(day).collect(Collectors.toList());
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
