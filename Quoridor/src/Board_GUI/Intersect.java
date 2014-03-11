package Board_GUI;
import java.awt.Dimension;

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
	
	public Intersect(String n, String id, VWall top, VWall bottom, HWall left, HWall right){
		super(n);
		this.id = id;
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
		setPreferredSize(new Dimension(4,12));
	}
	
	public Intersect(String n, String id, Intersect north, Intersect south, Intersect east, Intersect west){
		super(n);
		this.id = id;
		this.north = north;
		this.south = south;
		this.east = east;
		this.west = west;
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
