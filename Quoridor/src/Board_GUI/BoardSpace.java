package Board_GUI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardSpace extends JButton{
	boolean here = false;

	public BoardSpace(String n){
			super(n);
			setForeground(Color.BLACK);
			setBackground(new Color(100,50,50));
			setPreferredSize(new Dimension(20,20));
			addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					here = true;
					setBackground( Color.BLUE);
				}
			});
	}
	
	public void setHereTrue(){
		here = true;
		setBackground( Color.BLUE);
	}
	
	public void setHereFalse(){
		here = false;
		setBackground(new Color(100,50,50));
	}
	
	public boolean returnHere(){
		return here;
	}
}
