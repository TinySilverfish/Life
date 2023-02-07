import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Friends of Mycoplasma
 * Fun Fact: Youcoplasma was first created as a joke among friends.
 * It shares similar characteristics with Mycoplasma.
 * 
 * 
 */

public class Youcoplasma extends Cell {

        /**
		 * Create a new Mycoplasma.
		 *
		 * @param field The field currently occupied.
		 * @param location The location within the field.
		 */
		public Youcoplasma(Field field, Location location, Color col) {
				super(field, location, col);
		}

		/**
		 * This is how the Mycoplasma decides if it's alive or not
		 * Follows the following rules: 
		 * 
		 */
		public void act() {
			List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
			setNextState(false);


			if (isAlive()) {
				if (neighbours.size() > 1 && neighbours.size() < 4)
					setNextState(true);
			}
			else {
				if (neighbours.size() == 3) {
					setNextState(true);
				}
			}

		}
}
