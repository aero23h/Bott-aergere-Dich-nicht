package bottAergereDichNicht;

import java.util.ArrayList;

public class Board {
	private String[][] template;
	private int[][] posStart;
	private int[][] posBoard;
	private int[][] posGoal;
	private Color color;
	private Score score;
						
	public Board(){
		this.color = new Color();
		this.score = new Score();
		this.template = new String[][] {
			{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "( )", "---", "( )", "---", "y( )�", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
			{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | ", "   ", "   ", "   ", " | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
			{"   ", "   ", "r( )�", "r( )�", "   ", "   ", "   ", "   ", "( )", "   ", "y( )�", "   ", "( )", "   ", "   ", "   ", "   ", "y( )�", "y( )�", "   ", "   "},
			{"   ", "   ", "r( )�", "r( )�", "   ", "   ", "   ", "   ", " | ", "   ", " | ", "   ", " | ", "   ", "   ", "   ", "   ", "y( )r", "y( )�", "   ", "   "},
			{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "( )", "   ", "y( )�", "   ", "( )", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
			{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | ", "   ", " | ", "   ", " | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
			{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "( )", "   ", "y( )�", "   ", "( )", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
			{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | ", "   ", " | ", "   ", " | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
			{"r( )�", "---", "( )", "---", "( )", "---", "( )", "---", "( )", "   ", "y( )�", "   ", "( )", "---", "( )", "---", "( )", "---", "( )", "---", "( )"},
			{" | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | "},
			{"( )", "   ", "r( )�", "---", "r( )�", "---", "r( )�", "---", "r( )�", "   ", "   ", "   ", "b( )�", "---", "b( )�", "---", "b( )�", "---", "b( )�", "   ", "( )"},
			{" | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | "},
			{"( )", "---", "( )", "---", "( )", "---", "( )", "---", "( )", "   ", "g( )�", "   ", "( )", "---", "( )", "---", "( )", "---", "( )", "---", "b( )�"},
			{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | ", "   ", " | ", "   ", " | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
			{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "( )", "   ", "g( )�", "   ", "( )", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
			{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | ", "   ", " | ", "   ", " | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
			{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "( )", "   ", "g( )�", "   ", "( )", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
			{"   ", "   ", "g( )�", "g( )�", "   ", "   ", "   ", "   ", " | ", "   ", " | ", "   ", " | ", "   ", "   ", "   ", "   ", "b( )�", "b( )�", "   ", "   "},
			{"   ", "   ", "g( )�", "g( )�", "   ", "   ", "   ", "   ", "( )", "   ", "g( )�", "   ", "( )", "   ", "   ", "   ", "   ", "b( )�", "b( )�", "   ", "   "},
			{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | ", "   ", "   ", "   ", " | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
			{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "g( )�", "---", "( )", "---", "( )", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "}
			};
		this.posStart = new int[][] {{17,17}, {17,18}, {18,17}, {18,18},{2,17}, {2,18}, {3,17}, {3,18},{2,2}, {2,3}, {3,2}, {3,3},{17,2}, {17,3}, {18,2}, {18,3}};
		this.posBoard = new int[][] {{20,12}, {18,12}, {16,12}, {14,12}, {12,12}, {12,14}, {12,16}, {12,18}, {12,20},{10,20}, {8,20}, {8,18}, {8,16}, {8,14}, {8,12}, {6,12}, {4,12},{2,12},{0,12}, {0,10}, {0,8}, {2,8}, {4,8}, {6,8}, {8,8}, {8,6}, {8,4},{8,2},{8,0}, {10,0}, {12,0}, {12,2}, {12,4}, {12,6}, {12,8}, {14,8},{16,8},{18,8}, {20,8}, {20,10}};				
		this.posGoal = new int[][] {{18,10}, {16,10}, {14,10}, {12,10},{10,18}, {10,16}, {10,14}, {10,12},{2,10}, {4,10}, {6,10}, {8,10},{10,2}, {10,4}, {10,6}, {10,8}};
	}
	
	// ################################################################################################################
	
	public void nextPlayer() {
		do {
			this.score.setActPlayer(this.getScore().getPlayers()[(this.score.getActPlayer().getId() +1) % 4]);
		} while(this.score.getActPlayer().getId()<0);
	}
	
	public boolean didWin() {
		for(int i=0; i<4; i++) {
			if(this.score.getGoalBoard()[this.getScore().getActPlayer().getId()*4+i] == 0){
				// player did not win
				return false;
			}
		}
		// player win
		return true;
	}
	
	public String[][] copyOf(String[][] sourceArray){
		String[][] result = new String[sourceArray.length][sourceArray[0].length];
		for(int line=0; line<sourceArray.length; line++) {
			for(int row=0; row<sourceArray[line].length; row++) {
				result[line][row] = sourceArray[line][row];
			}
		}
		return result;
	}
	
	public String token2Board(int token) {
		int playerId = token / 10;
		String tokenChr = Character.toString(48 + token % 10);
		return this.score.getPlayers()[playerId].getColor().getCode() + "("+tokenChr+")" + color.reset();
	}
	
	public void plotScore2Console(ArrayList<MenuItem> menu) {
		// clear console
		this.clearConsole();
		// startup
		String[][] actualBoard = this.copyOf(this.template);
		// update start
		for(int i=0;i<this.score.getStartBoard().length; i++) {
			int token = this.score.getStartBoard()[i];
			if(token > 0) {
				actualBoard[this.posStart[i][1] ][this.posStart[i][0] ] = this.token2Board(token);
			}
		}
		// update goal
		for(int i=0;i<this.score.getGoalBoard().length; i++) {
			int token = this.score.getGoalBoard()[i];
			if(token > 0) {
				actualBoard[this.posGoal[i][1] ][this.posGoal[i][0] ] = this.token2Board(token);
			}
		}
		// update board
		for(int i=0;i<this.score.getOnBoard().length; i++) {
			int token = this.score.getOnBoard()[i];
			if(token > 0) {
				actualBoard[this.posBoard[i][1] ][this.posBoard[i][0] ] = this.token2Board(token);
			}
			
		}
		// print board to console
		for(int i = 0; i < actualBoard.length; i++) {
			String line = "";
			for(int j = 0; j < actualBoard[0].length; j++) {
				String data = actualBoard[i][j];
				data = data.replaceAll("b", color.getBlue().getCode());
				data = data.replaceAll("g", color.getGreen().getCode());
				data = data.replaceAll("r", color.getRed().getCode());
				data = data.replaceAll("y", color.getYellow().getCode());
				data = data.replaceAll("�", color.getReset().getCode());
				line += data;
			}
		// add player to board
		if(i>-1 && i<4) {
			line += this.score.getPlayers()[i].getColor().getCode()+"\t\tPlayer "+ (i+1) + ": " + this.score.getPlayers()[i].getName()+color.reset();
		}
		// add menu
		if(i>4 && ((i-5) < menu.size())) {
			String key = menu.get(i-5).getKey();
			String text = menu.get(i-5).getText();
			if(key != "@") {
				line += "\t\t" + "("+ key +")  " +  text;
			} else {
				line += "\t\t "+ text;
			}
		}	
		System.out.println(line);
		}
	}
	
	public void clearConsole() {
		String clear = "";
		for(int i=0; i<20;i++) {
			clear += "\n";
		}
		System.out.println(clear);
	}

	// ################################################################################################################
	
	public Score getScore() {
		return this.score;
	}

	public void setScore(Score score) {
		this.score = score;
	}
}
