
package AI;

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
	static int pPositionC, pPositionL;
	//list of all possible moves
	static String list,fastestPath;
	
	public static void allPossibleMoves(){
		list = "";
		for(int i=0; i<81; i++)
			switch (board[i/9][i%9]){
			case "1": list = "";
				list+=possiblePawnMoves(i);
				System.out.println("Pawn 1's possible moves:"+list);
				break;
			case "2": list = "";
				list+=possiblePawnMoves(i);
				System.out.println("Pawn 2's possible moves:"+list);
				break;
			case "3": list = "";
				list+=possiblePawnMoves(i);
				System.out.println("Pawn 3's possible moves:"+list);
				break;
			case "4": list = "";
				list+=possiblePawnMoves(i);
				System.out.println("Pawn 4's possible moves:"+list);
				break;
			}
	}

	public static String possiblePawnMoves(int i){
		list="";
		 String oldPiece;
		int r = i/9, c = i%9;
		for(int j = 0; j<9; j++){
			if (j == 1||j == 3||j == 5||j == 7){
				try{
					if(Character.isLowerCase(board[r-1+j/3][c-1+j%3].charAt(0)) || " ".equals(board[r-1+j/3][c-1+j%3])){
						oldPiece = board[r-1+j/3][c-1+j%3];
						board[r][c]=" ";
						board[r-1+j/3][c-1+j%3]="P";
						int pTemp = pPositionC;
						pPositionC=i+(j/3)*9+j%3-9;
						if(viableMove()){
							list = list+r+c+(r-1+j/3)+(c-1+j%3)+oldPiece;
						}
						board[r][c]="P";
						board[r-1+j/3][c-1+j%3]=oldPiece;
						pPositionC = pTemp;
					}
				}catch (Exception e) {}
			}
		}
		//need to add Jumps
		return list;
	}
	
	public static String fastestPath(String a[][]){
		if(true){
			//base case
		}
		return fastestPath;
	}
	public static boolean viableMove(){
		return true;
	}

	public static void main(String args[]){
		allPossibleMoves();
	}
}