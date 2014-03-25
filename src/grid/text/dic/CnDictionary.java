package grid.text.dic;

import grid.common.CountMap;
import grid.common.TextDatReader;
import grid.common.TextUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * CnDictionary.java 2013-9-20 17:05:49
 * 
 * @Author George Bourne
 */
public class CnDictionary {

	private final String COMMON_WORD_DIC_PATH = "common.dic";

	/**
	 * This text data is for character statistic. Change to your own if you
	 * like.
	 */
	private final String COMMON_LETTER_RESOURCE_PATH = "text.dat";

	private Set<String> dictionary = new HashSet<String>();

	private CountMap<Character> letterCountMap = new CountMap<Character>();

	private int totalLetterCount;

	private static CnDictionary instance;

	public static CnDictionary Instance() {
		if (null == instance) {
			try {
				instance = new CnDictionary();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	private CnDictionary() throws IOException {
		initWordDic();
		initLetterCountMap();
	}

	private void initLetterCountMap() throws IOException {
		String letterResource = TextDatReader.read(COMMON_LETTER_RESOURCE_PATH);
		final int len = letterResource.length();
		char c;
		for (int i = 0; i < len; i++) {
			c = letterResource.charAt(i);
			if (TextUtils.isCnLetter(c)) {
				letterCountMap.increase(c);
			}
		}
		totalLetterCount = letterCountMap.count();

	}

	private void initWordDic() throws IOException {

		String bytes = TextDatReader.read(COMMON_WORD_DIC_PATH);
		final int len = bytes.length();
		String s = "";
		char c;
		for (int i = 0; i < len; i++) {
			c = bytes.charAt(i);

			if ('\n' == c || '\r' == c || 0 == c) {
				if (!TextUtils.isBlank(s)) {
					dictionary.add(s.trim());
				}
				s = "";
			} else {
				s += c;
			}
			if (0 == c) {
				break;
			}
		}
	}

	public boolean contains(String word) {
		return dictionary.contains(word);
	}

	public double rate(char c) {
		return (double) letterCountMap.get(c) / totalLetterCount;
	}

	public int size() {
		return dictionary.size();
	}
}
