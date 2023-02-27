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

public class Theircoplasma extends Cell {

	/**
	* Create a new Mycoplasma.
	*
	* @param field The field currently occupied.
	* @param location The location within the field.
	*/
    
	public Theircoplasma(Field field, Location location, Color col) {
		super(field, location, col);		
	}
	
	/**
	* This is how the Mycoplasma decides if it's alive or not
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
            
			if ( size > 1 && size < 4) {
				setNextState(true);
			}

		}
		else {
			if (neighbours.size() == 3) {
				setNextState(true);
			}
		}

        for (Cell c : neighbours){
            if (c instanceof Theycoplasma){
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