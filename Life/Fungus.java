import java.awt.Color;
import java.util.List;
import java.util.Random;


public class Fungus extends Disease{
    
    private int maxAge;
    
    public Fungus(double infectionRate, Color hue, int maxAge){
        super(infectionRate, hue);
        this.maxAge = maxAge;

    }
    
    public void act(Cell cell){
        // if host died, spread
        if (!getHost().isAlive()){
            spread(cell);
        }
    }
    
    public void spread(Cell c){
        //spread to living neighbo7fjh;ghjurs of host with the infection rate
        List<Cell> livingNeighours = getHost().getField().getLivingNeighbours(getHost().getLocation());
        Random randomizer = new Random();

        for (Cell cell : livingNeighours){
            if (!cell.isInfected() && randomizer.nextDouble() <= getInfectionRate()){
                cell.setInfected(true);
                Disease fungus = new Fungus(getInfectionRate(), getHue(), getMaxAge());
                cell.setDisease(fungus);
                fungus.setHost(cell);
            }
        }
    }

    public void move(){
        
    }
    
    public int getMaxAge(){
        return maxAge;
    }
    
    public void setMaxAge(int maxAge){
        this.maxAge = maxAge;
    }

    
}