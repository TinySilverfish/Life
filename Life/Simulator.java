import java.util.Random;
import java.util.TreeMap;
import java.util.random.RandomGenerator.ArbitrarilyJumpableGenerator;
import javax.lang.model.util.ElementScanner14;
import java.util.List;
import java.util.NavigableMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.awt.Color;

/**
 * A Life (Game of Life) simulator, first described by British mathematician
 * John Horton Conway in 1970.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael, Hussain Ben Alshaikh & Ian Li
 * @KNumber K21081772 K21087882
 * @version 2022.02.28 
 */

public class Simulator {
	// The default width for the grid.
	private static final int DEFAULT_WIDTH = 100;
	
	// The default depth of the grid.
	private static final int DEFAULT_DEPTH = 100;
	
	// List of cells in the field.
	private List<Cell> cells;
	
	// The current state of the field.
	private Field field;
	
	// The current generation of the simulation.
	private int generation;
	
	// A graphical view of the simulation.
	private SimulatorView view;
	
	/**
	* Execute simulation
	*/	
	public static void main(String[] args) {
		Simulator sim = new Simulator(100, 100);
		sim.simulate(1000);
	}
	
	/**
	* Construct a simulation field with default size.
	*/
	public Simulator() {
		this(DEFAULT_DEPTH, DEFAULT_WIDTH);
	}
	
	/**
	* Create a simulation field with the given size.
	* @param depth Depth of the field. Must be greater than zero.
	* @param width Width of the field. Must be greater than zero.
	*/
	public Simulator(int depth, int width) {
		if (width <= 0 || depth <= 0) {
			System.out.println("The dimensions must be greater than zero.");
			System.out.println("Using default values.");
			depth = DEFAULT_DEPTH;
			width = DEFAULT_WIDTH;
		}
		
		cells = new ArrayList<>();
		field = new Field(depth, width);
		
		// Create a view of the state of each location in the field.
		view = new SimulatorView(depth, width);
		
		// Setup a valid starting point.
		reset();
	}
	
	/**
	* Run the simulation from its current state for a reasonably long period,
	* (4000 generations).
	*/
	public void runLongSimulation() {
		simulate(4000);
	}
	
	/**
	* Run the simulation from its current state for the given number of
	* generations.  Stop before the given number of generations if the
	* simulation ceases to be viable.
	* @param numGenerations The number of generations to run for.
	*/
	public void simulate(int numGenerations) {
		for (int gen = 1; gen <= numGenerations && view.isViable(field); gen++) {
			// If the simulation is paused, wait for the user to step through the generations.
			while (view.isPaused() && !view.isStep()) {
				delay(100);
			}
			
			// If the user has clicked the "step" button, clear the flag and run one generation.
			if (view.isStep()) {
				simOneGeneration();
				view.clearStep();
			} else {
				// Otherwise, run the simulation as normal.
				simOneGeneration();
				delay(250);   // comment out to run simulation faster
			}
		}
	}
	
	/**
	* Run the simulation from its current state for a single generation.
	* Iterate over the whole field updating the state of each life form.
	*/
	public void simOneGeneration() {
		generation++;
		for (Iterator<Cell> it = cells.iterator(); it.hasNext(); ) {
			Cell cell = it.next();
			cell.act();
		}
		
		for (Cell cell : cells) {
			cell.updateState();
		}
		view.showStatus(generation, field);
	}
	
	/**
	* Reset the simulation to a starting position.
	*/
	public void reset() {
		generation = 0;
		cells.clear();
		populate();
		
		// Show the starting state in the view.
		view.showStatus(generation, field);
	} 
	
	
	/**
	 * A helper method for Populate()
	 * gives equal chance for a cell to be either alive, dead, or infected
	 * 
	 * @param cell
	*/
	private void populateHelper(Cell cell) {
		Random rand = Randomizer.getRandom();
		int randInt = rand.nextInt(1, 4);  	
		
		if (randInt == 2){
			cell.setDead();
		}

		if (randInt == 3){
			setInfected(cell);
			
		}
		cells.add(cell);
	}
	
	/**
	 * Set a cell to be infected
	 * 
	 * @param cell
	 */
	private void setInfected(Cell cell) {
		Disease d = new Fungus(0.5, new Color(0, 0, 255));
        cell.setDisease(d);
        cell.setInfected(true);
		d.setHost(cell);
	}

	/**
	* Randomly populate the field live/dead life forms according to the weights
	*/
	private void populate() {
		Random rand = Randomizer.getRandom();
		field.clear();
		for (int row = 0; row < field.getDepth(); row++) {
			for (int col = 0; col < field.getWidth(); col++) {
				
				Location location = new Location(row, col);
				int randInt = rand.nextInt(1, 6);
				
				switch(randInt){
					case 1:
                        Cell myco = new Mycoplasma(field, location, Color.PINK);
                        populateHelper(myco);
                        break;
                    case 2:
                        Cell youco = new Youcoplasma(field, location, Color.CYAN);
                        populateHelper(youco);
                        break;
                    case 3:
                        Cell theirco = new Theircoplasma(field, location, Color.GREEN);
                        populateHelper(theirco);
                        break;
					case 4:
                        Cell theyco = new Theycoplasma(field, location, Color.PINK);
                        populateHelper(theyco);
                        break;
                    case 5:
                        Cell meco = new Mecoplasma(field, location, Color.magenta);
                        populateHelper(meco);
                     	break;
				}
			}
		}
	}
					
	/**
	* Pause for a given time.
	* @param millisec  The time to pause for, in milliseconds
	*/
	private void delay(int millisec) {
		try {
			Thread.sleep(millisec);
		}
		catch (InterruptedException ie) {
			// wake up
		}
	}
}