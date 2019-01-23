/*
ID: arjvik1
LANG: JAVA
TASK: poetry
*/
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.*;

@SuppressWarnings("unused")
public class poetry {
	
	private static final int MOD = 1_000_000_007;
	private static final BigInteger BIMOD = BigInteger.valueOf(MOD);
	private static final BiFunction<Integer, Integer, Integer> incrementBy = (j,k) -> j+k;
	
	private static int N_NUM_WORDS,M_NUM_LINES,K_NUM_SYLLABLES,C_NUM_CLASSES;
	
	// Class -> Length -> Count; Class=-1: all words
	private static Map<Integer,Map<Integer, Integer>> words = new HashMap<>();
	
	// class -> #lines ending with class
	private static Map<Integer, Long> lineCount = new HashMap<>();
	
	// class -> n -> lineCount[class]^n
	private static Map<Integer, Map<Integer, Long>> lineExp = new HashMap<>();
	
	private static long[] dp;
	
	public static void main(String[] args) throws IOException {
		// READ INPUT
		java.util.Scanner in = new java.util.Scanner(new java.io.BufferedReader(new java.io.FileReader("poetry.in")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("poetry.out")));
		N_NUM_WORDS = in.nextInt();
		M_NUM_LINES = in.nextInt();
		K_NUM_SYLLABLES = in.nextInt();
		C_NUM_CLASSES = -1;
		words.put(-1, new HashMap<>());
		for (int idx_word = 0; idx_word < N_NUM_WORDS; idx_word++) {
			int si = in.nextInt();
			int ci = in.nextInt();
			C_NUM_CLASSES = Math.max(C_NUM_CLASSES, ci);
			if (!words.containsKey(ci))
				words.put(ci, new HashMap<>());
			words.get(ci).merge(si, 1, incrementBy);
			words.get(-1).merge(si, 1, incrementBy);
		}
		
		
		// IGNORING RHYME, FIND LINE COUNTS
		// O(K^2)  ~2.5 * 10^7
		dp = new long[K_NUM_SYLLABLES+1];
		dp[0] = 1;
		for (int n = 1; n <= K_NUM_SYLLABLES; n++) {
			for (int i = 1; i <= n; i++) {
				dp[n] += words.get(-1).getOrDefault(i, 0)*dp[n-i];
			}
			dp[n] %= MOD;
		}
		
		
		// NOW TAKE RHYME INTO CONSIDERATION
		// O(N^2)  ~2.5*10^7
		for (int clazz = 1; clazz <= C_NUM_CLASSES; clazz++) {
			if (!words.containsKey(clazz))
				continue;
			long sum = 0;
			for (int ending_word_length : words.get(clazz).keySet()) {
				sum += words.get(clazz).get(ending_word_length) * dp[K_NUM_SYLLABLES - ending_word_length];
				sum %= MOD;
			}
			lineCount.put(clazz, sum);
		}
		
		
		// FIND LETTER COUNTS IN RHYME SCHEME
		int[] letterCount = new int[26];
		for (int idx_line = 0; idx_line < M_NUM_LINES; idx_line++)
			letterCount[in.next().charAt(0)-'A']++;
		
		
		// USING LETTER COUNTS, USE THE EXPONENTIATION TRICK THINGY
		// TO FIND HOW MANY POEMS CAN BE MADE (DONT FORGET TO MOD EVERYWHERE)
		BigInteger ans = BigInteger.ONE;
		for (int clazz : lineCount.keySet())
			lineExp.put(clazz, new HashMap<>());
		for (int letter = 0; letter < 26; letter++) {
			if (letterCount[letter] == 0)
				continue;
			BigInteger mult = BigInteger.ZERO;
			for (int clazz : lineCount.keySet()) {
				BigInteger exp = BigInteger.valueOf(lineCount.get(clazz)).modPow(BigInteger.valueOf(letterCount[letter]), BIMOD);
				mult = mult.add(exp).mod(BIMOD);
			}
			ans = ans.multiply(mult).mod(BIMOD);
		}
		
		out.println(ans.mod(BIMOD).longValue());
		in.close();
		out.close();
	}
	
	private static long exp(long x, int n, int rhymeclass) {
		if (rhymeclass != -1 && lineExp.get(rhymeclass).containsKey(n))
			return lineExp.get(rhymeclass).get(n);
		x %= MOD;
		long ans;
		if (n == 0)
			ans = 1;
		else if (n == 1)
			ans = x;
		else if (n == 2)
			ans = x * x;
		else if (n == 3)
			ans = (x * x)%MOD * x;
		else if (n == 4) {
			long sq = (x * x)%MOD;
			ans = (sq*sq);
		}
		else if (n % 2 == 0) {
			long sq = exp(x, n/2, rhymeclass) % MOD;
			ans = sq * sq;
		} else {
			long sq = exp(x, n/2, rhymeclass) % MOD;
			ans = sq * sq * x;
		}
		ans %= MOD;
		lineExp.get(rhymeclass).put(n, ans);
		return ans;
	}

}
