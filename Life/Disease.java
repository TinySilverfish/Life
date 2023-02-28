import java.awt.Color;

/**
 * Modify the code so that some cells are occasionally infected with disease. 
 * There should be two elements to the disease 
 * (i) it can spreads from one cell to its neighbours and 
 * (ii) once in a diseased state the cell’s behaviour changes.
 */
public abstract class Disease {
    
    private Cell host;
    
    private double infectionRate;

    private Color hue;
    
    private int maxAge;
    
    public Disease(double infectionRate, Color hue){
        this.infectionRate = infectionRate;
        this.hue = hue;
    }

    // protected abstract void spread();
    protected abstract void spread(Cell cell);

    public Cell getHost(){
        return host;
    }
    public void setHost(Cell host){
        this.host = host;
    }
    public void deHost(){
        host = null;
    }
    public Color getHue() {
        return this.hue;
    }

    public void setHue(Color hue) {
        this.hue = hue;
    }

    public int getMaxAge() {
        return this.maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public double getInfectionRate(){
        return infectionRate;
    }
    public void setInfectionRate(double infectionRate) {
        this.infectionRate = infectionRate;
    }
}
