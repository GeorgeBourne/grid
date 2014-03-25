package grid.text.selector;

import grid.common.TextUtils;

/**
 * CnTextSelector.java 2013-9-20 обнГ02:57:15
 * 
 * @Author George Bourne
 */
public class CnTextSelector extends CommonTextSelector {

	public CnTextSelector(String document, int minSelectLen, int maxSelectLen) {
		super(document, minSelectLen, maxSelectLen);
	}

	protected void adjustCurLen() {
		while (pos < docLen && !TextUtils.isCnLetter(document.charAt(pos))) {
			pos++;
		}
		for (int i = 0; i < maxSelectLen && pos + i < docLen; i++) {
			if (!TextUtils.isCnLetter(document.charAt(pos + i))) {
				curLen = i;
				if (curLen < minSelectLen) {
					pos++;
					adjustCurLen();
				}
				return;
			}
		}

		curLen = pos + maxSelectLen > docLen ? docLen - pos : maxSelectLen;
	}
}
