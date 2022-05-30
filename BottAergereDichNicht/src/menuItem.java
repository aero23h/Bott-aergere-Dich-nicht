
public class menuItem {
	private String text;
	private char key;
	private int result;
	
	public menuItem(String text, char key, int result) {
		this.text = text;
		this.key = key;
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

}
