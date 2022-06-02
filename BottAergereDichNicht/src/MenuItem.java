
public class MenuItem {
	private String text;
	private char key;
	private int result;
	private int intKey;
	
	public MenuItem(String text, char key, int result) {
		this.text = text;
		this.key = key;
		this.intKey = -1;
		this.result = result;
	}
	
	public MenuItem(String text, int intKey, int result) {
		this.text = text;
		this.key = Character.MIN_VALUE;
		this.intKey = intKey;
		this.result = result;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public char getKey() {
		return key;
	}

	public void setKey(char key) {
		this.key = key;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getIntKey() {
		return intKey;
	}

	public void setIntKey(int intKey) {
		this.intKey = intKey;
	}

}
