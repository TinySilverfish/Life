import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
* Simplest form of life.
*
* @author David J. Barnes, Michael Kölling & Jeffery Raphael, Hussain Ben Alshaikh & Ian Li
* @KNumber K21081772 K21087882
* @version 2022.02.28 
*/

public class Mycoplasma extends Cell {
	
	/**
	* Create a new Mycoplasma.
	*
	* @param field The field currently occupied.
	* @param location The location within the field.
	*/
	public Mycoplasma(Field field, Location location, Color col) {
		super(field, location, col);		
	}
	
	/**
	* This is how the Mycoplasma decides if it's alive or not
	* Follows the following rules:
	* If cell = Alive 
	*  -if it has 2 or 3 neighbours it stays alive otherwise dies
	* 
	* If cell = Dead
	*  -if it has 3 neighbours it alivens, otherwise stays dead 
	* 
	*/
	public void act() {
		List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
		setNextState(false);
		
		//Logic for when Cell is alive
		if (isAlive()) {
			if (neighbours.size() > 1 && neighbours.size() < 4) {
				setNextState(true);
			}
		}
		//Logic for when Cell is dead
		else {
			if (neighbours.size() == 3) {
				setNextState(true);
			}
		}

		updateAll();
	}
}
