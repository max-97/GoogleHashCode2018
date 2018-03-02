package pizza;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pizza {

	char[][] pizza;
	Point[] offsets;
	Slice[][] slicesOnPizza;
	ArrayList<Slice> slices;

	public Pizza(char[][] p) {
		this.pizza = p;
		this.slicesOnPizza = new Slice[p.length][p[0].length];
		this.slices = new ArrayList<>();
	}

	public void fitSlice(int x, int y) {
		for (int i = 0; i < offsets.length; i++) {
			if (fit(x, y, offsets[i])) {
				int x2 = x + offsets[i].x;
				int y2 = y + offsets[i].y;
				Slice s = new Slice(x, y, x2, y2);
				slices.add(s);
				setSlices(s);
				break;
			}
		}

		boolean con1 = y - 1 >= 0 && isUsed(x, y - 1);
		boolean con2 = x - 1 >= 0 && isUsed(x - 1, y);
		boolean con3 = x - 1 >= 0 && y - 1 >= 0 && isUsed(x -1 , y - 1);
		if (con1) {
			Slice s = slicesOnPizza[y-1][x];
			if (s.canContain(x, y) && !overlap(s, x, y)) {
				s.lr = new Point(x, y);
				setSlices(s);
				return;
			}
		}
		if (con2) {
			Slice s = slicesOnPizza[y][x-1];
			if (s.canContain(x, y) && !overlap(s, x, y)) {
				s.lr = new Point(x, y);
				setSlices(s);
				return;
			}
		}
		if (con3) {
			Slice s = slicesOnPizza[y-1][x-1];
			if (s.canContain(x, y) && !overlap(s, x, y)) {
				s.lr = new Point(x, y);
				setSlices(s);
				return;
			}
		}
	}

	boolean overlap(Slice s, int x, int y) {
		for (int i = s.ul.y; i <= y; i++) {
			if (slicesOnPizza[i][x] != null && slicesOnPizza[i][x] != s)
				return true;
		}
		for (int i = s.ul.x; i <= x; i++) {
			if (slicesOnPizza[y][i] != null && slicesOnPizza[y][i] != s)
				return true;
		}
		return false;
	}

	void setSlices(Slice s) {
		for (int i = s.ul.x; i <= s.lr.x; i++) {
			for (int j = s.ul.y; j <= s.lr.y; j++) {
				slicesOnPizza[j][i] = s;
			}
		}
	}

	public boolean fit (int x, int y, Point off) {
		int countT = 0;
		int countM = 0;
		for (int i = x; i <= x + off.x; i++) {
			for (int j = y; j <= y + off.y; j++) {
				if (i >= pizza[0].length || j >= pizza.length)
					return false;
				if (isUsed(i, j))
					return false;
				if (pizza[j][i] == 'T')
					countT++;
				else
					countM++;
			}
		}

		if (countM >= Main.minEach && countT >= Main.minEach)
			return true;
		else
			return false;
	}

	public void calcOffsets(int minEach, int maxSize) {
		List<Point> sizes = new ArrayList<>();
		int minSize = minEach * 2;
		for (int i = 0; i + minSize <= maxSize; i++) {
			int currentSize = minSize + i;
			for (int j = 1; j <= currentSize ; j++) {
				if (currentSize % j == 0) {
					sizes.add(new Point(j-1, (currentSize / j) - 1));
				}
			}
		}
		offsets = sizes.toArray(new Point[]{});
	}

	public boolean isUsed(int x, int y) {
		return (this.slicesOnPizza[y][x] != null);
	}
}
