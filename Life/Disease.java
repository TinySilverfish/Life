import java.awt.Color;

/**
 * This class represents a disease that can spread to neighbouring cells.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael, Hussain Ben Alshaikh & Ian Li
 * @KNumber K21081772 K21087882
 * @version 2022.02.28 
*/
public abstract class Disease {
    
    // The cell that currently hosts the disease.
    private Cell host;
    
    // The rate at which the disease spreads to neighbouring cells.
    private double infectionRate;

    // The color of the disease.
    private Color hue;
    
    // The maximum age of the disease before it dies off.
    private int maxAge;
    
    /**
     * Creates a new disease with the given infection rate and color.
     * 
     * @param infectionRate the rate at which the disease spreads
     * @param hue the color of the disease
     */
    public Disease(double infectionRate, Color hue){
        this.infectionRate = infectionRate;
        this.hue = hue;
    }

    /**
     * Spreads the disease to neighbouring cells with a certain probability.
     * 
     * @param cell the cell to spread the disease from
     */
    protected abstract void spread(Cell cell);

    /**
     * Returns the cell that currently hosts the disease.
     * 
     * @return the host cell
     */
    public Cell getHost(){
        return host;
    }

    /**
     * Sets the cell that currently hosts the disease.
     * 
     * @param host the host cell
     */
    public void setHost(Cell host){
        this.host = host;
    }

    /**
     * Removes the current host cell of the disease.
     */
    public void deHost(){
        host = null;
    }

    /**
     * Returns the color of the disease.
     * 
     * @return the disease color
     */
    public Color getHue() {
        return this.hue;
    }

    /**
     * Sets the color of the disease.
     * 
     * @param hue the new disease color
     */
    public void setHue(Color hue) {
        this.hue = hue;
    }

    /**
     * Returns the maximum age of the disease.
     * 
     * @return the maximum age
     */
    public int getMaxAge() {
        return this.maxAge;
    }

    /**
     * Sets the maximum age of the disease.
     * 
     * @param maxAge the new maximum age
     */
    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    /**
     * Returns the infection rate of the disease.
     * 
     * @return the infection rate
     */
    public double getInfectionRate(){
        return infectionRate;
    }

    /**
     * Sets the infection rate of the disease.
     * 
     * @param infectionRate the new infection rate
     */
    public void setInfectionRate(double infectionRate) {
        this.infectionRate = infectionRate;
    }
}