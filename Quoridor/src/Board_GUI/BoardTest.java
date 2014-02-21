package Board_GUI;

/*
 * GridBagLayoutDemo.java requires no other files.
 */

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BoardTest extends JFrame{
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;
	
  // Create a constructor method
  public BoardTest(){
    super();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		if (shouldFill) {
			//natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}

		button = new BoardSpace("1");
		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(button, c);

		//pane.add(new JLabel("     "));

		button = new BoardSpace("2");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(button, c);

		//pane.add(new JLabel("     "));

		button = new BoardSpace("3");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 4;
		c.gridy = 0;
		c.ipadx = 0;      //make this component tall
		c.ipadx = 1;      //make this component tall
		pane.add(button, c);

		button = new Intersect("");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		pane.add(button, c);

		button = new Intersect("");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		pane.add(button, c);

		button = new BoardSpace("4");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		c.ipadx = 0;      //make this component tall
		c.ipadx = 1;      //make this component tall
		pane.add(button, c);

		button = new BoardSpace("5");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 3;
		c.ipadx = 0;      //make this component tall
		c.ipadx = 1;      //make this component tall
		pane.add(button, c);

		button = new BoardSpace("6");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 4;
		c.gridy = 3;
		c.ipadx = 0;      //make this component tall
		c.ipadx = 1;      //make this component tall
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		pane.add(button, c);
		pane.repaint();
		

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
	super.paint(g);
    g.setColor(Color.MAGENTA);
    g.fillRect(47, 85, 30, 20);
    g.fillRect(47, 117, 30, 20);
    g.setColor(Color.BLUE);
    g.fillRect(1, 105, 46, 12);
    g.fillRect(77, 105, 46, 12);
  }

  public static void main(String arg[]){
    BoardTest frame = new BoardTest();
    frame.setSize(200,200);
    frame.setVisible(true);
    
    
  }
}