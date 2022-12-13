/**
 * file: PA3Main.java
 * class: CSC210
 * instructor: David Claveau
 * author: Aiden Foster
 * purpose: The purpose of this file is to be the main file in this folder of classes that will allow us to call
 * 			other classes from other files to run the back end of this project.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PA3Main {
	
	//  I created this list of strings so I can use them in this project to differentiate what is what
	static List<String> trees = Arrays.asList("oak","willow","banana","coconut","pine");
	static List<String> flowers = Arrays.asList("iris","lily","rose","daisy","tulip","sunflower");
	static List<String> vegetables = Arrays.asList("garlic","zucchini","tomato","yam","lettuce");
	
	static List<String> typeList = Arrays.asList("tree","flower","vegetable");
	static List<String> allList = Arrays.asList("oak","willow","banana","coconut","pine","iris","lily","rose","daisy","tulip","sunflower","garlic","zucchini","tomato","yam","lettuce");
	
	static int row;
	static int col;
	static Garden garden = new Garden();

	/**
	 * This is the main method that takes the file, splits it in to commands, and then executes it all
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner line = fileReader(args);
		String[] rowLine = line.nextLine().split(" ");
		String[] colLine = line.nextLine().split(" ");
		row = Integer.parseInt(rowLine[1]);
		col = Integer.parseInt(colLine[1]);
		// error check for too many columns
		if(col>16) {
			System.out.println("Too many plot columns.");
			System.exit(0);
		}
		// get a list of commands we can loop through later and execute
		ArrayList<String> commandList = fileCommands(line);
		readAndExecuteCommands(commandList);
		//garden.printGarden();
	}

	/**
	 * This method reads the file and returns the scanner holding it
	 * @return this returns the scanner of the file
	 * @throws FileNotFoundException
	 */
	public static Scanner fileReader(String[] args) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new File(args[0]));
		return fileReader;
	}
	
	/**
	 * This method reads the rest of the file and returns a list of the remaining commands
	 * @param line this is the line we will start to read the file from
	 * @return this returns an array list of the left over commands
	 */
	public static ArrayList<String> fileCommands(Scanner line) {
		ArrayList<String> commands = new ArrayList<String>();
		while(line.hasNextLine()) {
			String fileLine = line.nextLine();
			if(!fileLine.isBlank())
				commands.add(fileLine);
		}
		return commands;
	}
	
	/**
	 * This method executes all of the commands based on the first section of the command.
	 * @param commands This is the list of commands we will go through
	 */
	public static void readAndExecuteCommands(ArrayList<String> commands) {
		garden.setGardenDimensions(row,col);
		// go through each line of command
		for(int command=0;command<commands.size();command++) {
			String[] commandSplit = commands.get(command).split(" ");
			String commandName = commandSplit[0].toLowerCase();
			//
			if(commandName.equals("plant")) {
				String[] cords = commandSplit[1].split("");
				// get the row and column
				int rowToPlant = Integer.parseInt(cords[1]);
				int colToPlant = Integer.parseInt(cords[3]);
				// get the name
				String name = commandSplit[2];
				Plant newPlantObject = createPlantObject(name);
				garden.addToGarden(newPlantObject, rowToPlant, colToPlant);
			}
			//
			else if(commandName.equals("grow")) {
				if(commandSplit.length == 2) {
					//grow everything n number of times
					System.out.println("> GROW " + commandSplit[1]+"\n");
					garden.growEverything(Integer.parseInt(commandSplit[1]));
				}
				else if(commandSplit.length == 3 && typeList.contains(commandSplit[2].toLowerCase())) {
					System.out.println("> GROW " + commandSplit[2].toLowerCase()+"\n");
					//grow a certain plant a n amount of times
					garden.growAPlantType(Integer.parseInt(commandSplit[1]), commandSplit[2].toLowerCase());
				}
				else if(commandSplit.length == 3) {

					//grow a specific object n amount of times
					String cordinates = commandSplit[2];
					cordinates = cordinates.replaceAll("[()]","");
					String[] growCordinates = cordinates.split(",");
					int growRow = Integer.parseInt(growCordinates[0]);
					int growCol = Integer.parseInt(growCordinates[1]);
					System.out.println("> GROW "+commandSplit[1]+" (" +growCordinates[0]+ "," + growCordinates[1] + ")\n");
					if(growRow<row && growCol<col) {
						garden.growACertainPlant(Integer.parseInt(commandSplit[1]), growRow, growCol);
					}
					else {System.out.println("Can't grow there\n");}
				}
			}
			//
			else if(commandName.equals("harvest")) {
				if(commandSplit.length == 1) {
					System.out.println("> HARVEST\n");
					garden.harvest();
				}
				// if the second part of the line is part of the list we know what to run
				else if(commandSplit.length == 2 && allList.contains(commandSplit[1].toLowerCase())) {
					System.out.println("> HARVEST " + commandSplit[1].toLowerCase()+"\n");
					garden.harvestPlantType(commandSplit[1].toLowerCase());
				}
				// otherwise it will have a coordinate
				else if(commandSplit.length == 2) {
					String cordinates = commandSplit[1];
					cordinates = cordinates.replaceAll("[()]","");
					String[] growCordinates = cordinates.split(",");
					int harvestRow = Integer.parseInt(growCordinates[0]);
					int harvestCol = Integer.parseInt(growCordinates[1]);
					System.out.println("> HARVEST (" +growCordinates[0]+ "," + growCordinates[1] + ")\n");
					if(harvestRow<row && harvestCol<col) {
						garden.harvestSpecificPlot(harvestRow, harvestCol); 
					}
					else {System.out.println("Can't harvest there\n");}
				}
			}
			//
			else if(commandName.equals("pick")) {
				if(commandSplit.length == 1) {
					System.out.println("> PICK\n");
					garden.pick();
				}
				// if the second part of the line is part of the list we know what to run
				else if(commandSplit.length == 2 && allList.contains(commandSplit[1].toLowerCase())) {
					System.out.println("> PICK " + commandSplit[1].toLowerCase()+"\n");
					garden.pickPlantType(commandSplit[1].toLowerCase());
				}
				// otherwise it will have a coordinate
				else if(commandSplit.length == 2) {
					String cordinates = commandSplit[1];
					cordinates = cordinates.replaceAll("[()]","");
					String[] growCordinates = cordinates.split(",");
					int pickRow = Integer.parseInt(growCordinates[0]);
					int pickCol = Integer.parseInt(growCordinates[1]);
					System.out.println("> PICK (" +growCordinates[0]+ "," + growCordinates[1] + ")\n"); 
					if(pickRow<row && pickCol<col) {
						garden.pickSpecificPlot(pickRow, pickCol);					
					}
					else {System.out.println("Can't pick there\n");}
				}
			}
			//
			else if(commandName.equals("cut")) {
				if(commandSplit.length == 1) {
					System.out.println("> CUT\n");
					garden.cut();
				}
				// if the second part of the line is part of the list we know what to run
				else if(commandSplit.length == 2 && allList.contains(commandSplit[1].toLowerCase())) {
					System.out.println("> CUT " + commandSplit[1].toLowerCase()+"\n");
					garden.cutPlantType(commandSplit[1].toLowerCase());
				}
				// otherwise it will have a coordinate
				else if(commandSplit.length == 2) {
					String cordinates = commandSplit[1];
					cordinates = cordinates.replaceAll("[()]","");
					String[] growCordinates = cordinates.split(",");
					int cutRow = Integer.parseInt(growCordinates[0]);
					int cutCol = Integer.parseInt(growCordinates[1]);
					System.out.println("> CUT (" +growCordinates[0]+ "," + growCordinates[1] + ")\n"); 
					if(cutRow<row && cutCol<col) {
						garden.cutSpecificPlot(cutRow, cutCol);
					}
					else {System.out.println("Can't cut there\\n");}
				}
			}
			//
			else if(commandName.equals("print")) {
				System.out.println("> PRINT");
				garden.printPlot();
			}
		}
	}
	
	/**
	 * This method returns the plant type as a string so we can compare it
	 * @param name this is the name given in the file commands
	 * @return this returns the string type of the plant given
	 */
	public static String plantType(String name) {
		if(trees.contains(name.toLowerCase())) { return "tree"; }
		if(flowers.contains(name.toLowerCase())) { return "flower"; }
		if(vegetables.contains(name.toLowerCase())) { return "vegetable"; }
		// just for java's sake
		return null;
	}
	
	/**
	 * This method creates the new plant objects so they can be added to a list for the garden
	 * @param name This is the name given in the commands file that lets us see what it is 
	 * @return This returns the plant object we have created
	 */
	public static Plant createPlantObject(String name) {
		String type = plantType(name);
		char tokenChar = name.toLowerCase().charAt(0);
		String token = Character.toString(tokenChar);
		Plant newPlant = null;
		// check what type it is so we can use the correct sub class
		if(type.equals("tree")) {
			newPlant = new Tree(type,token,name);
		}
		if(type.equals("flower")) {
			newPlant = new Flower(type,token,name);
		}
		if(type.equals("vegetable")) {
			newPlant = new Vegetable(type,token,name);
		}
		return newPlant;
	}
	
}
