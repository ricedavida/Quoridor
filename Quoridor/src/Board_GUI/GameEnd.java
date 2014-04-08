package Board_GUI;

import javax.swing.JOptionPane;

import Board.Board;

public class GameEnd {

	public GameEnd(int playerCount, int winningPlayer, BoardGui frame) {
		int r = JOptionPane.showConfirmDialog (null, "Would you like to play again?",
				"Player " + winningPlayer +" You Win!", 0);
		
		frame.dispose();
		
		if (r == 0) {
			System.out.println("Player " + winningPlayer + " will play");
			
			Board board = new Board(2);

			board.setCurrPlayer(0);

			frame = new BoardGui(board.getPlayerCount(), board);
			frame.setSize(700,410);
			frame.setResizable( false );
			frame.setVisible(true);
		} else {
			System.out.println("Player " + winningPlayer + " will not play");
		}
	}
}
