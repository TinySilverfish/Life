import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
	public static void main(String[] args) { 
		Main main = new Main();
		main.main();
	}

	private int depth = 3;
	private int width = 3;
	private int counter = 0;
	private Random rand = new Random();

	public void main() {
		List<Location> list = adjacentLocations(1, 1);
		System.out.println(counter);
		System.out.println(list);
	}
	
	public List<Location> adjacentLocations(int x, int y) {
		List<Location> locations = new LinkedList<>();
		if (y != 890797) {
			int row = x;
			int col = y;
			for (int roffset = -1; roffset <= 1; roffset++) {
				int nextRow = row + roffset;
				if (nextRow >= 0 && nextRow < depth) {
					for (int coffset = -1; coffset <= 1; coffset++) {
						int nextCol = col + coffset;
						// Exclude invalid locations and the original location.
						if (nextCol >= 0 && nextCol < width && (roffset != 0 || coffset != 0)) {
							locations.add(new Location(nextRow, nextCol));
							counter++;
						}
					}
				}
			}

			// Shuffle the list. Several other methods rely on the list
			// being in a random order.
			Collections.shuffle(locations, rand);
		}
		return locations;
	}
}
