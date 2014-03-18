/* board.jav - quoridor backend storage
 * Authors: Edward Pryor
 * This is intended to be the computational storage for Quoridor
 * Intended to hold the board state of the game.
 */
package Board;
import java.util.ArrayList;

import Players.Players;

public class Board {
	//board will consist of spaces, as defined by inner class
	class space{
		boolean hasPawn;
		boolean visited; 
		boolean visited2;
		boolean [] canGo = new boolean[4];
		public space(){
			//isWall = false;
			hasPawn = false;
			visited = false;
			visited2 = false;
			canGo[0] = true; // north
			canGo[1] = true; // south
			canGo[2] = true; // west
			canGo[3] = true; // east
		}
	}
	class wall{
		int[] spacesAffected;
		String name;
		// name stored in the protocol form.
		public wall (String n, int nw, int ne, int sw, int se){
			spacesAffected = new int[4];
			spacesAffected[0] = nw;
			spacesAffected[1] = ne;
			spacesAffected[2] = sw;
			spacesAffected[3] = se;
			name = n;
		}
	}
	Players [] playerList;
	// player list is set up so that p1 @0, p2 @ 1, p3 @ 2, and p4 @ 3. 
	space playingGrid[];
	int numPlayers;
	private int playerNow;
	ArrayList<wall> wallList= new ArrayList<wall>();
	// for tracking, describe walls as the 4 spaces they modify?
	// every time a wall is placed, add it to the list, and also modify the spaces such that they reflect the no-go status


