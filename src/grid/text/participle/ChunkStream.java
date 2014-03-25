package grid.text.participle;

import grid.common.Node;
import grid.common.TextUtils;
import grid.common.Tree;
import grid.text.dic.CnDictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ChunkStream.java 2013-10-3 15:55:31
 * 
 * @Author George Bourne
 */
public class ChunkStream {

	/**
	 * Define the max supposed word length
	 * 
	 * You could shorten the value if you don't need too long participle result
	 */
	private static final int MAX_WORD_LEN = 7;

	/**
	 * Define the predict level while execute participle.
	 * 
	 * Negligible accuracy will be promoted if you increase this value
	 */
	private static final int PREDICT_LEVEL = 3;

	private static CnDictionary dictionary = CnDictionary.Instance();

	public String next(String text, int off) {
		Tree<String> root = new Tree<String>("ROOT");
		recurse(root, off, text, 0);
		List<Node<String>> list = root.getLeaves();
		List<Chunk> chunkList = new ArrayList<Chunk>();
		for (Node<String> node : list) {
			chunkList.add(new Chunk(node.getBranchPath()));
		}
		Collections.sort(chunkList);
		return chunkList.get(0).getHead();

	}

	private void recurse(Node<String> node, int off, String text,
			int predictDeep) {
		int len = MAX_WORD_LEN + off > text.length() ? text.length() - off
				: MAX_WORD_LEN;

		while (predictDeep < PREDICT_LEVEL) {
			if (len < 1) {
				return;
			}

			String s = text.substring(off, off + len);
			if (len < 2) {
				if (!TextUtils.isCnLetter(text.charAt(off))) {
					break;
				}
				recurse(node.add(s), off + 1, text, predictDeep + 1);
			} else if (dictionary.contains(s)) {
				recurse(node.add(s), off + s.length(), text, predictDeep + 1);
			}
			len--;
		}
	}
}
