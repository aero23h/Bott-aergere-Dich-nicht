
public class Color {
	private String red;
	private String green;
	private String blue;
	private String yellow;
	private String white;
	private String reset;
	
	public Color() {
		this.red = "\u001b[31m";
		this.green = "\u001b[32m";
		this.blue = "\u001b[34m";
		this.yellow = "\u001b[33m";
		this.white = "\u001b[37m";
		this.reset = "\u001b[0m";
	}
	public String getColorName(String color){
		switch(color) {
		// blue
		case "\u001b[34m":
			return this.getBlue() + "blue" + this.getReset();
			// green
		case "\u001b[32m":
			return this.getGreen() + "green" + this.getReset();
			// red
		case "\u001b[31m":
			return this.getRed() + "red" + this.getReset();
			// yellow
		case "\u001b[33m":
			return this.getYellow() + "yellow" + this.getReset();
		}
		return this.reset;
	}

	public String getRed() {
		return this.red;
	}

	public void setRed(String red) {
		this.red = red;
	}

	public String getGreen() {
		return this.green;
	}

	public void setGreen(String green) {
		this.green = green;
	}

	public String getBlue() {
		return this.blue;
	}

	public void setBlue(String blue) {
		this.blue = blue;
	}

	public String getYellow() {
		return this.yellow;
	}

	public void setYellow(String yellow) {
		this.yellow = yellow;
	}

	public String getWhite() {
		return this.white;
	}

	public void setWhite(String white) {
		this.white = white;
	}

	public String getReset() {
		return this.reset;
	}

	public void setReset(String reset) {
		this.reset = reset;
	}

}
