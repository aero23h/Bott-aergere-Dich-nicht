
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

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
	
	public Score() {
		this.color = new Color();
		this.players = new Player[4];
		this.noPlayer = new Player("", color.getReset(), -1);
		this.createTime = LocalDateTime.now().withNano(0).toString().replaceAll(":", "-");
		System.out.println(this.createTime);
		// default init
		this.init(4);
	}
	
	public void init(int playerCount) {
		// default player
		this.players[0] = new Player("Player A", color.getBlue(), 0);
		this.players[1] = new Player("Player B", color.getGreen(), 1);
		this.players[2] = new Player("Player C", color.getRed(), 2);
		this.players[3] = new Player("Player D", color.getYellow(), 3);
		
		// TokenNumber AB-A is PlayerID, B is TokenNumber eg 13 - playerID B token 3
		this.startBoard = new int[] {01,03,02,04,11,12,13,14,21,22,23,24,31,33,32,34};
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
	public void save2File(String path, String name){
	    if(!this.checkDirExist(path)) {
	    	new File(path).mkdirs();
	    }
	    ObjectMapper map = new ObjectMapper();
	    ObjectWriter writer = map.writer(new DefaultPrettyPrinter());
	    try {
			writer.writeValue(Paths.get(path + "/" + name + ".json").toFile(), this);
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
	
	public void loadFromFile(String path, String name){
	    if(!this.checkDirExist(path)) {
	    	new File(path).mkdirs();
	    }
		ObjectMapper mapper = new ObjectMapper();
	    Score loadedData = null;
		try {
			loadedData = mapper.readValue(Paths.get(path + "/" + name + ".json").toFile(), Score.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    this.goalBoard = loadedData.getGoalBoard();
	    this.startBoard = loadedData.getStartBoard();
	    this.onBoard = loadedData.getOnBoard();
	    this.players = loadedData.getPlayers();
	    this.noPlayer = loadedData.getNoPlayer();
	    this.createTime = loadedData.getCreateTime();
	}
	
	public Score loadFromFileToObject(String path, String name){
		ObjectMapper mapper = new ObjectMapper();
	    Score loadedData = null;
		try {
			loadedData = mapper.readValue(Paths.get(path + "/" + name + ".json").toFile(), Score.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return loadedData;
	}
	
	public int roll() {
	    Random random = new Random(System.currentTimeMillis());
	    return random.nextInt(6) + 1;
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
	
	public boolean checkMove(int token, int steps) {
		int pos = -1;
		int tokenPos = -1;
		int playerID = token/10;
		// reference tokens
		ArrayList<Integer> playerTokens = new ArrayList<Integer>();
		for(int i=1; i<5;i++) {
			playerTokens.add(playerID*10+i);
		}
		// find token in onStartBoard
		for(int i=0;i < this.startBoard.length; i++) {
			if(this.startBoard[i] == token) {
				pos = 0;
				tokenPos = i;
				break;
			}
		}
		if(pos == -1) {
			// find token in onBoard
			for(int i=0;i < this.onBoard.length; i++) {
				if(this.onBoard[i] == token) {
					pos = 1;
					tokenPos = i;
					break;
				}
			}
		}
		if(pos == -1) {
			// find token in goalBoard
			for(int i=0;i < this.goalBoard.length; i++) {
				if(this.goalBoard[i] == token) {
					pos = 2;
					tokenPos = i;
					break;
				}
			}
		}
		
		switch(pos) {
		// startBoard
		case 0:
			if(steps == 6) {
				// get new position of onBoard
				int newPos = (tokenPos + steps) % 40;
				// check if new position is available
				if(playerTokens.contains(this.onBoard[newPos])) {
					return false;
				}
				// move to onBoard successful
				return true;
			}
			break;
		// onBoard
		case 1:
			// get new position of onBoard
			int newPos = (tokenPos + steps) % 40;
			int boardSteps = (tokenPos + 40 - ((playerID*10+32) % 40)) % 40;
			int goalPos = (newPos + 40 - ((playerID*10+32) % 40)) % 40;
			// if token moved more than 40 --> into goalBoard
			if(boardSteps > goalPos) {
				if(!playerTokens.contains(this.goalBoard[(goalPos + (playerID+3)*4) % 16])) {
					// goalBoard position is free
					return true;
				}
			}
			if(!playerTokens.contains(this.onBoard[newPos])) {
				return true;
			}
			break;
		// goalBoard
		case 2:
			// get position goalBoard
			int newGoalPos = tokenPos + steps;
			// check if move is possible
			if(newGoalPos > (playerID*4+15)%16) {
				return false;
			}
			if(!playerTokens.contains(this.goalBoard[newGoalPos])) {
				// token can move in goalBoard
				return true;
			}
			break;
		// token not found
		default:
			System.err.println("Token not found: " + this.getClass().getName() + " checkMove(int token, int steps)");
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
		
		// find token in onStartBoard
		for(int i=0;i < this.startBoard.length; i++) {
			if(this.startBoard[i] == token) {
				pos = 0;
				tokenPos = i;
				break;
			}
		}
		if(pos == -1) {
			// find token in onBoard
			for(int i=0;i < this.onBoard.length; i++) {
				if(this.onBoard[i] == token) {
					pos = 1;
					tokenPos = i;
					break;
				}
			}
		}
		if(pos == -1) {
			// find token in goalBoard
			for(int i=0;i < this.goalBoard.length; i++) {
				if(this.goalBoard[i] == token) {
					pos = 2;
					tokenPos = i;
					break;
				}
			}
		}
		
		switch(pos) {
		// startBoard
		case 0:
			if(steps == 6) {
				// calculate joinOnBoard position
				int joinOnBoard = playerID*10;
				// check joinOnBoard position is 0
				if(this.onBoard[joinOnBoard] == 0) {
					this.onBoard[joinOnBoard] = token;
					this.startBoard[tokenPos] = 0;
					return true;
				}	
			}
			break;
		// onBoard
		case 1:
			// move token x steps
			int newPos = (tokenPos + steps) % 40;
			int boardSteps = (tokenPos + 40 - ((playerID*10) % 40)) % 40;
			int goalPos = (newPos + 40 - ((playerID*10) % 40)) % 40;
			// check if token move more than 40
			if(boardSteps > goalPos) {
				// check if run over goal length
				if((goalPos + (playerID*4)%16)<=(playerID*4+3%16)) {
					// check if tokenPos on goalBoard is blocked		
					if(this.goalBoard[(goalPos + (playerID)*4) % 16] == 0) {
						// is not blocked
						this.onBoard[tokenPos] = 0;
						this.goalBoard[(goalPos + (playerID)*4) % 16] = token;
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
			if(this.startBoard[(enemyPlayer*4)%16] == 0) {
				this.startBoard[(enemyPlayer*4)%16] = enemyToken;
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
				this.goalBoard[tokenPos] = 0;
				this.goalBoard[newGoalPos] = token;
				return true;
			}
			break;
		}
		return false;
	}

	public int[] getStartBoard() {
		return startBoard;
	}

	public void setStartBoard(int[] startBoard) {
		this.startBoard = startBoard;
	}

	public int[] getGoalBoard() {
		return goalBoard;
	}

	public void setGoalBoard(int[] goalBoard) {
		this.goalBoard = goalBoard;
	}

	public int[] getOnBoard() {
		return onBoard;
	}

	public void setOnBoard(int[] onBoard) {
		this.onBoard = onBoard;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public Player getNoPlayer() {
		return noPlayer;
	}

	public void setNoPlayer(Player noPlayer) {
		this.noPlayer = noPlayer;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}



}
