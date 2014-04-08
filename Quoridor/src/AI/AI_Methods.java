package AI;

import java.io.*;

public class AI_Methods {
	//String Array to represent board
	static String board[][]={
		{" "," "," "," ","1"," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "},
		{"4"," "," "," "," "," "," "," ","3"},
		{" "," "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "},
		{" "," "," "," ","2"," "," "," "," "},
	};
	static int pPositionC, pPositionL,lastMove;
	int Player1,Player2,Player3,Player4;
	int[] failedMoves = new int[81];
	//list of all possible moves
	static String list,fastestPathRight="",fastestPathLeft="";
	
	public static String allPossibleMoves(String pawn){
		list = "";
		for(int i=0; i<81; i++)
			if (board[i/9][i%9].equals(pawn)){
			    list = "";
				list+=possiblePawnMoves(i);
			}
		return list;
	}
	
	public static int currentPositionOfPawn(String pawn){
		int pos = -1;
		for(int i=0; i<81; i++)
			if (board[i/9][i%9].equals(pawn)){
				pos = i;
		}
		return pos;
	}

	public static int[] possiblePawnMovesInt(int i){
		int[] moves = new int[5];
		moves[0] = i;
		moves[1] = -1;
		moves[2] = -1;
		moves[3] = -1;
		moves[4] = -1;
		
		int r = i/9, c = i%9;
		for(int j = 0; j<9; j++){
			if (j == 1||j == 3||j == 5||j == 7){
				try{
					if(" ".equals(board[r-1+j/3][c-1+j%3])){
						if(viableMove()){
							if(j == 1 && i-9>0)
								moves[1] = i-9;
							if(j == 3 && i-1>0)
								moves[3] = i-1;
							if(j == 5 && i+1<81)
								moves[4] = i+1;
							if(j == 7 && i+9<81)
								moves[2] = i+9;
						}
					}
				}catch (Exception e) {}
			}
		}
		return moves;
	}
	public static String possiblePawnMoves(int i){
		list="";
		String oldPiece;
		int r = i/9, c = i%9;
		for(int j = 0; j<9; j++){
			if (j == 1||j == 3||j == 5||j == 7){
				try{
					if(" ".equals(board[r-1+j/3][c-1+j%3])){
						oldPiece = board[r-1+j/3][c-1+j%3];
						if(viableMove()){
							list = list+r+c+(r-1+j/3)+(c-1+j%3)+oldPiece;
						}
						board[r-1+j/3][c-1+j%3]=oldPiece;
					}
				}catch (Exception e) {}
			}
		}
		//need to add Jumps
		return list;
	}
	
	public static String fastestPathLeft(int position){
		int moves[] = possiblePawnMovesInt(position);
		if(position == 1){System.out.println("Fastest Path Left:");
		return "done";}//Should be if lastMove == winCondition
		
		else if(moves[1]!=-1){//moving up
				fastestPathLeft += position+"^"+moves[1]+" ";
				System.out.print(fastestPathLeft);
				fastestPathLeft(moves[1]);
				return fastestPathLeft;
		} 
		else if(moves[3]!=-1){//moving left
			fastestPathLeft += position+"<"+moves[3]+" ";
			System.out.print(fastestPathLeft);
			fastestPathLeft(moves[3]);
			return fastestPathLeft;
		}
		else if(moves[4]!=-1){//moving right
			fastestPathLeft += position+">"+moves[4]+" ";
			fastestPathLeft(moves[4]);
			System.out.print(fastestPathLeft);
			return fastestPathLeft;
		}
		return "Hope you never see this.";
	}
	
	public static String fastestPathRight(int position){
		int moves[] = possiblePawnMovesInt(position);
		if(position == 7){System.out.println("Fastest Path Right:");
		return "done";}//Should be if lastMove == winCondition
		
		if(moves[1]!=-1){//moving up
			fastestPathRight += position+"^"+moves[1]+" ";
			fastestPathRight(moves[1]);
			return fastestPathRight;
		}
		else if(moves[4]!=-1){//moving right
			fastestPathRight += position+">"+moves[4]+" ";
			fastestPathRight(moves[4]);
			return fastestPathRight;
		}
		else if(moves[3]!=-1){//moving left
			fastestPathRight += position+"<"+moves[3]+" ";
			fastestPathRight(moves[3]);
			return fastestPathRight;
		}
		return "Hope you never see this.";
	}
	public static boolean viableMove(){
		return true;
	}

	public static void main(String args[]){
		String a = "Shit";
		System.out.println("Enter a pawn to find moves for:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			a = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
			/*System.out.println(allPossibleMoves(a));
			System.out.println(currentPositionOfPawn(a));
			int b = currentPositionOfPawn(a);
			int moves[] = possiblePawnMovesInt(b);
			for(int i=0;i<moves.length;i++)
				System.out.println(moves[i]);*/
			System.out.println(fastestPathLeft(Integer.parseInt(a)));
			System.out.println(fastestPathRight(Integer.parseInt(a)));
	}
}