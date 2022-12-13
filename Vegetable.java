/**
 * file: Vegetable.java
 * class: CSC210
 * instructor: David Claveau
 * author: Aiden Foster
 * purpose: The purpose of this file is to hold the vegetable class extension
 */

class Vegetable extends Plant {

	// Initiate the constructor for the Vegetable plant extension
	public Vegetable(String type, String token, String name) {
		super(type,token,name);
		this.plot[0][2] = String.valueOf(token);
	}

}
