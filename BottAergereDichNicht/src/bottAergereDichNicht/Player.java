package bottAergereDichNicht;

public class Player {
	private String name;
	private ColorItem color;
	private int id;
	// 
	private boolean visible;
	
	// scoreBoard informations
	private int wins;
	private int totalPlayed;
	private int timesRolled6;
	
	public Player() {
		this.wins = 0;
		this.timesRolled6 = 0;
		this.totalPlayed = 0;
		this.visible = true;
	}
	
	public Player(String name) {
		this();
		this.name = name;
	}
	
	public Player(String name, ColorItem color) {
		this(name);
		this.color = color;
	}
	
	// Score use this constructor for creating his dummy players. id is always between 1-4 --> number of playing player
	public Player(String name, ColorItem color, int id) {
		this(name, color);
		this.id = id;
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

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}



}
