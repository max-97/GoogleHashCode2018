package car;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Car implements Comparable{

	Point pos;
	List<Integer> ridesDone;
	int currentTime;
	int index;

	public Car(int i) {
		this.pos = new Point(0, 0);
		ridesDone = new ArrayList<>();
		currentTime = -1;
		index = i;
	}

	public void assignRide(Ride r) {
		ridesDone.add(r.index);
		currentTime += r.getDistanceFrom(this.pos);
		if (currentTime < r.startTime)
			currentTime = r.startTime;
		currentTime += r.getLength();
		pos = new Point(r.end.x, r.end.y);
	}

	public boolean canAccomplish(Ride ride) {
		/*if (CarMain.f.equals("d_metropolis") && ride.getLength() > 6000 && index < 100) {
			return false;
		}*/
		int d = ride.getDistanceFrom(this.pos);
		int finishTime = currentTime + d + ride.getLength();
		if (finishTime < ride.endTime)
			return true;
		return false;
	}

	public boolean findNextRide() {
		int smallestD = Integer.MAX_VALUE;
		Ride ride = null;
		int index = Integer.MAX_VALUE;
		double pointsPerTime = Integer.MIN_VALUE;

		for (int i = 0; i < CarMain.rides.size(); i++) {
			Ride r = CarMain.rides.get(i);
			int d = r.getDistanceFrom(this.pos);

			if (d + currentTime <= r.startTime) {
				d = r.startTime - currentTime;
			}
			double points = r.calcPoints(this);
			double time = d + r.getLength();
			double pPT = points / time;
			// metric with pointsPerTime
			//if (this.canAccomplish(r) && pointsPerTime < pPT) {
			if (this.canAccomplish(r) && d < smallestD) {
				smallestD = d;
				ride = r;
				index = i;
				pointsPerTime = pPT;
			}
		}
		if (ride == null)
			return false;

		assignRide(ride);
		CarMain.rides.remove(index);
		return true;
	}

	public String toString() {
		String str = "" + ridesDone.size();
		for (int i = 0; i < ridesDone.size(); i++) {
			str += " " + ridesDone.get(i);
		}
		return str;
	}

	@Override
	public int compareTo(Object o) {
		Car c = (Car) o;
		return this.currentTime - c.currentTime;
	}

	public void driveTo(int i, int i1) {
		this.pos = new Point(i, i1);
		this.currentTime = i + i1;
	}
}
