package Board_GUI;
import java.awt.Dimension;

import javax.swing.*;

//public abstract class Intersect extends JButton {
public class Intersect extends JButton {
	private String id;
	private int wall;
	
	public Intersect(String n, String id, int wall){
		super(n);
		this.id = id;
		this.wall = wall;
		setOpaque(false);
		setPreferredSize(new Dimension(4,12));
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
	
	//public abstract HWAll[] getHWalls();
	
	//public abstract VWAll[] getHWalls();

}
