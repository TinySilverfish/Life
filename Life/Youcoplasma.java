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
	

	private final int DEAD_AGE = 4;

	private final Color MID_COLOR = new Color(69, 191, 85);

	private final Color DEAD_COLOR = new Color(4, 77, 41);

	private int minNeighbours = 0;

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
			if (neighbours.size() > 1 && neighbours.size() < 4) {
				setNextState(true);
			}
		}
		else {
			if (neighbours.size() == 1) {
				setNextState(true);
			}
		}

		if (age > DEAD_AGE) {
			setNextState(false);
		} 

		updateAll();
		changePeriod();
	}

	private void changePeriod() {
		switch (age) {
			case 1:
				setColor(MID_COLOR);
				minNeighbours = 2;
				System.out.println("Aged 1");
				break;
			case 3:
				setColor(DEAD_COLOR);
				minNeighbours = 3;
				System.out.println("Aged 3");
				break;
		}
	}
}
