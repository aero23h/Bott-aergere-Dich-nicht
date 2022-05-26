import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class Board {
	private Score score;
	private String[][] emptyBoard = {{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "( )", "---", "( )", "---", "( )", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
									 {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | ", "   ", "   ", "   ", " | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
									 {"   ", "   ", "( )", "( )", "   ", "   ", "   ", "   ", "( )", "   ", "( )", "   ", "( )", "   ", "   ", "   ", "   ", "( )", "( )", "   ", "   "},
									 {"   ", "   ", "( )", "( )", "   ", "   ", "   ", "   ", " | ", "   ", " | ", "   ", " | ", "   ", "   ", "   ", "   ", "( )", "( )", "   ", "   "},
									 {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "( )", "   ", "( )", "   ", "( )", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
									 {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | ", "   ", " | ", "   ", " | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
									 {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "( )", "   ", "( )", "   ", "( )", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
									 {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | ", "   ", " | ", "   ", " | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
									 {"( )", "---", "( )", "---", "( )", "---", "( )", "---", "( )", "   ", "( )", "   ", "( )", "---", "( )", "---", "( )", "---", "( )", "---", "( )"},
									 {" | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | "},
									 {"( )", "   ", "( )", "---", "( )", "---", "( )", "---", "( )", "   ", "   ", "   ", "( )", "---", "( )", "---", "( )", "---", "( )", "   ", "( )"},
									 {" | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | "},
									 {"( )", "---", "( )", "---", "( )", "---", "( )", "---", "( )", "   ", "( )", "   ", "( )", "---", "( )", "---", "( )", "---", "( )", "---", "( )"},
									 {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | ", "   ", " | ", "   ", " | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
									 {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "( )", "   ", "( )", "   ", "( )", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
									 {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | ", "   ", " | ", "   ", " | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
									 {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "( )", "   ", "( )", "   ", "( )", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
									 {"   ", "   ", "( )", "( )", "   ", "   ", "   ", "   ", " | ", "   ", " | ", "   ", " | ", "   ", "   ", "   ", "   ", "( )", "( )", "   ", "   "},
									 {"   ", "   ", "( )", "( )", "   ", "   ", "   ", "   ", "( )", "   ", "( )", "   ", "( )", "   ", "   ", "   ", "   ", "( )", "( )", "   ", "   "},
									 {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " | ", "   ", "   ", "   ", " | ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
									 {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "( )", "---", "( )", "---", "( )", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "}};
	private int[][] posStart;
	private int[][] posBoard;
	private int[][] posGoal;
	private Color color;
						
	public Board() throws StreamWriteException, DatabindException, IOException {
		this.color = new Color();
		this.score = new Score();
		this.posStart = new int[][] {{17,17}, {18,17}, {17,18}, {18,18},
									{2,17}, {2,18}, {3,17}, {3,18},
									{2,2}, {2,3}, {3,2}, {3,3},
									{17,2}, {18,2}, {17,3}, {18,3}};
			
		this.posBoard = new int[][] {{12,20}, {10,20}, {8,20}, {8,18}, {8,16}, {8,14}, {8,12}, {6,12}, {4,12}, {2,12}, {0,12}, {0,10}, {0,8}, {2,8}, 
									{4,8}, {6,8}, {8,8}, {8,6}, {8,4}, {8,2}, {8,0}, {10,0}, {12,0}, {12,2}, {12,4}, {12,6}, {12,8}, {14,8}, {16,8}, 
									{18,8}, {20,8}, {20,10}, {20,12}, {18,12}, {16,12}, {14,12}, {12,12}, {12,14}, {12,16}, {12,18}};
																
		this.posGoal = new int[][] {{10,18}, {10,16}, {10,14}, {10,12},
									{2,10}, {4,10}, {6,10}, {8,10},
									{10,2}, {10,4}, {10,6}, {10,8},
									{18,10}, {16,10}, {14,10}, {12,10}};
	}
	
	public String token2Board(byte token) {
		int playerId = token / 10;
		String tokenChr = Character.toString(48 + token % 10);
		return this.score.getPlayers()[playerId].getColor() + "("+tokenChr+")" + color.getReset();
	}
	
	public void plotScore2Console() {
		// startup
		String[][] actualBoard = this.emptyBoard;
		// update start
		for(int i=0;i<this.score.getStartBoard().length; i++) {
			byte token = this.score.getStartBoard()[i];
			if(token > 0) {
				actualBoard[this.posStart[i][1] ][this.posStart[i][0] ] = this.token2Board(token);
			}
		}
		// update goal
		for(int i=0;i<this.score.getGoalBoard().length; i++) {
			byte token = this.score.getGoalBoard()[i];
			if(token > 0) {
				actualBoard[this.posGoal[i][1] ][this.posGoal[i][0] ] = this.token2Board(token);
			}
		}
		// update board
		for(int i=0;i<this.score.getOnBoard().length; i++) {
			byte token = this.score.getOnBoard()[i];
			if(token > 0) {
				actualBoard[this.posBoard[i][1] ][this.posBoard[i][0] ] = this.token2Board(token);
			}
		}
		// print board to console
		for(int i = 0; i < actualBoard.length; i++) {
			String line = "";
			for(int j = 0; j < actualBoard[0].length; j++) {
				line += actualBoard[i][j];
			}
		// add player to board
		if(i>2 && i<7) {
			line += this.score.getPlayers()[i-3].getColor()+"        Player "+ (i-2) + ": " + this.score.getPlayers()[i-3].getName()+color.getReset();
		}
		System.out.println(line);
		}
		
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

}
