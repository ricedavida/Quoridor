package Board_GUI;
import java.awt.Dimension;
import javax.swing.*;

public class HWall extends JButton{
	
	public HWall(String n){
		super(n);
		this.setPreferredSize(new Dimension(3,20));
	}

}
