package Clients;
/*
 * main.java
 * Authors: David Rice
 */ 

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Board_GUI.BoardGui;
import Board_GUI.GameEnd;
import Board.Board;

/** This is the main for the Quoridor gamge. */
public class main {
	public static void main (String [] args) throws AWTException {
		int xButtonOffset = 16;
		int yButtonOffset = 63;
		
		int xInstersectOffset = 7;
		int yInstersectOffset = 51;
		
		
		int [] submitButton = {530, 390};
		
		Board board = new Board(2);

		board.setCurrPlayer(0);

		BoardGui frame = new BoardGui(board.getPlayerCount(), board);
		frame.setSize(700,410);
		frame.setResizable( false );
		frame.setVisible(true);

		//GameEnd end = new GameEnd(board.getPlayerCount(), 0);
		/*
		Robot bot = new Robot();
		// Spaces
		bot.mouseMove(frame.space.get(3).getX()+xButtonOffset,
				frame.space.get(3).getY()+yButtonOffset);

		bot.mousePress(InputEvent.BUTTON1_MASK);
		//add time between press and release or the input event system may 
		//not think it is a click
		try{Thread.sleep(250);}catch(InterruptedException e){}
		bot.mouseRelease(InputEvent.BUTTON1_MASK);

		// Rest for a little while 
		try{Thread.sleep(900,000);}catch(InterruptedException e){}

		// Submit
		bot.mouseMove(submitButton[0],submitButton[1]);

		bot.mousePress(InputEvent.BUTTON1_MASK);
		//add time between press and release or the input event system may 
		//not think it is a click
		try{Thread.sleep(250);}catch(InterruptedException e){}
		bot.mouseRelease(InputEvent.BUTTON1_MASK);

		// Rest for a little while 
		try{Thread.sleep(900,000);}catch(InterruptedException e){}

		// Intersects
		bot.mouseMove(frame.sect.get(17).getX()+xInstersectOffset,
				frame.sect.get(17).getY()+yInstersectOffset);

		bot.mousePress(InputEvent.BUTTON1_MASK);
		//add time between press and release or the input event system may 
		//not think it is a click
		try{Thread.sleep(250);}catch(InterruptedException e){}
		bot.mouseRelease(InputEvent.BUTTON1_MASK);

		// Rest for a little while 
		try{Thread.sleep(900,000);}catch(InterruptedException e){}

		// Submit
		bot.mouseMove(submitButton[0],submitButton[1]);

		bot.mousePress(InputEvent.BUTTON1_MASK);
		//add time between press and release or the input event system may 
		//not think it is a click
		try{Thread.sleep(250);}catch(InterruptedException e){}
		bot.mouseRelease(InputEvent.BUTTON1_MASK);
		*/
		
	}
}

/*
 * Possible fix for the board issues,
 * 		after each move, have a method go through and recheck each space and wall
 * 		to repaint things, this could help fix some of the issues.
 * 		This technique should also help things like walls, to force them to know 
 * 		what their syblings are doing, so they know if they should exist or not. 
 */