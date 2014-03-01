
package Board_GUI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JFrame {
	JButton grid[][] = new JButton[17][17];
	JFrame f;
	
	Board() {
		init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setSize(1100,700); 
		setVisible(true);
	}
	
	public void init(){
		setLayout(new GridLayout(17,17));
		int i,x;
		final int a,b;
		String letter = "I";
		int charValue = letter.charAt(0);	
		for(i=0;i<17;i++){
			if(i%2==0){
				for(x=0;x<17;x++){
					if(x%2==0){
						grid[i][x] = new BoardSpace(letter+"|"+x);
						add(grid[i][x]);
					}
					else{
						grid[i][x] = new VWall(i+"|"+x);
						add(grid[i][x]);
					}
				}
				charValue = charValue -1;
				letter = String.valueOf((char) charValue);
			}
			
			else{
				for(x=0;x<17;x++){
					if(x%2==0){
						grid[i][x] = new HWall(i+"|"+x);
						add(grid[i][x]);
					}
					else{
						grid[i][x] = new Intersect(i+"|"+x);
						add(grid[i][x]);
					}
				}
				
			}
		}
	}
}
