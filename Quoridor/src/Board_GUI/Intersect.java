package Board_GUI;
import java.awt.Dimension;

import javax.swing.*;

public class Intersect extends JButton {
	private String id;
	
	public Intersect(String n, String id){
		super(n);
		this.id = id;
		setOpaque(false);
		setPreferredSize(new Dimension(4,12));
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
