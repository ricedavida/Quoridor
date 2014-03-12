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
	private String wallPlaced = null;
	private String submitMove = null;
	private BoardSpace button = null;
	private Intersect inter = null;
	private ArrayList<BoardSpace> space = new ArrayList<BoardSpace>();
	private ArrayList<Intersect> sect = new ArrayList<Intersect>();
	private ArrayList<String> walls = new ArrayList<String>();
	
	// create the BoardGui based on a player count
	public BoardGui(int players) {
		super();
		this.players = players;
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
				BoardSpace tmp = new BoardSpace("", i + "-" + j, false); 
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
		
		 /* 
		  * FIX ME 
		  * 
		  * this area needs to call a method to get the current player positions
		  * 
		  */
		space.get(4).setColor(Color.BLUE);
		space.get(4).setPotential(false);
		space.get(4).setClicked(true);

		space.get(76).setColor(Color.GREEN);
		space.get(76).setPotential(false);
		space.get(76).setClicked(true);
		
		if (players == 4) {
			space.get(36).setColor(Color.RED);
			space.get(36).setPotential(false);
			space.get(36).setClicked(true);

			space.get(44).setColor(Color.CYAN);
			space.get(44).setPotential(false);
			space.get(44).setClicked(true);
		}
		
		/*
		 * FIX ME
		 * 
		 * this is an example for potential spaces, but needs to call a method
		 */
		space.get(3).setPotential(true);
		space.get(5).setPotential(true);
		space.get(13).setPotential(true);
		
		pane.add(panel);
		
		/*
		 * FIX ME
		 * 
		 * These should calculate the walls based on a method
		 */
		// Create 4 JLabels to hold player information
		JPanel grid = new JPanel(new GridLayout(5,1));
		grid.setOpaque(false);
		JLabel label = new JLabel("\t\t\t" + 5 + " walls remain");
		label.setForeground(Color.BLUE);
		grid.add(label);
		JLabel label2 = new JLabel("\t\t\t" + 5 + " walls remain");
		label2.setForeground(Color.GREEN);
		grid.add(label2);
		JLabel label3 = new JLabel("\t\t\t");
		label3.setForeground(Color.RED);
		grid.add(label3);
		JLabel label4 = new JLabel("\t\t\t");
		label4.setForeground(Color.CYAN);
		// Add the JLabels
		grid.add(label4);
		
		if (players == 4) {
			label3.setText("\t\t\t" + 5 + " walls remain");
			label4.setText("\t\t\t" + 5 + " walls remain");
		}
		
		// Create and add a Submit JButton
		submit = new JButton("Submit");
		submit.addMouseListener(this);
		submit.setForeground(Color.WHITE);
		submit.setOpaque(false);
		submit.setBorderPainted(false);
		submit.addActionListener(this);
		grid.add(submit);
		pane.add(grid);
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
			if(button.isClicked()) {
				button.setClicked(false);
			} else {
				button.setClicked(true);
			}
		} else if (e.getSource() instanceof Intersect) { // Handle Intersect clicking
			inter = (Intersect)e.getSource(); 

			if (wallPlaced == null){
				// This is how the horizontal walls are built
				if(inter.getWall() == 0) { // handle horizontal wall
					wallPlaced = inter.getId();
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
					wallPlaced = null;
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
				if (inter != null) {
					/*
					 * FIX HERE
					 * 
					 * Currently prints out the move, but should pass that move to a
					 * method of some kind
					 */
					System.out.println("THIS IS MY MOVE " + submitMove);
					
					if (inter.getWall() == 1) { // set horizontal
						walls.add(inter.getId()+"h");
						inter.setBorderPainted(false);
						inter.setClicked(true);
						inter.setEnabled(false);
						submitMove = null;
						wallPlaced = null;
					} else if (inter.getWall() ==2){ // set vertical
						walls.add(inter.getId()+"v");
						inter.setBorderPainted(false);
						inter.setClicked(true);
						inter.setEnabled(false);
						submitMove = null;
						wallPlaced = null;
					}
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