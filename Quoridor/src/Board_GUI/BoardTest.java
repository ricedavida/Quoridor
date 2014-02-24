package Board_GUI;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;

public class BoardTest extends JFrame{
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;
	private int width = 0;
	private int height = 0;
	private Container pane = getContentPane();

	// Create a constructor method
	public BoardTest(){
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Container pane = this.getContentPane();

		addComponentsToPane(this.getContentPane());

		this.pack();
		this.setVisible(true);
	}

	public static void addComponentsToPane(Container pane) {
		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		JButton button;
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		int row = 0;
		for (int h = 0 ; h < 17 ; h++) {
			if (h%2 == 0) {
				row++;
				for (int i = 0 ; i < 9 ; i++) {
					button = new BoardSpace(row + "-" + (i+1));
					c.weightx = 0.5;
					c.fill = GridBagConstraints.HORIZONTAL;
					c.gridx = i * 2;
					c.gridy = (h * 2);
					pane.add(button, c);
				}
			} else {
				for (int i = 0 ; i < 8 ; i++) {
					button = new Intersect("");
					c.fill = GridBagConstraints.HORIZONTAL;
					c.gridx = (i * 2) + 1;
					c.gridy = (h * 2) + 1;
					pane.add(button, c);
				}
			}
		}
		

		/*
	button = new JButton("Long-Named Button 4");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.ipady = 1;      //make this component tall
	c.weightx = 0.0;
	c.gridwidth = 3;
	c.gridx = 0;
	c.gridy = 1;
	pane.add(button, c);
		 */
		/*
	button = new JButton("5");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.ipady = 0;       //reset to default
	c.weighty = 1.0;   //request any extra vertical space
	c.anchor = GridBagConstraints.PAGE_END; //bottom of space
	c.insets = new Insets(10,0,0,0);  //top padding
	c.gridx = 2;       //aligned with button 2
	c.gridwidth = 2;   //2 columns wide
	c.gridy = 2;       //third row
	pane.add(button, c);
		 */
	}


	public void paint(Graphics g){
		g.clearRect(0, 0, getWidth(), getHeight());
		super.paint(g);

		//g.setColor(Color.MAGENTA);
		getHorizontalWall(g, 0, 0, Color.MAGENTA);
		//g.setColor(Color.BLUE);
		//g.fillRect(1, 105, 46, 12);
		//g.fillRect(77, 105, 46, 12);
	}
	
	public void getHorizontalWall(Graphics g, int row, int column, Color c) {
		//column = (column + 1) * 41;
		column = (column + 1) * 35;
		
		g.setColor(c);
		g.fillRect(0, column, 34, 13);
		g.fillRect(52, column, 34, 13);
	}
	

	public static void main(String arg[]){
		BoardTest frame = new BoardTest();
		frame.setSize(450,298);
		frame.setResizable( false );
		frame.setVisible(true); 
	}
}