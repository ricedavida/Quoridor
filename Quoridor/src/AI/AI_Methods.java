package AI;
import java.io.*;
import java.util.Arrays;
/** AI_Methods: The bulk of the AI, including fastest and longest paths along with AI strategies */
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
	// static ints for positioning and move calculation
	static int pPositionC, pPositionL,lastMove,x,l = 0, r = 0, f = 0, fl = 0, fr = 0;
	
	// ints to keep track of each players spots for quick recalls
	int Player1,Player2,Player3,Player4;
	
	//int array to keep track of moves made and moves that failed
	static int[] failedMovesLeft = new int[81];
	static int[] failedMovesRight = new int[81];
	static int[] reverseL = new int[81];
	static int[] reverseR = new int[81];
	
	//list of all possible moves
	static String list,fastestPathRight="",fastestPathLeft="";
	
	/** allPossibleMoves: Takes in the string name of a pawn finds it and returns all its possible moves as a String */
	public static String allPossibleMoves(String pawn){
		list = "";
		for(int i=0; i<81; i++)
			if (board[i/9][i%9].equals(pawn)){
			    list = "";
				list+=possiblePawnMoves(i);
			}
		return list;
	}
	
	/** currentPositionOfPawn: Takes in a string of the pawn to be found and returns its Int location */
	public static int currentPositionOfPawn(String pawn){
		int pos = -1;
		for(int i=0; i<81; i++)
			if (board[i/9][i%9].equals(pawn)){
				pos = i;
		}
		return pos;
	}

	/** allPossibleMovesInt: Takes in the string name of a pawn finds it and returns all its possible moves as an Int */
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
	/** possiblePawnMoves: Takes in the integer position of the pawn and returns all its moves as a String */
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
	/** fastestPathLeft: Takes the integer position of the pawn and returns the fastest path to its destination favoring moving up and left*/
	public static String fastestPathLeft(int position){
		int moves[] = possiblePawnMovesInt(position);
		if(position == 0 || position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7 || position == 8){System.out.println("Fastest Path Left:");
		return "done";}//Should be if lastMove == winCondition
		
		else if(moves[1]!=-1 && Arrays.asList(reverseL).contains(moves[1]) == false && Arrays.asList(failedMovesLeft).contains(moves[1]) == false){//moving up
			fastestPathLeft += position+"^"+moves[1]+" ";
			fastestPathLeft(moves[1]);
			reverseL[l] = position;
			l++;
			return fastestPathLeft;
		} 
		else if(moves[3]!=-1  && Arrays.asList(reverseL).contains(moves[1]) == false && Arrays.asList(failedMovesLeft).contains(moves[1]) == false){//moving left
			fastestPathLeft += position+"<"+moves[3]+" ";
			fastestPathLeft(moves[3]);
			reverseL[l] = position;
			l++;
			return fastestPathLeft;
		}
		else if(moves[4]!=-1  && Arrays.asList(reverseL).contains(moves[1]) == false && Arrays.asList(failedMovesLeft).contains(moves[1]) == false){//moving right
			fastestPathLeft += position+">"+moves[4]+" ";
			fastestPathLeft(moves[4]);
			reverseL[l] = position;
			l++;
			return fastestPathLeft;
		}
		else{
			failedMovesLeft[fl] = position;
			fl++;
			
			fastestPathLeft(reverseL[l-1]);
		}
		return "Hope you never see this.";
	}
	
	/** fastestPathRight: Takes the integer position of the pawn and returns the fastest path to its destination favoring moving up and right*/
	public static String fastestPathRight(int position){
		int moves[] = possiblePawnMovesInt(position);
		if( position == 0 || position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7 || position == 8){System.out.println("Fastest Path Right:");
		return "done";}//Should be if lastMove == winCondition
		
		if(moves[1]!=-1){//moving north
			fastestPathRight += position+"^"+moves[1]+" ";
			fastestPathRight(moves[1]);
			reverseR[r] = position;
			r++;
			return fastestPathRight;
		}
		else if(moves[4]!=-1){//moving east
			fastestPathRight += position+">"+moves[4]+" ";
			fastestPathRight(moves[4]);
			reverseR[r] = position;
			r++;
			return fastestPathRight;
		}
		else if(moves[3]!=-1){//moving west
			fastestPathRight += position+"<"+moves[3]+" ";
			fastestPathRight(moves[3]);
			reverseR[r] = position;
			r++;
			return fastestPathRight;
		}

		return "Hope you never see this.";
	}
	
	/** viableMove: Is passed a move and returns true or false based on its viability*/
	public static boolean viableMove(){
		return true;
	}

	/** Main method to test AI functionality alone*/
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