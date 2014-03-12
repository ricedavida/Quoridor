package Board_GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;

public class VWall extends JButton {
	private String id;
	private String vert;
	private String horz;
	private boolean clicked = false;
	private Color color = Color.lightGray;
	
	public VWall(String n, String id, boolean clicked){
		super(n);
		this.id = id;
		String [] sects = id.split("-");
		this.vert = sects[0];
		this.horz = sects[1];
		this.clicked = clicked;
		setPreferredSize(new Dimension(4,30));
		setBorderPainted(false);
		setEnabled(false);
	}
	
	public void setColor(Color color) {
		this.color = color;
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
			g.setColor(color);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
		String [] sects = id.split("-");
		this.vert = sects[0];
		this.horz = sects[1];
	}
	
	public String getVert() {
		return vert;
	}
	
	public String getHorz() {
		return horz;
	}
}
