package Board_GUI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardSpace extends JButton{
	boolean here = false;
	private String id;
	private boolean clicked = false;
	public BoardSpace(String n, String id, boolean clicked){
			super(n);
			this.id = id;
			this.clicked = clicked;
			setForeground(Color.BLACK);
			setBackground(new Color(100,50,50));
			setPreferredSize(new Dimension(20,30));
			
			setBorderPainted(false);
	}
	
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	
	public boolean isClicked() {
		return this.clicked;
	}
	
	public void paintComponent(Graphics g) {
		if (!clicked) {
			super.paintComponent(g);
		} else {
			g.setColor(Color.RED);
			g.fillOval(4, 4, 20, 20);
		}
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
