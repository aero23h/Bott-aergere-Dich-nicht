package v2;

public class MenuItem {
	private String text;
	private String key;
	private int result;
	
	public MenuItem(String text, String key, int result) {
		this.text = text;
		this.key = key;
		this.result = result;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getResult() {
		return this.result;
	}

	public void setResult(int result) {
		this.result = result;
	}

}
