package pizza;

import java.awt.*;

public class Slice {
	Point ul;
	Point lr;

	public Slice(int x1, int y1, int x2, int y2) {
		this.ul = new Point(x1, y1);
		this.lr = new Point(x2, y2);
	}

	public String toString() {
		return ul.y + " " + ul.x + " " + lr.y + " " + lr.x;
	}

	public boolean canContain(int x, int y) {
		if (x >= lr.x && y >= lr.y) {
			int deltaX = x - ul.x + 1;
			int deltaY = y - ul.y + 1;
			if (deltaX * deltaY <= Main.maxSize)
				return true;
		}
		return false;
	}
}