	public Board (int players) {
		playerList = new Players[players];
		playingGrid = new Board.space[81];
		numPlayers = players;
		playerNow = 0;
		for(int i = 0; i < 81; i++){
			playingGrid[i] = new space();
		}
		for(int i = 0; i < playingGrid.length; i++){
			if(i >=0 && i <=8)
				playingGrid[i].canGo[0] = false;
			else if(i >=72 && i <= 80)
				playingGrid[i].canGo[1] = false;
			else if(i % 9 == 8)
				playingGrid[i].canGo[3] = false;
			else if (i % 9 == 0)
				playingGrid[i].canGo[2] = false;
		}
		for(int i = 0; i < players; i++ )
			// list of players goes 0 - 3
			// location, walls, ect set in construction
			playerList[i] = new Players(i, players);
		if(players == 2){
			//player 1
			playingGrid[4].hasPawn = true; 
			//player 2
			playingGrid[76].hasPawn = true;
		}
		else if(players == 4){
			// player 1
			playingGrid[4].hasPawn = true;
			// player 2
			playingGrid[72].hasPawn = true;
			// player 3
			playingGrid[36].hasPawn = true;
			// player 4
			playingGrid[44].hasPawn = true;
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
	public Players getPlayer(int playerId){
		
		return playerList[playerId];
	}

	// POSSIBLE SPOT FOR PLAYERid ERROR
	public int getPos(int playerId){
		if(!playerList[playerId].getKickStatus())
			return (playerList[playerId].getPos());
		else
			return -1;	
	}

	// POTENTIAL PLAYERID ERROR
	public void setPos(int playerId, int pos){
		// add move legality check
		// if legal, then move
		if(!playerList[playerId].getKickStatus()){
			if(checkMove(playerId, pos)){
				if(!playerList[playerId].getKickStatus()){
					playingGrid[playerList[playerId].getPos()].hasPawn = false;
					playerList[playerId].move(pos);
					playingGrid[playerList[playerId].getPos()].hasPawn = true;
				}
			}
		}
	}
	public int getPlayerCount(){
		return numPlayers;
	}
	
	public int getCurrPlayer(){
		return playerNow;
	}
	
	public void setCurrPlayer(int playerId) {
		playerNow = playerId;
	}
	
	// POTENTIAL PLAYERID ERROR
	public int[] getPossible(int playerId){
		// THIS IS AN IMPORTANT METHOD	
		// players have some number of spaces around them that are possible destinations
		if(playerList[playerId].getKickStatus()){
			int[] nullReturn = {-1};
			return nullReturn;
		}
		else{	
			int pos = playerList[playerId].getPos();
			ArrayList <Integer> possibilities = new ArrayList<Integer>();
			for(int i = 0; i < 4; i++){
				playingGrid[pos].visited2 = true;
				if(playingGrid[pos].canGo[i]){
					if(i == 0){
						int newPos = pos-9;
						if(playingGrid[newPos].hasPawn && !playingGrid[newPos].visited){
							int[] possi = getPossible(playerAt(newPos));
							for(int j = 0; j < possi.length; j++)
								possibilities.add(possi[j]);
							unvisit2();
						}
						else{
							possibilities.add(newPos);
						}	
					}
					else if(i == 1){
						int newPos = pos+9;
						if(playingGrid[newPos].hasPawn && !playingGrid[newPos].visited){
							int[] possi = getPossible(playerAt(newPos));
							for(int j = 0; j < possi.length; j++)
								possibilities.add(possi[j]);
							unvisit2();
						}
						else{
							possibilities.add(newPos);
						}	
					}
					else if(i == 2){
						int newPos = pos-1;
						if(playingGrid[newPos].hasPawn && !playingGrid[newPos].visited){
							int[] possi = getPossible(playerAt(newPos));
							for(int j = 0; j < possi.length; j++)
								possibilities.add(possi[j]);
							unvisit2();
						}
						else{
							possibilities.add(newPos);
						}	
					}
					else if(i == 3){
						int newPos = pos+1;
						if(playingGrid[newPos].hasPawn && !playingGrid[newPos].visited){
							int[] possi = getPossible(playerAt(newPos));
							for(int j = 0; j < possi.length; j++)
								possibilities.add(possi[j]);
							unvisit2();
						} 
						else{
							possibilities.add(newPos);
						}	
					}
				}

			}
			int[] p = new int[possibilities.size()];
			for(int i = 0; i < p.length; i++)
				p[i] = possibilities.get(i);
			return p;
		}
	}

	// POTENTIAL PLAYERID ERROR
	public String setWall(int playerId, String w){
		if(playerList[playerId].getKickStatus())
			return "player "+playerId+" is out";
		else if(playerList[playerId].getWallCount() <= 0)
			return "player "+playerId+" has no walls";
		else{
			boolean canPlace = checkLegal(playerId, w);
			if(canPlace){
				placeWall(w);
				playerList[playerId].wallDec();
				return "wall "+w+ "placed";
			}
			else
				return "wall" + w + "is not legal";
		}
	}
	// POTENTIAL PLAYERID ERROR
	public int getWallCount(int playerId){
		if(playerList[playerId].getKickStatus())
			return -1;
		else
			return playerList[playerId].getWallCount();
	}

	// putting kick function here. making it public for now, but will talk to group about who dose kicking
	// in this form. it is a possible playerId error.

	public void kick(int playerId){
		int pos = playerList[playerId].getPos();
		playingGrid[pos].hasPawn = false;
		playerList[playerId].makeKicked();
	}


	// here there be helper functions. all private. 
	// from here on, all player id are form internal function, all coded knowing that
	// players are indexed 0 - 3

	// string w is the location of the northwest corner of the wall and H or V
	private boolean checkLegal(int playerId, String w){
		// rules for quoridor walls:
		// walls must not box a player in
		// walls must not intersect other walls
		// walls must not go off the edge
		// checking boxed in last.
		// first, make sure it is on the board
		char direction = w.charAt(w.length()-1);
		String temp = w.substring(0, w.length()-1);
		int northWestSpace = Integer.parseInt(temp);
		if(northWestSpace  < 0)
			return false;
		if(direction== 'h' ||direction == 'H'){
			// walls mod 9 == 8 means that a horizontal wall can't fit there
			if(northWestSpace % 9 == 8 )
				return false;
		}
		else if(direction == 'v' || direction == 'V'){
			if(northWestSpace >=72 && northWestSpace <=80)
				return false;	
		}
		// done checking that walls don't fall off board
		// don't need to check the other edges
		// walls can't fall off those edges since walls start at northwest space
		// now check that walls dont intersect all catawumpus
		// so walls can't cross through other walls
		// 4 spaces get modified by placing a wall.
		// if walls intersect, they will be modifying the same spaces. 
		int [] wallTouchingSpaces = new int[4];
		wallTouchingSpaces[0] = northWestSpace;
		wallTouchingSpaces[1] = northWestSpace+1;
		wallTouchingSpaces[2] = northWestSpace+9;
		wallTouchingSpaces[3] = northWestSpace+10;
		boolean wallEqFlag = false;
		if(!wallList.isEmpty()){
			for(int i = 0; i < wallList.size(); i++){

				int eqCount = 0;
				for(int j = 0; j < 4; j ++){
					if(wallList.get(i).spacesAffected[j] == wallTouchingSpaces[j])
						eqCount++;
				}
				if(eqCount >=4)
					wallEqFlag = true;
				if(wallEqFlag)
					i = wallList.size();
			}
		}
		if(wallEqFlag)
			return false;
		// that should check for intersecting walls.
		// now to check for boxed in players
		// thinking place walls, then try to find a way out
		
		//*******
		// no-fucntional path algorithm
		// *****
		/*
		placeWall(w);
		int pathCount = 0; 
		for(int i = 0; i < playerList.length; i++){
			if(!playerList[i].getKickStatus()){
				int pathWin = pathChecker(playerId, playerList[i].getPos());
				if(pathWin != 0)
					pathCount++;
			}
			unvisit();
		}
		if(pathCount != this.getPlayerCount())
			return false;
		removeWall(w);
		*/
		return true;
	}

	public boolean checkMove(int playerId, int newPos){
		// why wouldnt a player be able to move somewhere?
		// 1. it isnt on the board
		// 2. a wall is in the way
		// 3. they are trying to move more than one space at a time
		//		in this case, check for jumps
		// 4. they are moving diagonal
		// i think this whole method can just check the palyer's possible moves.
		// if a move isn't on the possible list, return false.
		int[] possible = getPossible(playerId);
		boolean possibleFlag = false;
		for(int i = 0; i < possible.length; i++){
			if(newPos == possible[i])
				possibleFlag = true;
		}
		return possibleFlag;
	}

	private void placeWall(String w){
		char direction = w.charAt(w.length()-1);
		String temp = w.substring(0, w.length()-1);
		int northWestSpace = Integer.parseInt(temp);
		wall t = new wall(w, northWestSpace, northWestSpace+1, northWestSpace+9, northWestSpace+10);
		wallList.add(t);
		if(direction == 'h' || direction == 'H'){
			playingGrid[northWestSpace].canGo[1] = false;
			playingGrid[northWestSpace+1].canGo[1] = false;
			playingGrid[northWestSpace+9].canGo[0] = false;
			playingGrid[northWestSpace+10].canGo[0] = false;
		}
		else if(direction == 'v' || direction == 'V'){
			playingGrid[northWestSpace].canGo[3] = false;
			playingGrid[northWestSpace+1].canGo[2] = false;
			playingGrid[northWestSpace+9].canGo[3] = false;
			playingGrid[northWestSpace+10].canGo[2] = false;
		}
		else
			System.out.println("Somebody tried to set some kind of bad wall");

	}

	private void removeWall(String w){
		char direction = w.charAt(w.length()-1);
		String temp = w.substring(0, w.length()-1);
		int northWestSpace = Integer.parseInt(temp);
		wallList.remove(wallList.size()-1);
		if(direction == 'h' || direction == 'H'){
			playingGrid[northWestSpace].canGo[1] = true;
			playingGrid[northWestSpace+1].canGo[1] = true;
			playingGrid[northWestSpace+9].canGo[0] = true;
			playingGrid[northWestSpace+10].canGo[0] = true;
		}
		else if(direction == 'v' || direction == 'V'){
			playingGrid[northWestSpace].canGo[3] = true;
			playingGrid[northWestSpace+1].canGo[2] = true;
			playingGrid[northWestSpace+9].canGo[3] = true;
			playingGrid[northWestSpace+10].canGo[2] = true;
		}
		else
			System.out.println("Somebody tried to remove some kind of bad wall");

	}

	private int pathChecker(int player, int pos){
		// mark this node as visited
		playingGrid[pos].visited = true;
		// return true if this is in player's win set
		boolean flag = false;
		int []tempEnd = playerList[player].getEnd();
		for(int i = 0; i < tempEnd.length; i++){
			if(pos == tempEnd[i]){
				flag = true;
			}
		}
		if(flag)
			return 1;
		else{
			// construct set of possible moves
			int[] possible = getPossible(player);
			int possCount = 0;
			for(int i =0; i < possible.length; i++){
				if(!playingGrid[possible[i]].visited)
					possCount++;
			}
			if(possCount <=0)
				return 0;
			else{

				int t;
				t = 0;
				for(int i = 0; i < possible.length; i++){
					t= t+ pathChecker(player, possible[i]);
				}
				// return false if all nearby are visited or no possible way off square
				// else return pathChecker(player pos) || pathChecker(player pos)|| ... 
				return t;
			}
		}
	}

	private int playerAt(int pos){
		int playerYouWant = -1;
		for( int i = 0; i < playerList.length; i++){
			if(playerList[i].getPos() == pos)
				playerYouWant = i;
		}
		return playerYouWant;
	}

	private void unvisit(){
		for(int i = 0; i < playingGrid.length; i++)
		{
			playingGrid[i].visited = false;
		}
	}
	private void unvisit2(){
		for(int i = 0; i < playingGrid.length; i++)
		{
			playingGrid[i].visited2 = false;
		}
	}
}
