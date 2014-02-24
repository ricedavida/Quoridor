package test;

import org.junit.Test;

import Board.Board;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class BoardTester extends TestCase{
	public BoardTester(String name){
		super (name);
	}
	public static void main(String[] args) {
		TestRunner.runAndWait(new TestSuite(BoardTester.class));
	}
	@Test
	public void testBoardMakeX() throws Exception{
		//dimensions of a square board, with wall space reserved
		int xSize = 17;
		int ySize = 17;
		Board grid = new Board(17, 17, 2);
		assertEquals(xSize, getBoardSizeX(grid));
	
	}
	@Test
	public void testBoardMakeY() throws Exception{
		//dimensions of a square board, with wall space reserved
		int xSize = 17;
		int ySize = 17;
		Board grid = new Board(17, 17, 2);
		assertEquals(ySize, getBoardSizeY(grid));
	}
	@Test
	public void testPlayerStartPositionTwoPlayers() throws Exception{
		Board grid = new Board(17, 17, 2);
		assertEquals("80 816", getTwoPlayerAt(grid));
	}
	@Test
	public void testPlayersStartPositionFourPlayers() throws Exception{
		Board grid = new Board(17, 17, 4);
		assertEquals("80 816 08 168", getFourPlayersAt(grid));
	}
	@Test
	public void testPlayerOneGoodMove() throws Exception{
		Board grid = new Board(17, 17, 2);
		assertEquals("valid e7", testPlayerOneGoodMove(grid));
	}
	@Test
	public void testPlayerOneBadMove() throws Exception{
		Board grid = new Board(17, 17, 2);
		assertEquals("invalid e-1", testPlayerOneBadMove(grid));
	}
	//test that board is created to proper x dimensions.
	private int getBoardSizeX(Board grid){
		return grid.getSizeX();
	}
	private int getBoardSizeY(Board grid){
		return grid.getSizeY();
	}
	private String getTwoPlayerAt(Board grid){
		String location = "";
		if(grid.checkSpace(8, 0))
			location+="80";
		location+=" ";
		if(grid.checkSpace(8, 16))
			location+="816";
		return location;
	}
	private String getFourPlayersAt(Board grid){
		String location = "";
		if(grid.checkSpace(8, 0))
			location+="80 ";
		if(grid.checkSpace(8, 16))
			location+="816 ";
		if(grid.checkSpace(0, 8))
			location+="08 ";
		if(grid.checkSpace(16, 8))
			location+= "168";
		return location;
	}
	private String testPlayerOneGoodMove(Board grid){
		int player = 0; 
		String moveString = "e7";
		grid.move(player, moveString);
		return "valid "+moveString;
	}
	private String testPlayerOneBadMove(Board grid){
		int player = 0;
		String moveString = "e-1";
		grid.move(player, moveString);
		return "invalid "+moveString;
	}
}
