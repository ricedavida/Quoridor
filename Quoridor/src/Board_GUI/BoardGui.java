package Board_GUI;
/* 
 * name of file: BoardGui.java
 * Authors: David Rice
 * Last updated: March 12, 6:38 David Rice
 * 
 * This File builds the GUI for the Quoridor game.  It passes and reads strings
 * to the back end so the board state will change. 
 */

import java.awt.*;
import Board.Board;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardGui extends JFrame implements ActionListener, MouseListener{
	final static boolean RIGHT_TO_LEFT = false;
	private int players; //how many players will the game originally start with
	private JButton submit; //recording current move
	private Image wood; //the background image for a game
	private Graphics2D g2d;
	//The following are for specific game moves
	private String placed = null;
	private String submitMove = null;
	private BoardSpace button = null;
	private Intersect inter = null;
	public ArrayList<BoardSpace> space = new ArrayList<BoardSpace>();
	public ArrayList<Intersect> sect = new ArrayList<Intersect>();
	private ArrayList<String> walls = new ArrayList<String>();
	private ArrayList<BoardSpace> movableSpace = new ArrayList<BoardSpace>();
	private JLabel[] labels = new JLabel[4];
	private int currentPosition = 0; // this is how the board will keep track of board space
	private int newPosition = 0; // this is how the board will keep track of board space

	private Board board;

	// create the BoardGui based on a player count
	public BoardGui(int players, Board board) {
		super();
		this.players = players;
		this.board = board;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// setting the background specific to the player count
		if (players == 4) {
			wood = new ImageIcon("board_4players.jpg").getImage();
		} else {
			wood = new ImageIcon("board_2players.jpg").getImage();
		}

		setContentPane(new ImagePanel(wood));
		addComponentsToPane(this.getContentPane());

		this.pack();
		this.setVisible(true);
	}

	// This method will construct the game board.
	// It will include BoardSpaces, Intersects, JLabels and a JButton
	// The BoardSpaces will represent player position
	// The Intersects will represent wall position
	// JLabels will keep track of player state
	// JButton will be used to submit the player move
	public void addComponentsToPane(Container pane) {
		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}
		// Using a GridBagLayout so that the spaces can be uniquely sized
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);
		pane.setLayout(new GridLayout());

		GridBagConstraints c = new GridBagConstraints();

		// Create the BoardSpaces in a 9x9 grid
		for (int i = 0 ; i < 9 ; i++) {
			for (int j = 0 ; j < 9 ; j++) {
				BoardSpace tmp = new BoardSpace("", ""+ ((i*9) + j), false); 
				tmp.addActionListener(this);

				// Add the BoardSpace
				c.weightx = 0.5;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = j*2;
				c.gridy = (i * 2);
				space.add(tmp);
				panel.add(tmp, c);
			}
		}
		// Create the Intersects, VWalls and HWalls interlacing them between the
		// BoardSpaces
		for (int i = 0 ; i < 8 ; i++) {
			for (int j = 0 ; j < 8 ; j++) {
				// Create 4 walls
				VWall top = new VWall("", ((i-1) + "-" + (j)), false);
				VWall bottom = new VWall("", ((i-1) + "-" + (j)), false);
				HWall left = new HWall("", ((i) + "-" + (j-1)), false);
				HWall right = new HWall("", ((i) + "-" + (j-1)), false);

				// Create an Intersect using the 4 walls
				Intersect tmp = new Intersect("", ((i*9)+j) + "", top,bottom,left,right);
				tmp.addActionListener(this);

				// Add the Intersect
				c.weightx = 0.5;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = j*2+1;
				c.gridy = (i * 2)+1;
				sect.add(tmp);
				panel.add(tmp, c);

				// Add the walls
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = j*2+1;
				c.gridy = (i * 2);
				panel.add(top, c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = j*2+1;
				c.gridy = (i * 2)+2;
				panel.add(bottom, c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = j*2;
				c.gridy = (i * 2)+1;
				panel.add(left, c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = j*2+2;
				c.gridy = (i * 2)+1;
				panel.add(right, c);
			}
		}

		//this area calls a board method to get the current player positions
		space.get(board.getPos(0)).setColor(Color.BLUE);
		space.get(board.getPos(0)).setPotential(false);
		space.get(board.getPos(0)).setClicked(true);

		space.get(board.getPos(1)).setColor(Color.GREEN);
		space.get(board.getPos(1)).setPotential(false);
		space.get(board.getPos(1)).setClicked(true);

		if (players == 4) {
			space.get(board.getPos(2)).setColor(Color.RED);
			space.get(board.getPos(2)).setPotential(false);
			space.get(board.getPos(2)).setClicked(true);

			space.get(board.getPos(3)).setColor(Color.CYAN);
			space.get(board.getPos(3)).setPotential(false);
			space.get(board.getPos(3)).setClicked(true);
		}

		// This sets the initial possible moves for player 1
		paintPos(0);

		pane.add(panel);

		// Create 4 JLabels to hold player wall count
		JPanel grid = new JPanel(new GridLayout(5,1));
		grid.setOpaque(false);
		labels[0] = new JLabel("\t\t\t\t\t\t\t" + board.getWallCount(0) + " walls remain");
		labels[0].setForeground(Color.BLUE);
		grid.add(labels[0]);
		labels[1] = new JLabel("\t\t\t\t\t\t\t" + board.getWallCount(1) + " walls remain");
		labels[1].setForeground(Color.GREEN);
		grid.add(labels[1]);
		labels[2] = new JLabel("\t\t\t\t\t\t\t");
		labels[2].setForeground(Color.RED);
		grid.add(labels[2]);
		labels[3] = new JLabel("\t\t\t\t\t\t\t");
		labels[3].setForeground(Color.CYAN);
		// Add the JLabels
		grid.add(labels[3]);

		if (players == 4) {
			labels[2].setText("\t\t\t\t\t\t\t" + board.getWallCount(2) + " walls remain");
			labels[3].setText("\t\t\t\t\t\t\t" + board.getWallCount(3) + " walls remain");
		}

		// Create and add a Submit JButton
		submit = new JButton("Submit");
		submit.addMouseListener(this);
		submit.setForeground(Color.WHITE);
		submit.setOpaque(false);
		submit.setBorderPainted(false);
		submit.setOpaque(false);
		submit.setContentAreaFilled(false);
		submit.addActionListener(this);
		grid.add(submit);
		pane.add(grid);
	}

	public void paintPos(int player) {
		// This sets the initial possible moves for player 1
		int[] pos = board.getPossible(player);

		for (int i = 0 ; i < pos.length ; i++) {
			space.get(pos[i]).setColor(board.getPlayer(board.getCurrPlayer()).getColor());
			space.get(pos[i]).setPotential(true);
			space.get(pos[i]).repaint();
		}
	}

	public void removePos(int player) {
		// This sets the initial possible moves for player 1
		int[] pos = board.getPossible(player);
		for (int i = 0 ; i < pos.length ; i++) {
			space.get(pos[i]).setPotential(false);
			space.get(pos[i]).repaint();
		}
	}

	public void removeOldMoves(int player, int currSpace) {
		// This sets the initial possible moves for player 1
		int[] pos = board.getPossible(player);
		for (int i = 0 ; i < pos.length ; i++) {
			space.get(pos[i]).setClicked(false);
			space.get(pos[i]).repaint();
		}
		space.get(currSpace).setClicked(false);
		space.get(currSpace).repaint();

	}

	// This paint method will draw the background image onto the board
	@Override
	public void paint(Graphics g){
		g2d = (Graphics2D)g;
		g2d.clearRect(0, 0, getWidth(), getHeight());
		g2d.drawImage(wood,0,0,getWidth(),getHeight(),null);
		super.paint(g2d);

	}

	// This actionPerformed method will handle the state on the board
	@Override
	public void actionPerformed(ActionEvent e) {
		// Handle BoardSpace clicking 
		if (e.getSource() instanceof BoardSpace) {
			button = (BoardSpace)e.getSource();
			// setting true will make a player appear, false will make them disappear
			newPosition = Integer.parseInt(button.getId());

			if (board.checkMove(board.getCurrPlayer(), newPosition) || 
					board.getPos(board.getCurrPlayer()) == newPosition) {
				button.setClicked(true);
				currentPosition = board.getPos(board.getCurrPlayer());


				removeOldMoves(board.getCurrPlayer(), currentPosition);
				paintPos(board.getCurrPlayer());

				/* 
				 * Fix Here
				 * 
				 * I need to have a win/lose state in this area,
				 * basically if a player selects a winning tile, 
				 * it will launch a JMessage with either, you win
				 * or you lose messages
				 */
				
				if (space.get(currentPosition).equals(space.get(newPosition))) {
					space.get(currentPosition).setPotential(false);
					space.get(currentPosition).setLastSpace(false);
					space.get(currentPosition).setClicked(true);
					space.get(currentPosition).repaint();
					submitMove = null;
					placed = null;
				} else if (placed == null || button.getId() != submitMove) {
					space.get(currentPosition).setClicked(false);
					space.get(currentPosition).setLastSpace(true);
					space.get(Integer.parseInt(button.getId())).setClicked(true);
					space.get(Integer.parseInt(button.getId())).setPotential(false);
					space.get(Integer.parseInt(button.getId())).repaint();
					space.get(currentPosition).repaint();
					submitMove = currentPosition + "";
					placed = button.getId();
				} else {
					space.get(currentPosition).setClicked(true);
					space.get(currentPosition).repaint();
				}	
			}
		} else if (e.getSource() instanceof Intersect) { // Handle Intersect clicking
			inter = (Intersect)e.getSource(); 
			if (placed == null){
				// This is how the horizontal walls are built
				if(inter.getWall() == 0) { // handle horizontal wall
					placed = inter.getId();
					inter.setWall(1);
					HWall left = inter.getLeftWall();

					// the submit move is the intersect location followed by h
					submitMove = inter.getId() + "h";
					// setting true will make a wall appear
					left.setClicked(true);
					left.repaint();
					HWall right = inter.getRightWall();
					right.setClicked(true);
					right.repaint();
				} 
			} else {
				if(inter.getWall() == 1) { // handle vertical wall
					inter.setWall(2);

					// This is how the vertical walls are cleared				
					HWall left = inter.getLeftWall();

					// the submit move is the intersect location followed by h
					submitMove = inter.getId() + "v";

					// paint walls
					left.setClicked(false);
					left.repaint();
					HWall right = inter.getRightWall();
					right.setClicked(false);
					right.repaint();

					// This is how the vertical walls are built
					VWall top = inter.getTopWall();
					top.setClicked(true);
					top.repaint();
					VWall bottom = inter.getBottomWall();
					bottom.setClicked(true);
					bottom.repaint();
				} else if(inter.getWall() == 2) {
					placed = null;
					submitMove = null;

					// This is how the vertical walls are cleared
					inter.setWall(0);
					VWall top = inter.getTopWall();
					top.setClicked(false);
					top.repaint();
					VWall bottom = inter.getBottomWall();
					bottom.setClicked(false);
					bottom.repaint();
				}
			}
		} else if (e.getSource().equals(submit)) { // handle Submit clicking
			if (submitMove != null) {
				if (button != null) { // handle spaces
					currentPosition = board.getPos(board.getCurrPlayer());

					if (!space.get(currentPosition).equals(space.get(newPosition))) {
						removePos(board.getCurrPlayer());
						space.get(currentPosition).setPotential(false);
						space.get(currentPosition).setLastSpace(false);
						space.get(currentPosition).repaint();
						board.setPos(board.getCurrPlayer(), Integer.parseInt(button.getId()));
						space.get(board.getPos(board.getCurrPlayer())).setClicked(true);
						space.get(board.getPos(board.getCurrPlayer())).setColor(board.getPlayer(board.getCurrPlayer()).getColor());

						space.get(board.getPos(board.getCurrPlayer())).repaint();

						if (players == 4) {
							board.setCurrPlayer((board.getCurrPlayer()+1)%4);
						} else {
							board.setCurrPlayer((board.getCurrPlayer()+1)%2);
						}

						paintPos(board.getCurrPlayer());
						submitMove = null;
						placed = null;
					}
				} else if (inter != null) {	// handle walls		
					int curr = board.getPos(board.getCurrPlayer());
					removePos(board.getCurrPlayer());
					space.get(curr).repaint();

					if (inter.getWall() == 1) { // set horizontal
						walls.add(inter.getId()+"h");

						board.setWall(board.getCurrPlayer(), inter.getId() + "h");

						labels[board.getCurrPlayer()].setText("\t\t\t\t\t\t\t" + board.getWallCount(board.getCurrPlayer()) + " walls remain");


						inter.setBorderPainted(false);
						inter.setClicked(true);
						inter.setEnabled(false);
						submitMove = null;
						placed = null;
					} else if (inter.getWall() ==2){ // set vertical
						walls.add(inter.getId()+"v");
						board.setWall(board.getCurrPlayer(), inter.getId() + "v");

						labels[board.getCurrPlayer()].setText("\t\t\t\t\t\t\t" + board.getWallCount(board.getCurrPlayer()) + " walls remain");


						inter.setBorderPainted(false);
						inter.setClicked(true);
						inter.setEnabled(false);
						submitMove = null;
						placed = null;
					}

					if (players == 4) {
						board.setCurrPlayer((board.getCurrPlayer()+1)%4);
					} else {
						board.setCurrPlayer((board.getCurrPlayer()+1)%2);
					}

					paintPos(board.getCurrPlayer());
				}
			}
		}
	}

	// this inner class will just create an image used as the background for our board
	class ImagePanel extends JComponent {
		private Image image;

		// create a ImagePangel by passing an image
		public ImagePanel(Image image) {
			this.image = image;
		}

		// This will paint the image it is provided
		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, null);
		}
	}

	// this will change the text color on the Submit button to green
	// when the mouse is over the button
	@Override
	public void mouseEntered(MouseEvent e) {
		submit.setForeground(Color.GREEN);
	}

	// this will change the text color on the Submit button to white
	// when the mouse is not over the button
	@Override
	public void mouseExited(MouseEvent e) {
		submit.setForeground(Color.WHITE);
	}


	//NOT CURRENTLY USED
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	// this will change the text color on the Submit button to blue
	// when the mouse is clicking the button
	@Override
	public void mousePressed(MouseEvent e) {
		submit.setForeground(Color.BLUE);
	}

	// this will change the text color on the Submit button to green
	// when the mouse is done clicking the button
	@Override
	public void mouseReleased(MouseEvent e) {
		submit.setForeground(Color.GREEN);
	}

}