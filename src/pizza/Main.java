package pizza;

import util.Reader;
import util.Writer;

public class Main {

	static Reader reader;
	static Writer writer;
	static int numRows;
	static int numColums;
	static int minEach;
	static int maxSize;
	static Pizza pizza;

	public static void main(String[] args) {
		String file = "big";
		reader = new Reader("pizza/" + file + ".in");
		/** Read from file here */
		int[] val = reader.readInts(4);
		reader.readLine();
		numRows = val[0];
		numColums = val[1];
		minEach = val[2];
		maxSize = val[3];
		pizza = new Pizza(reader.readCharArrays(numRows));
		reader.close();
		/** Solution */
		pizza.calcOffsets(minEach, maxSize);

		for (int x = 0; x < numColums; x++) {
			for (int y = 0; y < numRows; y++) {
				if (pizza.isUsed(x, y))
					continue;
				pizza.fitSlice(x, y);
			}
		}

		writer = new Writer("pizza/sol_" + file + ".txt");
		/** Write to file here */
		Slice[] slices = pizza.slices.toArray(new Slice[]{});
		writer.write(slices.length + "");
		for (Slice s : slices)
			writer.write(s.toString());
		writer.close();
	}
}
