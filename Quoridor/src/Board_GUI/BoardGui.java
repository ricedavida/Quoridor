package Board_GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardGui extends JFrame implements ActionListener, MouseListener{
	//final static boolean shouldFill = true;
	//final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;
	private int players;
	private JButton submit;
	private Image wood;
	private Graphics2D g2d;
	private String wallplaced = null;
	private String submitmove = null;
	private BoardSpace button = null;
	private Intersect inter = null;
	private ArrayList<BoardSpace> space = new ArrayList<BoardSpace>();
	private ArrayList<Intersect> sect = new ArrayList<Intersect>();
	private ArrayList<String> walls = new ArrayList<String>();
	
	// Create a constructor method
	public BoardGui(int players) {
		super();
		this.players = players;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if (players == 4) {
			wood = new ImageIcon("board_4players.jpg").getImage();
		} else {
			wood = new ImageIcon("board_2players.jpg").getImage();
		}

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
				
				Intersect tmp = new Intersect("", ((i*9)+j) + "", top,bottom,left,right);
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

		space.get(76).setColor(Color.GREEN);
		space.get(76).setPotential(false);
		space.get(76).setClicked(true);
		
		if (players == 4) {
			space.get(36).setColor(Color.RED);
			space.get(36).setPotential(false);
			space.get(36).setClicked(true);

			space.get(44).setColor(Color.CYAN);
			space.get(44).setPotential(false);
			space.get(44).setClicked(true);
		}
		
		/*
		 * this is an example for potential spaces
		 */
		space.get(3).setPotential(true);
		space.get(5).setPotential(true);
		space.get(13).setPotential(true);
		
		
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
		label4.setForeground(Color.CYAN);
		grid.add(label4);
		
		if (players == 4) {
			label3.setText("\t\t\t" + 5 + " walls remain");
			label4.setText("\t\t\t" + 5 + " walls remain");
		}
		
		submit = new JButton("Submit");
		submit.addMouseListener(this);
		submit.setForeground(Color.WHITE);
		submit.setOpaque(false);
		submit.setBorderPainted(false);
		submit.addActionListener(this);
		grid.add(submit);
		pane.add(grid);
	}
	
	@Override
	public void paint(Graphics g){
		g2d = (Graphics2D)g;
		g2d.clearRect(0, 0, getWidth(), getHeight());
		g2d.drawImage(wood,0,0,getWidth(),getHeight(),null);
		super.paint(g2d);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof BoardSpace) {
			button = (BoardSpace)e.getSource();
			if(button.isClicked()) {
				button.setClicked(false);
			} else {
				button.setClicked(true);
			}
		} else if (e.getSource() instanceof Intersect) {
			inter = (Intersect)e.getSource(); 

			if (wallplaced == null){
				// This is how the horizontal walls are built
				if(inter.getWall() == 0) {
					wallplaced = inter.getId();
					inter.setWall(1);
					HWall left = inter.getLeftWall();
					
					submitmove = inter.getId() + "h";
					
					left.setClicked(true);
					left.repaint();
					HWall right = inter.getRightWall();
					right.setClicked(true);
					right.repaint();
				} 
			} else {
				if(inter.getWall() == 1) {
					inter.setWall(2);
					// This is how the vertical walls are cleared				
					HWall left = inter.getLeftWall();
					submitmove = inter.getId() + "v";
					
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
					wallplaced = null;
					submitmove = null;
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
		} else if (e.getSource().equals(submit)) {
			if (submitmove != null) {
				if (inter != null) {
					System.out.println("THIS IS MY MOVE " + submitmove);
					if (inter.getWall() == 1) {
						walls.add(inter.getId()+"h");
						inter.setBorderPainted(false);
						inter.setClicked(true);
						inter.setEnabled(false);
						submitmove = null;
						wallplaced = null;
					} else if (inter.getWall() ==2){
						walls.add(inter.getId()+"v");
						inter.setBorderPainted(false);
						inter.setClicked(true);
						inter.setEnabled(false);
						submitmove = null;
						wallplaced = null;
					}
				}
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

	@Override
	public void mouseEntered(MouseEvent e) {
		submit.setForeground(Color.GREEN);
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		submit.setForeground(Color.WHITE);
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//submit.setForeground(Color.BLUE);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		submit.setForeground(Color.BLUE);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		submit.setForeground(Color.GREEN);
	}
	
}