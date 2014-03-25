package grid.text.index;

/**
 * SimpleTextIndexer.java 2013-9-9 16:32:05
 * 
 * @Author George Bourne
 */
public class SimpleTextIndexer implements TextIndexer {

	private String document;

	public SimpleTextIndexer(String document) {
		this.document = document;
	}

	@Override
	public int count(String text) {
		int off = 0;
		int count = 0;
		final int len = text.length();
		while ((off = document.indexOf(text, off)) > -1) {
			count++;
			off += len;
		}
		return count;
	}

	@Override
	public Pos find(Pos pos) {
		final String text = pos.getTarget();
		final int len = text.length();
		int off = pos.getPos() + len;
		if (pos.getPos() < 0)
			off = 0;

		pos.setFound(false);

		if ((off = document.indexOf(text, off)) > -1) {
			pos.setFound(true);
			pos.setPos(off);
		}
		return pos;
	}

	@Override
	public int len() {
		return document.length();
	}

	@Override
	public String sub(int off, int len) {
		return document.substring(off, off + len);
	}

	@Override
	public char charAt(int index) {
		if (index < 0 || index >= document.length()) {
			return 0;
		}
		return document.charAt(index);
	}
}
