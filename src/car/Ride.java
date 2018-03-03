package car;

import java.awt.*;

public class Ride implements Comparable{

	Point start;
	Point end;
	int startTime;
	int endTime;
	int index;
	int length;

	public Ride(int x, int y, int x2, int y2, int startT, int endT, int index) {
		this.start = new Point(x, y);
		this.end = new Point(x2, y2);
		this.startTime = startT;
		this.endTime = endT;
		this.index = index;
		this.length = getDistanceFrom(end);
	}

	public int getDistanceFrom(int x, int y) {
		return Math.abs(x - start.x) + Math.abs(y - start.y);
	}

	public int getDistanceFrom(Point p) {
		return getDistanceFrom(p.x, p.y);
	}

	public int getLength() {
		return length;
	}

	@Override
	public int compareTo(Object o) {
		Ride r = (Ride) o;
		return this.getDistanceFrom(0, 0) + startTime - r.getDistanceFrom(0, 0) - r.startTime;
	}

	public int calcPoints(Car car) {
		int sum = this.getLength();
		int arrivalTime = car.currentTime + getDistanceFrom(car.pos);
		if (arrivalTime <= this.startTime)
			sum += CarMain.bonus;
		return sum;
	}
}
