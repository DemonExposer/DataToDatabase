import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInteraction {
	/**
	 * Reads all the data from a file
	 * @param file the {@code File} to be read from
	 * @return the content of the {@code File}
	 * @throws IOException when the {@code File} could not be opened
	 */
	public static String read(File file) throws IOException {
		byte[] buffer = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(buffer);
		fis.close();

		return new String(buffer);
	}
}
