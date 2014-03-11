package Board_GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardGui extends JFrame implements ActionListener{
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;

	private Image wood = new ImageIcon("wood_board.jpg").getImage();
	private Graphics2D g2d;
	
	// Create a constructor method
	public BoardGui() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setContentPane(new ImagePanel(wood)); //must fix now that there are more panels
		addComponentsToPane(this.getContentPane());

		this.pack();
		this.setVisible(true);
	}

	public void addComponentsToPane(Container pane) {
		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);
		pane.setLayout(new GridLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
 		ArrayList<BoardSpace> space = new ArrayList<BoardSpace>();
		ArrayList<Intersect> sect = new ArrayList<Intersect>();
		
		for (int i = 0 ; i < 9 ; i++) {
			for (int j = 0 ; j < 9 ; j++) {
				BoardSpace tmp = new BoardSpace("", i + "-" + j, false); 
				tmp.addActionListener(this);
				c.weightx = 0.5;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = j*2;
				c.gridy = (i * 2);
				space.add(tmp);
				panel.add(tmp, c);
			}
		}
		for (int i = 0 ; i < 8 ; i++) {
			for (int j = 0 ; j < 8 ; j++) {
				VWall top = new VWall("", ((i-1) + "-" + (j)), false);
				VWall bottom = new VWall("", ((i-1) + "-" + (j)), false);
				HWall left = new HWall("", ((i) + "-" + (j-1)), false);
				HWall right = new HWall("", ((i) + "-" + (j-1)), false);
				
				Intersect tmp = new Intersect("", i + "-" + j, top,bottom,left,right);
				tmp.addActionListener(this);
				c.weightx = 0.5;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = j*2+1;
				c.gridy = (i * 2)+1;
				sect.add(tmp);
				panel.add(tmp, c);
				

				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = j*2+1;
				c.gridy = (i * 2);
				panel.add(top, c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = j*2+1;
				c.gridy = (i * 2)+2;
				panel.add(bottom, c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = j*2;
				c.gridy = (i * 2)+1;
				panel.add(left, c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = j*2+2;
				c.gridy = (i * 2)+1;
				panel.add(right, c);

			}
		}
		
		space.get(4).setColor(Color.BLUE);
		space.get(4).setPotential(false);
		space.get(4).setClicked(true);
		
		/*
		 * this is an example for potential spaces
		 */
		space.get(3).setPotential(true);
		space.get(5).setPotential(true);
		space.get(13).setPotential(true);
		
		
		space.get(76).setColor(Color.GREEN);
		space.get(76).setPotential(false);
		space.get(76).setClicked(true);
		
		pane.add(panel);
		
		JPanel grid = new JPanel(new GridLayout(5,1));
		grid.setOpaque(false);
		JLabel label = new JLabel("\t\t\t" + 5 + " walls remain");
		label.setForeground(Color.BLUE);
		grid.add(label);
		JLabel label2 = new JLabel("\t\t\t" + 5 + " walls remain");
		label2.setForeground(Color.GREEN);
		grid.add(label2);
		JLabel label3 = new JLabel("\t\t\t");
		label3.setForeground(Color.RED);
		grid.add(label3);
		JLabel label4 = new JLabel("\t\t\t");
		label4.setForeground(Color.PINK);
		grid.add(label4);
		JButton submit = new JButton("Submit");
		submit.setForeground(Color.WHITE);
		submit.setOpaque(false);
		submit.setBorderPainted(false);
		grid.add(submit);
		pane.add(grid);
	}
	
	public void paint(Graphics g){
		g2d = (Graphics2D)g;
		g2d.clearRect(0, 0, getWidth(), getHeight());
		g2d.drawImage(wood,0,0,getWidth(),getHeight(),null);
		super.paint(g2d);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		BoardSpace button;
		Intersect inter; 
		if (e.getSource() instanceof BoardSpace) {
			button = (BoardSpace)e.getSource();
			if(button.isClicked()) {
				button.setClicked(false);
			} else {
				button.setClicked(true);
			}
		} else if (e.getSource() instanceof Intersect) {
			inter = (Intersect)e.getSource(); 
			
			// This is how the horizontal walls are built
			if(inter.getWall() == 0) {
				inter.setWall(1);
				HWall left = inter.getLeftWall();
				left.setClicked(true);
				left.repaint();
				HWall right = inter.getRightWall();
				right.setClicked(true);
				right.repaint();
			} else if(inter.getWall() == 1) {
				inter.setWall(2);
				// This is how the vertical walls are cleared				
				HWall left = inter.getLeftWall();
				left.setClicked(false);
				left.repaint();
				HWall right = inter.getRightWall();
				right.setClicked(false);
				right.repaint();
				// This is how the vertical walls are built
				VWall top = inter.getTopWall();
				top.setClicked(true);
				top.repaint();
				VWall bottom = inter.getBottomWall();
				bottom.setClicked(true);
				bottom.repaint();
			} else if(inter.getWall() == 2) {
				// This is how the vertical walls are cleared
				inter.setWall(0);
				VWall top = inter.getTopWall();
				top.setClicked(false);
				top.repaint();
				VWall bottom = inter.getBottomWall();
				bottom.setClicked(false);
				bottom.repaint();
			}
		}
	}
	
	class ImagePanel extends JComponent {
	    private Image image;
	    public ImagePanel(Image image) {
	        this.image = image;
	    }
	    @Override
	    protected void paintComponent(Graphics g) {
	        g.drawImage(image, 0, 0, null);
	    }
	}
}