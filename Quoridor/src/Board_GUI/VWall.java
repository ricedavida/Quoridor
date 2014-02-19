package Board_GUI;
import java.awt.Dimension;
import javax.swing.*;

public class VWall extends JButton {
	
	public VWall(String n){
		super(n);
		setPreferredSize(new Dimension(20,3));
	}

}
