import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Represents a fungus disease that can infect cells in a field.
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael, Hussain Ben Alshaikh & Ian Li
 * @KNumber K21081772 K21087882
 * @version 2022.02.28 
*/
public class Fungus extends Disease{
        
    
    // Constructs a new fungus disease with the given infection rate and color hue.
    public Fungus(double infectionRate, Color hue){
        super(infectionRate, hue);
    }
    
    // If the host of the fungus has died, the fungus spreads to nearby living cells.
    public void act(Cell cell){
        // if host died, spread
        if (!getHost().isAlive()){
            spread(cell);
        }
    }
    
    // Spreads the fungus to nearby living cells with a probability equal to the fungus's infection rate.
    // Creates a new instance of the Fungus class for each newly infected cell.
    public void spread(Cell c){
        
        List<Cell> livingNeighours = getHost().getField().getLivingNeighbours(getHost().getLocation());
        Random randomizer = new Random();

        for (Cell cell : livingNeighours){
            if (!cell.isInfected() && randomizer.nextDouble() <= getInfectionRate()){
                cell.setInfected(true);
                Disease fungus = new Fungus(getInfectionRate(), getHue());
                cell.setDisease(fungus);
                fungus.setHost(cell);
            }
        }
    }    
}