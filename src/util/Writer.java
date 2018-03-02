package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;

public class Writer {

	PrintWriter pw;

	public Writer(String filePath) {
		File file = new File("./resources/solutions/" + filePath);
		try {
			pw = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void write(String line) {
		this.pw.println(line);
	}

	public void write(String[] lines) {
		for (int i = 0; i < lines.length; i++) {
			this.write(lines[i]);
		}
	}

	public void write(Collection<Object> collection) {
		for (Object obj : collection) {
			this.write(obj.toString());
		}
	}

	public void close() {
		this.pw.close();
	}
}
