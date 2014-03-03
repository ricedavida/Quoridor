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
		public space(){
			isWall = false;
			hasPawn = false;
		}
	}
	class player{ 
		// number of walls left for each player
		int walls;
		// location of their pawn
		int x_loc;
		int y_loc;
		boolean kicked = false;
		private String Move (String m){
			if(checkString(m)){
				int xy[] = parseMove(m);
				this.x_loc = xy[0];
				this.y_loc = xy[0];
				return ("valid "+m);
			}
			else{
				this.kicked = true;
				return("invalid "+m);
			}
		}
		private boolean checkString(String m){
			int xy[] = parseMove(m);
			// off the board
			if(xy[0] < 0 || xy[0] >16 || xy[1] < 0 || xy[1] > 16)
				// move out of bounds
				return false;
			if(xy[0] != (this.x_loc+2) || xy[0] != (this.x_loc-2) ||xy[1] != (this.y_loc+2) || xy[1] != (this.y_loc-2))
				// attempting to move to space not in range
				return false;
			if(xy[0] == this.x_loc+2 && xy[1] != 0)
				// attempt diagonal move on +x
				return false;
			if(xy[0] == this.x_loc-2 && xy[1] != 0)
				// attempting diagonal move on - x
				return false;
			int dirX = (xy[0] - this.x_loc)/2;
			int dirY = (xy[1] - this.y_loc)/2;
			// check in x and y directions for walls
			// already know that xy has values that are 2 away from curent loc.
			// should give a + or - 1 value, which is a wall position on the grid.
			// checks the board for walls in the direction calculated above
			if(playingGrid[(this.x_loc)+dirX][(this.y_loc)+dirY].isWall)
				return false;
			// insert board check for walls here
			return true;	
		}
		private int[] parseMove(String m){
			int [] xy = new int[2];
			String col = m.substring(0, 1);
			String row = m.substring(1, m.length());
			if(col.equalsIgnoreCase("a"))
				xy[0] = 0;
			else if(col.equalsIgnoreCase("b"))
				xy[0] = 2;
			else if(col.equalsIgnoreCase("c"))
				xy[0] = 4;
			else if(col.equalsIgnoreCase("d"))
				xy[0] = 6;
			else if(col.equalsIgnoreCase("e"))
				xy[0] = 8;
			else if(col.equalsIgnoreCase("f"))
				xy[0] = 10;
			else if(col.equalsIgnoreCase("g"))
				xy[0] = 12;
			else if(col.equalsIgnoreCase("h"))
				xy[0] = 14;
			else if(col.equalsIgnoreCase("i"))
				xy[0] = 16;
			else
				xy[0] = -1;
			//System.out.println(row);
			xy[1] = Integer.parseInt(row);
			return xy;
		}
	}
	//number of rows and cols in the board, including walls
	int rows;
	int cols;
	player [] playerList;
	//the grid. traverse this to learn about the board state
	//even is pawn space, odd is wall
	
	space playingGrid[][];
	public Board (int xSize, int ySize, int players)
	{
		playerList = new Board.player[players];
		rows = xSize;
		cols = ySize;
		playingGrid = new Board.space[rows][cols];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				playingGrid[i][j] = new space();
			}
		}
		for(int i = 0; i < players; i++ )
			playerList[i] = new Board.player();
		if(players == 2){
			//player 1
			playingGrid[8][0].hasPawn = true; 
			playerList[0].x_loc = 8;
			playerList[0].y_loc = 0;
			playerList[0].walls = 10;
			//player 2
			playingGrid[8][16].hasPawn = true;
			playerList[1].x_loc = 8;
			playerList[1].y_loc = 16;
			playerList[1].walls = 10;
		}
		else if(players == 4){
			// player 1
			playingGrid[8][0].hasPawn = true;
			playerList[0].x_loc = 8;
			playerList[0].y_loc = 0;
			playerList[0].walls = 5;
			// player 2
			playingGrid[8][16].hasPawn = true;
			playerList[1].x_loc = 8;
			playerList[1].y_loc = 16;
			playerList[1].walls = 5;
			// player 3
			playingGrid[0][8].hasPawn = true;
			playerList[2].x_loc = 0;
			playerList[2].y_loc = 8;
			playerList[2].walls = 5;
			// player 4
			playingGrid[16][8].hasPawn = true;
			playerList[3].x_loc = 16;
			playerList[3].y_loc = 8;
			playerList[3].walls = 5;
		}
		else{
			System.out.println("Invalid player configuration.");
		}
	}
	public int getSizeX(){
		return rows;
	}
	public int getSizeY(){
		return cols;
	}
	public boolean checkSpace(int x, int y){
		if(playingGrid[x][y].hasPawn)
			return true;
		else
			return false;
	}
	public String move(int player, String moveString){
		if(!playerList[player].kicked){
			String moveResult;
			moveResult = playerList[player].Move(moveString);
			if(moveResult.charAt(0)== 'v')
				return moveResult;
				else
					return "Player " +player+" has made an illegal move.";
		}
		else
			return "That player has been kicked";
	}
}
