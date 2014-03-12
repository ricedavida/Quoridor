package Board_GUI;
/* 
 * name of file: BoardSpace.java
 * Authors: David Rice
 * Last updated: March 12, 7:08 David Rice
 * 
 * This File will create a BoardSpace for the Quoridor game.  It will represent
 * where a given player is on the board.
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardSpace extends JButton{
	private String id; // the name of the BoardSpace
	private boolean clicked = false; // is there a player in the space
	private boolean potential = false; // is the space a potential move
	private Color color; // the color of the player
	
	// Construct a BoardSpace by passing it 2 Strings and a boolean variable
	public BoardSpace(String n, String id, boolean clicked){
			super(n);
			this.id = id;
			this.clicked = clicked;
			setForeground(Color.BLACK);
			setBackground(new Color(100,50,50));
			setPreferredSize(new Dimension(20,30));
			setBorderPainted(false);
	}
	
	// set the color of a player
	public void setColor(Color color) {
		this.color = color;
	}
	
	// set the field to be a player
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	// check if the field is a player
	public boolean isClicked() {
		return this.clicked;
	}
	
	// set the field to be a possible
	public void setPotential(boolean potential) {
		this.potential = potential;
	}
	
	// check if the is a possible 
	public boolean getPotential() {
		return this.potential;
	}

	// paint the state of the space
	public void paintComponent(Graphics g) {
		// handle player's location
		if (!clicked) {
			super.paintComponent(g);
		} else {
			g.setColor(color);
			g.fillOval(4, 4, 20, 20);
		}
		
		// handle the possible locations
		if(potential) {
			g.setColor(new Color(.5f, .5f, 1f, 0.60f));
			g.fillRect(0, 0, getWidth(), getHeight());
		}
	}
	
	// get the space's name
	public String getId() {
		return id;
	}
	// set the space's name
	public void setId(String id) {
		this.id = id;
	}
}
