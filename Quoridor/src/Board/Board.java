/* board.jav - quoridor backend storage
 * Authors: Edward Pryor
 * This is intended to be the computational storage for Quoridor
 * Intended to hold the board state of the game.
 */
package Board;

public class Board {
	
	//board will consist of spaces, as defined by inner class
	class space{
		//every other space is a wall
		boolean isWall;
		boolean hasPawn;
	}
	//number of rows and cols in the board, including walls
	int rows;
	int cols;
	//the grid. traverse this to learn about the board state
	space playingGrid[][];
	public Board (int xSize, int ySize)
	{
		rows = xSize;
		cols = ySize;
		playingGrid = new space[rows][cols];
	}
	public int getSizeX(){
		return rows;
	}
	public int getSizeY(){
		return cols;
	}

}
