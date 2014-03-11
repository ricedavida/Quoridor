package Board_GUI;

public class BoardMain {
	public static void main(String[] args){
		BoardGui frame = new BoardGui(2);
		frame.setSize(700,410);
		frame.setResizable( false );
		frame.setVisible(true);
	}
}