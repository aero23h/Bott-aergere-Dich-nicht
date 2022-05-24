import java.time.LocalDateTime;
import java.util.ArrayList;

public class Game {

	String[][] gameBoard = {{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "( )", "---", "( )", "---", "( )", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
							{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | ", "   ", "   ", "   ", " | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
							{"   ", "   ", "(X)", "(X)", "   ", "   ", "   ", "   ", "( )", "   ", "(=)", "   ", "( )", "   ", "   ", "   ", "   ", "(X)", "(X)", "   ", "   "},
							{"   ", "   ", "(X)", "(X)", "   ", "   ", "   ", "   ", " | ", "   ", " | ", "   ", " | ", "   ", "   ", "   ", "   ", "(X)", "(X)", "   ", "   "},
							{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "( )", "   ", "(=)", "   ", "( )", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
							{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | ", "   ", " | ", "   ", " | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
							{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "( )", "   ", "(=)", "   ", "( )", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
							{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | ", "   ", " | ", "   ", " | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
							{"( )", "---", "( )", "---", "( )", "---", "( )", "---", "( )", "   ", "(=)", "   ", "( )", "---", "( )", "---", "( )", "---", "( )", "---", "( )"},
							{" | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | "},
							{"( )", "   ", "(=)", "---", "(=)", "---", "(=)", "---", "(=)", "   ", "   ", "   ", "(=)", "---", "(=)", "---", "(=)", "---", "(=)", "   ", "( )"},
							{" | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | "},
							{"( )", "---", "( )", "---", "( )", "---", "( )", "---", "( )", "   ", "(=)", "   ", "( )", "---", "( )", "---", "( )", "---", "( )", "---", "( )"},
							{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | ", "   ", " | ", "   ", " | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
							{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "( )", "   ", "(=)", "   ", "( )", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
							{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | ", "   ", " | ", "   ", " | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
							{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "( )", "   ", "(=)", "   ", "( )", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
							{"   ", "   ", "(X)", "(X)", "   ", "   ", "   ", "   ", " | ", "   ", " | ", "   ", " | ", "   ", "   ", "   ", "   ", "(X)", "(X)", "   ", "   "},
							{"   ", "   ", "(X)", "(X)", "   ", "   ", "   ", "   ", "( )", "   ", "(=)", "   ", "( )", "   ", "   ", "   ", "   ", "(X)", "(X)", "   ", "   "},
							{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | ", "   ", "   ", "   ", " | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
							{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "( )", "---", "( )", "---", "( )", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},};
	
	
	private String[] posArray = {""};
		
		
	ArrayList<Player> playingPlayerList;
	LocalDateTime startTime;
	
	public Game(){
		this.startTime = LocalDateTime.now();
	}
	
	public void printBoard() {
		for(int i = 0; i < this.gameBoard.length; i++) {
			for(int j = 0; j < this.gameBoard[0].length; j++) {
				System.out.print(this.gameBoard[i][j]);
			}System.out.print("\n");
		}
	}	
	
	public String[][] getGameBoard() {
		return this.gameBoard;
	}

	public void setGameBoard(String[][] gameBoard) {
		this.gameBoard = gameBoard;
	}

	public String[] getPosArray() {
		return this.posArray;
	}

	public void setPosArray(String[] posArray) {
		this.posArray = posArray;
	}

	public ArrayList<Player> getPlayingPlayerList() {
		return this.playingPlayerList;
	}

	public void setPlayingPlayerList(ArrayList<Player> playingPlayerList) {
		this.playingPlayerList = playingPlayerList;
	}

	public LocalDateTime getStartTime() {
		return this.startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}


	
}