package Board_GUI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardSpace extends JButton{
	boolean here = false;
	private String id;
	private static boolean clicked = false;
	public BoardSpace(String n, String id, boolean clicked){
			super(n);
			this.id = id;
			this.clicked = clicked;
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
	
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	
	public boolean isClicked() {
		return this.clicked;
	}
	
	public void paintComponent(Graphics g) {
		if (!clicked) {
            //g.clearRect(0, 0, getWidth(), getHeight());
			super.paintComponent(g);
            //g.setColor(Color.RED);
			//g.fillOval(4, 4, 20, 20);
		} else {
			System.out.println("here");
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
