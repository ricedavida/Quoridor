/* board.jav - quoridor backend storage
 * Authors: Edward Pryor
 * This is intended to be the computational storage for Quoridor
 * Intended to hold the board state of the game.
 */
package Board;

public class Board {
	//board will consist of spaces, as defined by inner class
	class space{
		//boolean isWall;
		boolean hasPawn;
		boolean [] canGo = new boolean[4];
		public space(){
			//isWall = false;
			hasPawn = false;
			canGo[0] = true;
			canGo[1] = true;
			canGo[2] = true;
			canGo[3] = true;
		}
	}
	class player{ 
		int walls;
		int location;
		boolean kicked = false;
		int possibleMoves[];
		int end[];
		// Not Yet Implemeted
		private void move(int pos){
			
		}
		// Not Yet Implemented
		private void kick(){
			
		}
	}	
	
	player [] playerList;
	space playingGrid[];
	int numPlayers;
	
	public Board (int players)
	{
		playerList = new Board.player[players];
		playingGrid = new Board.space[81];
		numPlayers = players;
		for(int i = 0; i < 81; i++){
				playingGrid[i] = new space();
			}
		for(int i = 0; i < players; i++ )
			playerList[i] = new Board.player();
		if(players == 2){
			//player 1
			playingGrid[4].hasPawn = true; 
			playerList[0].location = 4;
			playerList[0].walls = 10;
			//player 2
			playingGrid[76].hasPawn = true;
			playerList[1].location = 76;
			playerList[1].walls = 10;
		}
		else if(players == 4){
			// player 1
			playingGrid[4].hasPawn = true;
			playerList[0].location = 4;
			playerList[0].walls = 5;
			// player 2
			playingGrid[72].hasPawn = true;
			playerList[1].location = 76;
			playerList[1].walls = 5;
			// player 3
			playingGrid[36].hasPawn = true;
			playerList[2].location = 36;
			playerList[2].walls = 5;
			// player 4
			playingGrid[44].hasPawn = true;
			playerList[3].location = 44;
			playerList[3].walls = 5;
		}
		else{
			System.out.println("Invalid player configuration.");
		}
	}
	public boolean checkSpace(int x){
		if(playingGrid[x].hasPawn)
			return true;
		else
			return false;
	}
	public int getPos(int playerId){
		if(!playerList[playerId].kicked)
			return (playerList[playerId].location);
		else
			return -1;	
	}
	// needs move implemetation
	public void setPos(int playerId, int pos){
		playerList[playerId].move(pos);
	}
	public int getPlayerCount(){
		return numPlayers;
	}
	public int[] getPossible(int playerId){
		int possible[] = new int[4];
		return possible;
	}
	// needs checkLegal implemented
	// needs placeWall implementation
	public String setWall(int playerId, String w){
		if(playerList[playerId].kicked)
			return "player "+playerId+" is out";
		else if(playerList[playerId].walls <= 0)
			return "player "+playerId+" has no walls";
		else{
				boolean canPlace = checkLegal(w);
				if(canPlace){
					placeWall(playerId, w);
					return "wall "+w+ "placed";
				}
				else
					return "wall" + w + "is not legal";
		}
	}
	public int getWallCount(int playerId){
		return playerList[playerId].walls;
		
	}
	private boolean checkLegal(String w){
	
		
		return true;
	}
	private void placeWall(int p, String w){
		
	}
}
