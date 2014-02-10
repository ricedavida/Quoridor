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
		Board grid = new Board(17, 17);
		assertEquals(xSize, getBoardSizeX(grid));
	
	}
	@Test
	public void testBoardMakeY() throws Exception{
		//dimensions of a square board, with wall space reserved
		int xSize = 17;
		int ySize = 17;
		Board grid = new Board(17, 17);
		assertEquals(ySize, getBoardSizeY(grid));
	}
	//test that board is created to proper x dimensions.
	private int getBoardSizeX(Board grid){
		return grid.getSizeX();
	}
	private int getBoardSizeY(Board grid){
		return grid.getSizeY();
	}

}
