package bottAergereDichNicht;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Score {
	private Color color;
	private int[] startBoard;
	private int[] goalBoard;
	private int[] onBoard;
	private Player[] players; 
	private Player noPlayer;
	private String createTime;
	private Player actPlayer;
	private boolean finished;
	
	public Score() {
		this.color = new Color();
		this.players = new Player[4];
		this.noPlayer = new Player("", color.getReset(), -1);
		this.init(4);
		this.actPlayer = this.players[0];
		this.finished = false;
	}
	
	// ################################################################################################################
	// game "engine"
	
	public int checkMove(int token, int roll) {
		int boardPos = -1;
		int playerId = token/10;
		// ##################################################
		// search in startBoard
		boardPos = this.findToken(token, this.startBoard);
		// token in Starboard
		if(boardPos != -1) {
			if(roll != 6) {
				return -1;
			}
			// token on A
			if(this.onBoard[playerId*10] == 0) {
				// join token
				return 100;
			}
			// is own token
			if(playerId != (this.onBoard[playerId*10]/10)) {
				return 110;
			}
		}
		// ##################################################
		// search in onBoard
		boardPos = this.findToken(token, this.onBoard);
		if(boardPos != -1) {
			int newPos = (boardPos + roll) % 40;
			int boardSteps = (boardPos + 40 - ((playerId*10) % 40)) % 40;
			int target = (newPos + 40 - ((playerId*10) % 40)) % 40;
			
			// check if token move more than 40
			if(boardSteps > target) {
				// check in range of goalBoard
				if((target + (playerId*4)%16)<=(playerId*4+3%16)) {
					// check if tokenPos on goalBoard is blocked		
					if(this.goalBoard[(target + (playerId)*4) % 16] == 0) {
						// join goal
						return 200;
					}
				}
			} else {
				// newPos Free
				if(this.onBoard[newPos] == 0) {
					// move
					return 210;
				}
				// is own token
				if(playerId != (this.onBoard[newPos]/10)) {
					// kick and swap
					return 220;
				}
			}
		}
		// ##################################################
		// search in onGoal
		boardPos = this.findToken(token, this.goalBoard);
		if(boardPos != -1) {
			int newGoalPos = boardPos + roll;
			// check if move in range
			if(newGoalPos <= (playerId*4+3)%16) {
				if(this.goalBoard[newGoalPos] == 0) {
					// move
					return 300;
				}
			}
		}
		return -1;
	}
	
	public boolean move(int token, int roll, int switchValue) {
		int playerId = token / 10;
		int tokenPos = 0;
		boolean returnValue = false;
		
		switch(switchValue) {
		// pas
		case 0:
			returnValue = true;
			break;
		// join onBoard
		case 100:
			tokenPos = this.findToken(token, this.startBoard);
			this.onBoard[playerId*10] = token;
			this.startBoard[tokenPos] = 0;
			returnValue = true;
			break;
		// kick and join onBoard
		case 110:
			// join
			tokenPos = this.findToken(token, this.startBoard);
			this.startBoard[tokenPos] = 0;
			this.kickToken(this.onBoard[playerId*10]);
			this.onBoard[playerId*10] = token;
			returnValue = true;
			break;
		// join goalBoard
		case 200:
			tokenPos = this.findToken(token, this.onBoard);
			int newPos = (tokenPos + roll) % 40;
			int target = (newPos + 40 - ((playerId*10) % 40)) % 40;
			
			tokenPos = this.findToken(token, this.onBoard);
			this.onBoard[tokenPos] = 0;
			this.goalBoard[(target + (playerId)*4) % 16] = token;
			returnValue = true;
			break;
		// move
		case 210:
			tokenPos = this.findToken(token, this.onBoard);
			this.onBoard[tokenPos] = 0;
			this.onBoard[(tokenPos + roll)%40] = token;
			returnValue = true;
			break;
		// kick and move onBoard
		case 220:
			tokenPos = this.findToken(token, this.onBoard);
			this.kickToken(this.onBoard[(tokenPos + roll)%40]);
			this.onBoard[tokenPos] = 0;
			this.onBoard[(tokenPos+roll) %40] = token;
			returnValue = true;
			break;
		// move in goal
		case 300:
			tokenPos = this.findToken(token, this.goalBoard);
			this.goalBoard[tokenPos] = 0;
			this.goalBoard[(tokenPos + roll)%16] = token;
			returnValue = true;
			break;
		}
		return returnValue;
	}
	
	public void kickToken(int token) {
		// AB A=PlyerID B=TokenValue
		// 21 A=2 B=1
		int playerId = token/10;
		int tokenValue = token%10;
		int tokenKickPos = (playerId*4+tokenValue %16)-1;
		this.startBoard[tokenKickPos] = token;
	}
	
	public int findToken(int token, int[] board) {
		// find token in board
		for(int i=0;i < board.length; i++) {
			if(board[i] == token) {
				return i;
			}
		}
		return -1;
	}
	
	// roll a 6 sided dice
	public int roll() {
	    return this.getRandomNumberInRange(1, 6);
	}
	
	public int getRandomNumberInRange(int min, int max) {
		return (int) (Math.random() * ((max - min) +1)) + min;
	}

	// #######################################################################################################################################
	// additional functions
	
	public void init(int playerCount) {
		// default player
		this.players[0] = new Player("", color.getBlue(), 0);
		this.players[1] = new Player("", color.getGreen(), 1);
		this.players[2] = new Player("", color.getRed(), 2);
		this.players[3] = new Player("", color.getYellow(), 3);
		
		// TokenNumber AB-A is PlayerID, B is TokenNumber eg 13 - playerID B token 3
		this.startBoard = new int[] {01,02,03,04,11,12,13,14,21,22,23,24,31,32,33,34};
		// Init startBoard between 2-4 playerID
		if(playerCount>1 && playerCount<4) {
			// remove token from list
			for(int i=playerCount*4;i<this.startBoard.length;i++) {
				this.startBoard[i] = 0;
			}
			// remove player from display
			for(int id=playerCount; id<this.players.length; id++) {
				this.players[id] = this.getNoPlayer();
			}
		}
		// initialize empty board arrays
		this.goalBoard = new int[16];
		this.onBoard = new int[40];
		// initialize new names
		this.createTime = LocalDateTime.now().withNano(0).toString().replaceAll(":", "-");
	}
	
	public void setPlayer(Player newPlayer) {
		switch(newPlayer.getColor().getName()) {
		// blue
		case "blue":
			newPlayer.setId(0);
			this.players[0] = newPlayer;
			break;
		// green
		case "green":
			newPlayer.setId(1);
			this.players[1] = newPlayer;
			break;
		// red
		case "red":
			newPlayer.setId(2);
			this.players[2] = newPlayer;
			break;
		// yellow
		case "yellow":
			newPlayer.setId(3);
			this.players[3] = newPlayer;
			break;
		default:
			break;
		}
	}
	
	public Score loadFromFile(String path, String name){
	    if(!this.checkDirExist(path)) {
	    	new File(path).mkdirs();
	    }
		ObjectMapper mapper = new ObjectMapper();
	    Score score = new Score();
		try {
			score = mapper.readValue(Paths.get(path + "/" + name + ".json").toFile(), Score.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return score;
	}
	
	public void save2File(String path){
	    if(!this.checkDirExist(path)) {
	    	new File(path).mkdirs();
	    }
	    ObjectMapper map = new ObjectMapper();
	    ObjectWriter writer = map.writer(new DefaultPrettyPrinter());
	    try {
			writer.writeValue(Paths.get(path + "/" + this.createTime + ".json").toFile(), this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean checkDirExist(String path) {
		if(new File(path).exists()) {
			return true;
		}
		return false;
	}

	// #######################################################################################################################################

	public int[] getStartBoard() {
		return this.startBoard;
	}

	public int[] getGoalBoard() {
		return this.goalBoard;
	}

	public int[] getOnBoard() {
		return this.onBoard;
	}

	public Player[] getPlayers() {
		return this.players;
	}

	public Player getNoPlayer() {
		return this.noPlayer;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public Player getActPlayer() {
		return this.actPlayer;
	}

	public void setActPlayer(Player actPlayer) {
		this.actPlayer = actPlayer;
	}

	public boolean isFinished() {
		return this.finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

}
