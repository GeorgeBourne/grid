package grid.text.evolution;

import grid.common.CountMap;
import grid.common.TextUtils;
import grid.text.index.Pos;
import grid.text.index.TextIndexer;

/**
 * EntropyJudger.java 2013-9-10 11:06:24
 * 
 * @Author George Bourne
 */
public class EntropyJudger {

	private TextIndexer indexer;

	/**
	 * A word least appeared count
	 */
	private static int LEAST_COUNT_THRESHOLD = 5;

	/**
	 * Threshold for solid rate calculated by word appeared count and every
	 * single letter.
	 * 
	 * The smaller this values is, more new words you will get, but with less
	 * accuracy. The greater this value is, less new words you will get, but
	 * with high accuracy.
	 */
	private static double SOLID_RATE_THRESHOLD = 0.018;

	/**
	 * Threshold for entropy value calculated by candidate word prefix character
	 * count and suffix character count
	 * 
	 * The smaller this values is, more new words you will get, but with less
	 * accuracy. The greater this value is, less new words you will get, but
	 * with high accuracy.
	 */
	private static double ENTROPY_THRESHOL = 1.92;

	public EntropyJudger(TextIndexer indexer) {
		this.indexer = indexer;
	}

	public boolean judge(String candidate) {
		double solidRate = getSolidRate(candidate);

		if (solidRate < SOLID_RATE_THRESHOLD) {
			return false;
		}

		double entropy = getEntropy(candidate);

		if (entropy < ENTROPY_THRESHOL) {
			return false;
		}
		return true;
	}

	private double getEntropy(String candidate) {
		Pos pos = new Pos(candidate);
		CountMap<Character> frontCountMap = new CountMap<Character>();
		CountMap<Character> backCountMap = new CountMap<Character>();
		final int candidateLen = candidate.length();
		int off = 0;
		char c;
		double rate, frontEntropy = 0, backEntropy = 0;

		while (indexer.find(pos).isFound()) {
			off = pos.getPos();

			c = indexer.charAt(off - 1);
			if (TextUtils.isCnLetter(c)) {
				frontCountMap.increase(c);
			}
			c = indexer.charAt(off + candidateLen);
			if (TextUtils.isCnLetter(c)) {
				backCountMap.increase(c);
			}

		}

		for (char key : frontCountMap.keySet()) {
			rate = (double) frontCountMap.get(key) / frontCountMap.count();
			frontEntropy -= rate * Math.log(rate);
		}
		for (char key : backCountMap.keySet()) {
			rate = (double) backCountMap.get(key) / backCountMap.count();
			backEntropy -= rate * Math.log(rate);
		}

		return frontEntropy > backEntropy ? backEntropy : frontEntropy;

	}

	/**
	 * @param candidate
	 * @return
	 */
	public double getSolidRate(String candidate) {

		final int candidateLen = candidate.length();

		if (candidateLen < 2) {
			return 1;
		}

		final int count = indexer.count(candidate);
		double rate = 1;

		if (count < LEAST_COUNT_THRESHOLD) {
			return 0;
		}

		for (int i = 0; i < candidateLen; i++) {
			rate *= (double) count / indexer.count("" + candidate.charAt(i));
		}

		return Math.pow(rate, 1D / candidateLen) * Math.sqrt(candidateLen);
	}

	public void setIndexer(TextIndexer indexer) {
		this.indexer = indexer;
	}

}
