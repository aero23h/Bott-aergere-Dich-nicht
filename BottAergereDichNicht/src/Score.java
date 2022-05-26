
import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


public class Score {

	private byte[] startBoard;
	private byte[] goalBoard;
	private byte[] onBoard;
	private Player[] players; 
	private Player noPlayer;
	
	public Score() {
		Color color = new Color();
		this.players = new Player[4];
		this.players[0] = new Player("Player A", color.getBlue(), 0);
		this.players[1] = new Player("Player B", color.getGreen(), 1);
		this.players[2] = new Player("Player C", color.getRed(), 2);
		this.players[3] = new Player("Player D", color.getYellow(), 3);
		this.noPlayer = new Player("", color.getReset(), -1);
		
		this.init(4);
	}
	
	public void init(int playerCount) {
		// TokenNumber AB-A is PlayerID, B is TokenNumber eg 13 - playerID B token 3
		this.startBoard = new byte[] {01,02,03,04,11,12,13,14,21,22,23,24,31,32,33,34};
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
		this.goalBoard = new byte[16];
		this.onBoard = new byte[40];
		
	}
	public void setPlayer(int id, String name, String color) {
		this.getPlayers()[id].setName(name);
		this.getPlayers()[id].setColor(color);
	}
	
	public void save2File(String fileName) throws StreamWriteException, DatabindException, IOException {
	    ObjectMapper map = new ObjectMapper();
	    ObjectWriter writer = map.writer(new DefaultPrettyPrinter());
	    writer.writeValue(Paths.get(fileName).toFile(), this);
	}
	
	public void loadFromFile(String fileName) throws StreamReadException, DatabindException, IOException {
		ObjectMapper mapper = new ObjectMapper();
	    Score loadedData = mapper.readValue(Paths.get(fileName).toFile(), Score.class);
	    this.goalBoard = loadedData.getGoalBoard();
	    this.startBoard = loadedData.getStartBoard();
	    this.onBoard = loadedData.getOnBoard();
	    this.players = loadedData.getPlayers();
	    this.noPlayer = loadedData.getNoPlayer();
	}
	
	public boolean move(byte token, byte steps) {
		// return true everything OK, false = something wrong
		// find token in onBoard
		int tokenPos = -1;
		for(int i=0;i < this.onBoard.length; i++) {
			if(this.onBoard[i] == token) {
				tokenPos = i;
				break;
			}
		}
		if(tokenPos > -1) {
			// move token x steps
			return true;
		}
		// if not found, find token in offBoard
		for(int i=0;i < this.goalBoard.length; i++) {
			if(this.goalBoard[i] == token) {
				tokenPos = i;
				break;
			}
		}
		if(tokenPos > -1) {
			// move token in goalBoard
			return true;
		}
		// draw start token
		if(steps == 6) {
			// get player from token AB --> A
			int playerID = (token / 10);
			for(int i=0; i<4;i++) {
				// counter i + playerNumber * 4
				if(this.startBoard[i+playerID*4] == token) {
					// draw token to start
					return true;
				}
			}
		}
		return false;
	}

	public byte[] getStartBoard() {
		return startBoard;
	}

	public void setStartBoard(byte[] startBoard) {
		this.startBoard = startBoard;
	}

	public byte[] getGoalBoard() {
		return goalBoard;
	}

	public void setGoalBoard(byte[] goalBoard) {
		this.goalBoard = goalBoard;
	}

	public byte[] getOnBoard() {
		return onBoard;
	}

	public void setOnBoard(byte[] onBoard) {
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

}
