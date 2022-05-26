
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

	public String getRed() {
		return red;
	}

	public void setRed(String red) {
		this.red = red;
	}

	public String getGreen() {
		return green;
	}

	public void setGreen(String green) {
		this.green = green;
	}

	public String getBlue() {
		return blue;
	}

	public void setBlue(String blue) {
		this.blue = blue;
	}

	public String getYellow() {
		return yellow;
	}

	public void setYellow(String yellow) {
		this.yellow = yellow;
	}

	public String getWhite() {
		return white;
	}

	public void setWhite(String white) {
		this.white = white;
	}

	public String getReset() {
		return reset;
	}

	public void setReset(String reset) {
		this.reset = reset;
	}

}
