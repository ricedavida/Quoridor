package Board_GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;

//public class Intersect extends JButton {
public class Intersect extends JButton {

	private String id;
	private int wall = 0;
	private VWall top;
	private VWall bottom;
	private HWall left;
	private HWall right;
	private Intersect north;
	private Intersect south;
	private Intersect east;
	private Intersect west;
	private boolean clicked = false;
	private Color color = Color.lightGray;
	
	public Intersect(String n, String id, VWall top, VWall bottom, HWall left, HWall right){
		super(n);
		this.id = id;
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
		setPreferredSize(new Dimension(4,12));
	}
	
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	
	public boolean isClicked(){
		return clicked;
	}
	
	public void paintComponent(Graphics g) {
		if (!clicked) {
			super.paintComponent(g);
		} else {
			g.setColor(color);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
	}
	
	public void setWall(int wall) {
		this.wall = wall;
	}
	
	public int getWall(){
		return wall;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public VWall getTopWall() {
		return this.top;
	}
	
	public VWall getBottomWall() {
		return this.bottom;
	}
	
	public HWall getLeftWall() {
		return this.left;
	}
	
	public HWall getRightWall() {
		return this.right;
	}
	
	public Intersect getTopNeighbor() {
		return north;
	}
	
	public void setTopNeighbor(Intersect north) {
		this.north = north;
	}
	
	public Intersect getBottomNeighbor() {
		return south;
	}
	
	public void setBottomNeighbor(Intersect south) {
		this.south = south;
	}

	public Intersect getLeftNeighbor() {
		return west;
	}
	
	public void setLeftNeighbor(Intersect west) {
		this.west = west;
	}
	
	public Intersect getRightNeighbor() {
		return east;
	}
	
	public void setRightNeighbor(Intersect east) {
		this.east = east;
	}
}
