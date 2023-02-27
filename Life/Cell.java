import java.awt.Color;
import java.util.List;

/**
* A class representing the shared characteristics of all forms of life
*
* @author David J. Barnes, Michael Kölling & Jeffery Raphael
* @version 2022.01.06 (1)
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

	protected int age;

	private boolean isInfected;

	private Disease disease;
	/**
	* Create a new cell at location in field.
	*
	* @param field The field currently occupied.
	* @param location The location within the field.
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
	* Make this cell act - that is: the cell decides it's status in the
	* next generation.
	*/
	abstract public void act();

	public int getAge() {
		return age;
	}

	public void setAge(int i) {
		age = i;
	}

	public void incrementAge() {
		age++;
	}
	
	/**
	* Check whether the cell is alive or not.
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
	* Indicate that the cell will be alive or dead in the next generation.
	*/
	public void setNextState(boolean value) {
		nextAlive = value;
	}
	
	/**
	* Changes the state of the cell
	*/
	public void updateState() {
		alive = nextAlive;
	}
	
	/**
	* Changes the color of the cell
	*/
	public void setColor(Color col) {
		color = col;
	}
	
	/**
	* Returns the cell's color
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
	
	protected boolean getNextState(){
		return nextAlive;
	}

	protected void updateAge(){
		if (getNextState()) {
			incrementAge();
		}
		else {
			age = 0;
		}
	}

	public void setInfected(boolean isInfected) {
		this.isInfected = isInfected;
	}

	public boolean isInfected() {
		return isInfected;
	}

	public Disease getDisease(){
		return disease;
	}

	public void setDisease(Disease disease){
		this.disease = disease;
	}

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
	private void updateInfection(){
		if(isInfected()){
			if(!getNextState()){
				disease.spread(this);
			}
		}
	}
	public void updateAll() {
		updateAge();
		updateColor();
		updateInfection();
	}
}
