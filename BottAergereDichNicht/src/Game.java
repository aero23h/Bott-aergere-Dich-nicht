import java.time.LocalDateTime;
import java.util.ArrayList;

public class Game {

	String[][] gameBoard = {{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "( )", "---", "( )", "---", "( )", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
							{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | ", "   ", "   ", "   ", " | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
							{"   ", "   ", "(X)", "(X)", "   ", "   ", "   ", "   ", "( )", "   ", "(=)", "   ", "( )", "   ", "   ", "   ", "   ", "(X)", "(X)", "   ", "   "},
							{"   ", "   ", "(X)", "(X)", "   ", "   ", "   ", "   ", " | ", "   ", " | ", "   ", " | ", "   ", "   ", "   ", "   ", "(X)", "(X)", "   ", "   "},
							{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "( )", "   ", "(=)", "   ", "( )", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},};
	
		
	int[][] posArray;
	ArrayList<Player> playingPlayerList;
	LocalDateTime startTime;
	
	public Game(){
		this.startTime = LocalDateTime.now();
	}
	
	public void printBoard() {
		for(int i = 0; i < this.gameBoard.length; i++) {
			for(int j = 0; j < this.gameBoard[0].length; j++) {
				System.out.print(this.gameBoard[i][j]);
			}System.out.println("\n");
		}
	}
	
}