package bottAergereDichNicht;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class PlayerList {
	private ArrayList<Player> playerList;
	private String fileName;
	
	public PlayerList() {
	this.playerList = new ArrayList<>();
	this.fileName = "bott-playerlist";
	}
	
	// ################################################################################################################
	
	public void allPlayersVisible() {
		for(Player p: this.playerList) {
			p.setVisible(true);
		}
	}
	
	public void setVisibility(String playerName, boolean visibility) {
		Player p = this.findPlayer(playerName);
		if(p != null) {
			p.setVisible(visibility);
		}
	}
	
	public boolean addNewPlayer(Player p) {
		// check of double name in list
		for(Player pl: this.playerList) {
			if(pl.getName().equals(p.getName())) {
				return false;
			}
		}
		// add p to list
		this.playerList.add(p);
		return true;
	}
	
	public boolean changePlayerName(String oldName, String newName) {
		if(oldName.equals(newName)) {
			return true;
		}
		// search for player with oldName in list
		for(Player pl: this.playerList) {
			if(pl.getName().equals(oldName)) {
				// set name
				pl.setName(newName);
				System.out.println("User name changed from: " + oldName + " into " + newName +".");
				return true;
			}
		}
		// player with oldName not found
		return false;
	}
	
	public Player findPlayer(String name) {
		// search for player with name in list
		for(Player pl: this.playerList) {
			if(pl.getName().equals(name)) {
				return pl;
			}
		}
		// player with name not found
		return null;
	}
	
	public void savePlayerList(String path) {
	    if(!this.checkDirExist(path)) {
	    	new File(path).mkdirs();
	    }
	    this.allPlayersVisible();
		ObjectMapper map = new ObjectMapper();
	    ObjectWriter writer = map.writer(new DefaultPrettyPrinter());
	    try {
			writer.writeValue(Paths.get(path + "/" + this.fileName + ".json").toFile(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean loadPlayerList(String path){
		ObjectMapper mapper = new ObjectMapper();
		PlayerList ld = new PlayerList();
		try {
			ld = mapper.readValue(Paths.get(path + "/" + this.fileName + ".json").toFile(), PlayerList.class);
			this.playerList = ld.getPlayerList();
			return true;
		} catch (IOException e) {
			// no action on fail, it will always fail on the first start time due to the creation time of the file.
		}
		return false;
	}
	
	public boolean checkDirExist(String path) {
		if(new File(path).exists()) {
			return true;
		}
		return false;
	}
	
	// ####################################################################################################

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

}
