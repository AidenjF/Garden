/**
 * file: Plant.java
 * class: CSC210
 * instructor: David Claveau
 * author: Aiden Foster
 * purpose: The purpose of this file is to hold the public class plant that we will make abstract so we can have
 * 			many different instances of the plant object we can use and differentiate from
 */

public abstract class Plant {
	
	private String type;
	private String token;
	private String name;
	public String[][] plot = new String[5][5];
	private int grow;
	
	// Initiate the constructor for the plant abstract class
	public Plant(String type, String token, String name) {
		this.type = type;
		this.token = token;
		this.grow = 0;
		this.name = name;
		// create the plot
		for(int i = 0;i<5;i++) {
			String[] innerPlot= new String[5];
			for(int r = 0;r<5;r++) {
				innerPlot[r] = ".";
			}
			plot[i] = innerPlot;
		}
	}
	
	/**
	 * This method returns the line specified of the plot
	 * @param plotIndex The line we are too look at 
	 * @return Returns the line we want
	 */
	public String returnPlotLine(int plotIndex) {
		String stringToReturn = String.join("", plot[plotIndex]);
		return stringToReturn;
	}
	/**
	 * @return This method is a getter for the type
	 */
	public String returnType() {return type;}
	/**
	 * @return This method is a getter for the grow
	 */
	public int returnGrow() { return grow;}
	/**
	 * @return This method is a getter for the name
	 */
	public String returnName() {return name;}
	/**
	 * This method grows the plant the way it is supposed to grow according to the type is is
	 * @param numberOfTimes This is the number of times we are supposed to grow it
	 */
	public void grow(int numberOfTimes) {
		int curRowPlot=0;
		int curColPlot=0;
		// run however many times we are told to grow
		for(int i = 0; i<numberOfTimes; i++) {
			// make sure to grow it the right way
			if(type.equals("tree")) {
				grow++;
				if(grow<5) {
					curRowPlot=4 - grow;
					curColPlot=2;
					plot[curRowPlot][curColPlot] = String.valueOf(token);
				}
			}
			if(type.equals("vegetable")) {
				grow++;
				if(grow<5) {
					curRowPlot=0 + grow;
					curColPlot=2;
					plot[curRowPlot][curColPlot] = String.valueOf(token);
				}
			}
			if(type.equals("flower")) {
				if(grow<5) {
					grow++;
					plot = returnFlowerGrowPlot(grow);
				}
			}
		}
	}
	
	/**
	 * This is the specific grow method for the flower that tells us what the plot should look like after each grow
	 * @param grow The increment of grow it is on
	 * @return This returns the plot and what it should equal
	 */
	public String[][] returnFlowerGrowPlot(int grow){
		if(grow == 1) { return new String[][] {{".",".",".",".","."},{".",".",token,".","."},
			{".",token,token,token,"."},{".",".",token,".","."},{".",".",".",".","."}};
		}
		if(grow == 2) { return new String[][] {{".",".",token,".","."},{".",token,token,token,"."},
			{token,token,token,token,token},{".",token,token,token,"."},{".",".",token,".","."}};
		}
		if(grow == 3) { return new String[][] {{".",token,token,token,"."},{token,token,token,token,token},
			{token,token,token,token,token},{token,token,token,token,token},{".",token,token,token,"."}};
		}
		if(grow >= 4) { return new String[][] {{token,token,token,token,token},{token,token,token,token,token},
			{token,token,token,token,token},{token,token,token,token,token},{token,token,token,token,token}};
		}
		return null;
	}
}