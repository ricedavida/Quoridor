package Board_GUI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardSpace extends JButton{
	boolean here = false;
	private String id;
	public BoardSpace(String n, String id){
			super(n);
			this.id = id;
			setForeground(Color.BLACK);
			setBackground(new Color(100,50,50));
			setPreferredSize(new Dimension(20,30));
			
			//setOpaque(false);
			setBorderPainted(false);
			
			addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					here = true;
					setBackground( Color.BLUE);
				}
			});
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
