package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

	Scanner scanner;

	public Reader(String filePath) {
		File file = new File("./resources/problems/" + filePath);
		try {
			this.scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public int readInt() {
		return this.scanner.nextInt();
	}

	public int[] readInts(int n) {
		int[] numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = this.readInt();
		}
		return numbers;
	}

	public double readDouble() {
		return this.scanner.nextDouble();
	}

	public double[] readDoubles(int n) {
		double[] numbers = new double[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = this.readDouble();
		}
		return numbers;
	}

	public String readLine() {
		return this.scanner.nextLine();
	}

	public String[] readLines(int n) {
		String[] lines = new String[n];
		for (int i = 0; i < n; i++) {
			lines[i] = this.readLine();
		}
		return lines;
	}

	public char[] readCharArray() {
		return this.readLine().toCharArray();
	}

	public char[][] readCharArrays(int n) {
		char[][] chars = new char[n][];
		for (int i = 0; i < n; i++) {
			chars[i] = this.readCharArray();
		}
		return chars;
	}

	public void close() {
		this.scanner.close();
	}
}
