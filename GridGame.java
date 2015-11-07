import java.util.*;

public class GridGame {
	
	private char[][] myGrid;
	private static final int mySize = 4;
	
	public int winningMoves(String[] grid){
		
		myGrid = new char[mySize][mySize];
		
		int row = 0;
		for (String c : grid){
			for (int col = 0; col < mySize; col++){
				myGrid[row][col] = c.charAt(col);
			}
			row++;
		}
		return countWins();
	}
	
	public int countWins(){
		int wins = 0;
		
		ArrayList<int[]> moves = nextMoves();//method to compute wins
		
		for (int[] m : moves){
			myGrid[m[0]][m[1]] = 'X'; //place an X
			if (countWins() == 0){
				wins++;//try
			}
			myGrid[m[0]][m[1]] = '.'; //unplace X
		}
		
		return wins;
	}
	
	private boolean isDot(int r, int c){
		if (r >= 0 && r < mySize && c >= 0 && c < mySize){
			return myGrid[r][c] == '.';
		}
		return true;
	}
	
	private ArrayList<int[]> nextMoves(){
		ArrayList<int[]> list = new ArrayList<int[]>();
		
		for (int r = 0; r < mySize; r++){
			for (int c = 0; c < mySize; c++){
				if (
						isDot(r+1, c)
						&&
						isDot(r-1, c)
						&&
						isDot(r, c+1)
						&&
						isDot(r, c-1)
						&&
						myGrid[r][c] != 'X'
						){
					int[] toAdd = new int[2];
					toAdd[0] = r;
					toAdd[1] = c;
					list.add(toAdd);
				}
			}
		}
		return list;
	}
	
}
