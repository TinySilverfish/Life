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
        int minValue = 0;
        int maxValue = 0;

        switch(randInt) {
            case 0, 1:
                minValue = 1;
                maxValue = 3;
                break;

            case 2, 3, 4, 5, 6, 7:
                minValue = 2;
                maxValue = 4;
                break;


            case 8, 9:
                minValue = 3;
                maxValue = 5;
                break;
        }

        if (isAlive()) {
            if (neighbours.size() > minValue && neighbours.size() < maxValue) {
                setNextState(true);
            }
        }
        else {
            if (neighbours.size() == minValue) {
                setNextState(true);
            }
        }
        
        updateAge();
    }
}

