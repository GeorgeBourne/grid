package grid.test;

import grid.common.TextDatReader;
import grid.text.evolution.NewWordDiscover;

import java.io.IOException;
import java.util.Set;

/**
 * NewWordDiscoverTest.java 2014-3-21 17:34:10
 * 
 * @Author George Bourne
 */
public class NewWordDiscoverTest {

	private final static String path = "text.dat";

	public static void main(String args[]) throws IOException {
		// Replace your document here
		String document = TextDatReader.read(path);

		NewWordDiscover discover = new NewWordDiscover();
		long start = System.currentTimeMillis();
		Set<String> words = discover.discover(document);
		System.out.println("Speed: " + (double) document.length()
				/ (System.currentTimeMillis() - start) * 1000);
		System.out.println("New words size: " + words.size());
	}
}
