import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
* A more advanced form of life.
*
* Speciality: Has a unique property that allows it to share resources with its neighbouring cells of Theirco
*
* @author David J. Barnes, Michael Kölling & Jeffery Raphael, Hussain Ben Alshaikh & Ian Li
* @KNumber K21081772 K21087882
* @version 2022.02.28 
*/

public class Theycoplasma extends Cell {

	/**
	* Create a new Theycoplasma.
	*
	* @param field The field currently occupied.
	* @param location The location within the field.
	*/
	public Theycoplasma(Field field, Location location, Color col) {
		super(field, location, col);		
	}
	
	/**
	* This is how the Theycoplasma decides if it's alive or not
	* Follows the following rules:
	* If cell = Alive 
	*  -if it has 2 or 3 neighbours it stays alive otherwise dies
	* 
	* If cell = Dead
	*  -if it has 3 neighbours it alives, otherwise stays dead 
	* 
	*/
	public void act() {
		List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
		setNextState(false);
		
        int size = neighbours.size();

		if (isAlive()) {
			if (neighbours.size() > 1 && neighbours.size() < 4) {
				setNextState(true);
			}
		}
		else {
			if (neighbours.size() == 3) {
				setNextState(true);
			}
		}

		//An additional chance for the cell to live through the neighbours of its partner class
        for (Cell c : neighbours){
            if (c instanceof Theircoplasma){
                size = c.getField().getLivingNeighbours(c.getLocation()).size();

                if ( size > 4 && size < 7) {
                    setNextState(true);
                    break;
                }
                
            }
        }

		updateAll();
	}

}