import java.awt.Color;

/**
 * Can only spread through living cells
 * Can move
 */
public abstract class Disease {
    
    private double infectionRate;

    private Color hue;
    
    private int maxAge;

    protected abstract void spread();


}
