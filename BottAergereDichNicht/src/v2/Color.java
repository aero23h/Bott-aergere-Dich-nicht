package v2;

public class Color {
	private ColorItem red;
	private ColorItem green;
	private ColorItem blue;
	private ColorItem yellow;
	private ColorItem reset;
	
	public Color() {
		this.red = new ColorItem("\u001b[31m", "red");
		this.green = new ColorItem("\u001b[32m","green");
		this.blue = new ColorItem("\u001b[34m","blue");
		this.yellow = new ColorItem("\u001b[33m","yellow");
		this.reset = new ColorItem("\u001b[0m","reset");
	}
	public String getColoredName(ColorItem color){
		return color.getCode() + color.getName() + this.reset();
	}
	
	public ColorItem getRed() {
		return this.red;
	}
	public void setRed(ColorItem red) {
		this.red = red;
	}
	public ColorItem getGreen() {
		return this.green;
	}
	public void setGreen(ColorItem green) {
		this.green = green;
	}
	public ColorItem getBlue() {
		return this.blue;
	}
	public void setBlue(ColorItem blue) {
		this.blue = blue;
	}
	public ColorItem getYellow() {
		return this.yellow;
	}
	public void setYellow(ColorItem yellow) {
		this.yellow = yellow;
	}
	public ColorItem getReset() {
		return this.reset;
	}
	public void setReset(ColorItem reset) {
		this.reset = reset;
	}
	
	public String reset() {
		return this.reset.getCode();
	}
}
