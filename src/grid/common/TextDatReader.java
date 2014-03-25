package grid.common;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * TextDatReader.java 2013-9-9 16:47:09
 * 
 * @Author George Bourne
 */
public class TextDatReader {
	public static String read(String path) throws IOException {
		File file = new File(path);
		FileReader reader = new FileReader(file);
		char buffer[] = new char[(int) file.length()];
		reader.read(buffer);
		return new String(buffer);
	}
}
