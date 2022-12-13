/**
 * file: Garden.java
 * class: CSC210
 * instructor: David Claveau
 * author: Aiden Foster
 * purpose: The purpose of this file is to hold the public class Garden that we will use to call other methods
 * 			from our plant class that can help us change and differentiate the commands and objects
 */

public class Garden {
	
	public Plant[][] garden;
	
	public Garden() {}
	
	/**
	 * This is the method that adds the object to a specific point in our garden
	 * @param plantObject This is the object we are adding
	 * @param row The row it is to be added at
	 * @param col The columns it is to be added at
	 */
	public void addToGarden(Plant plantObject, int row, int col) {
		garden[row][col] = plantObject;
	}
	
	/**
	 * This is the method that sets the dimensions of the garden
	 * @param row This is how many rows the garden should have
	 * @param col This is how many columns the garden should have
	 */
	public void setGardenDimensions(int row,int col) {
		garden = new Plant[row][col];
	}
	
	/**
	 * This is the method that will iterate through the garden and get ride of anything that is a vegetable
	 */
	public void harvest() {
		for(int i=0; i<garden.length;i++) {
			for(int j=0;j<garden[i].length;j++) {
				// if we find a vegetable delete that plot
				if(garden[i][j] instanceof Vegetable) {
					garden[i][j] = null;
				}
			}
		}
	}
	
	/**
	 * This is the method that will get rid of the vegetable at the specific point given
	 * @param row This is the row the command tells us to look at
	 * @param col This is the column the command tells us to look at
	 */
	public void harvestSpecificPlot(int row, int col) {
		if(garden[row][col] instanceof Vegetable) {
			garden[row][col] = null;
		}
		else {
			// if it is not a vegetable
			System.out.println("Can’t harvest there.\n");
		}
	}
	
	/**
	 * This method will search for a specific vegetable type and will get rid of it
	 * @param name This is the name of the specific vegetable we are looking for
	 */
	public void harvestPlantType(String name) {
		for(int i=0; i<garden.length;i++) {
			for(int j=0;j<garden[i].length;j++) {
				if(garden[i][j] != null) {
					if(garden[i][j].returnName().equals(name)) {
						// if this plot is the exact type then delete it
						garden[i][j] = null;
					}
				}
			}
		}
	}
	
	/**
	 * This method prints out the objects of the garden in the order of rows and columns the user gave us originally
	 */
	public void printPlot() {
		// iterate through the garden rows, then through the columns and rows again
		for(Plant[] mapRow: garden) {
			for(int plotLine = 0;plotLine<5;plotLine++) {
				StringBuilder line = new StringBuilder();
				for(Plant curPlant: mapRow) {
					if(curPlant == null) {
						line.append(".....");
					}
					else {
					String stringToAdd = curPlant.returnPlotLine(plotLine);
					line.append(stringToAdd);
					}
				}
				System.out.println(line);
			}
		}
		System.out.println("");
	}
	
	/**
	 * This method loops through the garden and if there is a plant there it will grow it once
	 * @param number This is the number of times we are told to grow it
	 */
	public void growEverything(int number) {
		for(Plant[] mapRow: garden) {
			for(Plant curPlant: mapRow) {
				if(curPlant instanceof Tree) {
					curPlant.grow(number);
				}
				if(curPlant instanceof Flower) {
					curPlant.grow(number);
				}
				if(curPlant instanceof Vegetable) {
					curPlant.grow(number);
				}
			}
		}
	}
	
	/**
	 * This is the method that will grow n number of times at the specific point given
	 * @param row This is the row of the garden the command tells us to look at
	 * @param col This is the column of the garden the command tells us to look at
	 */
	public void growACertainPlant(int number, int row, int col) {
		if (garden[row][col] != null)
			garden[row][col].grow(number);
		else {
			System.out.println("Can't grow there.\n");
		}
	}
	
	/**
	 * This method will search for a specific plant type type and will grow it n number of times
	 * @param name This is the name of the specific tree we are looking for
	 */
	public void growAPlantType(int number, String plantType) {
		for(Plant[] mapRow: garden) {
			for(Plant curPlant: mapRow) {
				if(curPlant.returnType().toLowerCase().equals(plantType))
					// if it is the plant type exactly then grow it
					curPlant.grow(number);
			}
		}
	}
	
	/**
	 * This is the method that will iterate through the garden and get ride of anything that is a flower
	 */
	public void pick() {
		for(int i=0; i<garden.length;i++) {
			for(int j=0;j<garden[i].length;j++) {
				if(garden[i][j] instanceof Flower) 
					// if it is a flower delete it
					garden[i][j] = null;
			}
		}
	}
	
	/**
	 * This is the method that will get rid of the flower at the specific point given
	 * @param row This is the row the command tells us to look at
	 * @param col This is the column the command tells us to look at
	 */
	public void pickSpecificPlot(int row, int col) {
		if(garden[row][col] instanceof Flower)
			garden[row][col] = null;
		else 
			System.out.println("Can’t pick there.\n");
	}
	
	/**
	 * This method will search for a specific flower type and will get rid of it 
	 * @param name This is the name of the specific flower we are looking for
	 */
	public void pickPlantType(String name) {
		for(int i=0; i<garden.length;i++) {
			for(int j=0;j<garden[i].length;j++) {
				if(garden[i][j] != null) {
					if(garden[i][j].returnName().equals(name))
						garden[i][j] = null;
				}
			}
		}
	}
	
	/**
	 * This is the method that will iterate through the garden and get rid of anything that is a tree
	 */
	public void cut() {
		for(int i=0; i<garden.length;i++) {
			for(int j=0;j<garden[i].length;j++) {
				if(garden[i][j] instanceof Tree)
					garden[i][j] = null;
			}
		}
	}
	
	/**
	 * This is the method that will get rid of the tree at the specific point given
	 * @param row This is the row the command tells us to look at
	 * @param col This is the column the command tells us to look at
	 */
	public void cutSpecificPlot(int row, int col) {
		if(garden[row][col] instanceof Tree) 
			garden[row][col] = null;
		else 
			System.out.println("Can’t cut there.\n");
	}
	
	/**
	 * This method will search for a specific tree type and will get rid of it
	 * @param name This is the name of the specific tree we are looking for
	 */
	public void cutPlantType(String name) {
		for(int i=0; i<garden.length;i++) {
			for(int j=0;j<garden[i].length;j++) {
				if(garden[i][j] != null) {
					if(garden[i][j].returnName().equals(name))
						garden[i][j] = null;
				}
			}
		}
	}
}
