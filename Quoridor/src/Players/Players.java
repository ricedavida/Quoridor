package Players;
//this class is intended to represent the palyers
//not gonna lie, i do not see the use of decoupling the players from the board

public class Players {

		private int playerName;
		private int totalPlayers;
		private int numWalls;
		private boolean isKicked;
		// location is a single int now. 
		// board is numbers from 0 to 80. 
		private int location;
		// players want to get to end
		// this is a list of winning spaces
		private int[] end; 
		// if a player wins, this becomes true
		//	probably don't need, but whatever
		private boolean hasWon;
		private int [] possibleMoves;
		// format of possible moves: 
		// array of ints
		// pM[0] = North
		// pM[1] = south
		// pM[2] = west
		// pM[3] = east
		
		public Players(int playerId, int numPlayers){
			// player initial possible moves are hard coded in, i think i can make this not so later.
			playerName = playerId;
			numWalls = 20 / numPlayers;
			isKicked = false;
			hasWon = false;
			totalPlayers = numPlayers;
			if(playerId == 1){
				this.location = 4;
				this.end = new int[8];
				int endLoc = 72;
				for(int i = 0; i < 8; i++){
					this.end[i] = endLoc;
					endLoc++;
				}
				this.possibleMoves = new int[4];
				this.possibleMoves[0] = -1;
				this.possibleMoves[1] = 13;
				this.possibleMoves[2] = 3;
				this.possibleMoves[3] = 5;
			}
			else if(playerId == 2){
				this.location = 76;
				this.end = new int[8];
				int endLoc = 0;
				for(int i = 0; i < 8; i++){
					this.end[i] = endLoc;
					endLoc++;
				}
				this.possibleMoves = new int[4];
				this.possibleMoves[0] = 67;
				this.possibleMoves[1] = -1;
				this.possibleMoves[2] = 75;
				this.possibleMoves[3] = 77;
			}
			else if(playerId ==3){
				this.location = 36;
				this.end = new int[8];
				int endLoc = 8;
				for(int i = 0; i < 8; i++){
					this.end[i] = endLoc;
					endLoc+=9;
				}
				this.possibleMoves = new int[4];
				this.possibleMoves[0] = 27;
				this.possibleMoves[1] = 45;
				this.possibleMoves[2] = -1;
				this.possibleMoves[3] = 37;
			}
			else if(playerId == 4){
				this.location = 44;
				this.end = new int[8];
				int endLoc = 0;
				for(int i = 0; i < 8; i++){
					this.end[i] = endLoc;
					endLoc+=9;
				}
				this.possibleMoves = new int[4];
				this.possibleMoves[0] = 35;
				this.possibleMoves[1] = 53;
				this.possibleMoves[2] = 43;
				this.possibleMoves[3] = -1;
			}
			else{
				// make some kind of impossible player excetion here
				// eg. player 5, or num players !=2 or 4
				System.out.println("Not vaild player configuration");
			}
		}
		// methods go here
		// getPos()
		// args: null
		// returns: int location
		// numeric value representing position on the board
		// the player is there right now.
		public int getPos(){
			return location;
		}
		
		// getWallCount()
		// args: null
		// returns: int numWalls
		// numeric value of number of walls this player has.
		public int getWallCount(){
			return numWalls;
		}
		
		// helper method setPossible()
		// args: null
		// returns: null
		// use this to calculate the current possible moves for a pawn
		// call it after a move, from the move method.
		// can not check for walls
		private void setPossible(){
			
		}
}
