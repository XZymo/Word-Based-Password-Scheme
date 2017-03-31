package NovelPasswordScheme;

import java.util.Set;
import java.util.HashSet;
import java.lang.Math;
import java.security.SecureRandom;
import java.nio.ByteBuffer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class PasswordGenerator {

	private Set<String> words;

	private String password;

	private SecureRandom generator;

	public PasswordGenerator(int wordCount){
		words = processWords();
		password = "";
		generator = new SecureRandom(ByteBuffer.allocate(Long.SIZE / Byte.SIZE).putLong(System.nanoTime()).array());
		generate(wordCount);
	}

	//Getters
	public String getPassword(){ return password; }

	private void generate(int desiredWordCount){
		int WORD_COUNT = words.size(), temp, numberIndex;
		Object[] WORD_SPACE = words.toArray();
		boolean hasNumber = false;

		while (5 > password.length() || password.length() > 25){
			password = "";
			hasNumber = false;
			numberIndex = generator.nextInt(desiredWordCount+2);
			for(int i = 0; i < desiredWordCount; ++i){
				if (i == numberIndex){
					temp = generator.nextInt(100);
					password += (temp < 10) ? "0" + (Integer.toString(temp)) : Integer.toString(temp);
					hasNumber = true;
				}
				password += WORD_SPACE[Math.abs(generator.nextInt()) % WORD_COUNT];
			}
			if (!hasNumber){
				temp = generator.nextInt(100);
				password += (temp < 10) ? "0" + (Integer.toString(temp)) : Integer.toString(temp);
				hasNumber = true;
			}
		}
	}

	private static Set<String> parseDictionary(BufferedReader r) throws IOException {
		Set<String> lines = new HashSet<String>();
		for (String line = r.readLine(); line != null; line = r.readLine())
			if (!lines.contains(line)) lines.add(line);
		return lines;
	}

	private static Set<String> processWords() {
		Set<String> result;
		try {
			BufferedReader r = new BufferedReader(new FileReader("NovelPasswordScheme/words.txt"));
			long start = System.nanoTime();
			result = parseDictionary(r);
			long stop = System.nanoTime();
			//("Execution time: " + 10e-9 * (stop-start) + "\n");
			return result;
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
		return null;
	}

	/** TEST USAGE **
	public static void main(String args[]){
		PasswordGenerator p = new PasswordGenerator(3);
		System.out.println(p.getPassword());
	}
	/**/
}