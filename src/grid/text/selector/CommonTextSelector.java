package grid.text.selector;

/**
 * CnTextSelector.java 2013-9-20 ÏÂÎç02:54:25
 * 
 * @Author George Bourne
 */
public class CommonTextSelector implements TextSelector {

	protected String document;

	protected int pos = 0;

	protected int maxSelectLen = 5;

	protected int minSelectLen = 2;

	protected int curLen;

	protected final int docLen;

	public CommonTextSelector(String document, int minSelectLen,
			int maxSelectLen) {
		this.document = document;
		this.minSelectLen = minSelectLen;
		this.maxSelectLen = maxSelectLen;
		docLen = document.length();
		adjustCurLen();
	}

	public void select() {
		pos += ++curLen;
		adjustCurLen();
	}

	protected void adjustCurLen() {
		curLen = pos + maxSelectLen > docLen ? docLen - pos : maxSelectLen;
	}

	public String next() {
		if (curLen < minSelectLen) {
			pos++;
			adjustCurLen();
		}

		if (pos + curLen <= docLen && curLen >= minSelectLen) {
			return document.substring(pos, pos + curLen--);
		} else {
			curLen--;
			// return document.substring(pos, docLen);
			return "";
		}
	}

	public boolean end() {
		return curLen < minSelectLen && curLen + pos >= docLen - 1;
	}

	@Override
	public int getCurPos() {
		return pos;
	}
}
