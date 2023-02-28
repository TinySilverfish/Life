import java.awt.Color;
import java.util.List;
import java.util.Random;


public class Virus2 extends Disease{
    
    private int maxAge;
    
    public Virus2(double infectionRate, Color hue, int maxAge){
        super(infectionRate, hue);
        this.maxAge = maxAge;
    }

    public void spread(Cell cell){

       for (Cell neighbour: cell.getField().getLivingNeighbours(cell.getLocation())){
            infect(neighbour);
       }

    }

    public void infect(Cell cell){
        Disease virus = new Fungus(getInfectionRate(), getHue(), maxAge);
        cell.setDisease(virus);
        cell.setInfected(true);
    }

    public int getMaxAge(){
        return maxAge;
    }
    
    public void setMaxAge(int maxAge){
        this.maxAge = maxAge;
    }

    
}