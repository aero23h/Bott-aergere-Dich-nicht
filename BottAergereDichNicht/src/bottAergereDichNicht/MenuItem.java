package bottAergereDichNicht;

public class MenuItem {
	private String text;	// display text
	private String key;		// key to compare
	private int result;		// return value
	
	public MenuItem(String text, String key, int result) {
		this.text = text;
		this.key = key;
		this.result = result;
	}
	
	// ################################################################################################################
	
	public String getText() {
		return this.text;
	}

	public String getKey() {
		return this.key;
	}

	public int getResult() {
		return this.result;
	}
}
