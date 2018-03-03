package car;

import util.Reader;
import util.Writer;

import java.util.*;

public class CarMain {

	static Reader reader;
	static Writer writer;
	static List<Car> cars;
	static List<Ride> rides;

	static int bonus;
	static String f;

	public static void main(String[] args) {
		String[] files = {"b_should_be_easy", "c_no_hurry", "d_metropolis", "e_high_bonus"};

		for (String file : files) {
			f = file;
			System.out.println(file);
			reader = new Reader("car/" + file + ".in");
			int[] numbers = reader.readInts(6);
			reader.readLine();
			int numRows = numbers[0];
			int numColums = numbers[1];
			int numCars = numbers[2];
			int numRides = numbers[3];
			bonus = numbers[4];
			int endTime = numbers[5];
			
			cars = new ArrayList<>();
			for (int i = 0; i < numCars; i++) {
				cars.add(new Car(i));
			}
			rides = new ArrayList<>();
			for (int i = 0; i < numRides; i++) {
				int[] p = reader.readInts(6);
				rides.add(new Ride(p[0], p[1], p[2], p[3], p[4], p[5], i));
			}
			reader.close();
			Collections.sort(rides);
			int j = 0;

			if (file.equals("d_metropolis")) {
				for (Car c : cars) {
					c.driveTo(1800, 700);
				}
			}

			Collections.sort(cars);

			boolean canGoOn = true;
			while (canGoOn) {
				canGoOn = false;
				for (int i = 0; i < cars.size(); i++) {
					canGoOn = cars.get(i).findNextRide();
					if (canGoOn)
						break;
				}
				Collections.sort(cars);
			}

			writer = new Writer("car/sol_" + file + ".txt");
			for (Car c : cars) {
				writer.write(c.toString());
			}
			writer.close();

			System.out.println("Rides mist: " + rides.size());
			int sum = 0;
			for (Ride r : rides) {
				sum += r.getLength();
			}
			System.out.println("Mist points: " + sum);
			System.out.println();
		}
	}
}
