package grid.text.selector;

/**
 * TextSelector.java 2013-9-20 ����01:56:14
 * 
 * @Author George Bourne
 */
public interface TextSelector {
	public boolean end();

	public void select();

	public String next();

	public int getCurPos();

}
