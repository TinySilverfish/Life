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
* @author David J. Barnes, Michael Kölling & Jeffery Raphael
* @version 2022.01.06 (1)
*/

public class Simulator {
	// The default width for the grid.
	private static final int DEFAULT_WIDTH = 100;
	
	// The default depth of the grid.
	private static final int DEFAULT_DEPTH = 100;
	
	// The probability that a Mycoplasma is alive
	private static final double MYCOPLASMA_ALIVE_PROB = 0.3;
	
	private static final double YOUCOPLASMA_ALIVE_PROB = 0.6;
	// List of cells in the field.
	private List<Cell> cells;
	
	// The current state of the field.
	private Field field;
	
	// The current generation of the simulation.
	private int generation;
	
	// A graphical view of the simulation.
	private SimulatorView view;

	private Color pink = new Color(249, 181, 208);
	
	/**
	* Execute simulation
	*/
	public static void main(String[] args) {
		Simulator sim = new Simulator(25, 25);
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
			simOneGeneration();
			delay(2500);   // comment out to run simulation faster
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
	*
	*
	*	
	*/
	// private List<Cell> generateCells(){
	// 	Location location = new Location(0, 0);

	// 	List<Cell> gridCells = Arrays.asList(
	// 			new Mycoplasma(field, location, Color.ORANGE),
	// 			new Youcoplasma(field, location, Color.CYAN),
	// 			null
	// 			);
	// 	//Setting weights
	// 	// gridCells.get(0).setWeight(6);
		
	// 	// Build map keyed by cumulative weight
	// 	NavigableMap<Integer, Cell> weighedMap = new TreeMap<>();
	// 	int totalWeight = 0;
	// 	for (Cell obj : gridCells) {
	// 		totalWeight += obj.getWeight();
	// 		weighedMap.put(totalWeight, obj);
	// 	}
	// 	// System.out.println(weighedMap);
		
	// 	List<Cell> result = Arrays.asList();
	// 	// Pick 20 objects randomly according to weight
	// 	Random rnd = new Random();
	// 	for (int i = 0; i < 10000; i++) {
	// 		int pick = rnd.nextInt(totalWeight);
	// 		location =  new Location(i/field.getWidth(), i%field.getWidth());
	// 		Cell temp = weighedMap.higherEntry(pick).getValue();
	// 		temp.setLocation(location);
	// 		gridCells.add(temp);
	// 		// System.out.printf("%2d: %s%n", pick, obj);
	// 	}
	// 	return gridCells;
		
	// }
	
	
	/**
	* Randomly populate the field live/dead life forms according to the weights
	*/
	
	
	private void populate() {
		Random rand = Randomizer.getRandom();
		field.clear();
		for (int row = 0; row < field.getDepth(); row++) {
			for (int col = 0; col < field.getWidth(); col++) {

				
				Location location = new Location(row, col);
				
				// Build list of objects
				
				Double randDouble = rand.nextDouble();  

				if (randDouble <= MYCOPLASMA_ALIVE_PROB) {
					Cell myco = new Mycoplasma(field, location, Color.PINK);
					cells.add(myco);
					
				}
				else if(randDouble <= YOUCOPLASMA_ALIVE_PROB) {
					Cell youco = new Youcoplasma(field, location, Color.CYAN);
					cells.add(youco);
				}
				
				else{
					randDouble = rand.nextDouble();
					if (randDouble <= MYCOPLASMA_ALIVE_PROB) {
						Cell myco = new Mycoplasma(field, location, Color.PINK);
						myco.setDead();
						cells.add(myco);
						
					}
					else {
						Cell youco = new Youcoplasma(field, location, Color.CYAN);
						youco.setDead();
						cells.add(youco);
					}
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
