package Clients;

import Board_GUI.BoardGui;
import Board.Board;

public class main {
	public static void main (String [] args) {
		Board board = new Board(2);
		
		board.setCurrPlayer(0);
		
		BoardGui frame = new BoardGui(board.getPlayerCount(), board);
		frame.setSize(700,410);
		frame.setResizable( false );
		frame.setVisible(true);
		
	}
}
