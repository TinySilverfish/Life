import java.awt.Color;
import java.util.List;
import java.util.Random;
/**
 * This class impliment nondetermistic
 *  
 */

public class Mecoplasma extends Cell {
    
    public Mecoplasma(Field field, Location location, Color col) {
        super(field, location, col);
    }

    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
		setNextState(false);

        Random rand = Randomizer.getRandom();
        int randInt = rand.nextInt(0, 10);
        int minNeighbours = 0;
        int maxNeighbours = 0;

        switch(randInt) {
            case 0, 1:
                minNeighbours = 1;
                maxNeighbours = 3;
                break;

            case 2, 3, 4, 5, 6, 7:
                minNeighbours = 2;
                maxNeighbours = 4;
                break;


            case 8, 9:
                minNeighbours = 3;
                maxNeighbours = 5;
                break;
        }

        if (isAlive()) {
            if (neighbours.size() > minNeighbours && neighbours.size() < maxNeighbours) {
                setNextState(true);
            }
        }
        else {
            if (neighbours.size() == minNeighbours) {
                setNextState(true);
            }
        }
        
        updateAll();
    }
}

