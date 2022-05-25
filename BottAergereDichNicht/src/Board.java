import java.util.ArrayList;

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
	private String[] playerColor;
						
	public Board() {
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
		this.color = new Color();
		this.playerColor = new String[] {color.getPcBlue(), color.getPcGreen(), color.getPcRed(), color.getPcYellow()};
		this.score = new Score();
		
		
		
	}
	public String token2Board(byte token) {
		int player = token / 10;
		String tokenChr = Character.toString(48 + token % 10);
		return this.playerColor[player]+"("+tokenChr+")"+color.getPcReset();
	}
	
	
	public void plotScore2Console() {
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
			for(int j = 0; j < actualBoard[0].length; j++) {
				System.out.print(actualBoard[i][j]);
			}System.out.print("\n");
		}
		
	}

}
