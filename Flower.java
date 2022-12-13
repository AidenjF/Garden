/**
 * file: Flower.java
 * class: CSC210
 * instructor: David Claveau
 * author: Aiden Foster
 * purpose: The purpose of this file is to hold the flower class extension
 */

class Flower extends Plant {

	// Initiate the constructor for the Flower plant extension
	public Flower(String type, String token, String name) {
		super(type,token,name);
		this.plot[2][2] = String.valueOf(token);
	}
}

