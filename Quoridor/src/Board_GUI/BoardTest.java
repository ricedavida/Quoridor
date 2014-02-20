package Board_GUI;

/*
 * GridBagLayoutDemo.java requires no other files.
 */

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BoardTest {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

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
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}


/*
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardTest extends JFrame {
	JButton grid[][] = new JButton[17][17];
	JFrame f;
	
	BoardTest() {
		init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setSize(1100,700); 
		setVisible(true);
	}
	
	public void init(){
		//setLayout(new GridLayout(17,17));
		setLayout(new GridBagLayout());
		int i,x;
		final int a,b;
		String letter = "I";
		int charValue = letter.charAt(0);	
		for(i=0;i<17;i++){
			if(i%2==0){
				for(x=0;x<17;x++){
					if(x%2==0){
						grid[i][x] = new BoardSpace(letter+"|"+x);
						add(grid[i][x]);
					}
					else{
						grid[i][x] = new VWall(i+"|"+x);
						add(grid[i][x]);
					}
				}
				charValue = charValue -1;
				letter = String.valueOf((char) charValue);
			}
			
			else{
				for(x=0;x<17;x++){
					if(x%2==0){
						grid[i][x] = new HWall(i+"|"+x);
						add(grid[i][x]);
					}
					else{
						grid[i][x] = new Intersect(i+"|"+x);
						add(grid[i][x]);
					}
				}
				
			}
		}
	}
	public static void main(String[] args){
		BoardTest b = new BoardTest();
	}
}
*/