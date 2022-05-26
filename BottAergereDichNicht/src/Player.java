
public class Player {
	private String name;
	private String color;
	private int id;
	
	public Player(String name, String color) {
		this.name = name;
		this.color = color;
	}
	
	public Player(String name, String color, int id) {
		this(name, color);
		this.id = id;
	}

	public String getName() {
		return name;
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

	public void setName(String name) {
		this.name = name;
	}

}
