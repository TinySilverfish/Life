import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
* Simplest form of life.
* Fun Fact: Mycoplasma are one of the simplest forms of life.  A type of
* bacteria, they only have 500-1000 genes! For comparison, fruit flies have
* about 14,000 genes.
*
* @author David J. Barnes, Michael Kölling & Jeffery Raphael
* @version 2022.01.06 (1)
*/

public class Mycoplasma extends Cell {
	
	// The cell's weight for spawning
	private static int weight;
	/**
	* Create a new Mycoplasma.
	*
	* @param field The field currently occupied.
	* @param location The location within the field.
	*/
	public Mycoplasma(Field field, Location location, Color col) {
		super(field, location, col);
		weight = 1; // 1/100 chance of spawning
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
	
	/**
	* Return the cell's weight.
	* @return The cell's weight.
	*/
	protected int getWeight() {
		return weight;
	}
	/**
	* Sets the cell's weight.
	*/
	protected void setWeight(int x) {
		weight = x;
	}
}
