package v2;

public class ColorItem {
	private String code;
	private String name;
	
	public ColorItem() {
	}

	public ColorItem(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
