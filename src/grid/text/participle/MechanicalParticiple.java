package grid.text.participle;

import grid.common.TextUtils;

import java.util.Vector;

/**
 * MechanicalParticiple.java 2013-10-2 17:07:50
 * 
 * @Author George Bourne
 */
public class MechanicalParticiple {

	public Vector<String> partition(String document) {
		Vector<String> vector = new Vector<String>();
		final int docLen = document.length();
		int off = 0;
		char c;
		String seg = "";
		ChunkStream stream = new ChunkStream();

		while (off < docLen) {
			c = document.charAt(off);
			if (TextUtils.isEnLetter(c) || TextUtils.isNumeric(c)) {
				seg += c;
				off++;
			} else if (TextUtils.isCnLetter(c)) {
				if (!TextUtils.isBlank(seg)) {
					vector.add(seg);
					seg = "";
				}
				String word = stream.next(document, off);
				if (!TextUtils.isBlank(word)) {
					vector.add(word);
					off += word.length();
				}
			} else {
				if (!TextUtils.isBlank(seg)) {
					vector.add(seg);
					seg = "";
				}

				/**
				 * TODO: Uncomment the "ELSE IF" clause if you would like to
				 * reserve punctuations
				 */

				// else if (!TextUtils.isBlank("" + c)) { vector.add("" + c); }

				off++;
			}
		}
		if (!TextUtils.isBlank(seg)) {
			vector.add(seg);
		}
		return vector;

	}
}
