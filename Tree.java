/**
 * file: Tree.java
 * class: CSC210
 * instructor: David Claveau
 * author: Aiden Foster
 * purpose: The purpose of this file is to hold the tree class extension
 */

class Tree extends Plant {
	
	// Initiate the constructor for the Tree plant extension
	public Tree(String type, String token, String name) {
		super(type,token,name);
		this.plot[4][2] = String.valueOf(token);

	}
}
