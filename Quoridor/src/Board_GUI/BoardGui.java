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
	private ArrayList<VWall> vertwalls = new ArrayList<VWall>();
	private ArrayList<HWall> horzwalls = new ArrayList<HWall>();

	private Image wood = new ImageIcon("wood_board.jpg").getImage();
	private Graphics2D g2d;
	/*
	 * Col (1-37,2-80,3-122,4-163,5-205,6-247,7-289,8-331,9-373)
	 * Row (1-4,2-44,3-84,4-124,5-164,6-204,7-244,8-284,9-324)
	 */
	//final static int[] vertdim = new int[]{37,80,122,163,205,247,289,331,373};
	//final static int[] horzdim = new int[]{4,44,84,124,164,204,244,284,324}; 
	//public static ImageIcon lblwood = new ImageIcon("labelwood.jpg").getImage();
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
		//JButton button;
		pane.setLayout(new GridLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		//int row = 0;
		
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
				
				System.out.println(tmp.getLeftWall().getId());

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
		space.get(4).setClicked(true);
		
		space.get(76).setColor(Color.GREEN);
		space.get(76).setClicked(true);
		
		/*for (int h = 0 ; h < 17 ; h++) {
			if (h%2 == 0) {
				//for (int i = 0 ; i < 9 ; i++) {
				for (int i = 0 ; i < 17 ; i++) {
					if (i % 2 == 0) {
						//button = new BoardSpace(row + "-" + (i+1));
						button = new BoardSpace("", (row + "-" + i), false);
						button.addActionListener(this);
						c.weightx = 0.5;
						c.fill = GridBagConstraints.HORIZONTAL;
						//c.gridx = i * 2;
						c.gridx = i;
						c.gridy = (h * 2);
						panel.add(button, c);						
					} else {
						button = new VWall("", (((row-1)/2) + "-" + i/2), false);
						vertwalls.add((VWall)button);
						button.addActionListener(this);
						c.weightx = 0.5;
						c.fill = GridBagConstraints.HORIZONTAL;
						c.gridx = i;
						c.gridy = (h * 2);
						panel.add(button, c);
					}
				}
			} else {
				for (int i = 0 ; i < 9 ; i++) {
					button = new HWall("", (row + "-" + i), false);
					horzwalls.add((HWall)button);
					button.addActionListener(this);
					c.weightx = 0.5;
					c.fill = GridBagConstraints.HORIZONTAL;
					c.gridx = (i * 2);
					c.gridy = (h * 2)+1;
					panel.add(button, c);
				}			
				for (int i = 0 ; i < 8 ; i++) {
						button = new Intersect("", (row + "-" + i), 0);
						button.addActionListener(this);
						c.fill = GridBagConstraints.HORIZONTAL;
						c.gridx = (i * 2)+1;
						c.gridy = (h * 2) + 1;
						panel.add(button, c);
				}
			}
			row++;
		}*/
		
		/*
		ArrayList<Intersect> sect = new ArrayList<Intersect>();
		
		for (int i = 0; i < 7 ; i++ ){
			for (int j = 0; j < 7; j++) {
				if (i == 0) {
					if (j == 0) {
						sect.add(new Intersect("", (i + "-" + j), null, null, null, null));
						sect.get(j).addActionListener(this);
						c.fill = GridBagConstraints.HORIZONTAL;
						c.gridx = j;//(i * 2)+1;
						c.gridy = i;//(h * 2) + 1;
						panel.add(sect.get(j), c);
					} else {
						sect.add(new Intersect("", (i + "-" + j), null, null, null, sect.get(j-1)));
						sect.get(j-1).setRightNeighbor(sect.get(j));
						sect.get(j).addActionListener(this);
						c.fill = GridBagConstraints.HORIZONTAL;
						c.gridx = j;//(i * 2)+1;
						c.gridy = i;//(h * 2) + 1;
						panel.add(sect.get(j), c);
					}
				} else {
					if (j == 0) {
						sect.add(new Intersect("", (i + "-" + j), sect.get((i-1)*7+j), null, null, null));
						sect.get((i-1)*7+j).setBottomNeighbor(sect.get(j));
						sect.get(j).addActionListener(this);
						c.fill = GridBagConstraints.HORIZONTAL;
						c.gridx = (j * 2)+1;
						c.gridy = i;//(h * 2) + 1;
						panel.add(sect.get(j), c);
					} else {
						sect.add(new Intersect("", (i + "-" + j), sect.get((i-1)*7+j), null, null, sect.get(j-1)));
						sect.get(j-1).setRightNeighbor(sect.get(j));
						sect.get((i-1)*7+j).setBottomNeighbor(sect.get(j));
						sect.get(j).addActionListener(this);
						c.fill = GridBagConstraints.HORIZONTAL;
						c.gridx = (j * 2)+1;
						c.gridy = i;//(h * 2) + 1;
						panel.add(sect.get(j), c);
					}
				}
			}
		}
		*/
		pane.add(panel);
		
		JPanel grid = new JPanel(new GridLayout(5,1));
		grid.setOpaque(false);
		JLabel label = new JLabel("\t\t\tfield 1");
		label.setForeground(Color.MAGENTA);
		grid.add(label);
		JLabel label2 = new JLabel("\t\t\tfield 2");
		label2.setForeground(Color.GREEN);
		grid.add(label2);
		JLabel label3 = new JLabel("\t\t\tfield 3");
		label3.setForeground(Color.BLUE);
		grid.add(label3);
		JLabel label4 = new JLabel("\t\t\tfield 4");
		label4.setForeground(Color.RED);
		grid.add(label4);
		JButton submit = new JButton("Submit");
		submit.setOpaque(false);
		//submit.setContentAreaFilled(false);
		submit.setBorderPainted(false);
		//submit.setPreferredSize(new Dimension(20,30));
		grid.add(submit);
		pane.add(grid);
	}


	public void paint(Graphics g){
		g2d = (Graphics2D)g;
		g2d.clearRect(0, 0, getWidth(), getHeight());
		g2d.drawImage(wood,0,0,getWidth(),getHeight(),null);
		super.paint(g2d);
		
		//g.setColor(Color.MAGENTA);
		//g.fillOval(horzdim[4], vertdim[0], 20, 20);
		
		/*g2d.setColor(Color.GREEN);
		g2d.fillOval(horzdim[0], vertdim[4], 20, 20);
		g2d.setColor(Color.BLUE);
		g2d.fillOval(horzdim[8], vertdim[4], 20, 20);
		g2d.setColor(Color.RED);
		g2d.fillOval(horzdim[4], vertdim[8], 20, 20);
		*/
		//player1 = new Player("player1");
		
		//paint(pl1);
	}
	
	public void getHorizontalWall(Graphics g, int row, int column, Color c) {
		//column = (column + 1) * 41;
		column = (column + 1) * 35;
		
		g.setColor(c);
		g.fillRect(0, column, 34, 13);
		g.fillRect(52, column, 34, 13);
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
			System.out.println(inter.getId());
			String [] sects = inter.getId().split("-");   
			System.out.println(sects[0]);
			System.out.println(sects[1]);
			
			// This is how the horizontal walls are built
			if(inter.getWall() == 0) {
				inter.setWall(1);
				HWall left = inter.getLeftWall();
				left.setClicked(true);
				left.repaint();
				HWall right = inter.getRightWall();
				right.setClicked(true);
				right.repaint();
			}
			// This is how the vertical walls are built
			else if(inter.getWall() == 1) {
				inter.setWall(2);
				
				HWall left = inter.getLeftWall();
				left.setClicked(false);
				left.repaint();
				HWall right = inter.getRightWall();
				right.setClicked(false);
				right.repaint();
				
				VWall top = inter.getTopWall();
				top.setClicked(true);
				top.repaint();
				VWall bottom = inter.getBottomWall();
				bottom.setClicked(true);
				bottom.repaint();
			}
			// This is how the walls are cleared
			else if(inter.getWall() == 2) {
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