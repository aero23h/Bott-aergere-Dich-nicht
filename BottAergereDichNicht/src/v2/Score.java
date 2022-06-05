package v2;

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
	
	public Score() {
		this.color = new Color();
		this.players = new Player[4];
		this.noPlayer = new Player("", color.getReset(), -1);
		this.createTime = LocalDateTime.now().withNano(0).toString().replaceAll(":", "-");
		// default init
		this.init(4);
		this.actPlayer = this.players[0];
	}
	
	public void init(int playerCount) {
		// default player
		this.players[0] = new Player("", color.getBlue(), 0);
		this.players[1] = new Player("", color.getGreen(), 1);
		this.players[2] = new Player("", color.getRed(), 2);
		this.players[3] = new Player("", color.getYellow(), 3);
		
		// TokenNumber AB-A is PlayerID, B is TokenNumber eg 13 - playerID B token 3
		this.startBoard = new int[] {01,02,03,04,11,12,13,14,21,22,23,24,31,33,32,34};
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
	
	public boolean isPlayerAlreadyPlaying(Player player) {
		for(Player p: this.getPlayers()) {
			if(p.getName() == player.getName()) {
				return true;
			}
		}
		return false;
	}
	// @
	// setplayer
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
	
	public int roll() {
	    return this.getRandomNumberInRange(1, 6);
	}
	
	public int getRandomNumberInRange(int min, int max) {
		return (int) (Math.random() * ((max - min) +1)) + min;
	}
	
	public boolean tokenToBoard(int removeIndex, int token) {
		// token AB -A playerID
		int playerID = (token / 10);
		// check if token can put to board
		if(this.onBoard[playerID*10] == 0) {
			this.startBoard[removeIndex] = 0;
			this.onBoard[(playerID*10+32) % 40] = token;
			return true;
		}
		return false;
	}
	
	public int findToken(int token, int[] board) {
		// find token in onStartBoard
		for(int i=0;i < board.length; i++) {
			if(board[i] == token) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean checkMove(int token, int steps) {
		// pass move
		if(token == 0) {
			return true;
		}
		// init values
		int pos = -1;
		int tokenPos = -1;
		int playerID = token/10;
		
		int startPos = this.findToken(token, this.startBoard);
		int onPos = this.findToken(token, this.onBoard);
		int goalPos = this.findToken(token, this.goalBoard);
		
		if(startPos != -1) {
			pos = 0;
			tokenPos = startPos;
		}
		if(onPos != -1) {
			pos = 1;
			tokenPos = onPos;
		}
		if(goalPos != -1) {
			pos = 2;
			tokenPos = goalPos;
		}
		
		switch(pos) {
		// startBoard
		case 0:
			if(steps == 6) {
				if((this.onBoard[playerID*10] == 0) || (playerID != (this.onBoard[playerID*10]/10))) {
					return true;
				}
			}
			break;
		// onBoard
		case 1:
			// move token x steps
			int newPos = (tokenPos + steps) % 40;
			int boardSteps = (tokenPos + 40 - ((playerID*10) % 40)) % 40;
			int target = (newPos + 40 - ((playerID*10) % 40)) % 40;
			// check if token move more than 40
			if(boardSteps > target) {
				// check if run over goal length
				if((target + (playerID*4)%16)<=(playerID*4+3%16)) {
					// check if tokenPos on goalBoard is blocked		
					if(this.goalBoard[(target + (playerID)*4) % 16] == 0) {
						// is not blocked
						return true;
					}
				}
				// overshoot goalBoard or blocked
				return false;
			}
			// check if newPos is blocked
			if(this.onBoard[newPos] == 0) {
				return true;
			}
			// position is blocked --> kick?
			int enemyToken = this.onBoard[newPos];
			int enemyPlayer = enemyToken/10;
			// do not kick your own token
			if((playerID) == (enemyPlayer)) {
				return false;
			}
			// kick token and swap place
			if(this.startBoard[(enemyPlayer*4)%16] == 0) {
				return true;
			}
			break;
		// goalBoard
		case 2:
			// move token in goalBoard
			int newGoalPos = tokenPos + steps;
			// check if move is possible
			if(newGoalPos <= (playerID*4+3)%16) {
				if(playerID == (this.goalBoard[newGoalPos]/10)) {
					return false;
				}
				return true;
			}
			break;
		}
		return false;
	}
	
	public boolean move(int token, int steps) {
		// pass move
		if(token == 0) {
			return true;
		}
		// init values
		int pos = -1;
		int tokenPos = -1;
		int playerID = token/10;
		
		int startPos = this.findToken(token, this.startBoard);
		int onPos = this.findToken(token, this.onBoard);
		int goalPos = this.findToken(token, this.goalBoard);
		
		if(startPos != -1) {
			pos = 0;
			tokenPos = startPos;
		}
		if(onPos != -1) {
			pos = 1;
			tokenPos = onPos;
		}
		if(goalPos != -1) {
			pos = 2;
			tokenPos = goalPos;
		}
		switch(pos) {
		// startBoard
		case 0:
			if(steps == 6) {
				// check joinOnBoard position is 0			
				if(this.onBoard[playerID*10] == 0){
					this.onBoard[playerID*10] = token;
					this.startBoard[tokenPos] = 0;
					return true;
				} else {
					if(playerID != (this.onBoard[playerID*10]/10)) {
						int enemyToken = this.onBoard[playerID*10];
						int enemyPlayer = enemyToken/10;
						
						// kick token and swap place
						if(this.startBoard[(enemyPlayer*4 + enemyToken%10)%16] == 0) {
							this.startBoard[(enemyPlayer*4 + enemyToken%10)%16] = enemyToken;
							this.onBoard[playerID*10] = token;
							this.startBoard[tokenPos] = 0;
							return true;
						}
					}
				} 
			}
			break;
		// onBoard
		case 1:
			// move token x steps
			int newPos = (tokenPos + steps) % 40;
			int boardSteps = (tokenPos + 40 - ((playerID*10) % 40)) % 40;
			int target = (newPos + 40 - ((playerID*10) % 40)) % 40;
			// check if token move more than 40
			if(boardSteps > target) {
				// check if run over goal length
				if((target + (playerID*4)%16)<=(playerID*4+3%16)) {
					// check if tokenPos on goalBoard is blocked		
					if(this.goalBoard[(target + (playerID)*4) % 16] == 0) {
						// is not blocked
						this.onBoard[tokenPos] = 0;
						this.goalBoard[(target + (playerID)*4) % 16] = token;
						return true;
					}
				}
				// overshoot goalBoard or blocked
				return false;
			}
			// check if newPos is blocked
			if(this.onBoard[newPos] == 0) {
				this.onBoard[newPos] = token;
				this.onBoard[tokenPos] = 0;
				return true;
			}
			// position is blocked --> kick?
			int enemyToken = this.onBoard[newPos];
			int enemyPlayer = enemyToken/10;
			// do not kick your own token
			if((playerID) == (enemyPlayer)) {
				return false;
			}
			// kick token and swap place
			if(this.startBoard[(enemyPlayer*4 + enemyToken%10)%16] == 0) {
				this.startBoard[(enemyPlayer*4 + enemyToken%10)%16] = enemyToken;
				this.onBoard[newPos] = token;
				this.onBoard[tokenPos] = 0;
				return true;
			}
			break;
		// goalBoard
		case 2:
			// move token in goalBoard
			int newGoalPos = tokenPos + steps;
			// check if move is possible
			if(newGoalPos <= (playerID*4+3)%16) {
				if(playerID == (this.goalBoard[newGoalPos]/10)) {
					return false;
				}
				this.goalBoard[tokenPos] = 0;
				this.goalBoard[newGoalPos] = token;
				return true;
			}
			break;
		}
		return false;
	}

	public int[] getStartBoard() {
		return this.startBoard;
	}

	public void setStartBoard(int[] startBoard) {
		this.startBoard = startBoard;
	}

	public int[] getGoalBoard() {
		return this.goalBoard;
	}

	public void setGoalBoard(int[] goalBoard) {
		this.goalBoard = goalBoard;
	}

	public int[] getOnBoard() {
		return this.onBoard;
	}

	public void setOnBoard(int[] onBoard) {
		this.onBoard = onBoard;
	}

	public Player[] getPlayers() {
		return this.players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public Player getNoPlayer() {
		return this.noPlayer;
	}

	public void setNoPlayer(Player noPlayer) {
		this.noPlayer = noPlayer;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Player getActPlayer() {
		return this.actPlayer;
	}

	public void setActPlayer(Player actPlayer) {
		this.actPlayer = actPlayer;
	}



}
