import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Player {
	private String name;
	private ColorItem color;
	private int id;
	// scoreBoard informations
	private int wins;
	private int totalPlayed;
	private int timesRolled6;
	
	public Player() {
		this.wins = 0;
		this.timesRolled6 = 0;
		this.totalPlayed = 0;
	}
	
	public Player(String name, ColorItem color) {
		this();
		this.name = name;
		this.color = color;
	}
	
	// Score use this constructor for creating his dummy players. id is always between 1-4 --> number of playing player
	public Player(String name, ColorItem color, int id) {
		this(name, color);
		this.id = id;
	}
	
	public boolean checkDirExist(String path) {
		if(new File(path).exists()) {
			return true;
		}
		return false;
	}
		
	public void save2File(String path) {
	    if(!this.checkDirExist(path)) {
	    	new File(path).mkdirs();
	    }
		ObjectMapper map = new ObjectMapper();
	    ObjectWriter writer = map.writer(new DefaultPrettyPrinter());
	    try {
			writer.writeValue(Paths.get(path + "/" + this.getName() + ".json").toFile(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Player loadFromFile(String path, String playerName){
		ObjectMapper mapper = new ObjectMapper();
	    Player p = new Player();
		try {
			p = mapper.readValue(Paths.get(path + "/" + playerName + ".json").toFile(), Player.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return p;
	}
	
	// ######################################################################################################################
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public ColorItem getColor() {
		return this.color;
	}

	public void setColor(ColorItem color) {
		this.color = color;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWins() {
		return this.wins;
	}


	public void setWins(int wins) {
		this.wins = wins;
	}


	public int getTimesRolled6() {
		return this.timesRolled6;
	}


	public void setTimesRolled6(int timesRolled6) {
		this.timesRolled6 = timesRolled6;
	}

	public int getTotalPlayed() {
		return this.totalPlayed;
	}

	public void setTotalPlayed(int toalPlayed) {
		this.totalPlayed = toalPlayed;
	}



}
