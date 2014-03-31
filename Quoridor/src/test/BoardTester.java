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
		int boardSize = 81; // grid is 9 x 9, so 81 spaces
		Board grid = new Board (2);
		assertEquals(boardSize, getBoardSize(grid));
	
	}
	@Test
	public void testPlayerStartPositionTwoPlayers() throws Exception{
		Board grid = new Board(2);
		assertEquals("4 76", getTwoPlayerAt(grid));
	}
	@Test
	public void testPlayersStartPositionFourPlayers() throws Exception{
		Board grid = new Board(4);
		assertEquals("4 76 36 44", getFourPlayersAt(grid));
	}
	@Test
	public void testPlayerOneGoodMove() throws Exception{
		Board grid = new Board(2);
		assertEquals("valid 5", testPlayerOneGoodMoveRight(grid));
	}
	@Test
	public void testPlayerOneBadMoveRange() throws Exception{
		Board grid = new Board(2);
		assertEquals("invalid 4", testPlayerOneBadMoveRange(grid));
	}
	@Test
	public void testPlayerOneStartPossibleMoves() throws Exception{
		Board grid = new Board(2);
		String firstPoss = "13 3 5 ";
		assertEquals(firstPoss, testPlayerOneStartPossibleMoves(grid));
	}
	@Test
	public void testPlayerOneMoveDownThenPossible() throws Exception{
		Board grid = new Board(2);
		String possibleAfterMove = "4 22 12 14 ";
		assertEquals(possibleAfterMove, testPlayerOneMoveDownThenPossible(grid));
	}
	//test that board is created to proper x dimensions.
	private int getBoardSize(Board grid){
		return grid.getSizeOfBoard();
	}
	private String getTwoPlayerAt(Board grid){
		String location = "";
		location+=grid.getPos(0);
		location+=" ";
		location +=grid.getPos(1);
		return location;
	}
	private String getFourPlayersAt(Board grid){
		String location = "";
		location+= grid.getPos(0) +" ";
		location+= grid.getPos(1) +" ";
		location+= grid.getPos(2) +" ";
		location+= grid.getPos(3);
		return location;
	}
	private String testPlayerOneGoodMoveRight(Board grid){
		int player = 0; 
		int moveString = 5;
		grid.setPos(player, moveString);
		moveString = grid.getPos(0);
		return "valid "+moveString;
	}
	private String testPlayerOneBadMoveRange(Board grid){
		int player = 0;
		int moveString = 7;
		grid.setPos(player, moveString);
		moveString = grid.getPos(0);
		return "invalid "+moveString;
	}
	private String testPlayerOneStartPossibleMoves(Board grid){
		int player = 0;
		String posslist = "";
		int[] poss = grid.getPossible(player);
		for(int i = 0; i < poss.length; i++){
			posslist+=poss[i] + " ";
		}
		return posslist;
	}
	private String testPlayerOneMoveDownThenPossible(Board grid){
		int player = 0;
		grid.setPos(0, 13);
		String posslist = "";
		int [] poss = grid.getPossible(player);
		for(int i = 0; i < poss.length; i++){
			posslist+=poss[i] + " ";
		}
		return posslist;
		
	}
}
