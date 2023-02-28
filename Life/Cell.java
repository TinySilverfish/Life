import java.awt.Color;
import java.util.List;

/**
 * A class representing the shared characteristics of all living cells.
 * This is an abstract class, meaning that it cannot be instantiated directly.
 * Instead, subclasses must be created to represent specific types of cells.
 * 
 * The class provides methods for accessing and changing the state of the cell,
 * including whether it is alive or dead, its age, its color, and whether it is
 * infected with a disease.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael, Hussain Ben Alshaikh & Ian Li
 * @KNumber K21081772 K21087882
 * @version 2022.02.28 
 */
public abstract class Cell {
    // Whether the cell is alive or not.
    private boolean alive;

    // Whether the cell will be alive in the next generation.
    private boolean nextAlive;

    // The cell's field.
    private Field field;

    // The cell's position in the field.
    private Location location;

    // The cell's color
    private Color color = Color.white;

    // The age of the cell.
    protected int age;

    // Whether the cell is infected with a disease.
    private boolean isInfected;

    // The disease that the cell is infected with (if any).
    private Disease disease;

    /**
     * Create a new cell at the given location in the field with the given color.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param col The color of the cell.
     */
    public Cell(Field field, Location location, Color col) {
        alive = true;
        nextAlive = false;
        this.field = field;
        setLocation(location);
        setColor(col);
        age = 0;
    }

    /**
     * Make this cell act - that is, the cell decides its status in the
     * next generation.
     */
    abstract public void act();

    /**
     * Get the age of the cell.
     * 
     * @return The age of the cell.
     */
    public int getAge() {
        return age;
    }

    /**
     * Set the age of the cell.
     * 
     * @param i The new age of the cell.
     */
    public void setAge(int i) {
        age = i;
    }

    /**
     * Increment the age of the cell by 1.
     */
    public void incrementAge() {
        age++;
    }

    /**
     * Check whether the cell is alive or not.
     * 
     * @return true if the cell is still alive.
     */
    protected boolean isAlive() {
        return alive;
    }

    /**
     * Indicate that the cell is no longer alive.
     */
    protected void setDead() {
        alive = false;
    }

    /**
     * Indicate whether the cell will be alive or dead in the next generation.
     * 
     * @param value true if the cell will be alive, false if it will be dead.
     */
    public void setNextState(boolean value) {
        nextAlive = value;
    }

    /**
     * Update the state of the cell to its next state.
     */
    public void updateState() {
        alive = nextAlive;
    }

    /**
     * Set the color of the cell.
     * 
     * @param col The new color of the cell.
     */
    public void setColor(Color col) {
        color = col;
    }

    /**
     * Get the color of the cell.
     * 
     * @return The color of the cell.
     */
    public Color getColor() {
        return color;
    }

	/**
	 * Return the cell's location.
	 * @return The cell's location.
	 */
	protected Location getLocation() {
		return location;
	}
	
	/**
	 * Place the cell at the new location in the given field.
	 * @param location The cell's location.
	 */
	protected void setLocation(Location location) {
		this.location = location;
		field.place(this, location);
	}
	
	/**
	 * Return the cell's field.
	 * @return The cell's field.
	 */
	protected Field getField() {
		return field;
	}

	/**
	 * Returns the next state of the cell, whether it will be alive or dead in the next iteration
	 * @return a boolean indicating whether the cell will be alive or dead in the next iteration
	 */
	protected boolean getNextState(){
		return nextAlive;
	}

	/**
	 * Updates the age of the cell depending on its next state. 
	 * If it will be alive, increments the age; otherwise, sets the age to 0.
	 */
	protected void updateAge(){
		if (getNextState()) {
			incrementAge();
		}
		else {
			age = 0;
		}
	}

	/**
	 * Sets the infected status of the cell
	 * @param isInfected a boolean indicating whether the cell is infected or not
	 */
	public void setInfected(boolean isInfected) {
		this.isInfected = isInfected;
	}

	/**
	 * Returns the infected status of the cell
	 * @return a boolean indicating whether the cell is infected or not
	 */
	public boolean isInfected() {
		return isInfected;
	}

	/**
	 * Returns the disease that the cell is infected with
	 * @return the disease that the cell is infected with
	 */
	public Disease getDisease(){
		return disease;
	}

	/**
	 * Sets the disease that the cell is infected with
	 * @param disease the disease that the cell will be infected with
	 */
	public void setDisease(Disease disease){
		this.disease = disease;
	}

	/**
	 * Updates the color of the cell. If the cell is infected, 
	 * adds the color of the disease to its original color
	 */
	public void updateColor(){
		if (isInfected()) {
			// Get the color of the disease
			Color diseaseColor = getDisease().getHue();
			
			// Add the two colors by adding their individual RGB components
			int red = getColor().getRed() + diseaseColor.getRed();
			int green = getColor().getGreen() + diseaseColor.getGreen();
			int blue = getColor().getBlue() + diseaseColor.getBlue();

			// Limit the RGB values to the range of 0-255
			red = Math.min(red, 255);
			green = Math.min(green, 255);
			blue = Math.min(blue, 255);
			
			// 	Create a new Color object with the added RGB values
			Color resultColor = new Color(red, green, blue);
			// Color resultColor = color.BLACK;

			// Set the cell's color to the new color
			setColor(resultColor);
		}
	}

	/**
	 * Updates the infection status of the cell. 
	 * If the cell is infected and is going to die in the next iteration, 
	 * spreads the disease to a neighboring cell.
	 */
	private void updateInfection(){
		if(isInfected()){
			if(!getNextState()){
				disease.spread(this);
				isInfected = false;
				disease.deHost();
				disease = null;

			}
		}
	}

	/**
	 * Updates all the properties of the cell (age, infection, color)
	 */
	public void updateAll() {
		updateAge();
		updateInfection();
		updateColor();
	}
}
