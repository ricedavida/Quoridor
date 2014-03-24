package Board_GUI;

import javax.swing.JOptionPane;

public class GameEnd {

	public GameEnd(int playerCount, int winningPlayer) {
		for (int i = 0 ; i < playerCount ; i++) {
			int r;
			if (winningPlayer == i) {
				r = JOptionPane.showConfirmDialog (null, "Would you like to play again?","You Win!", 0);
			} else {
				r = JOptionPane.showConfirmDialog (null, "Would you like to play again?","You Lose!", 0);
			}
			
			if (r == 0) {
				System.out.println("Player " + i + " will play");
			} else {
				System.out.println("Player " + i + " will not play");
			}
		}
	}
}
