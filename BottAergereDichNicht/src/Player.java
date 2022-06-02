import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Player {
	private String name;
	private String color;
	private int id;
	// addition possible
	//private int timesPlayed;
	//private int wins;
	
	public Player() {
	}
	
	public Player(String name, String color) {
		this.name = name;
		this.color = color;
	}
	
	// Score use this constructor for creating his dummy players. id is always between 1-4 --> number of playing player
	public Player(String name, String color, int id) {
		this(name, color);
		this.id = id;
	}
	
	public boolean checkDirExist(String path) {
		if(new File(path).exists()) {
			return true;
		}
		return false;
	}
		
	public void save2File(String path) throws StreamWriteException, DatabindException, IOException {
	    if(!this.checkDirExist(path)) {
	    	new File(path).mkdirs();
	    }
		ObjectMapper map = new ObjectMapper();
	    ObjectWriter writer = map.writer(new DefaultPrettyPrinter());
	    writer.writeValue(Paths.get(path + "/" + this.getName() + ".json").toFile(), this);
	}
	
	public Player loadFromFile(String path, String playerName){
		ObjectMapper mapper = new ObjectMapper();
	    Player ld = null;
	    Player p = null;
		try {
			ld = mapper.readValue(Paths.get(path + "/" + playerName).toFile(), Player.class);
		    p = new Player(ld.getName(), ld.getColor(), ld.getId());
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return p;
	}
	
	// ######################################################################################################################
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



}
