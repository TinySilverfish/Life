import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
* Friends of Mycoplasma
* Fun Fact: Youcoplasma was first created as a joke among friends.
* It shares similar characteristics with Mycoplasma.
* Speciality: Has a 
* 
* @author David J. Barnes, Michael Kölling & Jeffery Raphael, Hussain Ben Alshaikh & Ian Li
* @KNumber K21081772 K21087882
* @version 2022.02.28 
*/

public class Youcoplasma extends Cell {
	
	//Maximum age for Youco cells
	private final int DEAD_AGE = 4;

	private final Color YOUNG_COLOR = new Color(0,0,0);

	private final Color MID_COLOR = new Color(69, 191, 85);

	private final Color DEAD_COLOR = new Color(4, 77, 41);

	private int minNeighbours = 1;

	private int maxNeighbours = 4;

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
			if (neighbours.size() > minNeighbours && neighbours.size() < maxNeighbours) {
				setNextState(true);
			}
		}
		else {
			if (neighbours.size() == 1) {
				setNextState(true);
			}
		}

		//Checking maximum age
		if (age > DEAD_AGE) {
			setNextState(false);
		} 

		changePeriod();
		updateAll();
	}

	/**
	 * Updates the cell with the correct paraemters for its age and updates color 
	 */
	private void changePeriod() {
		switch (age) {
			case 0:
				setColor(YOUNG_COLOR);
				minNeighbours = 1;
				maxNeighbours = 4;
				break;
			case 1:
				setColor(MID_COLOR);
				minNeighbours = 2;
				maxNeighbours = 5;
				break;
			case 3:
				setColor(DEAD_COLOR);
				minNeighbours = 3;
				maxNeighbours = 6;
				break;
		}
	}
}
