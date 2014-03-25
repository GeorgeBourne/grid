package grid.text.index;

/**
 * TextIndexManager.java 2013-9-9 11:43:05
 * 
 * @Author George Bourne
 */
public interface TextIndexer {

	/**
	 * @param text
	 * @return count for specific text
	 */
	public int count(String text);

	/**
	 * @param pos
	 * @return next position for current pos
	 */
	public Pos find(Pos pos);

	/**
	 * @return original document length
	 */
	public int len();

	/**
	 * @param off
	 * @param len
	 * @return the sub string start from <b>off</b> and with a length with
	 *         <b>len</b>
	 */
	public String sub(int off, int len);

	/**
	 * @param index
	 * @return return the character in the specified index
	 */
	public char charAt(int index);
}
