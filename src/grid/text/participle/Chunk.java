package grid.text.participle;

import grid.text.dic.CnDictionary;

import java.util.List;

/**
 * Chunk.java 2013-10-2 22:32:36
 * 
 * @Author George Bourne
 */
public class Chunk implements Comparable<Chunk> {

	private List<String> list;

	private int len = 0;

	private double avg = 0;

	private double variance = 0;

	public Chunk(List<String> list) {
		this.list = list;
		init();
	}

	private void init() {

		for (String s : list) {
			len += s.length();
		}
		avg = (double) len / list.size();

		for (String s : list) {
			variance += Math.pow(avg - s.length(), 2);
		}
		variance = Math.sqrt(variance);
	}

	public int getLen() {
		return len;
	}

	public double getAvg() {
		return avg;
	}

	public double getVariance() {
		return variance;
	}

	public String getHead() {
		if (null == list || list.isEmpty()) {
			return "";
		}
		return list.get(0);
	}

	private int compareDouble(double d1, double d2) {
		if (d1 - d2 < -0.0000001D) {
			return 1;
		} else if (d1 - d2 > 0.0000001D) {
			return -1;
		}
		return 0;
	}

	@Override
	public int compareTo(Chunk o) {

		if (len != o.len) {
			return o.len - len;
		}

		int d = compareDouble(avg, o.avg);
		if (0 != d) {
			return d;
		}

		d = compareDouble(variance, o.variance);
		if (0 != d) {
			return d;
		}

		CnDictionary dictionary = CnDictionary.Instance();

		double rateSrc = 0, rateDest = 0;
		for (String s : list) {
			if (1 == s.length()) {
				rateSrc += dictionary.rate(s.charAt(0));
			}
		}
		for (String s : o.list) {
			if (1 == s.length()) {
				rateDest += dictionary.rate(s.charAt(0));
			}
		}
		return compareDouble(rateSrc, rateDest);
	}

	public String toString() {
		return list.toString();
	}
}
