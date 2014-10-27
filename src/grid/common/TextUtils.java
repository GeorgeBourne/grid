package grid.common;

/**
 * TextUtils.java 2013-9-9 14:32:12
 * 
 * @Author George Bourne
 */
public class TextUtils {

	public static boolean isCnLetter(char c) {
		return c >= 0x4E00 && c <= 0x9FCB;
	}

	public static boolean isNumeric(char c) {
		return c >= '0' && c <= '9';
	}

	public static boolean isEnLetter(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}

	public static boolean match(String src, int off, String dest) {
		int len = dest.length();
		int srcLen = src.length();
		for (int i = 0; i < len; i++) {
			if (srcLen <= off + i) {
				return false;
			}
			if (dest.charAt(i) != src.charAt(off + i)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isBlank(String str) {
		return null == str || str.isEmpty() || str.trim().isEmpty();
	}
}
