
package AI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
	
	public static void allPossibleMoves(String a){
		list = "";
		for(int i=0; i<81; i++)
			if (board[i/9][i%9].equals(a)){
			    list = "";
				list+=possiblePawnMoves(i);
				System.out.println("Pawn "+a+"'s possible moves:"+list);
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
		String a = null;
		System.out.println("Enter a pawn to find moves for:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			a = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(a.equals("1")||a.equals("2")||a.equals("3")||a.equals("4"))
			allPossibleMoves(a);
		else
			System.out.println("Not valid input");
		
	}
}